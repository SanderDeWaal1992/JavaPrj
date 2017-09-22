package Tiles;

public class Tree extends Tile {
    //private String toolTipString = "This is a tree.";
    private int sizeH, sizeV;
    public Tree(int width, int height){
        super("./src/media/tree2.jpg", width, height);

        this.setToolTipString("This is a tree.");

    }
    //public void setToolTipString(String newToolTipString) {
    //    this.setToolTipText(newToolTipString);
    //}

    public Boolean getCollisable(){
        return true;
    }

    //public Tree clone(){
    //    return new Tree(sizeH,sizeV);
    //}
}
