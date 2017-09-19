package Tiles;

public class Wall extends Tile {
    public String toolTipString = "This is a wall.";

    public Wall(){
        super("./src/media/tree3.jpg");
        this.setToolTipString(toolTipString);
    }
    public void setToolTipString(String newToolTipString) {
        this.setToolTipText(newToolTipString);
    }

    public String getToolTipString() {
        return toolTipString;
    }
}
