package V2.Models.Tiles;

public class Pavement extends FixedTile {
    public Pavement(util.GridCoords coord){
        super( "This is a pavement tile.", "./src/media/pavement/", coord);
        util.GridCoords b;
        b = new util.GridCoords(0,0); this.setTilePart(b, new TilePart(false,Index.GROUND, this, b));
        this.setSizeX(1);
        this.setSizeY(1);
    }
}
