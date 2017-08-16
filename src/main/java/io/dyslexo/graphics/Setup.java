package io.dyslexo.graphics;

import com.apple.eawt.Application;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import io.dyslexo.Dyslexo;
import io.dyslexo.components.Settings;
import io.dyslexo.components.Translator;
import io.dyslexo.system.Software;
import org.apache.commons.collections.MapUtils;
import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTMLDocument;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by stefano on 27/04/2017.
 */
public class Setup {
    private static JFrame frame = new JFrame();
    private Font SanFranciscoDisplay_Regular;
    private Font SanFranciscoDisplay_Semibold;
    private Font SanFranciscoDisplay_Medium;
    private Font SanFranciscoText_Regular;
    private ImageIcon leftArrow;
    private ImageIcon rightArrow;
    private final boolean[] isPathSelected = {false};

    HashMap<String, String> languages;
    HashMap<String, String> reversedHashMap;

    private JPanel mainPanel;
    private JPanel pnl_1;
    private JPanel pnl_2;
    private JPanel pnl_3;
    private JPanel pnl_4;
    private JPanel pnl_5;
    private JPanel pnl_6;
    private JPanel pnl_7;
    private JLabel img_pnl1Logo;
    private JLabel lbl_pnl1Title;
    private JLabel lbl_pnl1Description;
    private JTextPane txt_license;
    private JLabel img_pnl2Logo;
    private JLabel lbl_pnl2Title;
    private JLabel lbl_pnl2Description;
    private JLabel lbl_pnl3Title;
    private JLabel lbl_pnl3Description;
    private JLabel img_pnl3Logo;
    private JLabel lbl_pnl4Title;
    private JCheckBox chbx_pnl4Startup;
    private JCheckBox chbx_pnl4SoundTest;
    private JPanel pnl_1commands;
    private JPanel pnl_2commands;
    private JPanel pnl_4commands;
    private JButton btn_A1;
    private JLabel lbl_A1;
    private JButton btn_A2;
    private JButton btn_B2;
    private JLabel lbl_A2;
    private JLabel lbl_B2;
    private JPanel pnl_3commands;
    private JButton btn_A3;
    private JButton btn_B3;
    private JLabel lbl_A3;
    private JLabel lbl_B3;
    private JButton btn_A4;
    private JLabel lbl_A4;
    private JButton btn_B4;
    private JLabel lbl_B4;
    private JLabel lbl_pnl4Description;
    private JLabel img_pnl4Logo;
    private JPanel pnl_5commands;
    private JTextPane txt_functions;
    private JButton btn_pnl5Key0;
    private JButton btn_pnl5Key1;
    private JButton btn_A5;
    private JLabel lbl_A5;
    private JButton btn_B5;
    private JLabel lbl_B5;
    private JLabel lbl_pnl5Title;
    private JLabel lbl_pnl5Description;
    private JTextField txt_pnl6Path;
    private JCheckBox chbx_pnl6AutoSave;
    private JPanel pnl_6commands;
    private JButton btn_A6;
    private JLabel lbl_A6;
    private JButton btn_B6;
    private JLabel lbl_B6;
    private JLabel lbl_pnl6Title;
    private JLabel lbl_pnl6Description;
    private JLabel img_pnl6Logo;
    private JLabel lbl_pnl6Path;
    private JButton btn_pnl6SelectPath;
    private JPanel pnl_8;
    private JPanel pnl_8commands;
    private JPanel pnl_7commands;
    private JComboBox cmb_pnl7Speech;
    private JComboBox cmb_pnl7Language;
    private JButton btn_A7;
    private JButton btn_B7;
    private JLabel lbl_A7;
    private JLabel lbl_B7;
    private JLabel lbl_pnl7Title;
    private JLabel lbl_pnl7Description;
    private JLabel lbl_pnl7Speech;
    private JLabel lbl_pnl7Language;
    private JPanel pnl_contents;
    private JPanel pnl_commands;
    private JRadioButton rdbtn_pnl7enUS;
    private JRadioButton rdbtn_pnl7enGB;
    private JLabel lbl_pnl7Defaulten;
    private JLabel lbl_pnl8Title;
    private JLabel lbl_pnl8Description;
    private JLabel img_pnl8Logo;
    private JLabel lbl_A8;
    private JButton btn_A8;
    private JButton btn_B8;
    private JLabel lbl_B8;
    private JLabel lbl_pnl5msg;
    private JButton btn_pnl5Cancel;

    private NativeKeyListener key0Listener;
    private NativeKeyListener key1Listener;

    private int key0;
    private int key1;

    public Setup() throws IOException, FontFormatException {
        SanFranciscoDisplay_Regular = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/fonts/SanFranciscoDisplay-Regular.otf").openStream());
        SanFranciscoDisplay_Semibold = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/fonts/SanFranciscoDisplay-Semibold.otf").openStream());
        SanFranciscoDisplay_Medium = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/fonts/SanFranciscoDisplay-Medium.otf").openStream());
        SanFranciscoText_Regular = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/fonts/SanFranciscoText-Regular.otf").openStream());
        leftArrow = ImageResizer.getResizedImage((Dyslexo.class.getResource("/images/setup/arrowbuttonL.png")), 32, 32);
        rightArrow = ImageResizer.getResizedImage((Dyslexo.class.getResource("/images/setup/arrowbuttonR.png")), 32, 32);

        this.languages = new HashMap<>();
        languages.put("en", Translator.getWord("english"));
        languages.put("it", Translator.getWord("pnl_it"));
        languages.put("fr", Translator.getWord("pnl_fr"));
        languages.put("de", Translator.getWord("pnl_de"));
        languages.put("auto", Translator.getWord("auto"));

        this.reversedHashMap = (HashMap<String, String>) MapUtils.invertMap(languages);

        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(SanFranciscoDisplay_Regular);

        if (Software.getOS().equals(Software.macOS)) {
            Application application = Application.getApplication();
            application.removeAboutMenuItem();
            application.removePreferencesMenuItem();
            application.setDockIconImage(Toolkit.getDefaultToolkit().getImage(Dyslexo.class.getResource("/images/tray/icon.png")));
        }

        this.key0Listener = null;
        this.key1Listener = null;

        this.key0 = 3675;
        this.key1 = 1;

        scene1();
    }

