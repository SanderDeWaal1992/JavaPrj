package V2.Models.Tiles;

//import V2.Models.Tiles.Tile;

public class Wall extends Tile {

    public Wall(){
        super("./src/media/wall/", "This is a wall.");
        this.setTilePart(new util.GridCoords(0,1), new TilePart(true, Index.GROUNDOVERLAY));
        this.setTilePart(new util.GridCoords(0,0), new TilePart(false, Index.OVERLAY1));
        this.setSizeX(1);
        this.setSizeY(2);
    }
}
