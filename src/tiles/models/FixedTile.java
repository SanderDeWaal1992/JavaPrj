package tiles.models;

public class FixedTile extends Tile{
    public FixedTile(String description, String imgDir, remaining.GridCoords coord){
        super(imgDir, description);
        setCoord(coord);
    }

}
