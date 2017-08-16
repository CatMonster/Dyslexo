package io.dyslexo.components;

import io.dyslexo.system.Software;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * Created by stefano on 16/12/2016.
 */
public class ClipboardManager {

    /**
     * Given a String, the method put it into clipboard
     * @param text text to put into clipboard
     * @throws AWTException
     */
    public void set(String text) throws AWTException {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable transferable = new StringSelection(text);
        clipboard.setContents(transferable, null);
    }

    /**
     *
     * @return Returns a String of the current clipboard
     * @throws IOException
     * @throws UnsupportedFlavorException
     */
    public String get() throws IOException, UnsupportedFlavorException {
        String temp = "";
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        Transferable contents = clipboard.getContents(null);
        boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
        if (hasTransferableText) {
            try {
                temp = (String)contents.getTransferData(DataFlavor.stringFlavor);
            }
            catch (UnsupportedFlavorException | IOException e){
                e.printStackTrace();
            }
        }
        return temp;
    }

    /**
     * It simulate a copy key combination
     * @throws AWTException
     */
    public static void fakeCopyCombo() throws AWTException {
        int functionKey;
        if(Software.getOS().equals(Software.macOS))
            functionKey = KeyEvent.VK_META;
        else
            functionKey = KeyEvent.VK_CONTROL;

        Robot robot = new Robot();

        //Press copy keys
        robot.keyPress(functionKey);
        robot.keyPress(KeyEvent.VK_C);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Relase copy keys
        robot.keyRelease(KeyEvent.VK_C);
        robot.keyRelease(functionKey);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}