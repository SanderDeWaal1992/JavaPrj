package V2.Models.Tiles;

public class Human1 extends Tile{
    public Human1(){
        super("./src/media/human1/", "This is a human.");
        this.setTilePart(new util.GridCoords(0,1), new Tile.TilePart(true, Tile.Index.PLAYERGROUND));
        this.setTilePart(new util.GridCoords(0,0), new Tile.TilePart(false, Index.PLAYERTOP));
        this.setSizeX(1);
        this.setSizeY(2);

    }
}
