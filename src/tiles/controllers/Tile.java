package tiles.controllers;

import map.models.MapGridInf;
import tiles.models.MovableTile;
import map.controllers.Map;

public abstract class Tile {
    protected final Map mapController;
    protected final MapGridInf mapGridInf;

    public Tile(Map mapController, MapGridInf mapGridInf){
        this.mapController=mapController;
        this.mapGridInf=mapGridInf;
    }

    public abstract Boolean moveTile(MovableTile.Directions direction, int addX, int addY);
    public abstract Boolean execute(String argument);
    public abstract void addPad(int steps, int ticks, tiles.models.Tile.Directions direction);
}
