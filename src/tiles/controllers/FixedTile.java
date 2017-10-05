package tiles.controllers;

import map.models.MapGridInf;
import tiles.models.MovableTile;
import map.controllers.Map;

public class FixedTile extends Tile{
    private final tiles.mcWrapper.Tile tileWrapper;

    public FixedTile(tiles.mcWrapper.Tile tileWrapper, Map mapController, MapGridInf mapGridInf){
        super( mapController, mapGridInf);
        this.tileWrapper = tileWrapper;
    }

    public Boolean moveTile(MovableTile.Directions direction, int addX, int addY){return false;};

    public Boolean execute(String argument){
        //nothing to execute
        return false;
    }
    public void addPad(int steps, int ticks, tiles.models.Tile.Directions direction){return;}
}
