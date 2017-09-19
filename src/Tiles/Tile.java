package Tiles;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Tile extends JLabel {
    String imgLoc = "";
    public Tile (String _imgLoc){
        super();
        imgLoc = _imgLoc;
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(this.getWidth()!=0 && this.getIcon()==null)
            this.setIcon(new ImageIcon(getImage(imgLoc, this.getWidth(), this.getHeight())));
    }
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
    public static BufferedImage getImage(String location, int sizeX, int sizeY) {
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File(location));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resize(myPicture, sizeX, sizeY);
    }
    //public Tile clone(){
    //}
}
