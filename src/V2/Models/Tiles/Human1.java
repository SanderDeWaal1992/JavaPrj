package V2.Models.Tiles;

public class Human1 extends MovableTile{
    public Human1(util.GridCoords coord){
        super("This is a human.","./src/media/human1/", coord);
        util.GridCoords b;
        b = new util.GridCoords(0,1); this.setTilePart(b, new Tile.TilePart(true, Tile.Index.PLAYERGROUND, this, b));
        b = new util.GridCoords(0,0); this.setTilePart(b, new Tile.TilePart(false, Index.PLAYERTOP, this, b));
        this.setSizeX(1);
        this.setSizeY(2);

    }
}
