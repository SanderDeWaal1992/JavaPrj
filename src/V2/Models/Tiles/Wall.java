package Tiles;

public class Wall extends Tile {
    public static final Boolean collisable = true;

    public Wall(int width, int height){
        super("./src/media/tree3.png", width, height);
        this.setToolTipString("This is a wall.");
    }
    //public void setToolTipString(String newToolTipString) {
    //    this.setToolTipText(newToolTipString);
    //}

    //public String getToolTipString() {
    //    return toolTipString;
    // }
    public Boolean getCollisable(){ return collisable; }
}
