package Tiles;

public class Water extends Tile {
    public String toolTipString = "This is water.";

    public Water(){
        super("./src/media/tree1.png");
        this.setToolTipString(toolTipString);
    }

    public Boolean getCollisable(){
        return true;
    }
}
