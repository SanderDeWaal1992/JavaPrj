package V2.Models.Tiles;

public class Hause extends Tile {
    public Hause() {
        super("./src/media/hause/", "This is a hause.");
        this.setTilePart(new util.GridCoords(0, 0), new TilePart(false, Index.OVERLAY1));
        this.setTilePart(new util.GridCoords(1, 0), new TilePart(false, Index.OVERLAY1));
        this.setTilePart(new util.GridCoords(2, 0), new TilePart(false, Index.OVERLAY1));
        this.setTilePart(new util.GridCoords(0, 1), new TilePart(true, Index.GROUNDOVERLAY));
        this.setTilePart(new util.GridCoords(1, 1), new TilePart(true, Index.GROUNDOVERLAY));
        this.setTilePart(new util.GridCoords(2, 1), new TilePart(true, Index.GROUNDOVERLAY));
        this.setSizeX(3);
        this.setSizeY(2);
    }
}
