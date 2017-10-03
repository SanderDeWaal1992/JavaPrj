package Factories.Tiles;

public class MovableTileFactory extends AbstractFactory {
    public Wrappers.Tiles.Tile getMovableTile(String Tile, util.GridCoords coord, Controllers.Map mapController, Models.MapGridInf mapGridInf){
        Models.Tiles.MovableTile MovableTileModel;
        Wrappers.Tiles.Tile tileWrapper;

        if(Tile.equalsIgnoreCase("Human1")) {
            MovableTileModel = new Models.Tiles.Human1(coord);
        } else
            return null;

        tileWrapper = new Wrappers.Tiles.Tile();
        tileWrapper.setModel(MovableTileModel);
        tileWrapper.setController(new Controllers.Tiles.MovableTile(tileWrapper, mapController, mapGridInf));

        return tileWrapper;
    }
    public Wrappers.Tiles.Tile getFixedTile(String Tile, util.GridCoords coord, Controllers.Map mapController, Models.MapGridInf mapGridInf){
        return null;
    }
}
