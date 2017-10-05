package tiles.models;


import static tiles.models.Tile.Index.*;

public class TileFactory {
    private TileFactory(){}
    public static Tile getTile(String Tile, remaining.GridCoords coord) {
        tiles.models.Tile TileModel;
        remaining.GridCoords b = new remaining.GridCoords(0,0);;

        if (Tile.equalsIgnoreCase("Hause_1")) {
            //TileModel = new Models.tiles.Hause(coord);
            TileModel = new FixedTile( "This is a hause.", "./src/media/hause/",coord);
            //b = new remaining.GridCoords(0,0); TileModel.setTilePart(b, new TilePart(false, OVERLAY1, TileModel, b));
            /*b = new remaining.GridCoords(1,0); TileModel.setTilePart(b, new TilePart(false, OVERLAY1, TileModel, b));
            b = new remaining.GridCoords(2,0); TileModel.setTilePart(b, new TilePart(false, OVERLAY1, TileModel, b));
            b = new remaining.GridCoords(0,1); TileModel.setTilePart(b, new TilePart(true, GROUNDOVERLAY, TileModel, b));
            b = new remaining.GridCoords(1,1); TileModel.setTilePart(b, new TilePart(true, GROUNDOVERLAY, TileModel, b));
            b = new remaining.GridCoords(2,1); TileModel.setTilePart(b, new TilePart(true, GROUNDOVERLAY, TileModel, b));*/
            setTileParts(TileModel, 11, 3, 0, 6, GROUNDOVERLAY, true);
            setTileParts(TileModel, 11, 6, 0, 0, OVERLAY1, false);

            //TileModel.setSizeX(8);
            //TileModel.setSizeY(5);
        } else if (Tile.equalsIgnoreCase("Pavement_1")) {
            TileModel = new FixedTile( "This is a pavement tile.", "./src/media/pavement/", coord);
            b.setXY(0,0); TileModel.setTilePart(b, new TilePart(false, GROUND, TileModel, b));
            //TileModel.setSizeX(1);
            //TileModel.setSizeY(1);
            //TileModel = new Models.tiles.Pavement(coord);
        } else if (Tile.equalsIgnoreCase("Tree_1")) {
            //TileModel = new Models.tiles.Tree(coord);
            TileModel = new FixedTile( "This is a tree.","./src/media/tree/", coord);
            b.setXY(0,1); TileModel.setTilePart(b, new TilePart(true, GROUNDOVERLAY, TileModel, b));
            b.setXY(0,0); TileModel.setTilePart(b, new TilePart(false, OVERLAY1, TileModel, b));
            //TileModel.setSizeX(1);
            //TileModel.setSizeY(2);
        } else if (Tile.equalsIgnoreCase("Wall_1")) {
            TileModel = new FixedTile( "This is a wall.","./src/media/wall/", coord);
            b.setXY(0,1); TileModel.setTilePart(new remaining.GridCoords(0,1), new TilePart(true, GROUNDOVERLAY, TileModel, b));
            b.setXY(0,0); TileModel.setTilePart(new remaining.GridCoords(0,0), new TilePart(false, OVERLAY1, TileModel, b));
            //TileModel.setSizeX(1);
            //TileModel.setSizeY(2);
        } else if(Tile.equalsIgnoreCase("Human_1")) {
            TileModel = new MovableTile("This is a human.", "./src/media/human1/", coord);
            b.setXY(0,1); TileModel.setTilePart(b, new TilePart(true, PLAYERGROUND, TileModel, b));
            b.setXY(0,0); TileModel.setTilePart(b, new TilePart(false, PLAYERTOP, TileModel, b));
            //TileModel.setSizeX(1);
            //TileModel.setSizeY(2);
        } else
            return null;

        return TileModel;
    }

    private static void setTileParts(Tile TileModel, int x, int y, int xOffset, int yOffset, Tile.Index index, Boolean collidable){
        remaining.GridCoords b = new remaining.GridCoords(0,0);
        int bY = y;
        while(--x >= 0) {
            y = bY;
            while (--y >= 0) {
                b.setXY(x + xOffset, y + yOffset);
                TileModel.setTilePart(b, new TilePart(collidable, index, TileModel, b));
            }
        }
    }
}
