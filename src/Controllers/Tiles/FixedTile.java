package Controllers.Tiles;

import Models.MapGridInf;

public class FixedTile extends Tile{
    private final Remaining.Tiles.Tile tileWrapper;

    public FixedTile(Remaining.Tiles.Tile tileWrapper, Controllers.Map mapController, MapGridInf mapGridInf){
        super( mapController, mapGridInf);
        this.tileWrapper = tileWrapper;
    }

    public Boolean moveTile(Models.Tiles.MovableTile.Directions direction, int addX, int addY){return false;};

    public Boolean execute(String argument){
        //nothing to execute
        return false;
    }
    public void addPad(int steps, int ticks, Models.Tiles.Tile.Directions direction){return;}
}
