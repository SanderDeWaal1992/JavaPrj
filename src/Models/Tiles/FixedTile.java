package Models.Tiles;

public class FixedTile extends Tile{
    public FixedTile(String description, String imgDir, util.GridCoords coord){
        super(imgDir, description);
        setCoord(coord);
    }

    public void setDirection(Directions direction){return;}
}
