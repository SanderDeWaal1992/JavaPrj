package Tiles.models;

import util.GridCoords;

import java.util.HashMap;
import java.util.Map;

public abstract class Tile{
    private String img = "default.png";
    private String imgDir = "";
    private String descriptionString = "";
    private int sizeX = 0;
    private int sizeY = 0;
    private util.GridCoords coord = new util.GridCoords(0,0);
    private Directions direction = Directions.DOWN;
    //private int index = 0;
    //private int bWidth=0, bHeight=0;
    //private Boolean collidable = true;
    private Map<util.GridCoords, TilePart> tileParts = new HashMap<util.GridCoords, TilePart>();

    public Tile (String imgDir, String descriptionString){
        this.imgDir = imgDir;
        this.descriptionString=descriptionString;
        //this.index = index;
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

    public Boolean getCollidableAtPos(GridCoords gridCoords){
        TilePart b;
        if(tileParts.containsKey(gridCoords)==false)//TODO: should never happen. Throw exception
            return false;
        b = tileParts.get(gridCoords);
        if(b==null) return false;
        else return b.getCollidable();
    }

    protected void setTilePart(util.GridCoords gridCoords, TilePart tilePart){
        if((gridCoords.getX()+1) > sizeX) setSizeX(gridCoords.getX()+1);
        if((gridCoords.getY()+1) > sizeY) setSizeY(gridCoords.getY()+1);
        tileParts.put(new util.GridCoords(gridCoords.getX(), gridCoords.getY()), tilePart);
    }
    public TilePart getTilePart(util.GridCoords gridCoords){
        return tileParts.get(gridCoords);
    }
    protected void setDescriptionString(String descriptionString) {
        this.descriptionString = descriptionString;
    }
    public String getDescriptionString() {
        return descriptionString;
    }
    protected void setImg(String img){
        this.img = img;
    }
    public String getImg(){
        return img;
    }
    protected void setImgDir(String imgDir){
        this.imgDir = imgDir;
    }
    public String getImgDir(){
        return imgDir;
    }
    public int getSizeX(){return sizeX;}
    public int getSizeY(){return sizeY;}
    protected void setSizeX(int sizeX){this.sizeX=sizeX;}
    protected void setSizeY(int sizeY){this.sizeY=sizeY;}
    public final util.GridCoords getCoord(){
        return coord;
    }
    public void setCoord(final util.GridCoords coord){
        this.coord.setX(coord.getX());
        this.coord.setY(coord.getY());
    }

    public enum Directions {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }
    public void setDirection(Directions direction){
        this.direction=direction;
    };
    public Directions getDirection(){return direction;}
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