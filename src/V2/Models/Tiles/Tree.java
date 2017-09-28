package Tiles;

public class Tree extends Tile {
    public static final Boolean collisable = true;
    private int sizeH, sizeV;

    public Tree(int width, int height){
        super("./src/media/tree2.png", width, height);

        this.setToolTipString("This is a tree.");

    }
    //public void setToolTipString(String newToolTipString) {
    //    this.setToolTipText(newToolTipString);
    //}

    public Boolean getCollisable(){
        return collisable;
    }

    //public Tree clone(){
    //    return new Tree(sizeH,sizeV);
    //}
}
