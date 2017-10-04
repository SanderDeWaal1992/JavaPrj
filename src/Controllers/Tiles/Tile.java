package Controllers.Tiles;

import Models.MapGridInf;

public abstract class Tile {
    protected final Controllers.Map mapController;
    protected final MapGridInf mapGridInf;

    public Tile(Controllers.Map mapController, MapGridInf mapGridInf){
        this.mapController=mapController;
        this.mapGridInf=mapGridInf;
    }

    public abstract Boolean moveTile(Models.Tiles.MovableTile.Directions direction, int addX, int addY);
    public abstract Boolean execute(String argument);
    public abstract void addPad(int steps, int ticks, Models.Tiles.Tile.Directions direction);
}
