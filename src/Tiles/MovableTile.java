package Tiles;

public class MovableTile extends Tile {
    private String text = "";
    private String imgLoc = "";
    private Boolean isMoving = false;

    public enum Directions {
        Left, Up, Right, Down,
    }
    Directions direction = Directions.Down;
    public MovableTile(String imgLoc){
        super(imgLoc+"down.jpg");
        this.imgLoc = imgLoc;
    }
    public void setText(String text){
        this.text=text;
    }
    public String getText(){
        return text;
    }
    public void setDirection(Directions direction){
        this.direction = direction;
    }
    private String imageLoc(){
        String bLoc;
        switch(direction){
            case Up:
                bLoc= imgLoc+"up.jpg";
            break;
            case Left:
                bLoc= imgLoc+"left.jpg";

            break;
            case Right:
                bLoc= imgLoc+"right.jpg";

            break;
            default: case Down:
                bLoc= imgLoc+"down.jpg";
            break;
        }
        return bLoc;
    }
    public void setMoving(Boolean move){

    }
    public Tile getCollisionShape(){
        return null;
    }
    public Boolean getCollisable(){
        return true;
    }
}
