package V2.Models.Tiles;

import V2.Models.Tiles.Tile;

public class Player extends Tile{
    Player(int width, int height){
        super("./src/media/player", "This is your player.", Tile.Index.PLAYERGROUND);
        this.setTilePart(new util.GridCoords(0,0), new Tile.TilePart(true));
        this.setTilePart(new util.GridCoords(0,-1), new Tile.TilePart(false));
        this.setSizeX(1);
        this.setSizeY(2);

    }
}
