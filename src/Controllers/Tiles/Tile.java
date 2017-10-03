package Controllers.Tiles;

import Models.MapGridInf;

public abstract class Tile {
    protected final Controllers.Map mapController;
    protected final MapGridInf mapGridInf;

    public Tile(Controllers.Map mapController, MapGridInf mapGridInf){
        this.mapController=mapController;
        this.mapGridInf=mapGridInf;
    }

    public abstract void moveTile(Models.Tiles.MovableTile.Directions direction, int addX, int addY);
}
