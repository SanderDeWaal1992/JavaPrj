package V2.Models.Tiles;

public class Pavement extends Tile {
    public Pavement(){
        super("./src/media/pavement/", "This is a pavement tile.");
        util.GridCoords b;
        b = new util.GridCoords(0,0); this.setTilePart(b, new TilePart(false,Index.GROUND, this, b));
        this.setSizeX(1);
        this.setSizeY(1);
    }
}
