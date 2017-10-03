package V2.Models.Tiles;

import V2.Models.Tiles.Tile;

public class Tree extends FixedTile {
    //public static final Boolean collisable = true;
    //private int sizeH, sizeV;

    public Tree(util.GridCoords coord){
        super( "This is a tree.","./src/media/tree/", coord);
        util.GridCoords b;
        b = new util.GridCoords(0,1); this.setTilePart(b, new TilePart(true, Index.GROUNDOVERLAY, this, b));
        b = new util.GridCoords(0,0); this.setTilePart(b, new TilePart(false, Index.OVERLAY1, this, b));
        this.setSizeX(1);
        this.setSizeY(2);
    }

    /*
    private int state = 0;
    public void executeTasks(){
        switch(state){
            case 0:

                break;
            case 1:

                break;
            default:

                state = 0;
                break;
        }
    }*/
    //public void setToolTipString(String newToolTipString) {
    //    this.setToolTipText(newToolTipString);
    //}

    //public Boolean getCollisable(){
    //    return collisable;
    //}

    //public Tree clone(){
    //    return new Tree(sizeH,sizeV);
    //}
}
