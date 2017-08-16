package io.dyslexo;

import io.dyslexo.components.GlobalKeyListener;
import io.dyslexo.components.Language;
import io.dyslexo.components.Settings;
import io.dyslexo.components.Translator;
import io.dyslexo.components.audio.synthesis.MaryController;
import io.dyslexo.graphics.Setup;
import io.dyslexo.graphics.Tray;
import io.dyslexo.system.Software;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import org.apache.commons.configuration.ConfigurationException;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Dyslexo {

    public static void main(String[] args) throws SynthesisException, MaryConfigurationException, InterruptedException, IOException, UnsupportedFlavorException, AWTException /*, LangDetectException*/, ConfigurationException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, LineUnavailableException, FontFormatException, NativeHookException {
        //clear previous logging configurations.
        LogManager.getLogManager().reset();

        //jNativehook logger
        Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.OFF);
        //maryTTS logger
        Logger.getLogger("log4j.logger.marytts").setLevel(Level.OFF);

        //Initialize jNativehook
        GlobalScreen.registerNativeHook();

        //setting native system look and feel
        if(Software.getOS().equals(Software.linux)) {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("com.sun.java.swing.plaf.gtk.GTKLookAndFeel".equals(info.getClassName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        else
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        //io.dyslexo.components.Logger DyslexoTTSlogger = new io.dyslexo.components.Logger(args[0] + "/logs", true);
        //io.dyslexo.components.Logger DyslexoTTSlogger = new io.dyslexo.components.Logger(args[0] + "/logs");

        File settings = new File("settings.xml");
        new Translator();
        if(!settings.exists() && !settings.isDirectory()) {
            Locale defaultLocale = new Locale(Locale.getDefault().toString().substring(0, 2));
            if(Translator.isSupported(defaultLocale))
                new Translator(defaultLocale.toString());
            else
                new Translator("en");
            Setup setup = new Setup();
            setup.show();
        }
        else {
            start(settings);
        }
    }

    /**
     * It starts Dyslexo
     * @param settings XML file needed to load all user settings
     */
    public static void start(File settings) throws UnsupportedEncodingException, AWTException, LineUnavailableException, MaryConfigurationException, SynthesisException, NativeHookException, ConfigurationException {
        new Settings(settings);
        Locale defaultLocale = Settings.getSpeechLanguage();
        if(defaultLocale == null)
            defaultLocale = new Locale(Locale.getDefault().toString().substring(0, 2));
        new Translator(defaultLocale.toString());
        new Language();
        Tray tray = new Tray();
        tray.show();
        MaryController maryController = new MaryController();
        if(Boolean.parseBoolean(Settings.get("startup/test"))) {
            if(defaultLocale.toString().equals("en")) {
                if(Boolean.parseBoolean(Settings.get("voices/voice[@locale='en_US']/default")))
                    maryController.TTS(Locale.US, Settings.get("voices/voice[@locale='en_US']/message"));
                else if(Boolean.parseBoolean(Settings.get("voices/voice[@locale='en_GB']/default")))
                    maryController.TTS(Locale.UK, Settings.get("voices/voice[@locale='en_GB']/message"));
            }
        }
        else
            maryController.TTS(Locale.ENGLISH, "");
        GlobalScreen.addNativeKeyListener(new GlobalKeyListener(maryController));
    }
}