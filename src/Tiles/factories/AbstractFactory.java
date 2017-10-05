package Tiles.factories;


import Tiles.mcWrapper.Tile;
import map.controllers.Map;
import map.views.MapGridInf;

public abstract class AbstractFactory {
    public abstract Tile getMovableTile(String Tile, util.GridCoords coord, Map mapController, MapGridInf mapGridInf);

    public abstract Tile getFixedTile(String Tile, util.GridCoords coord, Map mapController, MapGridInf mapGridInf);
}



















