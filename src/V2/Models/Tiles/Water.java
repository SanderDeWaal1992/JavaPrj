package Tiles;

public class Water extends Tile {
    public static final Boolean collisable = true;

    public Water(int width, int height){
        super("./src/media/tree1.png", width, height);
        this.setToolTipString("This is water.");
    }

    public Boolean getCollisable(){
        return collisable;
    }
}
