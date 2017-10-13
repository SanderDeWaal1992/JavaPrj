package tiles.factories;

import tiles.controllers.MovableTile;
import tiles.models.Tile;
import map.controllers.Map;
import map.models.MapGridInf;
import tiles.models.TileFactory;

public class MovableTileFactory extends AbstractFactory {
    public tiles.mcWrapper.Tile getMovableTile(String tile, remaining.GridCoords coord, Map mapController, MapGridInf mapGridInf){
        Tile MovableTileModel;
        tiles.mcWrapper.Tile tileWrapper;

        MovableTileModel = TileFactory.getTile(tile,coord);
        if(MovableTileModel == null) return null;

        /*
        if(Tile.equalsIgnoreCase("Human1")) {
            MovableTileModel = new Models.tiles.Human1(coord);
        } else
            return null;*/

        tileWrapper = new tiles.mcWrapper.Tile();
        tileWrapper.setModel(MovableTileModel);
        tileWrapper.setController(new MovableTile(tileWrapper, mapController, mapGridInf));

        return tileWrapper;
    }
    public tiles.mcWrapper.Tile getFixedTile(String Tile, remaining.GridCoords coord, Map mapController, MapGridInf mapGridInf){
        return null;
    }
}
