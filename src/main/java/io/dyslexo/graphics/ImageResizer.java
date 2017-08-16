package io.dyslexo.graphics;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by stefano on 16/02/2017.
 */
public class ImageResizer {
    public static ImageIcon getResizedImage(URL path, int Width, int Height) {
        ImageIcon tempIcon = new ImageIcon(path);
        Image tempImage = tempIcon.getImage();
        tempImage = tempImage.getScaledInstance(Width, Height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(tempImage);
    }
}
