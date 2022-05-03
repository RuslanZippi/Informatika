package org.example.image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageConvertor {

    public static ImageIcon resizeImage(String fileName){
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = bufferedImage.getScaledInstance(100,100,Image.SCALE_DEFAULT);

        return new ImageIcon(image);
    }
}
