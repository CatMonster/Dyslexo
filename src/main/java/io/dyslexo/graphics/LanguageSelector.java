package io.dyslexo.graphics;

import io.dyslexo.Dyslexo;
import io.dyslexo.components.Translator;
import io.dyslexo.components.Settings;
import io.dyslexo.components.audio.synthesis.MaryController;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;

/**
 * Created by stefano on 30/12/2016.
 */
public class LanguageSelector {
    private static final int pnlHeight = 100;
    private static final int pnlWidth = 500;
    private static final int btnHeight = 100;
    private static final int btnWidth = 100;

    private Locale userInput;
    private boolean isLocaleSelected;

    private static JFrame frm_languageSelector;

    public LanguageSelector() throws LineUnavailableException {
        this.userInput = null;
        this.isLocaleSelected = false;

        JButton btn_enUS = new JButton();
        JButton btn_enGB = new JButton();
        JButton btn_it = new JButton();
        JButton btn_fr = new JButton();
        JButton btn_de = new JButton();

        this.frm_languageSelector = new JFrame();

        frm_languageSelector.setSize(pnlWidth, pnlHeight);
        frm_languageSelector.setLayout(new GridLayout(1,5));
        frm_languageSelector.setUndecorated(true);
        frm_languageSelector.setBackground(new Color(0, 0, 0, 0));
        frm_languageSelector.getRootPane().setBorder(null);
        frm_languageSelector.setLocationRelativeTo(null);

        btn_enUS.setSize(btnWidth, btnHeight);
        btn_enGB.setSize(btnWidth, btnHeight);
        btn_it.setSize(btnWidth, btnHeight);
        btn_fr.setSize(btnWidth, btnHeight);
        btn_de.setSize(btnWidth, btnHeight);

        btn_enUS.setIcon(new ImageIcon(Dyslexo.class.getResource("/images/countryFlags/en_us.png")));
        btn_enGB.setIcon(new ImageIcon(Dyslexo.class.getResource("/images/countryFlags/en_gb.png")));
        btn_it.setIcon(new ImageIcon(Dyslexo.class.getResource("/images/countryFlags/it.png")));
        btn_fr.setIcon(new ImageIcon(Dyslexo.class.getResource("/images/countryFlags/fr.png")));
        btn_de.setIcon(new ImageIcon(Dyslexo.class.getResource("/images/countryFlags/de.png")));

        btn_enUS.setToolTipText(Translator.getWord("pnl_enUS"));
        btn_enGB.setToolTipText(Translator.getWord("pnl_enGB"));
        btn_it.setToolTipText(Translator.getWord("pnl_it"));
        btn_fr.setToolTipText(Translator.getWord("pnl_fr"));
        btn_de.setToolTipText(Translator.getWord("pnl_de"));

        MaryController tts = new MaryController();
        btn_enUS.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if(SwingUtilities.isLeftMouseButton(e)) {
                show(false);
                userInput = Locale.US;
                isLocaleSelected = true;
            }
            if(SwingUtilities.isRightMouseButton(e))
                try {
                    tts.TTS(Settings.getSpeechLanguage(), Translator.getWord("lbl_0") + " " + Translator.getWord("pnl_enUS"));
                } catch (SynthesisException | MaryConfigurationException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btn_enGB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if(SwingUtilities.isLeftMouseButton(e)) {
                show(false);
                userInput = Locale.UK;
                isLocaleSelected = true;
            }
            if(SwingUtilities.isRightMouseButton(e))
                try {
                    tts.TTS(Settings.getSpeechLanguage(), Translator.getWord("lbl_0") + " " + Translator.getWord("pnl_enGB"));
                } catch (SynthesisException | MaryConfigurationException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btn_it.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if(SwingUtilities.isLeftMouseButton(e)) {
                show(false);
                userInput = Locale.ITALIAN;
                isLocaleSelected = true;
            }
            if(SwingUtilities.isRightMouseButton(e))
                try {
                    tts.TTS(Settings.getSpeechLanguage(), Translator.getWord("lbl_0") + " " + Translator.getWord("pnl_it"));
                } catch (SynthesisException | MaryConfigurationException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btn_fr.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if(SwingUtilities.isLeftMouseButton(e)) {
                show(false);
                userInput = Locale.FRENCH;
                isLocaleSelected = true;
            }
            if(SwingUtilities.isRightMouseButton(e))
                try {
                    tts.TTS(Settings.getSpeechLanguage(), Translator.getWord("lbl_0") + " " + Translator.getWord("pnl_fr"));
                } catch (SynthesisException | MaryConfigurationException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btn_de.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if(SwingUtilities.isLeftMouseButton(e)) {
                show(false);
                userInput = Locale.GERMAN;
                isLocaleSelected = true;
            }
            if(SwingUtilities.isRightMouseButton(e))
                try {
                    tts.TTS(Settings.getSpeechLanguage(), Translator.getWord("lbl_0") + " " + Translator.getWord("pnl_de"));
                } catch (SynthesisException | MaryConfigurationException e1) {
                    e1.printStackTrace();
                }
            }
        });

        KeyListener escape = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getExtendedKeyCode() == KeyEvent.VK_ESCAPE){
                    show(false);
                    isLocaleSelected = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };

        btn_enUS.addKeyListener(escape);
        btn_enGB.addKeyListener(escape);
        btn_it.addKeyListener(escape);
        btn_fr.addKeyListener(escape);
        btn_de.addKeyListener(escape);

        frm_languageSelector.add(btn_enUS);
        frm_languageSelector.add(btn_enGB);
        frm_languageSelector.add(btn_it);
        frm_languageSelector.add(btn_fr);
        frm_languageSelector.add(btn_de);
    }

    public void show(boolean b) {
        frm_languageSelector.setVisible(b);
        frm_languageSelector.toFront();
    }

    public Locale getUserInput(){
        show(true);
        while(!isLocaleSelected){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return userInput;
    }
}