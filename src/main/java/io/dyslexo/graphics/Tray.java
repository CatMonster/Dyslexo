package io.dyslexo.graphics;

import com.apple.eawt.Application;
import io.dyslexo.Dyslexo;
import io.dyslexo.components.Translator;
import io.dyslexo.system.Software;
import marytts.exceptions.MaryConfigurationException;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by stefano on 17/12/2016.
 */
public class Tray {
    private Image image;

    public Tray() throws AWTException {
        this.image = Toolkit.getDefaultToolkit().getImage(Dyslexo.class.getResource("/images/tray/icon.png"));
    }

    public void show() throws AWTException, UnsupportedEncodingException {
        if(Software.getOS().equals(Software.macOS)){
            Application application = Application.getApplication();
            PopupMenu popupMenu = new PopupMenu();
            MenuItem mnItm_settings = new MenuItem(Translator.getWord("mnItm_settings"));
            mnItm_settings.addActionListener(e -> {
                try {
                    getGUIsettings().show();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
            popupMenu.add(mnItm_settings);

            application.setDockIconImage(image);
            application.setDockMenu(popupMenu);
            application.setAboutHandler(e -> {
                try {
                    getAbout().show();
                } catch (IOException | FontFormatException e1) {
                    e1.printStackTrace();
                }
            });
        }
        else if(Software.getOS().equals(Software.windows) || Software.getOS().equals(Software.linux) && SystemTray.isSupported()) {
            TrayIcon trayIcon = new TrayIcon(image);
            trayIcon.setImageAutoSize(true);
            trayIcon.setToolTip(Translator.getWord("tooltipText"));

            PopupMenu popupMenu = new PopupMenu();

            MenuItem mnItm_quit = new MenuItem(Translator.getWord("mnItm_quit"));
            MenuItem mnItm_settings = new MenuItem(Translator.getWord("mnItm_settings"));
            MenuItem mnItm_about = new MenuItem(Translator.getWord("btn_about"));

            mnItm_quit.addActionListener(e -> System.exit(0));
            mnItm_settings.addActionListener(e -> {
                try {
                    getGUIsettings().show();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            });

            mnItm_about.addActionListener(e -> {
                try {
                    getAbout().show();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            });

            popupMenu.add(mnItm_quit);
            popupMenu.add(mnItm_settings);
            popupMenu.add(mnItm_about);

            trayIcon.setPopupMenu(popupMenu);

            SystemTray systemTray = SystemTray.getSystemTray();
            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
        else {
            Component frame = null;
            JOptionPane.showMessageDialog(
                    frame,
                    Translator.getWord("JoptionpaneMessage"),
                    Translator.getWord("fatalError"), JOptionPane.ERROR_MESSAGE,
                    ImageResizer.getResizedImage(Dyslexo.class.getResource("/images/tray/icon.png"), 50, 50));
            System.exit(0);
        }
    }

    private Settings getGUIsettings() throws UnsupportedEncodingException, MaryConfigurationException, LineUnavailableException {
        return new Settings();
    }

    private About getAbout() throws IOException, FontFormatException {
        return new About();
    }
}
