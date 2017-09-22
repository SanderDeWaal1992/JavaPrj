package Tiles;

import com.sun.prism.image.Coords;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.Position;
import javax.xml.stream.Location;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Tile extends JLabel {
    private String imgLoc = "";
    private String toolTipString = "";
    private int index = 0;

    public Tile (String _imgLoc, int width, int height){
        super();
        imgLoc = _imgLoc;
        //this.setPreferredSize(new Dimension(width, height));
    }
    public void setSize(int width, int height){
        this.setPreferredSize(new Dimension(width, height));
        this.setMinimumSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(this.getWidth()!=0/* && this.getIcon()==null*/)
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
    public abstract Boolean getCollisable();

    public void setToolTipString(String newToolTipString) {
        toolTipString = newToolTipString;
        this.setToolTipText(newToolTipString);
    }
    public String getToolTipString() {
        return toolTipString;
    }
    protected void setIndex(int index){
        this.index=index;
    }
    public int getIndex(){
        return this.index;
    }
    public void setImage(String _imgLoc){
        imgLoc = _imgLoc;
    }
}
