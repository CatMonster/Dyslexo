package io.dyslexo.graphics;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import io.dyslexo.Dyslexo;
import io.dyslexo.components.Translator;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.io.IOException;
import java.net.URI;

/**
 * Created by stefano on 29/04/2017.
 */
public class About {
    private JPanel mainPanel;
    private JLabel img_logo;
    private JLabel lbl_title;
    private JLabel lbl_version;
    private JTextPane txt_credits;
    private JTextPane txt_translators;
    private JTextPane txt_developers;
    private JLabel lbl_developers;
    private JLabel lbl_credits;
    private JLabel lbl_translators;

    public About() throws IOException, FontFormatException {
        Font SanFranciscoDisplay_Regular = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/fonts/SanFranciscoDisplay-Regular.otf").openStream());
        Font SanFranciscoDisplay_Semibold = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/fonts/SanFranciscoDisplay-Semibold.otf").openStream());

        String txtPanefont = "body { font-family: " + SanFranciscoDisplay_Regular.getFamily() + "; font-size: " + SanFranciscoDisplay_Regular.deriveFont(13f).getSize() + "pt; }";

        lbl_title.setFont(SanFranciscoDisplay_Semibold.deriveFont(24f));
        lbl_title.setForeground(Color.decode("#414141"));
        lbl_version.setFont(SanFranciscoDisplay_Regular.deriveFont(7.6f));
        lbl_version.setForeground(Color.decode("#858585"));
        lbl_developers.setFont(SanFranciscoDisplay_Semibold.deriveFont(9f));
        lbl_credits.setFont(SanFranciscoDisplay_Semibold.deriveFont(9f));
        lbl_translators.setFont(SanFranciscoDisplay_Semibold.deriveFont(9f));
        txt_translators.setFont(SanFranciscoDisplay_Regular.deriveFont(13f));

        txt_developers.setOpaque(false);
        txt_credits.setOpaque(false);
        txt_translators.setOpaque(false);

        img_logo.setIcon(ImageResizer.getResizedImage((Dyslexo.class.getResource("/images/tray/icon.png")), 135, 135));
        lbl_title.setText("Dyslexo");
        lbl_version.setText(Translator.getWord("lbl_version") + " 0.2B");
        lbl_developers.setText(Translator.getWord("lbl_developers"));
        txt_developers.setText("Stefano Castiglia - CatMonster " + Translator.getWord("lbl_developers_prep") + " <a href=\"https://github.com/CatMonster\">GitHub");
        txt_developers.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                String url = e.getURL().toString();
                try {
                    Desktop.getDesktop().browse(URI.create(url));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        ((HTMLDocument) txt_developers.getDocument()).getStyleSheet().addRule(txtPanefont);
        lbl_credits.setText(Translator.getWord("lbl_credits"));
        txt_credits.setText(
                "<a href=\"http://mary.dfki.de/\">MaryTTS</a><br>" +
                        "<a href=\"https://github.com/optimaize/language-detector\">Optimaize Language Detector</a><br>" +
                        "<a href=\"https://github.com/kwhat/jnativehook \">JNativeHook</a><br>" +
                        "<a href=\"https://commons.apache.org/\">Apache commmons</a><br><br>" +
                        Translator.getWord("lbl_credits_sub") +
                        "<br><a href=\"http://www.flaticon.com/authors/popcorns-arts\">Icon Pond</a><br>" +
                        "<a href=\"http://www.flaticon.com/authors/roundicons\">Roundicons</a><br>" +
                        "<a href=\"http://www.flaticon.com/authors/flat-icons\">Flat Icons</a><br>" +
                        "<a href=\"http://www.flaticon.com/authors/vectors-market\">Vectors Market</a>"
        );
        txt_credits.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                String url = e.getURL().toString();
                try {
                    Desktop.getDesktop().browse(URI.create(url));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        ((HTMLDocument) txt_credits.getDocument()).getStyleSheet().addRule(txtPanefont);
        lbl_translators.setText(Translator.getWord("lbl_translators"));
        txt_translators.setText(
                "<p>" +
                        Translator.getWord("english") + " - Stefano Castiglia<br>" +
                        Translator.getWord("pnl_fr") + " - Anna Giacalone<br>" +
                        Translator.getWord("pnl_de") + " - Anna Giacalone<br>" +
                        Translator.getWord("pnl_it") + " - Stefano Castiglia" +
                        "</p>");
        ((HTMLDocument) txt_translators.getDocument()).getStyleSheet().addRule("body { font-family: " + SanFranciscoDisplay_Regular.getFamily() + "; font-size: " + SanFranciscoDisplay_Regular.deriveFont(7.5f).getSize() + "pt; }");
    }

    public void show() {
        JFrame frame = new JFrame();
        try {
            frame.setContentPane(new About().mainPanel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(600, 650));
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.toFront();
        frame.setVisible(true);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Dyslexo.class.getResource("/images/tray/icon.png")));
        frame.setTitle("Dyslexo - " + Translator.getWord("btn_about"));
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
        mainPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, -1), null, 0, false));
        img_logo = new JLabel();
        img_logo.setText("");
        panel1.add(img_logo, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(30, -1), null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel1.add(spacer4, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(13, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(400, -1), null, 0, false));
        lbl_title = new JLabel();
        lbl_title.setText("Label");
        panel2.add(lbl_title, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel2.add(spacer5, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        panel2.add(spacer6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 90), null, 0, false));
        lbl_version = new JLabel();
        lbl_version.setText("Label");
        panel2.add(lbl_version, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbl_developers = new JLabel();
        lbl_developers.setText("Label");
        panel2.add(lbl_developers, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_SOUTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        panel2.add(spacer7, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        final Spacer spacer8 = new Spacer();
        panel2.add(spacer8, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 25), null, 0, false));
        lbl_credits = new JLabel();
        lbl_credits.setText("Label");
        panel2.add(lbl_credits, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_SOUTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txt_credits = new JTextPane();
        txt_credits.setContentType("text/html");
        txt_credits.setEditable(false);
        txt_credits.setFocusable(false);
        panel2.add(txt_credits, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 50), null, 0, false));
        lbl_translators = new JLabel();
        lbl_translators.setText("Label");
        panel2.add(lbl_translators, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_SOUTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        panel2.add(spacer9, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 25), null, 0, false));
        txt_developers = new JTextPane();
        txt_developers.setContentType("text/html");
        txt_developers.setEditable(false);
        txt_developers.setFocusable(false);
        panel2.add(txt_developers, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 20), null, 0, false));
        txt_translators = new JTextPane();
        txt_translators.setContentType("text/html");
        txt_translators.setEditable(false);
        txt_translators.setFocusable(false);
        panel2.add(txt_translators, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 50), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
