package Tiles.controllers;

import map.views.MapGridInf;
import Tiles.models.MovableTile;
import map.controllers.Map;

public class FixedTile extends Tile{
    private final Tiles.mcWrapper.Tile tileWrapper;

    public FixedTile(Tiles.mcWrapper.Tile tileWrapper, Map mapController, MapGridInf mapGridInf){
        super( mapController, mapGridInf);
        this.tileWrapper = tileWrapper;
    }

    public Boolean moveTile(MovableTile.Directions direction, int addX, int addY){return false;};

    public Boolean execute(String argument){
        //nothing to execute
        return false;
    }
    public void addPad(int steps, int ticks, Tiles.models.Tile.Directions direction){return;}
}
