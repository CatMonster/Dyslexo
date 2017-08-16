package io.dyslexo.components;

import io.dyslexo.components.audio.Player;
import io.dyslexo.components.audio.synthesis.Converter;
import io.dyslexo.components.audio.synthesis.MaryController;
import io.dyslexo.graphics.SaveAudioToFile;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import javax.sound.sampled.LineUnavailableException;
import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by stefano on 12/12/2016.
 */
public class GlobalKeyListener implements NativeKeyListener {
    private MaryController maryController;
    private ClipboardManager clipboard;
    private boolean isKey0Pressed;
    private boolean isKey1Pressed;
    private boolean isKey0Released;
    private boolean isKey1Released;
    private long timeKey1Pressed;
    private boolean isSpeechCasePause;
    private long saveComboTime;

    public GlobalKeyListener(MaryController maryController) {
        this.maryController = maryController;
        this.clipboard = new ClipboardManager();
        this.isKey0Pressed = false;
        this.isKey1Pressed = false;
        this.isKey0Released = false;
        this.isKey1Released = false;
        this.timeKey1Pressed = 0;
        this.isSpeechCasePause = false;
        this.saveComboTime = Long.parseLong(Settings.get("keys/timeSave"));
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if(e.getKeyCode() == Integer.parseInt(Settings.get("keys/key0")))
            isKey0Pressed = true;

        if(e.getKeyCode() == Integer.parseInt(Settings.get("keys/key1"))) {
            if(!isKey1Pressed)
                timeKey1Pressed = System.currentTimeMillis();
            isKey1Pressed = true;
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        if(e.getKeyCode() == Integer.parseInt(Settings.get("keys/key0")))
            isKey0Released = true;

        if(e.getKeyCode() == Integer.parseInt(Settings.get("keys/key1")))
            isKey1Released = true;

        try {
            doAction();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
    }

    /**
     * It checks the pressed keys to launch Dyslexo actions
     * @throws IOException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     * @throws MaryConfigurationException
     * @throws SynthesisException
     * @throws LineUnavailableException
     */
    private void doAction() throws IOException, UnsupportedFlavorException, AWTException, MaryConfigurationException, SynthesisException /*, LangDetectException*/, LineUnavailableException {
        Player player = maryController.getPlayer();
        if(isKey0Pressed && !isKey0Released && isKey1Pressed && isKey1Released && System.currentTimeMillis() - timeKey1Pressed >= saveComboTime && !isSpeechCasePause) {
            System.out.println("Save action activated");
            String oldClipboard = clipboard.get();
            System.out.println("old clipboard: " + oldClipboard);
            ClipboardManager.fakeCopyCombo();
            String currentClipboard = clipboard.get();
            System.out.println("current clipboard :" + currentClipboard);
            String savePath;
            if(!Boolean.parseBoolean(Settings.get("save/autoSave")))
                savePath = Settings.get("save/savePath") + "/" + new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").toString() + "." + Settings.get("save/format");
            else {
                SaveAudioToFile saveAudioToFile = new SaveAudioToFile();
                savePath = saveAudioToFile.save();
            }
            Converter converter = new Converter();
            try {
                if (Settings.getSpeechLanguage() == null)
                    converter.toWAW(savePath, maryController.renderAudio(Language.detect(currentClipboard), currentClipboard), maryController.getQueueLenght());
                else
                    converter.toWAW(savePath, maryController.renderAudio(Settings.getSpeechLanguage(), currentClipboard), maryController.getQueueLenght());
            } catch (Exception e) {
                e.printStackTrace();
            }
            clipboard.set(oldClipboard);
            flush();
        }
        else if(isKey0Pressed && !isKey0Released && isKey1Pressed && isKey1Released && !isSpeechCasePause) {
            flush();
            isSpeechCasePause = true;
        }
        else if(isSpeechCasePause && isKey0Released){
            System.out.println("Speech action activated");

            if(player.isPlaying()){
                player.clear();
                System.out.println("Ho stoppato il player");
            }
            else {
                String oldClipboard = clipboard.get();
                ClipboardManager.fakeCopyCombo();
                if(Settings.getSpeechLanguage() == null)
                    maryController.TTS(Language.detect(clipboard.get()), clipboard.get());
                else
                    maryController.TTS(Settings.getSpeechLanguage(), clipboard.get());
                clipboard.set(oldClipboard);
            }
            flush();
        }
        else if(isSpeechCasePause && !isKey0Released && isKey1Pressed && isKey1Released) {
            System.out.println("Pause action activated");
            if(player.isPlaying() || player.isPaused())
                maryController.pauseTTS();
            flush();
        }
        else flush();
    }

    private void flush() {
        isKey0Pressed = false;
        isKey1Pressed = false;
        isKey0Released = false;
        isKey1Released = false;
        isSpeechCasePause = false;
    }
}