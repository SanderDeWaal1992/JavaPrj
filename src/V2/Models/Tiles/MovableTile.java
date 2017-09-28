package Tiles;

public class MovableTile extends Tile {
    private String text = "";
    private String imgLoc = "";
    private Boolean isMoving = false;
    public static final Boolean collisable = true;

    public enum Directions {
        Left, Up, Right, Down,
    }
    Directions direction = Directions.Down;
    public MovableTile(String imgLoc, int width, int height){
        super(imgLoc+"down.png", width, height);
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
        this.setImage(imageLoc());
        this.revalidate();
        this.repaint();
    }
    private String imageLoc(){
        String bLoc;
        switch(direction){
            case Up:
                bLoc= imgLoc+"up.png";
            break;
            case Left:
                bLoc= imgLoc+"left.png";

            break;
            case Right:
                bLoc= imgLoc+"right.png";

            break;
            default: case Down:
                bLoc= imgLoc+"down.png";
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
        return collisable;
    }
}
