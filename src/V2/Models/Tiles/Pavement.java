package V2.Models.Tiles;

public class Pavement extends Tile {
    public Pavement(){
        super("./src/media/pavement/", "This is a pavement tile.", Index.GROUND);
        this.setTilePart(new util.GridCoords(0,0), new TilePart(false));
        this.setSizeX(1);
        this.setSizeY(2);
    }
}
