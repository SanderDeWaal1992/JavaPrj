package V2.Models.Tiles;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.prism.image.Coords;
import util.GridCoords;

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
import java.util.HashMap;
import java.util.Map;

public abstract class Tile{
    private String img = "default.png";
    private String imgDir = "";
    private String descriptionString = "";
    private int sizeX = 0;
    private int sizeY = 0;

    //private int index = 0;
    //private int bWidth=0, bHeight=0;
    //private Boolean collidable = true;
    private Map<util.GridCoords, TilePart> tileParts = new HashMap<util.GridCoords, TilePart>();

    public Tile (String imgDir, String descriptionString){
        this.imgDir = imgDir;
        this.descriptionString=descriptionString;
        //this.index = index;
    }
    public enum Directions {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }
    public enum Index {
        GROUND(0),
        GROUNDOVERLAY(1),
        PLAYERGROUND(2),
        PLAYERTOP(3),
        OVERLAY1(4), OVERLAY2(5), OVERLAY3(6);
        private static int maxValue = 6; //Should be manually set
        private final int value;
        private Index(int value){this.value=value; }
        public int getValue(){return value;}
        public static int getMaxValue(){return maxValue;}
    }

    public class TilePart{
        private Boolean collidable = true;
        private Index index = Index.GROUND;

        public TilePart(Boolean collidable, Index index){
            setCollidable(collidable);
            setIndex(index);
        }
        public Boolean getCollidable() {
            return collidable;
        };
        public void setCollidable(Boolean collidable) {
            this.collidable=collidable;
        };

        public void setIndex(Index index){
            this.index=index;
        }
        public Index getIndex(){
            return this.index;
        }

    }
    public Boolean getCollidableAtPos(GridCoords gridCoords){
        TilePart b;
        if(tileParts.containsKey(gridCoords)==false)//TODO: should never happen. Throw exception
            return false;
        b = tileParts.get(gridCoords);
        if(b==null) return false;
        else return b.getCollidable();
    }

    protected void setTilePart(util.GridCoords gridCoords, TilePart tilePart){
        tileParts.put(gridCoords, tilePart);
    }
    protected TilePart getTilePart(util.GridCoords gridCoords){
        return tileParts.get(gridCoords);
    }
    public void setDescriptionString(String descriptionString) {
        this.descriptionString = descriptionString;
    }
    public String getDescriptionString() {
        return descriptionString;
    }
    public void setImg(String img){
        this.img = img;
    }
    public String getImg(){
        return img;
    }
    public void setImgDir(String imgDir){
        this.imgDir = imgDir;
    }
    public String getImgDir(){
        return imgDir;
    }
    public int getSizeX(){return sizeX;}
    public int getSizeY(){return sizeY;}
    public void setSizeX(int sizeX){this.sizeX=sizeX;}
    public void setSizeY(int sizeY){this.sizeY=sizeY;}
}


/*public static BufferedImage resize(BufferedImage img, int newW, int newH) {
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
    }*/