package Tiles;

public class Pavement extends Tile {
    public static final Boolean collisable = false;

    public Pavement(int width, int height){
        super("./src/media/pavement.png", width, height);
        this.setToolTipString("This is a pavement tile.");
    }


    public Boolean getCollisable(){
        return collisable;
    }
}
