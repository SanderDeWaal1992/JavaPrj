package tiles.factories;

import tiles.controllers.FixedTile;
import tiles.models.Tile;
import tiles.models.TileFactory;
import map.controllers.Map;
import map.models.MapGridInf;

public class FixedTileFactory extends AbstractFactory {
    public tiles.mcWrapper.Tile getMovableTile(String Tile, remaining.GridCoords coord, Map mapController, MapGridInf mapGridInf) {
        return null;
    }

    public tiles.mcWrapper.Tile getFixedTile(String tile, remaining.GridCoords coord, Map mapController, MapGridInf mapGridInf) {
        Tile FixedTileModel;
        tiles.mcWrapper.Tile tileWrapper;

        FixedTileModel = TileFactory.getTile(tile, coord);
        if(FixedTileModel == null) return null;
        /*if (Tile.equalsIgnoreCase("Hause")) {
            FixedTileModel = new Models.tiles.Hause(coord);
        } else if (Tile.equalsIgnoreCase("Pavement")) {
            FixedTileModel = new Models.tiles.Pavement(coord);
        } else if (Tile.equalsIgnoreCase("Tree")) {
            FixedTileModel = new Models.tiles.Tree(coord);
        } else if (Tile.equalsIgnoreCase("Wall")) {
            FixedTileModel = new Models.tiles.Wall(coord);
        } else
            return null;*/

        tileWrapper = new tiles.mcWrapper.Tile();
        tileWrapper.setModel(FixedTileModel);
        tileWrapper.setController(new FixedTile(tileWrapper, mapController, mapGridInf));

        return tileWrapper;
    }
}
