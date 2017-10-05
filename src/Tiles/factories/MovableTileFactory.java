package Tiles.factories;

import Tiles.controllers.MovableTile;
import Tiles.models.Tile;
import Tiles.models.TileFactory;
import map.controllers.Map;
import map.views.MapGridInf;

public class MovableTileFactory extends AbstractFactory {
    public Tiles.mcWrapper.Tile getMovableTile(String tile, util.GridCoords coord, Map mapController, MapGridInf mapGridInf){
        Tile MovableTileModel;
        Tiles.mcWrapper.Tile tileWrapper;

        MovableTileModel = TileFactory.getTile(tile,coord);
        if(MovableTileModel == null) return null;

        /*
        if(Tile.equalsIgnoreCase("Human1")) {
            MovableTileModel = new Models.Tiles.Human1(coord);
        } else
            return null;*/

        tileWrapper = new Tiles.mcWrapper.Tile();
        tileWrapper.setModel(MovableTileModel);
        tileWrapper.setController(new MovableTile(tileWrapper, mapController, mapGridInf));

        return tileWrapper;
    }
    public Tiles.mcWrapper.Tile getFixedTile(String Tile, util.GridCoords coord, Map mapController, MapGridInf mapGridInf){
        return null;
    }
}
