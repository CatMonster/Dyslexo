package io.dyslexo.graphics;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import io.dyslexo.Dyslexo;
import io.dyslexo.components.Translator;
import io.dyslexo.components.audio.synthesis.MaryController;
import marytts.LocalMaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import org.apache.commons.collections.MapUtils;
import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by stefano on 25/12/2016.
 */
public class Settings {
    private static final int MIN = 0;
    private static final int MID = 3;
    private static final int MAX = 6;

    private JButton btn_voices;
    private JButton btn_keys;
    private JButton btn_startup;
    private JButton btn_language;
    private JButton btn_request;
    private JButton btn_about;
    private JTabbedPane tbdpnl_voices;
    private JPanel pnl_enUS;
    private JPanel pnl_voices;
    private JPanel pnl_language;
    private JPanel pnl_keys;
    private JPanel pnl_startup;
    private JPanel pnl_component;
    private JPanel pnl_selector;
    private JComboBox cmb_voice_enUS;
    private JSlider sld_speed_enUS;
    private JPanel pnl_it;
    private JPanel pnl_fr;
    private JPanel pnl_de;
    private JButton btn_pnl_de;
    private JButton btn_key0;
    private JCheckBox chkb_0;
    private JCheckBox chkb_1;
    private JPanel mainPanel;
    private JLabel pnl_label0_enUS;
    private JLabel pnl_label1_enUS;
    private JLabel pnl_label0_it;
    private JLabel pnl_label1_it;
    private JLabel pnl_label0_fr;
    private JLabel pnl_label1_fr;
    private JLabel pnl_label0_de;
    private JLabel pnl_label1_de;
    private JButton btn_pnl_enUS;
    private JButton btn_pnl_it;
    private JButton btn_pnl_fr;
    private JComboBox cmb_voice_it;
    private JComboBox cmb_voice_fr;
    private JComboBox cmb_voice_de;
    private JSlider sld_speed_it;
    private JSlider sld_speed_fr;
    private JSlider sld_speed_de;
    private JComboBox cmb_defaultLang;
    private JLabel lbl_0;
    private JLabel lbl_1;
    private JButton btn_key1;
    private JButton btn_cancel;
    private JTextPane txtpnl_keyinfo;
    private JPanel pnl_enGB;
    private JLabel pnl_label0_enGB;
    private JLabel pnl_label1_enGB;
    private JButton btn_pnl_enGB;
    private JSlider sld_speed_enGB;
    private JComboBox cmb_voice_enGB;
    private JCheckBox chkbx_enUS;
    private JCheckBox chkbx_enGB;
    private JTabbedPane tbdpnl_keys;
    private JTextField txt_saveDelay;
    private JPanel pnl_advanced;
    private JLabel lbl_millis;
    private JLabel lbl_save;
    private JTextPane txtpn_startupMessage_enUS;
    private JTextPane txtpn_startupMessage_enGB;
    private JTextPane txtpn_startupMessage_it;
    private JTextPane txtpn_startupMessage_fr;
    private JTextPane txtpn_startupMessage_de;
    private JLabel lbl_startupMsg_enUS;
    private JLabel lbl_startupMsg_enGB;
    private JLabel lbl_startupMsg_it;
    private JLabel lbl_startupMsg_fr;
    private JLabel lbl_startupMsg_de;
    private JLabel lbl_path;
    private JTextField txt_path;
    private JButton btn_selectPath;
    private JCheckBox chkbx_autoSave;
    private JComboBox cmb_progamLang;
    private JPanel pnl_combo;
    private JTabbedPane tabbedPane1;
    private JFormattedTextField formattedTextField1;
    private JFormattedTextField formattedTextField2;
    private JFormattedTextField formattedTextField3;

    private NativeKeyListener key0Listener;
    private NativeKeyListener key1Listener;

