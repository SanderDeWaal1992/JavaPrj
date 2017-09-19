package Tiles;

public class Water extends Tile {
    public String toolTipString = "This is water.";

    public Water(){
        super("./src/media/tree1.png");
        this.setToolTipString(toolTipString);
    }
    public void setToolTipString(String newToolTipString) {
        this.setToolTipText(newToolTipString);
    }

    public String getToolTipString() {
        return toolTipString;
    }
}
