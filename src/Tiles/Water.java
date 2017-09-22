package Tiles;

public class Water extends Tile {
    //public String toolTipString = "This is water.";

    public Water(int width, int height){
        super("./src/media/tree1.png", width, height);
        this.setToolTipString("This is water.");
    }

    public Boolean getCollisable(){
        return true;
    }
}
