package tiles.factories;


import tiles.mcWrapper.Tile;
import map.controllers.Map;
import map.models.MapGridInf;

public abstract class AbstractFactory {
    public abstract Tile getMovableTile(String Tile, remaining.GridCoords coord, Map mapController, MapGridInf mapGridInf);

    public abstract Tile getFixedTile(String Tile, remaining.GridCoords coord, Map mapController, MapGridInf mapGridInf);
}



