    public Settings() throws MaryConfigurationException, UnsupportedEncodingException, LineUnavailableException {
        LocalMaryInterface marytts = new LocalMaryInterface();

        this.key0Listener = null;
        this.key1Listener = null;

        btn_language.setText(Translator.getWord("btn_language"));
        btn_voices.setText(Translator.getWord("btn_voices"));
        btn_startup.setText(Translator.getWord("btn_startup"));
        btn_keys.setText(Translator.getWord("btn_keys"));
        btn_about.setText(Translator.getWord("btn_about"));
        btn_request.setText(Translator.getWord("btn_request"));
        pnl_enUS.setToolTipText(Translator.getWord("pnl_enUS"));
        pnl_enGB.setToolTipText(Translator.getWord("pnl_enGB"));
        pnl_it.setToolTipText(Translator.getWord("pnl_it"));
        pnl_fr.setToolTipText(Translator.getWord("pnl_fr"));
        pnl_de.setToolTipText(Translator.getWord("pnl_de"));
        tbdpnl_voices.setTitleAt(0, Translator.getWord("pnl_enUS"));
        tbdpnl_voices.setTitleAt(1, Translator.getWord("pnl_enGB"));
        tbdpnl_voices.setTitleAt(2, Translator.getWord("pnl_it"));
        tbdpnl_voices.setTitleAt(3, Translator.getWord("pnl_fr"));
        tbdpnl_voices.setTitleAt(4, Translator.getWord("pnl_de"));
        tbdpnl_keys.setTitleAt(0, Translator.getWord("pnl_combo"));
        tbdpnl_keys.setTitleAt(1, Translator.getWord("pnl_advanced"));
        chkb_0.setText(Translator.getWord("chkb_0"));
        chkb_1.setText(Translator.getWord("chkb_1"));
        txtpnl_keyinfo.setEditable(false);
        txtpnl_keyinfo.setFocusable(false);
        txtpnl_keyinfo.setFocusable(false);
        txtpnl_keyinfo.setOpaque(false);
        btn_cancel.setVisible(false);
        btn_cancel.setText(Translator.getWord("btn_cancel"));
        pnl_label0_enUS.setText(Translator.getWord("pnl_label0"));
        pnl_label1_enUS.setText(Translator.getWord("pnl_label1"));
        pnl_label0_enGB.setText(Translator.getWord("pnl_label0"));
        pnl_label1_enGB.setText(Translator.getWord("pnl_label1"));
        pnl_label0_it.setText(Translator.getWord("pnl_label0"));
        pnl_label1_it.setText(Translator.getWord("pnl_label1"));
        pnl_label0_fr.setText(Translator.getWord("pnl_label0"));
        pnl_label1_fr.setText(Translator.getWord("pnl_label1"));
        pnl_label0_de.setText(Translator.getWord("pnl_label0"));
        pnl_label1_de.setText(Translator.getWord("pnl_label1"));
        btn_pnl_enUS.setText(Translator.getWord("pnl_btn"));
        btn_pnl_enGB.setText(Translator.getWord("pnl_btn"));
        btn_pnl_it.setText(Translator.getWord("pnl_btn"));
        btn_pnl_fr.setText(Translator.getWord("pnl_btn"));
        btn_pnl_de.setText(Translator.getWord("pnl_btn"));
        lbl_save.setText(Translator.getWord("lbl_save"));
        lbl_millis.setText(Translator.getWord("lbl_millis"));
        lbl_path.setText(Translator.getWord("lbl_path"));
        btn_selectPath.setText(Translator.getWord("btn_selectPath"));
        chkbx_autoSave.setText(Translator.getWord("chkbx_autoSave"));
        Hashtable labelTable = new Hashtable();
        labelTable.put(MIN, new JLabel(Translator.getWord("lbl_speedSlow")));
        labelTable.put(MID, new JLabel(Translator.getWord("lbl_speedNormal")));
        labelTable.put(MAX, new JLabel(Translator.getWord("lbl_speedFast")));

        //pnl_selector
        btn_voices.addActionListener(e -> {
            showPanel(pnl_voices);
        });

        btn_startup.addActionListener(e -> {
            showPanel(pnl_startup);
        });

        btn_keys.addActionListener(e -> {
            showPanel(pnl_keys);
        });
        btn_language.addActionListener(e -> {
            showPanel(pnl_language);
        });

        btn_request.addActionListener(e -> {

        });

        btn_about.addActionListener(e -> {
            try {
                About about = new About();
                about.show();
            } catch (IOException | FontFormatException e1) {
                e1.printStackTrace();
            }
        });

        //pnl_voices
        //Getting available voices and put into respective comboBox
        Set<String> en_voicesUS = marytts.getAvailableVoices(Locale.US);
        for (String temp : en_voicesUS)
            cmb_voice_enUS.addItem(temp);
        cmb_voice_enUS.setSelectedItem(io.dyslexo.components.Settings.get("voices/voice[@locale='en_US']/name"));
        cmb_voice_enUS.addActionListener(e -> io.dyslexo.components.Settings.set("voices/voice[@locale='en_US']/name", String.valueOf(cmb_voice_enUS.getSelectedItem())));

        Set<String> en_voicesUK = marytts.getAvailableVoices(Locale.UK);
        for (String temp : en_voicesUK)
            cmb_voice_enGB.addItem(temp);
        cmb_voice_enGB.setSelectedItem(io.dyslexo.components.Settings.get("voices/voice[@locale='en_GB']/name"));
        cmb_voice_enGB.addActionListener(e -> io.dyslexo.components.Settings.set("voices/voice[@locale='en_GB']/name", String.valueOf(cmb_voice_enGB.getSelectedItem())));

        Set<String> it_voices = marytts.getAvailableVoices(Locale.ITALIAN);
        for (String temp : it_voices)
            cmb_voice_it.addItem(temp);
        cmb_voice_it.setSelectedItem(io.dyslexo.components.Settings.get("voices/voice[@locale='it']/name"));
        cmb_voice_it.addActionListener(e -> io.dyslexo.components.Settings.set("voices/voice[@locale='it']/name", String.valueOf(cmb_voice_it.getSelectedItem())));

        Set<String> de_voices = marytts.getAvailableVoices(Locale.GERMAN);
        for (String temp : de_voices)
            cmb_voice_de.addItem(temp);
        cmb_voice_de.setSelectedItem(io.dyslexo.components.Settings.get("voices/voice[@locale='de']/name"));
        cmb_voice_de.addActionListener(e -> io.dyslexo.components.Settings.set("voices/voice[@locale='de']/name", String.valueOf(cmb_voice_de.getSelectedItem())));

        Set<String> fr_voices = marytts.getAvailableVoices(Locale.FRENCH);
        for (String temp : fr_voices)
            cmb_voice_fr.addItem(temp);
        cmb_voice_fr.setSelectedItem(io.dyslexo.components.Settings.get("voices/voice[@locale='fr']/name"));
        cmb_voice_fr.addActionListener(e -> io.dyslexo.components.Settings.set("voices/voice[@locale='fr']/name", String.valueOf(cmb_voice_fr.getSelectedItem())));

        sld_speed_enUS.setValue(Integer.parseInt(io.dyslexo.components.Settings.get("voices/voice[@locale='en_US']/speed")));
        sld_speed_enUS.addChangeListener(e -> io.dyslexo.components.Settings.set("voices/voice[@locale='en_US']/speed", String.valueOf(sld_speed_enUS.getValue())));
        sld_speed_enUS.setMinimum(MIN);
        sld_speed_enUS.setMaximum(MAX);
        sld_speed_enUS.setMajorTickSpacing(1);
        sld_speed_enUS.setPaintTicks(true);
        sld_speed_enUS.setLabelTable(labelTable);
        sld_speed_enUS.setPaintLabels(true);

        sld_speed_enGB.setValue(Integer.parseInt(io.dyslexo.components.Settings.get("voices/voice[@locale='en_GB']/speed")));
        sld_speed_enGB.addChangeListener(e -> io.dyslexo.components.Settings.set("voices/voice[@locale='en_GB']/speed", String.valueOf(sld_speed_enGB.getValue())));
        sld_speed_enGB.setMinimum(MIN);
        sld_speed_enGB.setMaximum(MAX);
        sld_speed_enGB.setMajorTickSpacing(1);
        sld_speed_enGB.setPaintTicks(true);
        sld_speed_enGB.setLabelTable(labelTable);
        sld_speed_enGB.setPaintLabels(true);


        sld_speed_it.setValue(Integer.parseInt(io.dyslexo.components.Settings.get("voices/voice[@locale='it']/speed")));
        sld_speed_it.addChangeListener(e -> io.dyslexo.components.Settings.set("voices/voice[@locale='it']/speed", String.valueOf(sld_speed_it.getValue())));
        sld_speed_it.setMinimum(MIN);
        sld_speed_it.setMaximum(MAX);
        sld_speed_it.setMajorTickSpacing(1);
        sld_speed_it.setPaintTicks(true);
        sld_speed_it.setLabelTable(labelTable);
        sld_speed_it.setPaintLabels(true);

        sld_speed_fr.setValue(Integer.parseInt(io.dyslexo.components.Settings.get("voices/voice[@locale='fr']/speed")));
        sld_speed_fr.addChangeListener(e -> io.dyslexo.components.Settings.set("voices/voice[@locale='fr']/speed", String.valueOf(sld_speed_fr.getValue())));
        sld_speed_fr.setMinimum(MIN);
        sld_speed_fr.setMaximum(MAX);
        sld_speed_fr.setMajorTickSpacing(1);
        sld_speed_fr.setPaintTicks(true);
        sld_speed_fr.setLabelTable(labelTable);
        sld_speed_fr.setPaintLabels(true);

        sld_speed_de.setValue(Integer.parseInt(io.dyslexo.components.Settings.get("voices/voice[@locale='de']/speed")));
        sld_speed_de.addChangeListener(e -> io.dyslexo.components.Settings.set("voices/voice[@locale='de']/speed", String.valueOf(sld_speed_de.getValue())));
        sld_speed_de.setMinimum(MIN);
        sld_speed_de.setMaximum(MAX);
        sld_speed_de.setMajorTickSpacing(1);
        sld_speed_de.setPaintTicks(true);
        sld_speed_de.setLabelTable(labelTable);
        sld_speed_de.setPaintLabels(true);

        chkbx_enUS.setText(Translator.getWord("chkbx_en"));
        chkbx_enUS.setSelected(Boolean.parseBoolean(io.dyslexo.components.Settings.get("voices/voice[@locale='en_US']/default")));
        chkbx_enUS.addActionListener(e -> {
            io.dyslexo.components.Settings.set("voices/voice[@locale='en_US']/default", String.valueOf(chkbx_enUS.isSelected()));
            if (Boolean.parseBoolean(io.dyslexo.components.Settings.get("voices/voice[@locale='en_GB']/default"))) {
                io.dyslexo.components.Settings.set("voices/voice[@locale='en_GB']/default", String.valueOf(false));
                chkbx_enGB.setSelected(false);
            }
        });

        chkbx_enGB.setText(Translator.getWord("chkbx_en"));
        chkbx_enGB.setSelected(Boolean.parseBoolean(io.dyslexo.components.Settings.get("voices/voice[@locale='en_GB']/default")));
        chkbx_enGB.addActionListener(e -> {
            io.dyslexo.components.Settings.set("voices/voice[@locale='en_GB']/default", String.valueOf(chkbx_enGB.isSelected()));
            if (Boolean.parseBoolean(io.dyslexo.components.Settings.get("voices/voice[@locale='en_US']/default"))) {
                io.dyslexo.components.Settings.set("voices/voice[@locale='en_US']/default", String.valueOf(false));
                chkbx_enUS.setSelected(false);
            }
        });

        MaryController testTTS = new MaryController();
        btn_pnl_enUS.addActionListener(e -> {
            Translator testTranslator = new Translator(Locale.ENGLISH.toString());
            try {
                testTTS.TTS(Locale.US, testTranslator.getWord("testMessage"));
            } catch (SynthesisException | MaryConfigurationException e1) {
                e1.printStackTrace();
            }
        });

        btn_pnl_enGB.addActionListener(e -> {
            Translator testTranslator = new Translator(Locale.ENGLISH.toString());
            try {
                testTTS.TTS(Locale.UK, testTranslator.getWord("testMessage"));
            } catch (SynthesisException | MaryConfigurationException e1) {
                e1.printStackTrace();
            }
        });

        btn_pnl_it.addActionListener(e -> {
            Translator testTranslator = new Translator(Locale.ITALIAN.toString());
            try {
                testTTS.TTS(Locale.ITALIAN, testTranslator.getWord("testMessage"));
            } catch (SynthesisException | MaryConfigurationException e1) {
                e1.printStackTrace();
            }
        });

        btn_pnl_fr.addActionListener(e -> {
            Translator testTranslator = new Translator(Locale.FRENCH.toString());
            try {
                testTTS.TTS(Locale.FRENCH, testTranslator.getWord("testMessage"));
            } catch (SynthesisException | MaryConfigurationException e1) {
                e1.printStackTrace();
            }
        });

        btn_pnl_de.addActionListener(e -> {
            Translator testTranslator = new Translator(Locale.GERMAN.toString());
            try {
                testTTS.TTS(Locale.GERMAN, testTranslator.getWord("testMessage"));
            } catch (SynthesisException | MaryConfigurationException e1) {
                e1.printStackTrace();
            }
        });

        lbl_startupMsg_enUS.setText(Translator.getWord("lbl_startupMsg"));
        lbl_startupMsg_enGB.setText(Translator.getWord("lbl_startupMsg"));
        lbl_startupMsg_it.setText(Translator.getWord("lbl_startupMsg"));
        lbl_startupMsg_fr.setText(Translator.getWord("lbl_startupMsg"));
        lbl_startupMsg_de.setText(Translator.getWord("lbl_startupMsg"));

        txtpn_startupMessage_enUS.setText(io.dyslexo.components.Settings.get("voices/voice[@locale='en_US']/message").replace("\"", ""));
        txtpn_startupMessage_enGB.setText(io.dyslexo.components.Settings.get("voices/voice[@locale='en_GB']/message").replace("\"", ""));
        txtpn_startupMessage_it.setText(io.dyslexo.components.Settings.get("voices/voice[@locale='it']/message").replace("\"", ""));
        txtpn_startupMessage_fr.setText(io.dyslexo.components.Settings.get("voices/voice[@locale='fr']/message").replace("\"", ""));
        txtpn_startupMessage_de.setText(io.dyslexo.components.Settings.get("voices/voice[@locale='de']/message").replace("\"", ""));

        txtpn_startupMessage_enUS.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                io.dyslexo.components.Settings.set("voices/voice[@locale='en_US']/message", "\"" + txtpn_startupMessage_enUS.getText() + "\"");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        txtpn_startupMessage_enGB.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                io.dyslexo.components.Settings.set("voices/voice[@locale='en_GB']/message", "\"" + txtpn_startupMessage_enGB.getText() + "\"");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        txtpn_startupMessage_it.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                io.dyslexo.components.Settings.set("voices/voice[@locale='it']/message", "\"" + txtpn_startupMessage_it.getText() + "\"");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        txtpn_startupMessage_fr.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                io.dyslexo.components.Settings.set("voices/voice[@locale='fr']/message", "\"" + txtpn_startupMessage_fr.getText() + "\"");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        txtpn_startupMessage_de.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                io.dyslexo.components.Settings.set("voices/voice[@locale='de']/message", "\"" + txtpn_startupMessage_de.getText() + "\"");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        //pnl_startup
        chkb_0.setSelected(Boolean.parseBoolean(io.dyslexo.components.Settings.get("startup/program")));
        chkb_0.addActionListener(e -> io.dyslexo.components.Settings.set("startup/program", String.valueOf(chkb_0.isSelected())));

        chkb_1.setSelected(Boolean.parseBoolean(io.dyslexo.components.Settings.get("startup/test")));
        if (!Boolean.parseBoolean(io.dyslexo.components.Settings.get("startup/test"))) {
            lbl_startupMsg_enUS.setEnabled(false);
            lbl_startupMsg_enGB.setEnabled(false);
            lbl_startupMsg_it.setEnabled(false);
            lbl_startupMsg_fr.setEnabled(false);
            lbl_startupMsg_de.setEnabled(false);

            txtpn_startupMessage_enUS.setEnabled(false);
            txtpn_startupMessage_enGB.setEnabled(false);
            txtpn_startupMessage_it.setEnabled(false);
            txtpn_startupMessage_fr.setEnabled(false);
            txtpn_startupMessage_de.setEnabled(false);

            lbl_startupMsg_enUS.setToolTipText(Translator.getWord("lbl_startupMsgTip"));
            lbl_startupMsg_enGB.setToolTipText(Translator.getWord("lbl_startupMsgTip"));
            lbl_startupMsg_it.setToolTipText(Translator.getWord("lbl_startupMsgTip"));
            lbl_startupMsg_fr.setToolTipText(Translator.getWord("lbl_startupMsgTip"));
            lbl_startupMsg_de.setToolTipText(Translator.getWord("lbl_startupMsgTip"));

            txtpn_startupMessage_enUS.setToolTipText(Translator.getWord("lbl_startupMsgTip"));
            txtpn_startupMessage_enGB.setToolTipText(Translator.getWord("lbl_startupMsgTip"));
            txtpn_startupMessage_it.setToolTipText(Translator.getWord("lbl_startupMsgTip"));
            txtpn_startupMessage_fr.setToolTipText(Translator.getWord("lbl_startupMsgTip"));
            txtpn_startupMessage_de.setToolTipText(Translator.getWord("lbl_startupMsgTip"));
        }
        chkb_1.addActionListener(e -> {
            io.dyslexo.components.Settings.set("startup/test", String.valueOf(chkb_1.isSelected()));
            if (chkb_1.isSelected()) {
                lbl_startupMsg_enUS.setEnabled(true);
                lbl_startupMsg_enGB.setEnabled(true);
                lbl_startupMsg_it.setEnabled(true);
                lbl_startupMsg_fr.setEnabled(true);
                lbl_startupMsg_de.setEnabled(true);

                txtpn_startupMessage_enUS.setEnabled(true);
                txtpn_startupMessage_enGB.setEnabled(true);
                txtpn_startupMessage_it.setEnabled(true);
                txtpn_startupMessage_fr.setEnabled(true);
                txtpn_startupMessage_de.setEnabled(true);

                txtpn_startupMessage_enUS.setToolTipText(null);
                txtpn_startupMessage_enGB.setToolTipText(null);
                txtpn_startupMessage_it.setToolTipText(null);
                txtpn_startupMessage_fr.setToolTipText(null);
                txtpn_startupMessage_de.setToolTipText(null);
            } else {
                lbl_startupMsg_enUS.setEnabled(false);
                lbl_startupMsg_enGB.setEnabled(false);
                lbl_startupMsg_it.setEnabled(false);
                lbl_startupMsg_fr.setEnabled(false);
                lbl_startupMsg_de.setEnabled(false);

                txtpn_startupMessage_enUS.setEnabled(false);
                txtpn_startupMessage_enGB.setEnabled(false);
                txtpn_startupMessage_it.setEnabled(false);
                txtpn_startupMessage_fr.setEnabled(false);
                txtpn_startupMessage_de.setEnabled(false);

                lbl_startupMsg_enUS.setToolTipText(Translator.getWord("lbl_startupMsgTip"));
                lbl_startupMsg_enGB.setToolTipText(Translator.getWord("lbl_startupMsgTip"));
                lbl_startupMsg_it.setToolTipText(Translator.getWord("lbl_startupMsgTip"));
                lbl_startupMsg_fr.setToolTipText(Translator.getWord("lbl_startupMsgTip"));
                lbl_startupMsg_de.setToolTipText(Translator.getWord("lbl_startupMsgTip"));

                txtpn_startupMessage_enUS.setToolTipText(Translator.getWord("lbl_startupMsgTip"));
                txtpn_startupMessage_enGB.setToolTipText(Translator.getWord("lbl_startupMsgTip"));
                txtpn_startupMessage_it.setToolTipText(Translator.getWord("lbl_startupMsgTip"));
                txtpn_startupMessage_fr.setToolTipText(Translator.getWord("lbl_startupMsgTip"));
                txtpn_startupMessage_de.setToolTipText(Translator.getWord("lbl_startupMsgTip"));
            }
        });

        //pnl_keys
        btn_key0.setText(NativeKeyEvent.getKeyText(Integer.parseInt(io.dyslexo.components.Settings.get("keys/key0"))));
        btn_key1.setText(NativeKeyEvent.getKeyText(Integer.parseInt(io.dyslexo.components.Settings.get("keys/key1"))));


        btn_key0.addActionListener(e -> {
            enablePnl_selector(false);
            tbdpnl_keys.setEnabledAt(1, false);
            btn_key1.setEnabled(false);
            showKeyInfo(true, Translator.getWord("lbl_keyinfo0"));
            btn_cancel.setVisible(true);
            key0Listener = new NativeKeyListener() {
                @Override
                public void nativeKeyPressed(NativeKeyEvent e) {
                    if (e.getKeyCode() == 0)
                        txtpnl_keyinfo.setText(Translator.getWord("lbl_info"));
                    else {
                        io.dyslexo.components.Settings.set("keys/key0", String.valueOf(e.getKeyCode()));
                        btn_key0.setText(NativeKeyEvent.getKeyText(Integer.parseInt(io.dyslexo.components.Settings.get("keys/key0"))));
                        showKeyInfo(false);
                        enablePnl_selector(true);
                        btn_key1.setEnabled(true);
                        tbdpnl_keys.setEnabledAt(1, true);
                        GlobalScreen.removeNativeKeyListener(key0Listener);
                    }
                }

                @Override
                public void nativeKeyReleased(NativeKeyEvent e) {
                }

                @Override
                public void nativeKeyTyped(NativeKeyEvent e) {
                }
            };
            GlobalScreen.addNativeKeyListener(key0Listener);
        });

        btn_key1.addActionListener(e -> {
            enablePnl_selector(false);
            tbdpnl_keys.setEnabledAt(1, false);
            btn_key0.setEnabled(false);
            showKeyInfo(true, Translator.getWord("lbl_keyinfo1"));
            btn_cancel.setVisible(true);
            key1Listener = new NativeKeyListener() {
                @Override
                public void nativeKeyPressed(NativeKeyEvent e) {
                    if (e.getKeyCode() == 0)
                        txtpnl_keyinfo.setText(Translator.getWord("lbl_info"));
                    else {
                        io.dyslexo.components.Settings.set("keys/key1", String.valueOf(e.getKeyCode()));
                        btn_key1.setText(NativeKeyEvent.getKeyText(Integer.parseInt(io.dyslexo.components.Settings.get("keys/key1"))));
                        showKeyInfo(false);
                        enablePnl_selector(true);
                        btn_key0.setEnabled(true);
                        tbdpnl_keys.setEnabledAt(1, true);
                        GlobalScreen.removeNativeKeyListener(key1Listener);
                    }
                }

                @Override
                public void nativeKeyReleased(NativeKeyEvent e) {
                }

                @Override
                public void nativeKeyTyped(NativeKeyEvent e) {
                }
            };
            GlobalScreen.addNativeKeyListener(key1Listener);
        });

        btn_cancel.addActionListener(e -> {
            GlobalScreen.removeNativeKeyListener(key0Listener);
            GlobalScreen.removeNativeKeyListener(key1Listener);
            enablePnl_selector(true);
            tbdpnl_keys.setEnabledAt(1, true);
            showKeyInfo(false);
            btn_key0.setEnabled(true);
            btn_key1.setEnabled(true);
            btn_cancel.setVisible(false);
        });

        //pnl_advanced
        txt_saveDelay.setText(io.dyslexo.components.Settings.get("keys/timeSave"));
        txt_saveDelay.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                io.dyslexo.components.Settings.set("keys/timeSave", txt_saveDelay.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                io.dyslexo.components.Settings.set("keys/timeSave", txt_saveDelay.getText());
            }
        });

        txt_path.setText(io.dyslexo.components.Settings.get("save/savePath"));
        txt_path.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                io.dyslexo.components.Settings.set("save/savePath", txt_path.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                io.dyslexo.components.Settings.set("save/savePath", txt_path.getText());
            }
        });
        btn_selectPath.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.setApproveButtonText(Translator.getWord("approveButtonText"));
            if (fileChooser.showOpenDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                String path = fileChooser.getSelectedFile().toString();
                txt_path.setText(path);
                io.dyslexo.components.Settings.set("save/savePath", path);
            } else {
                System.out.println("No Selection");
            }
        });
        chkbx_autoSave.setSelected(Boolean.parseBoolean(io.dyslexo.components.Settings.get("save/autoSave")));
        chkbx_autoSave.addChangeListener(e -> io.dyslexo.components.Settings.set("save/autoSave", String.valueOf(chkbx_autoSave.isSelected())));

        //pnl_language
        HashMap<String, String> languages = new HashMap<>();
        languages.put("en", Translator.getWord("english"));
        languages.put("it", Translator.getWord("pnl_it"));
        languages.put("fr", Translator.getWord("pnl_fr"));
        languages.put("de", Translator.getWord("pnl_de"));
        languages.put("auto", Translator.getWord("auto"));
        HashMap<String, String> reversedHashMap = (HashMap<String, String>) MapUtils.invertMap(languages);

        lbl_0.setText(Translator.getWord("lbl_0"));
        lbl_1.setText(Translator.getWord("lbl_1"));

        HashMap<String, Locale> supportedLanguages = Translator.getSupportedLocales();
        for (Object temp : supportedLanguages.values()) {
            cmb_defaultLang.addItem(languages.get(String.valueOf(temp)));
            cmb_progamLang.addItem(languages.get(String.valueOf(temp)));
        }
        cmb_defaultLang.addItem(Translator.getWord("auto"));
        cmb_progamLang.addItem(Translator.getWord("auto"));

        cmb_defaultLang.setSelectedItem(languages.get(io.dyslexo.components.Settings.get("language/speech")));
        cmb_defaultLang.addActionListener(e -> {
            if (!cmb_defaultLang.getSelectedItem().equals(languages.get(io.dyslexo.components.Settings.get("language/speech"))))
                io.dyslexo.components.Settings.set("language/speech", String.valueOf(reversedHashMap.get(cmb_defaultLang.getSelectedItem())));
        });
        cmb_progamLang.setSelectedItem(languages.get(io.dyslexo.components.Settings.get("language/translator")));
        cmb_progamLang.addActionListener(e -> {
            if (!cmb_progamLang.getSelectedItem().equals(languages.get(io.dyslexo.components.Settings.get("language/translator")))) {
                io.dyslexo.components.Settings.set("language/translator", String.valueOf(reversedHashMap.get(cmb_progamLang.getSelectedItem())));
                Component frame = null;
                JOptionPane.showMessageDialog(
                        frame,
                        Translator.getWord("restartMessage"),
                        "Dyslexo", JOptionPane.INFORMATION_MESSAGE,
                        ImageResizer.getResizedImage(Dyslexo.class.getResource("/images/tray/icon.png"), 50, 50));
                System.exit(0);
            }
        });
    }

    private void enablePnl_selector(boolean b) {
        Component[] pnl_selectorComponents = pnl_selector.getComponents();
        for (Component component : pnl_selectorComponents)
            component.setEnabled(b);
    }

    private void showKeyInfo(boolean b) {
        txtpnl_keyinfo.setVisible(b);
        btn_cancel.setVisible(b);
    }

    private void showKeyInfo(boolean b, String message) {
        txtpnl_keyinfo.setVisible(b);
        txtpnl_keyinfo.setText(message);
        btn_cancel.setVisible(b);
    }

    private void showPanel(JPanel panel) {
        pnl_component.removeAll();
        pnl_component.add(panel);
        pnl_component.repaint();
        pnl_component.revalidate();
    }

    public void show() throws MaryConfigurationException, IOException {
        JFrame frame = new JFrame();
        try {
            frame.setContentPane(new Settings().mainPanel);
        } catch (MaryConfigurationException | UnsupportedEncodingException | LineUnavailableException e) {
            e.printStackTrace();
        }
        frame.setTitle("Dyslexo - " + Translator.getWord("mnItm_settings"));
        frame.setResizable(true);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.toFront();
        frame.setVisible(true);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Dyslexo.class.getResource("/images/tray/icon.png")));
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        pnl_selector = new JPanel();
        pnl_selector.setLayout(new GridLayoutManager(6, 1, new Insets(10, 15, 10, 10), -1, -1));
        mainPanel.add(pnl_selector, BorderLayout.WEST);
        btn_voices = new JButton();
        btn_voices.setText("Button");
        pnl_selector.add(btn_voices, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btn_startup = new JButton();
        btn_startup.setText("Button");
        pnl_selector.add(btn_startup, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btn_keys = new JButton();
        btn_keys.setText("Button");
        pnl_selector.add(btn_keys, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btn_about = new JButton();
        btn_about.setText("Button");
        pnl_selector.add(btn_about, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btn_request = new JButton();
        btn_request.setText("Button");
        pnl_selector.add(btn_request, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btn_language = new JButton();
        btn_language.setText("Button");
        pnl_selector.add(btn_language, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnl_component = new JPanel();
        pnl_component.setLayout(new CardLayout(0, 0));
        mainPanel.add(pnl_component, BorderLayout.CENTER);
        pnl_voices = new JPanel();
        pnl_voices.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pnl_component.add(pnl_voices, "crd_voices");
        tbdpnl_voices = new JTabbedPane();
        pnl_voices.add(tbdpnl_voices, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        pnl_enUS = new JPanel();
        pnl_enUS.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        tbdpnl_voices.addTab("label0", pnl_enUS);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        pnl_enUS.add(panel1, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        cmb_voice_enUS = new JComboBox();
        panel1.add(cmb_voice_enUS, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnl_label0_enUS = new JLabel();
        pnl_label0_enUS.setText("Label");
        panel1.add(pnl_label0_enUS, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sld_speed_enUS = new JSlider();
        panel1.add(sld_speed_enUS, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnl_label1_enUS = new JLabel();
        pnl_label1_enUS.setText("Label");
        panel1.add(pnl_label1_enUS, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btn_pnl_enUS = new JButton();
        btn_pnl_enUS.setText("Button");
        panel1.add(btn_pnl_enUS, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        chkbx_enUS = new JCheckBox();
        chkbx_enUS.setText("CheckBox");
        panel1.add(chkbx_enUS, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        pnl_enUS.add(panel2, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lbl_startupMsg_enUS = new JLabel();
        lbl_startupMsg_enUS.setText("Label");
        panel2.add(lbl_startupMsg_enUS, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel2.add(scrollPane1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        txtpn_startupMessage_enUS = new JTextPane();
        txtpn_startupMessage_enUS.setPreferredSize(new Dimension(0, 16));
        scrollPane1.setViewportView(txtpn_startupMessage_enUS);
        pnl_enGB = new JPanel();
        pnl_enGB.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        tbdpnl_voices.addTab("label1", pnl_enGB);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        pnl_enGB.add(panel3, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        cmb_voice_enGB = new JComboBox();
        panel3.add(cmb_voice_enGB, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnl_label0_enGB = new JLabel();
        pnl_label0_enGB.setText("Label");
        panel3.add(pnl_label0_enGB, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sld_speed_enGB = new JSlider();
        panel3.add(sld_speed_enGB, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnl_label1_enGB = new JLabel();
        pnl_label1_enGB.setText("Label");
        panel3.add(pnl_label1_enGB, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        chkbx_enGB = new JCheckBox();
        chkbx_enGB.setText("CheckBox");
        panel3.add(chkbx_enGB, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btn_pnl_enGB = new JButton();
        btn_pnl_enGB.setText("Button");
        panel3.add(btn_pnl_enGB, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        pnl_enGB.add(panel4, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lbl_startupMsg_enGB = new JLabel();
        lbl_startupMsg_enGB.setText("Label");
        panel4.add(lbl_startupMsg_enGB, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane2 = new JScrollPane();
        panel4.add(scrollPane2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        txtpn_startupMessage_enGB = new JTextPane();
        scrollPane2.setViewportView(txtpn_startupMessage_enGB);
        pnl_it = new JPanel();
        pnl_it.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        tbdpnl_voices.addTab("label2", pnl_it);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        pnl_it.add(panel5, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        cmb_voice_it = new JComboBox();
        panel5.add(cmb_voice_it, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnl_label0_it = new JLabel();
        pnl_label0_it.setText("Label");
        panel5.add(pnl_label0_it, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sld_speed_it = new JSlider();
        panel5.add(sld_speed_it, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnl_label1_it = new JLabel();
        pnl_label1_it.setText("Label");
        panel5.add(pnl_label1_it, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btn_pnl_it = new JButton();
        btn_pnl_it.setText("Button");
        panel5.add(btn_pnl_it, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        pnl_it.add(panel6, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lbl_startupMsg_it = new JLabel();
        lbl_startupMsg_it.setText("Label");
        panel6.add(lbl_startupMsg_it, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane3 = new JScrollPane();
        panel6.add(scrollPane3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        txtpn_startupMessage_it = new JTextPane();
        scrollPane3.setViewportView(txtpn_startupMessage_it);
        pnl_fr = new JPanel();
        pnl_fr.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        tbdpnl_voices.addTab("label3", pnl_fr);
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        pnl_fr.add(panel7, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        cmb_voice_fr = new JComboBox();
        panel7.add(cmb_voice_fr, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnl_label0_fr = new JLabel();
        pnl_label0_fr.setText("Label");
        panel7.add(pnl_label0_fr, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sld_speed_fr = new JSlider();
        panel7.add(sld_speed_fr, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnl_label1_fr = new JLabel();
        pnl_label1_fr.setText("Label");
        panel7.add(pnl_label1_fr, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btn_pnl_fr = new JButton();
        btn_pnl_fr.setText("Button");
        panel7.add(btn_pnl_fr, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        pnl_fr.add(panel8, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lbl_startupMsg_fr = new JLabel();
        lbl_startupMsg_fr.setText("Label");
        panel8.add(lbl_startupMsg_fr, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane4 = new JScrollPane();
        panel8.add(scrollPane4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        txtpn_startupMessage_fr = new JTextPane();
        scrollPane4.setViewportView(txtpn_startupMessage_fr);
        pnl_de = new JPanel();
        pnl_de.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        tbdpnl_voices.addTab("label4", pnl_de);
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        pnl_de.add(panel9, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        cmb_voice_de = new JComboBox();
        panel9.add(cmb_voice_de, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnl_label0_de = new JLabel();
        pnl_label0_de.setText("Label");
        panel9.add(pnl_label0_de, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sld_speed_de = new JSlider();
        panel9.add(sld_speed_de, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btn_pnl_de = new JButton();
        btn_pnl_de.setText("Button");
        panel9.add(btn_pnl_de, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnl_label1_de = new JLabel();
        pnl_label1_de.setText("Label");
        panel9.add(pnl_label1_de, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel10 = new JPanel();
        panel10.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        pnl_de.add(panel10, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lbl_startupMsg_de = new JLabel();
        lbl_startupMsg_de.setText("Label");
        panel10.add(lbl_startupMsg_de, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane5 = new JScrollPane();
        panel10.add(scrollPane5, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        txtpn_startupMessage_de = new JTextPane();
        scrollPane5.setViewportView(txtpn_startupMessage_de);
        pnl_keys = new JPanel();
        pnl_keys.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pnl_component.add(pnl_keys, "");
        tbdpnl_keys = new JTabbedPane();
        pnl_keys.add(tbdpnl_keys, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        pnl_combo = new JPanel();
        pnl_combo.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        tbdpnl_keys.addTab("Untitled", pnl_combo);
        final JPanel panel11 = new JPanel();
        panel11.setLayout(new GridLayoutManager(1, 5, new Insets(0, 0, 0, 0), -1, -1));
        pnl_combo.add(panel11, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        btn_key0 = new JButton();
        btn_key0.setText("Button");
        panel11.add(btn_key0, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btn_key1 = new JButton();
        btn_key1.setText("Button");
        panel11.add(btn_key1, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("+");
        panel11.add(label1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel11.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel11.add(spacer2, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel12 = new JPanel();
        panel12.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        pnl_combo.add(panel12, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        txtpnl_keyinfo = new JTextPane();
        txtpnl_keyinfo.setEditable(false);
        panel12.add(txtpnl_keyinfo, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        btn_cancel = new JButton();
        btn_cancel.setText("Button");
        panel12.add(btn_cancel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel12.add(spacer3, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel12.add(spacer4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        pnl_advanced = new JPanel();
        pnl_advanced.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        tbdpnl_keys.addTab("Untitled", pnl_advanced);
        final JPanel panel13 = new JPanel();
        panel13.setLayout(new GridLayoutManager(3, 4, new Insets(0, 0, 0, 0), -1, -1));
        pnl_advanced.add(panel13, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lbl_save = new JLabel();
        lbl_save.setText("Label");
        panel13.add(lbl_save, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txt_saveDelay = new JTextField();
        panel13.add(txt_saveDelay, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("(1 s = 1000 ms)");
        panel13.add(label2, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbl_millis = new JLabel();
        lbl_millis.setText("Label");
        panel13.add(lbl_millis, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel13.add(spacer5, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        panel13.add(spacer6, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel14 = new JPanel();
        panel14.setLayout(new GridLayoutManager(3, 4, new Insets(0, 0, 0, 0), -1, -1));
        pnl_advanced.add(panel14, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lbl_path = new JLabel();
        lbl_path.setText("Label");
        panel14.add(lbl_path, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txt_path = new JTextField();
        panel14.add(txt_path, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer7 = new Spacer();
        panel14.add(spacer7, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        panel14.add(spacer8, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        btn_selectPath = new JButton();
        btn_selectPath.setText("Button");
        panel14.add(btn_selectPath, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        chkbx_autoSave = new JCheckBox();
        chkbx_autoSave.setText("CheckBox");
        panel14.add(chkbx_autoSave, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        pnl_advanced.add(spacer9, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        pnl_startup = new JPanel();
        pnl_startup.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        pnl_component.add(pnl_startup, "Card3");
        chkb_0 = new JCheckBox();
        chkb_0.setText("Enable TTS to every startup. This is useful to get ready every time your personal TTS assistant");
        pnl_startup.add(chkb_0, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        chkb_1 = new JCheckBox();
        chkb_1.setText("Enable sound test every time you opeen your personal TTS assistant");
        pnl_startup.add(chkb_1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnl_language = new JPanel();
        pnl_language.setLayout(new GridLayoutManager(4, 5, new Insets(0, 0, 0, 0), -1, -1));
        pnl_component.add(pnl_language, "Card2");
        lbl_0 = new JLabel();
        lbl_0.setText("Label");
        pnl_language.add(lbl_0, new GridConstraints(0, 1, 2, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbl_1 = new JLabel();
        lbl_1.setText("Label");
        pnl_language.add(lbl_1, new GridConstraints(0, 3, 2, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cmb_defaultLang = new JComboBox();
        pnl_language.add(cmb_defaultLang, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cmb_progamLang = new JComboBox();
        pnl_language.add(cmb_progamLang, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer10 = new Spacer();
        pnl_language.add(spacer10, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(15, -1), null, 0, false));
        final Spacer spacer11 = new Spacer();
        pnl_language.add(spacer11, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(20, -1), null, 0, false));
        final Spacer spacer12 = new Spacer();
        pnl_language.add(spacer12, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(20, -1), null, 0, false));
        final Spacer spacer13 = new Spacer();
        pnl_language.add(spacer13, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 10), null, 0, false));
        final Spacer spacer14 = new Spacer();
        pnl_language.add(spacer14, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 10), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}