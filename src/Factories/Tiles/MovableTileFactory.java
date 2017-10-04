package Factories.Tiles;

public class MovableTileFactory extends AbstractFactory {
    public Remaining.Tiles.Tile getMovableTile(String tile, util.GridCoords coord, Controllers.Map mapController, Models.MapGridInf mapGridInf){
        Models.Tiles.Tile MovableTileModel;
        Remaining.Tiles.Tile tileWrapper;

        MovableTileModel = Models.Tiles.TileFactory.getTile(tile,coord);
        if(MovableTileModel == null) return null;

        /*
        if(Tile.equalsIgnoreCase("Human1")) {
            MovableTileModel = new Models.Tiles.Human1(coord);
        } else
            return null;*/

        tileWrapper = new Remaining.Tiles.Tile();
        tileWrapper.setModel(MovableTileModel);
        tileWrapper.setController(new Controllers.Tiles.MovableTile(tileWrapper, mapController, mapGridInf));

        return tileWrapper;
    }
    public Remaining.Tiles.Tile getFixedTile(String Tile, util.GridCoords coord, Controllers.Map mapController, Models.MapGridInf mapGridInf){
        return null;
    }
}
