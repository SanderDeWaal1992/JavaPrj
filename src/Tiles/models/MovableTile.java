package Tiles.models;

public class MovableTile extends Tile {
    public MovableTile(String description, String imgDir, util.GridCoords coord){
        super(imgDir, description);
        setCoord(coord);
    }
    @Override
    public void setDirection(Directions direction){
        super.setDirection(direction);
        switch(direction){
            case LEFT:
                setImg("left.png");
                break;
            case RIGHT:
                setImg("right.png");
                break;
            case UP:
                setImg("up.png");
                break;
            case DOWN:
                setImg("down.png");
                break;
            default:
                setImg("default.png");
                break;
        }
    }
}