    private void scene1() {
        showScene(pnl_1, pnl_1commands);

        lbl_pnl1Title.setFont(SanFranciscoDisplay_Semibold.deriveFont(18f));
        lbl_pnl1Title.setForeground(Color.decode("#414141"));
        lbl_pnl1Description.setFont(SanFranciscoDisplay_Regular.deriveFont(8.5f));
        lbl_pnl1Description.setForeground(Color.decode("#222222"));
        lbl_A1.setFont(SanFranciscoDisplay_Medium.deriveFont(6f));
        lbl_A1.setForeground(Color.decode("#222222"));

        img_pnl1Logo.setIcon(ImageResizer.getResizedImage((Dyslexo.class.getResource("/images/tray/icon.png")), 270, 270));
        lbl_pnl1Title.setText("Dyslexo");
        lbl_pnl1Description.setText(String.format(Translator.getWord("lbl_pnl1Description"), Software.getUser()));
        btn_A1.setIcon(rightArrow);
        btn_A1.setBorder(null);
        btn_A1.setContentAreaFilled(false);
        btn_A1.setFocusPainted(false);
        btn_A1.addActionListener(e -> {
            scene2();
        });
        lbl_A1.setText(Translator.getWord("lbl_B"));
    }

    private void scene2() {
        showScene(pnl_2, pnl_2commands);

        lbl_pnl2Title.setFont(SanFranciscoDisplay_Semibold.deriveFont(18f));
        lbl_pnl2Title.setForeground(Color.decode("#414141"));
        lbl_pnl2Description.setFont(SanFranciscoDisplay_Regular.deriveFont(8.5f));
        lbl_pnl2Description.setForeground(Color.decode("#222222"));
        txt_license.setFont(SanFranciscoDisplay_Regular.deriveFont(7f));
        lbl_A2.setFont(SanFranciscoDisplay_Medium.deriveFont(6f));
        lbl_A2.setForeground(Color.decode("#222222"));
        lbl_B2.setFont(SanFranciscoDisplay_Medium.deriveFont(6f));
        lbl_B2.setForeground(Color.decode("#222222"));

        img_pnl2Logo.setIcon(ImageResizer.getResizedImage((Dyslexo.class.getResource("/images/tray/icon.png")), 170, 170));
        lbl_pnl2Title.setText("Dyslexo");
        lbl_pnl2Description.setText(Translator.getWord("lbl_pnl2Description"));
        txt_license.setText(
                "<p align='center'><b>Copyright © 2017 Dyslexo</b><br></p>" +
                        "<br>" +
                        "<p align='justify'>" +
                        "This program is free software: you can redistribute it and/or modify " +
                        "it under the terms of the GNU General Public License as published by " +
                        "the Free Software Foundation, either version 3 of the License, or " +
                        "(at your option) any later version.<br>" +
                        "<br>" +
                        "This program is distributed in the hope that it will be useful, " +
                        "but WITHOUT ANY WARRANTY; without even the implied warranty of " +
                        "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the " +
                        "GNU General Public License for more details." +
                        "<br>" +
                        "You should have received a copy of the GNU General Public License " +
                        "along with this program.  If not, see http://www.gnu.org/licenses." +
                        "</p>");
        ((HTMLDocument) txt_license.getDocument()).getStyleSheet().addRule("body { font-family: " + SanFranciscoDisplay_Regular.getFamily() + "; font-size: " + SanFranciscoDisplay_Regular.deriveFont(7f).getSize() + "pt; }");
        btn_A2.setIcon(leftArrow);
        btn_A2.setBorder(null);
        btn_A2.setContentAreaFilled(false);
        btn_A2.setFocusPainted(false);
        btn_A2.addActionListener(e -> {
            scene1();
        });
        lbl_A2.setText(Translator.getWord("lbl_Alic"));
        btn_B2.setIcon(rightArrow);
        btn_B2.setBorder(null);
        btn_B2.setContentAreaFilled(false);
        btn_B2.setFocusPainted(false);
        btn_B2.addActionListener(e -> {
            scene3();
        });
        lbl_B2.setText(Translator.getWord("lbl_Blic"));
    }

    private void scene3() {
        showScene(pnl_3, pnl_3commands);

        lbl_pnl3Title.setFont(SanFranciscoDisplay_Semibold.deriveFont(18f));
        lbl_pnl3Title.setForeground(Color.decode("#414141"));
        lbl_pnl3Description.setFont(SanFranciscoDisplay_Regular.deriveFont(8.5f));
        lbl_pnl3Description.setForeground(Color.decode("#222222"));
        lbl_A3.setFont(SanFranciscoDisplay_Medium.deriveFont(6f));
        lbl_A3.setForeground(Color.decode("#222222"));
        lbl_B3.setFont(SanFranciscoDisplay_Medium.deriveFont(6f));
        lbl_B3.setForeground(Color.decode("#222222"));

        img_pnl3Logo.setIcon(ImageResizer.getResizedImage((Dyslexo.class.getResource("/images/setup/settings.png")), 300, 300));
        lbl_pnl3Title.setText(Translator.getWord("lbl_pnl3Title"));
        lbl_pnl3Description.setText(Translator.getWord("lbl_pnl3Description"));
        btn_A3.setIcon(leftArrow);
        btn_A3.setBorder(null);
        btn_A3.setContentAreaFilled(false);
        btn_A3.setFocusPainted(false);
        btn_A3.addActionListener(e -> {
            scene2();
        });
        lbl_A3.setText(Translator.getWord("lbl_A"));
        btn_B3.setIcon(rightArrow);
        btn_B3.setBorder(null);
        btn_B3.setContentAreaFilled(false);
        btn_B3.setFocusPainted(false);
        btn_B3.addActionListener(e -> {
            scene4();
        });
        lbl_B3.setText(Translator.getWord("lbl_B"));
    }

    private void scene4() {
        showScene(pnl_4, pnl_4commands);

        lbl_pnl4Title.setFont(SanFranciscoDisplay_Semibold.deriveFont(18f));
        lbl_pnl4Title.setForeground(Color.decode("#414141"));
        lbl_pnl4Description.setFont(SanFranciscoDisplay_Regular.deriveFont(8.5f));
        lbl_pnl4Description.setForeground(Color.decode("#222222"));
        lbl_A4.setFont(SanFranciscoDisplay_Medium.deriveFont(6f));
        lbl_A4.setForeground(Color.decode("#222222"));
        lbl_B4.setFont(SanFranciscoDisplay_Medium.deriveFont(6f));
        lbl_B4.setForeground(Color.decode("#222222"));
        chbx_pnl4Startup.setFont(SanFranciscoDisplay_Regular.deriveFont(7f));
        chbx_pnl4Startup.setForeground(Color.decode("#222222"));
        chbx_pnl4SoundTest.setFont(SanFranciscoDisplay_Regular.deriveFont(7f));
        chbx_pnl4SoundTest.setForeground(Color.decode("#222222"));

        img_pnl4Logo.setIcon(ImageResizer.getResizedImage((Dyslexo.class.getResource("/images/setup/rocket.png")), 150, 150));
        lbl_pnl4Title.setText(Translator.getWord("btn_startup"));
        lbl_pnl4Description.setText(Translator.getWord("lbl_pnl4Description"));
        chbx_pnl4Startup.setText(Translator.getWord("chkb_0"));
        chbx_pnl4SoundTest.setText(Translator.getWord("chkb_1"));
        btn_A4.setIcon(leftArrow);
        btn_A4.setBorder(null);
        btn_A4.setContentAreaFilled(false);
        btn_A4.setFocusPainted(false);
        btn_A4.addActionListener(e -> {
            scene3();
        });
        lbl_A4.setText(Translator.getWord("lbl_A"));
        btn_B4.setIcon(rightArrow);
        btn_B4.setBorder(null);
        btn_B4.setContentAreaFilled(false);
        btn_B4.setFocusPainted(false);
        btn_B4.addActionListener(e -> {
            scene5();
        });
        lbl_B4.setText(Translator.getWord("lbl_B"));
    }

