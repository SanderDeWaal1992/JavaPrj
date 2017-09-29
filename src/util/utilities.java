package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class utilities {
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
    public static BufferedImage getImage(String location, int sizeX, int sizeY) throws java.io.FileNotFoundException {
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File(location));
        } catch (IOException e) {
            throw new java.io.FileNotFoundException();
        }
        return resize(myPicture, sizeX, sizeY);
    }
    //TODO: add singleton functionality to images
}
