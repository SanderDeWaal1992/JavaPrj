package V2.Models.Tiles;

public class Hause extends Tile {
    public Hause() {
        super("./src/media/hause/", "This is a hause.");
        util.GridCoords b;
        b = new util.GridCoords(0,0); this.setTilePart(b, new TilePart(false, Index.OVERLAY1, this, b));
        b = new util.GridCoords(1,0); this.setTilePart(b, new TilePart(false, Index.OVERLAY1, this, b));
        b = new util.GridCoords(2,0); this.setTilePart(b, new TilePart(false, Index.OVERLAY1, this, b));
        b = new util.GridCoords(0,1); this.setTilePart(b, new TilePart(true, Index.GROUNDOVERLAY, this, b));
        b = new util.GridCoords(1,2); this.setTilePart(b, new TilePart(true, Index.GROUNDOVERLAY, this, b));
        b = new util.GridCoords(2,3); this.setTilePart(b, new TilePart(true, Index.GROUNDOVERLAY, this, b));
        this.setSizeX(3);
        this.setSizeY(2);
    }
}