    private void scene5() {
        showScene(pnl_5, pnl_5commands);

        lbl_pnl5Title.setFont(SanFranciscoDisplay_Semibold.deriveFont(18f));
        lbl_pnl5Title.setForeground(Color.decode("#414141"));
        lbl_pnl5Description.setFont(SanFranciscoDisplay_Regular.deriveFont(8.5f));
        lbl_pnl5Description.setForeground(Color.decode("#222222"));
        lbl_A5.setFont(SanFranciscoDisplay_Medium.deriveFont(6f));
        lbl_A5.setForeground(Color.decode("#222222"));
        lbl_B5.setFont(SanFranciscoDisplay_Medium.deriveFont(6f));
        lbl_B5.setForeground(Color.decode("#222222"));
        txt_functions.setFont(SanFranciscoText_Regular.deriveFont(14f));
        txt_functions.setOpaque(false);

        lbl_pnl5Title.setText(Translator.getWord("lbl_pnl5Title"));
        lbl_pnl5Description.setText(Translator.getWord("lbl_pnl5Description"));
        txt_functions.setText(Translator.getWord("txt_functions"));
        ((HTMLDocument) txt_functions.getDocument()).getStyleSheet().addRule("body { font-family: " + SanFranciscoDisplay_Regular.getFamily() + "; font-size: " + SanFranciscoDisplay_Regular.deriveFont(8f).getSize() + "pt; }");
        StyledDocument doc = txt_functions.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        btn_pnl5Key0.setText(NativeKeyEvent.getKeyText(key0));
        btn_pnl5Key1.setText(NativeKeyEvent.getKeyText(key1));

        lbl_pnl5msg.setVisible(false);
        lbl_pnl5msg.setText(Translator.getWord("lbl_keyinfo0"));
        btn_pnl5Cancel.setVisible(false);
        btn_pnl5Cancel.setText(Translator.getWord("btn_cancel"));
        btn_pnl5Key0.addActionListener(e -> {
            btn_pnl5Key1.setEnabled(false);
            lbl_pnl5msg.setVisible(true);
            btn_pnl5Cancel.setVisible(true);
            key0Listener = new NativeKeyListener() {
                @Override
                public void nativeKeyPressed(NativeKeyEvent e) {
                    if (e.getKeyCode() == 0)
                        lbl_pnl5msg.setText(Translator.getWord("lbl_info"));
                    else {
                        key0 = e.getKeyCode();
                        btn_pnl5Key0.setText(NativeKeyEvent.getKeyText(key0));
                        btn_pnl5Key1.setEnabled(true);
                        lbl_pnl5msg.setVisible(false);
                        btn_pnl5Cancel.setVisible(false);
                        pnl_5.repaint();
                        pnl_5.revalidate();
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
            btn_pnl5Key1.setEnabled(false);
        });

        btn_pnl5Key1.addActionListener(e -> {
            btn_pnl5Key0.setEnabled(false);
            lbl_pnl5msg.setVisible(true);
            btn_pnl5Cancel.setVisible(true);
            key1Listener = new NativeKeyListener() {
                @Override
                public void nativeKeyPressed(NativeKeyEvent e) {
                    if (e.getKeyCode() == 0)
                        lbl_pnl5msg.setText(Translator.getWord("lbl_info"));
                    else {
                        key1 = e.getKeyCode();
                        btn_pnl5Key1.setText(NativeKeyEvent.getKeyText(key1));
                        btn_pnl5Key0.setEnabled(true);
                        lbl_pnl5msg.setVisible(false);
                        btn_pnl5Cancel.setVisible(false);
                        pnl_5.repaint();
                        pnl_5.revalidate();
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

        btn_pnl5Cancel.addActionListener(e -> {
            GlobalScreen.removeNativeKeyListener(key0Listener);
            GlobalScreen.removeNativeKeyListener(key1Listener);
            btn_pnl5Key0.setEnabled(true);
            btn_pnl5Key1.setEnabled(true);
            btn_pnl5Cancel.setVisible(false);
            lbl_pnl5msg.setVisible(false);
            pnl_5.repaint();
            pnl_5.revalidate();
        });

        btn_A5.setIcon(leftArrow);
        btn_A5.setBorder(null);
        btn_A5.setContentAreaFilled(false);
        btn_A5.setFocusPainted(false);
        btn_A5.addActionListener(e -> {
            scene4();
        });
        lbl_A5.setText(Translator.getWord("lbl_A"));
        btn_B5.setIcon(rightArrow);
        btn_B5.setBorder(null);
        btn_B5.setContentAreaFilled(false);
        btn_B5.setFocusPainted(false);
        btn_B5.addActionListener(e -> {
            scene6();
        });
        lbl_B5.setText(Translator.getWord("lbl_B"));
    }

    private void scene6() {
        showScene(pnl_6, pnl_6commands);

        lbl_pnl6Title.setFont(SanFranciscoDisplay_Semibold.deriveFont(18f));
        lbl_pnl6Title.setForeground(Color.decode("#414141"));
        lbl_pnl6Description.setFont(SanFranciscoDisplay_Regular.deriveFont(8.5f));
        lbl_pnl6Description.setForeground(Color.decode("#222222"));
        lbl_pnl6Path.setFont(SanFranciscoDisplay_Regular.deriveFont(7f));
        lbl_pnl6Path.setForeground(Color.decode("#222222"));
        txt_pnl6Path.setFont(SanFranciscoDisplay_Regular.deriveFont(7f));
        txt_pnl6Path.setForeground(Color.decode("#222222"));
        txt_pnl6Path.setHorizontalAlignment(JTextField.CENTER);
        btn_pnl6SelectPath.setFont(SanFranciscoDisplay_Regular.deriveFont(6.5f));
        btn_pnl6SelectPath.setForeground(Color.decode("#222222"));
        chbx_pnl6AutoSave.setFont(SanFranciscoDisplay_Regular.deriveFont(7f));
        chbx_pnl6AutoSave.setForeground(Color.decode("#222222"));
        lbl_A6.setFont(SanFranciscoDisplay_Medium.deriveFont(6f));
        lbl_A6.setForeground(Color.decode("#222222"));
        lbl_B6.setFont(SanFranciscoDisplay_Medium.deriveFont(6f));
        lbl_B6.setForeground(Color.decode("#222222"));

        img_pnl6Logo.setIcon(ImageResizer.getResizedImage((Dyslexo.class.getResource("/images/setup/folder.png")), 150, 150));
        lbl_pnl6Title.setText(Translator.getWord("lbl_pnl6Title"));
        lbl_pnl6Description.setText(Translator.getWord("lbl_pnl6Description"));
        lbl_pnl6Path.setText(Translator.getWord("lbl_pnl6Path"));
        chbx_pnl6AutoSave.setText(Translator.getWord("chkbx_autoSave"));
        btn_pnl6SelectPath.setText(Translator.getWord("approveButtonText"));
        btn_pnl6SelectPath.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.setApproveButtonText(Translator.getWord("approveButtonText"));
            if (fileChooser.showOpenDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                String path = fileChooser.getSelectedFile().toString();
                txt_pnl6Path.setText(path);
                isPathSelected[0] = true;
            } else {
                isPathSelected[0] = false;
                System.out.println("No Selection");
            }
        });

        btn_A6.setIcon(leftArrow);
        btn_A6.setBorder(null);
        btn_A6.setContentAreaFilled(false);
        btn_A6.setFocusPainted(false);
        btn_A6.addActionListener(e -> {
            scene5();
        });
        lbl_A6.setText(Translator.getWord("lbl_A"));
        btn_B6.setIcon(rightArrow);
        btn_B6.setBorder(null);
        btn_B6.setContentAreaFilled(false);
        btn_B6.setFocusPainted(false);
        btn_B6.addActionListener(e -> {
            if (isPathSelected[0])
                scene7();
            else {
                Component frame = null;
                JOptionPane.showMessageDialog(
                        frame,
                        Translator.getWord("pathNotSelectedMessage"),
                        Translator.getWord("pathNotSelectedTitle"),
                        JOptionPane.WARNING_MESSAGE,
                        ImageResizer.getResizedImage(Dyslexo.class.getResource("/images/setup/error.png"), 50, 50));
            }

        });
        lbl_B6.setText(Translator.getWord("lbl_B"));
    }

    private void scene7() {
        showScene(pnl_7, pnl_7commands);

        lbl_pnl7Title.setFont(SanFranciscoDisplay_Semibold.deriveFont(18f));
        lbl_pnl7Title.setForeground(Color.decode("#414141"));
        lbl_pnl7Description.setFont(SanFranciscoDisplay_Regular.deriveFont(8.5f));
        lbl_pnl7Description.setForeground(Color.decode("#222222"));
        cmb_pnl7Speech.setFont(SanFranciscoDisplay_Regular.deriveFont(7f));
        cmb_pnl7Speech.setForeground(Color.decode("#222222"));
        cmb_pnl7Language.setFont(SanFranciscoDisplay_Regular.deriveFont(7f));
        cmb_pnl7Language.setForeground(Color.decode("#222222"));
        lbl_pnl7Speech.setFont(SanFranciscoDisplay_Regular.deriveFont(7f));
        lbl_pnl7Speech.setForeground(Color.decode("#222222"));
        lbl_pnl7Language.setFont(SanFranciscoDisplay_Regular.deriveFont(7f));
        lbl_pnl7Language.setForeground(Color.decode("#222222"));
        lbl_pnl7Defaulten.setFont(SanFranciscoDisplay_Regular.deriveFont(7f));
        lbl_pnl7Defaulten.setForeground(Color.decode("#222222"));
        rdbtn_pnl7enUS.setFont(SanFranciscoDisplay_Regular.deriveFont(7f));
        rdbtn_pnl7enUS.setForeground(Color.decode("#222222"));
        rdbtn_pnl7enGB.setFont(SanFranciscoDisplay_Regular.deriveFont(7f));
        rdbtn_pnl7enGB.setForeground(Color.decode("#222222"));
        lbl_A7.setFont(SanFranciscoDisplay_Medium.deriveFont(6f));
        lbl_A7.setForeground(Color.decode("#222222"));
        lbl_B7.setFont(SanFranciscoDisplay_Medium.deriveFont(6f));
        lbl_B7.setForeground(Color.decode("#222222"));

        ButtonGroup btngr_rdbtnLanguage = new ButtonGroup();
        btngr_rdbtnLanguage.add(rdbtn_pnl7enUS);
        btngr_rdbtnLanguage.add(rdbtn_pnl7enGB);
        rdbtn_pnl7enUS.setSelected(true);

        lbl_pnl7Title.setText(Translator.getWord("btn_language"));
        lbl_pnl7Description.setText(Translator.getWord("lbl_pnl7Description"));
        lbl_pnl7Speech.setText(Translator.getWord("lbl_0"));
        lbl_pnl7Language.setText(Translator.getWord("lbl_1"));

        HashMap<String, Locale> supportedLanguages = Translator.getSupportedLocales();
        cmb_pnl7Speech.removeAllItems();
        cmb_pnl7Language.removeAllItems();
        for (Object temp : supportedLanguages.values()) {
            cmb_pnl7Speech.addItem(languages.get(String.valueOf(temp)));
            cmb_pnl7Language.addItem(languages.get(String.valueOf(temp)));
        }
        cmb_pnl7Speech.addItem(Translator.getWord("auto"));
        cmb_pnl7Language.addItem(Translator.getWord("auto"));

        cmb_pnl7Speech.setSelectedIndex(cmb_pnl7Speech.getItemCount() - 1);
        cmb_pnl7Language.setSelectedIndex(cmb_pnl7Language.getItemCount() - 1);

        lbl_pnl7Defaulten.setText(Translator.getWord("lbl_pnl7Defaulten"));
        rdbtn_pnl7enUS.setText(Translator.getWord("pnl_enUS"));
        rdbtn_pnl7enGB.setText(Translator.getWord("pnl_enGB"));

        btn_A7.setIcon(leftArrow);
        btn_A7.setBorder(null);
        btn_A7.setContentAreaFilled(false);
        btn_A7.setFocusPainted(false);
        btn_A7.addActionListener(e -> {
            scene6();
        });
        lbl_A7.setText(Translator.getWord("lbl_A"));
        btn_B7.setIcon(rightArrow);
        btn_B7.setBorder(null);
        btn_B7.setContentAreaFilled(false);
        btn_B7.setFocusPainted(false);
        btn_B7.addActionListener(e -> {
            scene8();
        });
        lbl_B7.setText(Translator.getWord("lbl_B"));
    }

    private void scene8() {
        showScene(pnl_8, pnl_8commands);

        lbl_pnl8Title.setFont(SanFranciscoDisplay_Semibold.deriveFont(18f));
        lbl_pnl8Title.setForeground(Color.decode("#414141"));
        lbl_pnl8Description.setFont(SanFranciscoDisplay_Regular.deriveFont(8.5f));
        lbl_pnl8Description.setForeground(Color.decode("#222222"));
        lbl_A8.setFont(SanFranciscoDisplay_Medium.deriveFont(6f));
        lbl_A8.setForeground(Color.decode("#222222"));
        lbl_B8.setFont(SanFranciscoDisplay_Medium.deriveFont(6f));
        lbl_B8.setForeground(Color.decode("#222222"));

        img_pnl8Logo.setIcon(ImageResizer.getResizedImage((Dyslexo.class.getResource("/images/setup/ok.png")), 300, 300));
        lbl_pnl8Title.setText(Translator.getWord("lbl_pnl8Title"));
        lbl_pnl8Description.setText(Translator.getWord("lbl_pnl8Description"));
        btn_A8.setIcon(leftArrow);
        btn_A8.setBorder(null);
        btn_A8.setContentAreaFilled(false);
        btn_A8.setFocusPainted(false);
        btn_A8.addActionListener(e -> {
            scene7();
        });
        lbl_A8.setText(Translator.getWord("lbl_A"));
        btn_B8.setIcon(rightArrow);
        btn_B8.setBorder(null);
        btn_B8.setContentAreaFilled(false);
        btn_B8.setFocusPainted(false);
        btn_B8.addActionListener(e -> {
            try {
                Settings.genSettingsFile(genSettingsDocument());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            try {
                Dyslexo.start(new File("settings.xml"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            frame.setVisible(false);
        });
        lbl_B8.setText(Translator.getWord("lbl_Bend"));
    }

    private Document genSettingsDocument() throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document document = docBuilder.newDocument();
        Element settings = document.createElement("settings");
        document.appendChild(settings);

        Element voices = document.createElement("voices");

        //en_US
        Element en_US = document.createElement("voice");
        en_US.setAttribute("locale", "en_US");

        Element en_US_default = document.createElement("default");
        en_US_default.appendChild(document.createTextNode(String.valueOf(rdbtn_pnl7enUS.isSelected())));
        en_US.appendChild(en_US_default);

        Element en_US_name = document.createElement("name");
        en_US_name.appendChild(document.createTextNode("cmu-rms-hsmm"));
        en_US.appendChild(en_US_name);

        Element en_US_speed = document.createElement("speed");
        en_US_speed.appendChild(document.createTextNode("3"));
        en_US.appendChild(en_US_speed);

        Element en_US_message = document.createElement("message");
        en_US_message.appendChild(document.createTextNode("\"Hi I am Dyslexo\""));
        en_US.appendChild(en_US_message);

        //en_GB
        Element en_GB = document.createElement("voice");
        en_GB.setAttribute("locale", "en_GB");

        Element en_GB_default = document.createElement("default");
        en_GB_default.appendChild(document.createTextNode(String.valueOf(rdbtn_pnl7enGB.isSelected())));
        en_GB.appendChild(en_GB_default);

        Element en_GB_name = document.createElement("name");
        en_GB_name.appendChild(document.createTextNode("dfki-spike-hsmm"));
        en_GB.appendChild(en_GB_name);

        Element en_GB_speed = document.createElement("speed");
        en_GB_speed.appendChild(document.createTextNode("3"));
        en_GB.appendChild(en_GB_speed);

        Element en_GB_message = document.createElement("message");
        en_GB_message.appendChild(document.createTextNode("\"Hi I am Dyslexo\""));
        en_GB.appendChild(en_GB_message);

        //it
        Element it = document.createElement("voice");
        it.setAttribute("locale", "it");

        Element it_name = document.createElement("name");
        it_name.appendChild(document.createTextNode("istc-lucia-hsmm"));
        it.appendChild(it_name);

        Element it_speed = document.createElement("speed");
        it_speed.appendChild(document.createTextNode("3"));
        it.appendChild(it_speed);

        Element it_message = document.createElement("message");
        it_message.appendChild(document.createTextNode("\"Ciao sono Dyslexo\""));
        it.appendChild(it_message);

        //fr
        Element fr = document.createElement("voice");
        fr.setAttribute("locale", "fr");

        Element fr_name = document.createElement("name");
        fr_name.appendChild(document.createTextNode("upmc-pierre-hsmm"));
        fr.appendChild(fr_name);

        Element fr_speed = document.createElement("speed");
        fr_speed.appendChild(document.createTextNode("3"));
        fr.appendChild(fr_speed);

        Element fr_message = document.createElement("message");
        fr_message.appendChild(document.createTextNode("\"Salut je suis ton Dyslexo\""));
        fr.appendChild(fr_message);

        //de
        Element de = document.createElement("voice");
        de.setAttribute("locale", "de");

        Element de_name = document.createElement("name");
        de_name.appendChild(document.createTextNode("bits1-hsmm"));
        de.appendChild(de_name);

        Element de_speed = document.createElement("speed");
        de_speed.appendChild(document.createTextNode("3"));
        de.appendChild(de_speed);

        Element de_message = document.createElement("message");
        de_message.appendChild(document.createTextNode("\"Hallo ich bin Dyslexo\""));
        de.appendChild(de_message);

        //append voices to voices node
        voices.appendChild(en_US);
        voices.appendChild(en_GB);
        voices.appendChild(it);
        voices.appendChild(fr);
        voices.appendChild(de);

        Element startup = document.createElement("startup");
        Element program = document.createElement("program");
        program.appendChild(document.createTextNode(String.valueOf(chbx_pnl4Startup.isSelected())));
        startup.appendChild(program);
        Element test = document.createElement("test");
        test.appendChild(document.createTextNode(String.valueOf(chbx_pnl4SoundTest.isSelected())));
        startup.appendChild(test);

        Element keys = document.createElement("keys");
        Element ekey0 = document.createElement("key0");
        ekey0.appendChild(document.createTextNode(String.valueOf(key0)));
        keys.appendChild(ekey0);
        Element ekey1 = document.createElement("key1");
        ekey1.appendChild(document.createTextNode(String.valueOf(key1)));
        keys.appendChild(ekey1);
        Element timeSave = document.createElement("timeSave");
        timeSave.appendChild(document.createTextNode("2500"));
        keys.appendChild(timeSave);

        Element language = document.createElement("language");
        Element speech = document.createElement("speech");
        speech.appendChild(document.createTextNode(String.valueOf(reversedHashMap.get(cmb_pnl7Speech.getSelectedItem()))));
        language.appendChild(speech);
        Element translator = document.createElement("translator");
        translator.appendChild(document.createTextNode(String.valueOf(reversedHashMap.get(cmb_pnl7Language.getSelectedItem()))));
        language.appendChild(translator);

        Element save = document.createElement("save");
        Element savePath = document.createElement("savePath");
        savePath.appendChild(document.createTextNode(txt_pnl6Path.getText()));
        save.appendChild(savePath);
        Element autoSave = document.createElement("autoSave");
        autoSave.appendChild(document.createTextNode(String.valueOf(chbx_pnl6AutoSave.isSelected())));
        save.appendChild(autoSave);
        Element format = document.createElement("format");
        format.appendChild(document.createTextNode("waw"));
        save.appendChild(format);

        //append tags to settings tag
        settings.appendChild(voices);
        settings.appendChild(startup);
        settings.appendChild(keys);
        settings.appendChild(language);
        settings.appendChild(save);

        return document;
    }


    private void showScene(JPanel scene, JPanel commands) {
        pnl_contents.removeAll();
        pnl_contents.add(scene);
        pnl_contents.repaint();
        pnl_contents.revalidate();
        pnl_commands.removeAll();
        pnl_commands.add(commands);
        pnl_commands.repaint();
        pnl_commands.revalidate();
    }

    public void show() {
        try {
            frame.setContentPane(new Setup().mainPanel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame.setTitle("Dyslexo - " + Translator.getWord("frameTitle"));
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        mainPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        pnl_contents = new JPanel();
        pnl_contents.setLayout(new CardLayout(0, 0));
        mainPanel.add(pnl_contents, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(800, 504), null, 0, false));
        pnl_1 = new JPanel();
        pnl_1.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        pnl_contents.add(pnl_1, "Card1");
        img_pnl1Logo = new JLabel();
        img_pnl1Logo.setText("");
        pnl_1.add(img_pnl1Logo, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbl_pnl1Title = new JLabel();
        lbl_pnl1Title.setText("Label");
        pnl_1.add(lbl_pnl1Title, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbl_pnl1Description = new JLabel();
        lbl_pnl1Description.setText("Label");
        pnl_1.add(lbl_pnl1Description, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnl_2 = new JPanel();
        pnl_2.setLayout(new GridLayoutManager(8, 3, new Insets(0, 0, 0, 0), -1, -1));
        pnl_contents.add(pnl_2, "Card2");
        img_pnl2Logo = new JLabel();
        img_pnl2Logo.setText("");
        pnl_2.add(img_pnl2Logo, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbl_pnl2Title = new JLabel();
        lbl_pnl2Title.setText("Label");
        pnl_2.add(lbl_pnl2Title, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbl_pnl2Description = new JLabel();
        lbl_pnl2Description.setText("Label");
        pnl_2.add(lbl_pnl2Description, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        pnl_2.add(scrollPane1, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        txt_license = new JTextPane();
        txt_license.setContentType("text/html");
        txt_license.setDragEnabled(false);
        txt_license.setEditable(false);
        txt_license.setMargin(new Insets(15, 50, 15, 50));
        txt_license.setPreferredSize(new Dimension(720, 150));
        scrollPane1.setViewportView(txt_license);
        final Spacer spacer1 = new Spacer();
        pnl_2.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 30), null, 0, false));
        final Spacer spacer2 = new Spacer();
        pnl_2.add(spacer2, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 10), null, 0, false));
        final Spacer spacer3 = new Spacer();
        pnl_2.add(spacer3, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(40, -1), null, 0, false));
        final Spacer spacer4 = new Spacer();
        pnl_2.add(spacer4, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(40, -1), null, 0, false));
        final Spacer spacer5 = new Spacer();
        pnl_2.add(spacer5, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        final Spacer spacer6 = new Spacer();
        pnl_2.add(spacer6, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        pnl_3 = new JPanel();
        pnl_3.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        pnl_contents.add(pnl_3, "Card3");
        img_pnl3Logo = new JLabel();
        img_pnl3Logo.setText("");
        pnl_3.add(img_pnl3Logo, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbl_pnl3Description = new JLabel();
        lbl_pnl3Description.setText("Label");
        pnl_3.add(lbl_pnl3Description, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbl_pnl3Title = new JLabel();
        lbl_pnl3Title.setText("Label");
        pnl_3.add(lbl_pnl3Title, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnl_4 = new JPanel();
        pnl_4.setLayout(new GridLayoutManager(6, 3, new Insets(0, 0, 0, 0), -1, -1));
        pnl_contents.add(pnl_4, "Card4");
        lbl_pnl4Title = new JLabel();
        lbl_pnl4Title.setText("Label");
        pnl_4.add(lbl_pnl4Title, new GridConstraints(0, 0, 2, 3, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        chbx_pnl4Startup = new JCheckBox();
        chbx_pnl4Startup.setText("CheckBox");
        pnl_4.add(chbx_pnl4Startup, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        chbx_pnl4SoundTest = new JCheckBox();
        chbx_pnl4SoundTest.setText("CheckBox");
        pnl_4.add(chbx_pnl4SoundTest, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbl_pnl4Description = new JLabel();
        lbl_pnl4Description.setText("Label");
        pnl_4.add(lbl_pnl4Description, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        img_pnl4Logo = new JLabel();
        img_pnl4Logo.setText("");
        pnl_4.add(img_pnl4Logo, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnl_5 = new JPanel();
        pnl_5.setLayout(new GridLayoutManager(7, 6, new Insets(0, 0, 0, 0), -1, -1));
        pnl_5.setToolTipText("");
        pnl_contents.add(pnl_5, "Card5");
        lbl_pnl5Title = new JLabel();
        lbl_pnl5Title.setText("Label");
        pnl_5.add(lbl_pnl5Title, new GridConstraints(0, 0, 2, 6, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbl_pnl5Description = new JLabel();
        lbl_pnl5Description.setText("Label");
        pnl_5.add(lbl_pnl5Description, new GridConstraints(2, 0, 1, 6, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btn_pnl5Key0 = new JButton();
        btn_pnl5Key0.setText("");
        pnl_5.add(btn_pnl5Key0, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 20), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("+");
        pnl_5.add(label1, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        pnl_5.add(spacer7, new GridConstraints(3, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        pnl_5.add(spacer8, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        pnl_5.add(spacer9, new GridConstraints(6, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btn_pnl5Key1 = new JButton();
        btn_pnl5Key1.setText("");
        pnl_5.add(btn_pnl5Key1, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        txt_functions = new JTextPane();
        txt_functions.setContentType("text/html");
        txt_functions.setDragEnabled(false);
        txt_functions.setEditable(false);
        txt_functions.setFocusable(true);
        pnl_5.add(txt_functions, new GridConstraints(5, 0, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(500, 80), null, 0, false));
        final Spacer spacer10 = new Spacer();
        pnl_5.add(spacer10, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 30), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        pnl_5.add(panel1, new GridConstraints(4, 0, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 50), null, 0, false));
        lbl_pnl5msg = new JLabel();
        lbl_pnl5msg.setText("Label");
        panel1.add(lbl_pnl5msg, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btn_pnl5Cancel = new JButton();
        btn_pnl5Cancel.setText("Button");
        panel1.add(btn_pnl5Cancel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer11 = new Spacer();
        panel1.add(spacer11, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer12 = new Spacer();
        panel1.add(spacer12, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        pnl_6 = new JPanel();
        pnl_6.setLayout(new GridLayoutManager(7, 4, new Insets(0, 0, 0, 0), -1, -1));
        pnl_contents.add(pnl_6, "Card6");
        lbl_pnl6Title = new JLabel();
        lbl_pnl6Title.setText("Label");
        pnl_6.add(lbl_pnl6Title, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbl_pnl6Description = new JLabel();
        lbl_pnl6Description.setText("Label");
        pnl_6.add(lbl_pnl6Description, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        img_pnl6Logo = new JLabel();
        img_pnl6Logo.setText("");
        pnl_6.add(img_pnl6Logo, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txt_pnl6Path = new JTextField();
        txt_pnl6Path.setEditable(false);
        pnl_6.add(txt_pnl6Path, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(350, -1), null, 0, false));
        chbx_pnl6AutoSave = new JCheckBox();
        chbx_pnl6AutoSave.setText("CheckBox");
        pnl_6.add(chbx_pnl6AutoSave, new GridConstraints(6, 1, 1, 2, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbl_pnl6Path = new JLabel();
        lbl_pnl6Path.setText("Label");
        pnl_6.add(lbl_pnl6Path, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btn_pnl6SelectPath = new JButton();
        btn_pnl6SelectPath.setText("Button");
        pnl_6.add(btn_pnl6SelectPath, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer13 = new Spacer();
        pnl_6.add(spacer13, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer14 = new Spacer();
        pnl_6.add(spacer14, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer15 = new Spacer();
        pnl_6.add(spacer15, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 25), null, 0, false));
        pnl_7 = new JPanel();
        pnl_7.setLayout(new GridLayoutManager(8, 1, new Insets(0, 0, 0, 0), -1, -1));
        pnl_contents.add(pnl_7, "Card7");
        lbl_pnl7Title = new JLabel();
        lbl_pnl7Title.setText("Label");
        pnl_7.add(lbl_pnl7Title, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbl_pnl7Description = new JLabel();
        lbl_pnl7Description.setText("Label");
        pnl_7.add(lbl_pnl7Description, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer16 = new Spacer();
        pnl_7.add(spacer16, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 25), null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(2, 5, new Insets(0, 0, 0, 0), -1, -1));
        pnl_7.add(panel2, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 50), null, 0, false));
        cmb_pnl7Speech = new JComboBox();
        panel2.add(cmb_pnl7Speech, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        cmb_pnl7Language = new JComboBox();
        panel2.add(cmb_pnl7Language, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        lbl_pnl7Speech = new JLabel();
        lbl_pnl7Speech.setText("Label");
        panel2.add(lbl_pnl7Speech, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbl_pnl7Language = new JLabel();
        lbl_pnl7Language.setText("Label");
        panel2.add(lbl_pnl7Language, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer17 = new Spacer();
        panel2.add(spacer17, new GridConstraints(0, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer18 = new Spacer();
        panel2.add(spacer18, new GridConstraints(0, 4, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer19 = new Spacer();
        panel2.add(spacer19, new GridConstraints(0, 2, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(35, -1), null, 0, false));
        final Spacer spacer20 = new Spacer();
        pnl_7.add(spacer20, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 40), null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        pnl_7.add(panel3, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbl_pnl7Defaulten = new JLabel();
        lbl_pnl7Defaulten.setText("Label");
        panel3.add(lbl_pnl7Defaulten, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rdbtn_pnl7enUS = new JRadioButton();
        rdbtn_pnl7enUS.setText("RadioButton");
        panel3.add(rdbtn_pnl7enUS, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rdbtn_pnl7enGB = new JRadioButton();
        rdbtn_pnl7enGB.setText("RadioButton");
        panel3.add(rdbtn_pnl7enGB, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer21 = new Spacer();
        pnl_7.add(spacer21, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 100), null, 0, false));
        final Spacer spacer22 = new Spacer();
        pnl_7.add(spacer22, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 30), null, 0, false));
        pnl_8 = new JPanel();
        pnl_8.setLayout(new GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        pnl_contents.add(pnl_8, "Card8");
        lbl_pnl8Title = new JLabel();
        lbl_pnl8Title.setText("Label");
        pnl_8.add(lbl_pnl8Title, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer23 = new Spacer();
        pnl_8.add(spacer23, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 25), null, 0, false));
        lbl_pnl8Description = new JLabel();
        lbl_pnl8Description.setText("Label");
        pnl_8.add(lbl_pnl8Description, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        img_pnl8Logo = new JLabel();
        img_pnl8Logo.setText("");
        pnl_8.add(img_pnl8Logo, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer24 = new Spacer();
        pnl_8.add(spacer24, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 25), null, null, 0, false));
        pnl_commands = new JPanel();
        pnl_commands.setLayout(new CardLayout(0, 0));
        mainPanel.add(pnl_commands, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(800, 100), null, 0, false));
        pnl_1commands = new JPanel();
        pnl_1commands.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        pnl_commands.add(pnl_1commands, "Card1");
        btn_A1 = new JButton();
        btn_A1.setText("");
        pnl_1commands.add(btn_A1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer25 = new Spacer();
        pnl_1commands.add(spacer25, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer26 = new Spacer();
        pnl_1commands.add(spacer26, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        lbl_A1 = new JLabel();
        lbl_A1.setText("Label");
        pnl_1commands.add(lbl_A1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer27 = new Spacer();
        pnl_1commands.add(spacer27, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 15), null, 0, false));
        pnl_3commands = new JPanel();
        pnl_3commands.setLayout(new GridLayoutManager(3, 5, new Insets(0, 0, 0, 0), -1, -1));
        pnl_commands.add(pnl_3commands, "Card3");
        btn_A3 = new JButton();
        btn_A3.setText("");
        pnl_3commands.add(btn_A3, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer28 = new Spacer();
        pnl_3commands.add(spacer28, new GridConstraints(2, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 15), null, 0, false));
        lbl_A3 = new JLabel();
        lbl_A3.setText("Label");
        pnl_3commands.add(lbl_A3, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer29 = new Spacer();
        pnl_3commands.add(spacer29, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer30 = new Spacer();
        pnl_3commands.add(spacer30, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        btn_B3 = new JButton();
        btn_B3.setText("");
        pnl_3commands.add(btn_B3, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbl_B3 = new JLabel();
        lbl_B3.setText("Label");
        pnl_3commands.add(lbl_B3, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer31 = new Spacer();
        pnl_3commands.add(spacer31, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(35, -1), null, 0, false));
        pnl_4commands = new JPanel();
        pnl_4commands.setLayout(new GridLayoutManager(3, 5, new Insets(0, 0, 0, 0), -1, -1));
        pnl_commands.add(pnl_4commands, "Card4");
        btn_A4 = new JButton();
        btn_A4.setText("");
        pnl_4commands.add(btn_A4, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer32 = new Spacer();
        pnl_4commands.add(spacer32, new GridConstraints(2, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 15), null, 0, false));
        lbl_A4 = new JLabel();
        lbl_A4.setText("Label");
        pnl_4commands.add(lbl_A4, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer33 = new Spacer();
        pnl_4commands.add(spacer33, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer34 = new Spacer();
        pnl_4commands.add(spacer34, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        btn_B4 = new JButton();
        btn_B4.setText("");
        pnl_4commands.add(btn_B4, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbl_B4 = new JLabel();
        lbl_B4.setText("Label");
        pnl_4commands.add(lbl_B4, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer35 = new Spacer();
        pnl_4commands.add(spacer35, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(35, -1), null, 0, false));
        pnl_5commands = new JPanel();
        pnl_5commands.setLayout(new GridLayoutManager(3, 5, new Insets(0, 0, 0, 0), -1, -1));
        pnl_commands.add(pnl_5commands, "Card5");
        btn_A5 = new JButton();
        btn_A5.setText("");
        pnl_5commands.add(btn_A5, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer36 = new Spacer();
        pnl_5commands.add(spacer36, new GridConstraints(2, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 15), null, 0, false));
        lbl_A5 = new JLabel();
        lbl_A5.setText("Label");
        pnl_5commands.add(lbl_A5, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btn_B5 = new JButton();
        btn_B5.setText("");
        pnl_5commands.add(btn_B5, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbl_B5 = new JLabel();
        lbl_B5.setText("Label");
        pnl_5commands.add(lbl_B5, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer37 = new Spacer();
        pnl_5commands.add(spacer37, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(35, -1), null, 0, false));
        final Spacer spacer38 = new Spacer();
        pnl_5commands.add(spacer38, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer39 = new Spacer();
        pnl_5commands.add(spacer39, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        pnl_6commands = new JPanel();
        pnl_6commands.setLayout(new GridLayoutManager(3, 5, new Insets(0, 0, 0, 0), -1, -1));
        pnl_commands.add(pnl_6commands, "Card6");
        btn_A6 = new JButton();
        btn_A6.setText("");
        pnl_6commands.add(btn_A6, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer40 = new Spacer();
        pnl_6commands.add(spacer40, new GridConstraints(2, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 15), null, 0, false));
        lbl_A6 = new JLabel();
        lbl_A6.setText("Label");
        pnl_6commands.add(lbl_A6, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btn_B6 = new JButton();
        btn_B6.setText("");
        pnl_6commands.add(btn_B6, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbl_B6 = new JLabel();
        lbl_B6.setText("Label");
        pnl_6commands.add(lbl_B6, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer41 = new Spacer();
        pnl_6commands.add(spacer41, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(35, -1), null, 0, false));
        final Spacer spacer42 = new Spacer();
        pnl_6commands.add(spacer42, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer43 = new Spacer();
        pnl_6commands.add(spacer43, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        pnl_7commands = new JPanel();
        pnl_7commands.setLayout(new GridLayoutManager(3, 5, new Insets(0, 0, 0, 0), -1, -1));
        pnl_commands.add(pnl_7commands, "Card7");
        btn_A7 = new JButton();
        btn_A7.setText("");
        pnl_7commands.add(btn_A7, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer44 = new Spacer();
        pnl_7commands.add(spacer44, new GridConstraints(2, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 15), null, 0, false));
        lbl_A7 = new JLabel();
        lbl_A7.setText("Label");
        pnl_7commands.add(lbl_A7, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btn_B7 = new JButton();
        btn_B7.setText("");
        pnl_7commands.add(btn_B7, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbl_B7 = new JLabel();
        lbl_B7.setText("Label");
        pnl_7commands.add(lbl_B7, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer45 = new Spacer();
        pnl_7commands.add(spacer45, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(35, -1), null, 0, false));
        final Spacer spacer46 = new Spacer();
        pnl_7commands.add(spacer46, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer47 = new Spacer();
        pnl_7commands.add(spacer47, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        pnl_2commands = new JPanel();
        pnl_2commands.setLayout(new GridLayoutManager(3, 5, new Insets(0, 0, 0, 0), -1, -1));
        pnl_commands.add(pnl_2commands, "Card2");
        btn_A2 = new JButton();
        btn_A2.setText("");
        pnl_2commands.add(btn_A2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer48 = new Spacer();
        pnl_2commands.add(spacer48, new GridConstraints(2, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 15), null, 0, false));
        lbl_A2 = new JLabel();
        lbl_A2.setText("Label");
        pnl_2commands.add(lbl_A2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btn_B2 = new JButton();
        btn_B2.setText("");
        pnl_2commands.add(btn_B2, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbl_B2 = new JLabel();
        lbl_B2.setText("Label");
        pnl_2commands.add(lbl_B2, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer49 = new Spacer();
        pnl_2commands.add(spacer49, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(35, -1), null, 0, false));
        final Spacer spacer50 = new Spacer();
        pnl_2commands.add(spacer50, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer51 = new Spacer();
        pnl_2commands.add(spacer51, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        pnl_8commands = new JPanel();
        pnl_8commands.setLayout(new GridLayoutManager(3, 5, new Insets(0, 0, 0, 0), -1, -1));
        pnl_commands.add(pnl_8commands, "Card8");
        btn_A8 = new JButton();
        btn_A8.setText("");
        pnl_8commands.add(btn_A8, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer52 = new Spacer();
        pnl_8commands.add(spacer52, new GridConstraints(2, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 15), null, 0, false));
        lbl_A8 = new JLabel();
        lbl_A8.setText("Label");
        pnl_8commands.add(lbl_A8, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btn_B8 = new JButton();
        btn_B8.setText("");
        pnl_8commands.add(btn_B8, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbl_B8 = new JLabel();
        lbl_B8.setText("Label");
        pnl_8commands.add(lbl_B8, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer53 = new Spacer();
        pnl_8commands.add(spacer53, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(35, -1), null, 0, false));
        final Spacer spacer54 = new Spacer();
        pnl_8commands.add(spacer54, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer55 = new Spacer();
        pnl_8commands.add(spacer55, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}