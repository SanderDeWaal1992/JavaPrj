package Factories.Tiles;

import Wrappers.Tiles.FixedTile;
import Wrappers.Tiles.MovableTile;

public class FixedTileFactory extends AbstractFactory {
    public Wrappers.Tiles.Tile getMovableTile(String Tile, util.GridCoords coord, Controllers.Map mapController, Models.MapGridInf mapGridInf) {
        return null;
    }

    public Wrappers.Tiles.Tile getFixedTile(String Tile, util.GridCoords coord, Controllers.Map mapController, Models.MapGridInf mapGridInf) {
        Models.Tiles.FixedTile FixedTileModel;
        Wrappers.Tiles.Tile tileWrapper;

        if (Tile.equalsIgnoreCase("Hause")) {
            FixedTileModel = new Models.Tiles.Hause(coord);
        } else if (Tile.equalsIgnoreCase("Pavement")) {
            FixedTileModel = new Models.Tiles.Pavement(coord);
        } else if (Tile.equalsIgnoreCase("Tree")) {
            FixedTileModel = new Models.Tiles.Tree(coord);
        } else if (Tile.equalsIgnoreCase("Wall")) {
            FixedTileModel = new Models.Tiles.Wall(coord);
        } else
            return null;

        tileWrapper = new Wrappers.Tiles.Tile();
        tileWrapper.setModel(FixedTileModel);
        tileWrapper.setController(new Controllers.Tiles.FixedTile(tileWrapper, mapController, mapGridInf));

        return tileWrapper;
    }
}
