package io.dyslexo.components.audio.synthesis;

import io.dyslexo.components.Settings;
import io.dyslexo.components.audio.Player;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by stefano on 12/12/2016.
 */
public class MaryController {
    private Player player;
    private HashMap<String, String> speeds;

    private int QTApieces;

    public MaryController() throws LineUnavailableException {
        this.player = new Player();
        this.speeds = new HashMap<>();
        speeds.put("0", "-60%");
        speeds.put("1", "-50%");
        speeds.put("2", "-25%");
        speeds.put("3", "+0%");
        speeds.put("4", "+25%");
        speeds.put("5", "+50%");
        speeds.put("6", "+60%");

        this.QTApieces = 0;
    }

    public void TTS(Locale locale, String phrase) throws SynthesisException, MaryConfigurationException {
        try {
            player = new Player(renderAudio(locale,phrase));
            player.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BlockingQueue<Optional<AudioInputStream>> renderAudio(Locale locale, String phrase) throws ParserConfigurationException, SynthesisException, MaryConfigurationException, TransformerException {
        return createAudio(locale, phrase);
    }

    private BlockingQueue<Optional<AudioInputStream>> createAudio(Locale locale, String phrase) throws TransformerException, ParserConfigurationException, SynthesisException, MaryConfigurationException {
        if(locale.toString().equals("en"))
            if(Boolean.parseBoolean(Settings.get("voices/voice[@locale='en_GB']/default")))
                locale  = Locale.UK;
            else
                locale = Locale.US;

        //cleaning phrase from commas issue
        if(phrase.contains("- "))
            phrase = phrase.replace("- ", "");

        //cleaning phrase from double points issue
        if(phrase.contains(".."))
            phrase = phrase.replace("..", ".");

        //cleaning phrase from suspension points issue
        if(phrase.contains("..."))
            phrase = phrase.replace("...", ".");

        if(phrase.contains("[...]"))
            phrase = phrase.replace("[...]", "");

        //splitting phrase into littler pieces
        BlockingQueue<Optional<AudioInputStream>> outputQueue = new LinkedBlockingQueue<>();

        List<String> pieces = Arrays.asList(phrase.split("\\."));
        QTApieces = pieces.size();
        Thread textProcesser = new TextProcessor(
                locale,
                speeds.get(Settings.get("voices/voice[@locale='" + String.valueOf(locale) + "']/speed")),
                pieces,
                outputQueue);
        textProcesser.start();

        return outputQueue;
    }

    public void pauseTTS() {
        if (player.isPaused())
            try {
                player.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        else player.pause();
    }

    public Player getPlayer() {
        return player;
    }

    public int getQueueLenght() {
        return QTApieces;
    }
}