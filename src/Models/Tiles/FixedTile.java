package Models.Tiles;

public abstract class FixedTile extends Tile{
    public FixedTile(String description, String imgDir, util.GridCoords coord){
        super(imgDir, description);
        setCoord(coord);
    }

    public void setDirection(Directions direction){return;}
}
