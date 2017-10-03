package Controllers.Tiles;

import Models.MapGridInf;

public class FixedTile extends Tile{
    private final Wrappers.Tiles.Tile tileWrapper;

    public FixedTile(Wrappers.Tiles.Tile tileWrapper, Controllers.Map mapController, MapGridInf mapGridInf){
        super( mapController, mapGridInf);
        this.tileWrapper = tileWrapper;
    }

    public void moveTile(Models.Tiles.MovableTile.Directions direction, int addX, int addY){return;};
}
