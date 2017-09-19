package Tiles;

public class Tree extends Tile {
    private String toolTipString = "This is a tree.";
    private int sizeH, sizeV;
    public Tree(){
        super("./src/media/tree2.jpg");

        this.setToolTipString(toolTipString);

    }
    public void setToolTipString(String newToolTipString) {
        this.setToolTipText(newToolTipString);
    }

    public String getToolTipString() {
        return toolTipString;
    }
    //public Tree clone(){
    //    return new Tree(sizeH,sizeV);
    //}
}
