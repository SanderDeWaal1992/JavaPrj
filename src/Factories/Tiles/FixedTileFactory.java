package Factories.Tiles;

public class FixedTileFactory extends AbstractFactory {
    public Remaining.Tiles.Tile getMovableTile(String Tile, util.GridCoords coord, Controllers.Map mapController, Models.MapGridInf mapGridInf) {
        return null;
    }

    public Remaining.Tiles.Tile getFixedTile(String tile, util.GridCoords coord, Controllers.Map mapController, Models.MapGridInf mapGridInf) {
        Models.Tiles.Tile FixedTileModel;
        Remaining.Tiles.Tile tileWrapper;

        FixedTileModel = Models.Tiles.TileFactory.getTile(tile, coord);
        if(FixedTileModel == null) return null;
        /*if (Tile.equalsIgnoreCase("Hause")) {
            FixedTileModel = new Models.Tiles.Hause(coord);
        } else if (Tile.equalsIgnoreCase("Pavement")) {
            FixedTileModel = new Models.Tiles.Pavement(coord);
        } else if (Tile.equalsIgnoreCase("Tree")) {
            FixedTileModel = new Models.Tiles.Tree(coord);
        } else if (Tile.equalsIgnoreCase("Wall")) {
            FixedTileModel = new Models.Tiles.Wall(coord);
        } else
            return null;*/

        tileWrapper = new Remaining.Tiles.Tile();
        tileWrapper.setModel(FixedTileModel);
        tileWrapper.setController(new Controllers.Tiles.FixedTile(tileWrapper, mapController, mapGridInf));

        return tileWrapper;
    }
}
