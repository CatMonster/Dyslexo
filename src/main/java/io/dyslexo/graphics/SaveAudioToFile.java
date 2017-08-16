package io.dyslexo.graphics;

import io.dyslexo.components.Translator;

import javax.swing.*;

/**
 * Created by stefano on 07/02/2017.
 */
public class SaveAudioToFile {

    public String save() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(Translator.getWord("filechooserTitle"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
            return fileChooser.getSelectedFile().toString();
        else
            return null;
    }
}