package V2.Models.Tiles;

//import V2.Models.Tiles.Tile;

public class Wall extends Tile {

    public Wall(){
        super("./src/media/wall/", "This is a wall.");
        util.GridCoords b;
         b = new util.GridCoords(0,1); this.setTilePart(new util.GridCoords(0,1), new TilePart(true, Index.GROUNDOVERLAY, this, b));
         b = new util.GridCoords(0,0); this.setTilePart(new util.GridCoords(0,0), new TilePart(false, Index.OVERLAY1, this, b));
        this.setSizeX(1);
        this.setSizeY(2);
    }
}
