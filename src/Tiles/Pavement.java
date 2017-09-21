package Tiles;

public class Pavement extends Tile {
    private String toolTipString = "This is a pavement tile.";
    public Pavement(){
        super("./src/media/pavement.jpg");
        this.setToolTipString(toolTipString);
    }

    public Boolean getCollisable(){
        return false;
    }
}
