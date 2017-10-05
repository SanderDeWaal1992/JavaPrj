package remaining;

import java.awt.*;

public class GridCoords {
    private Point point = new Point();
    //public int y;

    public GridCoords(int x, int y) {
        super();
        this.point.x = x;
        this.point.y = y;
    }

    public int getX(){
        return point.x;
    }
    public int getY(){
        return point.y;
    }
    public int setX(int x){
        point.x = x;
        return x;
    }
    public int setY(int y){
        point.y = y;
        return y;
    }
    public void setXY(int x, int y){
        point.x = x;
        point.y = y;
    }

    @Override
    public int hashCode()
    {
        return point.hashCode();
    }

    @Override
    public boolean equals(Object o)
    {
        if(! (o instanceof GridCoords)) return false;
        return this.point.x==((GridCoords)o).point.x && this.point.y==((GridCoords)o).point.y;
    }

}