package Tiles;

public class Pavement extends Tile {
    //private String toolTipString = "This is a pavement tile.";
    public Pavement(int width, int height){
        super("./src/media/pavement.png", width, height);
        this.setToolTipString("This is a pavement tile.");
    }

    public Boolean getCollisable(){
        return false;
    }
}
