package util;

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

    @Override
    public int hashCode()
    {
        return point.hashCode();
    }

    @Override
    public boolean equals(Object o)
    {

        return this.point.equals(o);
    }

}