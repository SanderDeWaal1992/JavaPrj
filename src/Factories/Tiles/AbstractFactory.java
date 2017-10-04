package Factories.Tiles;


public abstract class AbstractFactory {
    public abstract Remaining.Tiles.Tile getMovableTile(String Tile, util.GridCoords coord, Controllers.Map mapController, Models.MapGridInf mapGridInf);

    public abstract Remaining.Tiles.Tile getFixedTile(String Tile, util.GridCoords coord, Controllers.Map mapController, Models.MapGridInf mapGridInf);
}



















