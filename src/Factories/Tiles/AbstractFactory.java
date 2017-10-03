package Factories.Tiles;


import Wrappers.Tiles.FixedTile;
import Wrappers.Tiles.MovableTile;

public abstract class AbstractFactory {
    public abstract Wrappers.Tiles.Tile getMovableTile(String Tile, util.GridCoords coord, Controllers.Map mapController, Models.MapGridInf mapGridInf);

    public abstract Wrappers.Tiles.Tile getFixedTile(String Tile, util.GridCoords coord, Controllers.Map mapController, Models.MapGridInf mapGridInf);
}



















