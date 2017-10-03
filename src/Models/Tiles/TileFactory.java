package Models.Tiles;


import static Models.Tiles.Tile.Index.*;

public class TileFactory {
    private TileFactory(){}
    public static Models.Tiles.Tile getTile(String Tile, util.GridCoords coord) {
        Models.Tiles.Tile TileModel;
        util.GridCoords b;

        if (Tile.equalsIgnoreCase("Hause")) {
            //TileModel = new Models.Tiles.Hause(coord);
            TileModel = new FixedTile( "This is a hause.", "./src/media/hause/",coord);
            b = new util.GridCoords(0,0); TileModel.setTilePart(b, new TilePart(false, OVERLAY1, TileModel, b));
            b = new util.GridCoords(1,0); TileModel.setTilePart(b, new TilePart(false, OVERLAY1, TileModel, b));
            b = new util.GridCoords(2,0); TileModel.setTilePart(b, new TilePart(false, OVERLAY1, TileModel, b));
            b = new util.GridCoords(0,1); TileModel.setTilePart(b, new TilePart(true, GROUNDOVERLAY, TileModel, b));
            b = new util.GridCoords(1,2); TileModel.setTilePart(b, new TilePart(true, GROUNDOVERLAY, TileModel, b));
            b = new util.GridCoords(2,3); TileModel.setTilePart(b, new TilePart(true, GROUNDOVERLAY, TileModel, b));
            TileModel.setSizeX(3);
            TileModel.setSizeY(2);
        } else if (Tile.equalsIgnoreCase("Pavement")) {
            TileModel = new FixedTile( "This is a pavement tile.", "./src/media/pavement/", coord);
            b = new util.GridCoords(0,0); TileModel.setTilePart(b, new TilePart(false, GROUND, TileModel, b));
            TileModel.setSizeX(1);
            TileModel.setSizeY(1);
            //TileModel = new Models.Tiles.Pavement(coord);
        } else if (Tile.equalsIgnoreCase("Tree")) {
            //TileModel = new Models.Tiles.Tree(coord);
            TileModel = new FixedTile( "This is a tree.","./src/media/tree/", coord);
            b = new util.GridCoords(0,1); TileModel.setTilePart(b, new TilePart(true, GROUNDOVERLAY, TileModel, b));
            b = new util.GridCoords(0,0); TileModel.setTilePart(b, new TilePart(false, OVERLAY1, TileModel, b));
            TileModel.setSizeX(1);
            TileModel.setSizeY(2);
        } else if (Tile.equalsIgnoreCase("Wall")) {
            TileModel = new FixedTile( "This is a wall.","./src/media/wall/", coord);
            b = new util.GridCoords(0,1); TileModel.setTilePart(new util.GridCoords(0,1), new TilePart(true, GROUNDOVERLAY, TileModel, b));
            b = new util.GridCoords(0,0); TileModel.setTilePart(new util.GridCoords(0,0), new TilePart(false, OVERLAY1, TileModel, b));
            TileModel.setSizeX(1);
            TileModel.setSizeY(2);
        } else if(Tile.equalsIgnoreCase("Human1")) {
            TileModel = new MovableTile("This is a human.", "./src/media/human1/", coord);
            b = new util.GridCoords(0,1); TileModel.setTilePart(b, new TilePart(true, PLAYERGROUND, TileModel, b));
            b = new util.GridCoords(0,0); TileModel.setTilePart(b, new TilePart(false, PLAYERTOP, TileModel, b));
            TileModel.setSizeX(1);
            TileModel.setSizeY(2);
        } else
            return null;

        return TileModel;
    }
}
