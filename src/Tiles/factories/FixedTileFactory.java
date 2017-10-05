package Tiles.factories;

import Tiles.controllers.FixedTile;
import Tiles.models.Tile;
import Tiles.models.TileFactory;
import map.controllers.Map;
import map.views.MapGridInf;

public class FixedTileFactory extends AbstractFactory {
    public Tiles.mcWrapper.Tile getMovableTile(String Tile, util.GridCoords coord, Map mapController, MapGridInf mapGridInf) {
        return null;
    }

    public Tiles.mcWrapper.Tile getFixedTile(String tile, util.GridCoords coord, Map mapController, MapGridInf mapGridInf) {
        Tile FixedTileModel;
        Tiles.mcWrapper.Tile tileWrapper;

        FixedTileModel = TileFactory.getTile(tile, coord);
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

        tileWrapper = new Tiles.mcWrapper.Tile();
        tileWrapper.setModel(FixedTileModel);
        tileWrapper.setController(new FixedTile(tileWrapper, mapController, mapGridInf));

        return tileWrapper;
    }
}
