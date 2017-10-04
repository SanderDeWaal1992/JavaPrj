package Controllers.Tiles;

//import Models.Tiles.Tile;

import Models.MapGridInf;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;

public class MovableTile extends Tile{
    private final Remaining.Tiles.Tile tileWrapper;

    public MovableTile(Remaining.Tiles.Tile tileWrapper, Controllers.Map mapController, MapGridInf mapGridInf){
        super( mapController, mapGridInf);
        this.tileWrapper=tileWrapper;
    }

    public Boolean moveTile(Models.Tiles.MovableTile.Directions direction, int addX, int addY) {
        Boolean success = false;
        Boolean directionChanged = false;
        if(this.tileWrapper.getModel().getDirection()!=direction) {
            directionChanged = true;
            this.tileWrapper.getModel().setDirection(direction);
        }
        util.GridCoords nextCoord = new util.GridCoords(tileWrapper.getModel().getCoord().getX(),tileWrapper.getModel().getCoord().getY());
        Boolean colide = false;

        //check image won't get outside the map
        int bX = this.tileWrapper.getModel().getCoord().getX() + addX;
        if ((bX + this.tileWrapper.getModel().getSizeX() > mapGridInf.getColumnCnt())) {
            bX = mapGridInf.getColumnCnt() - this.tileWrapper.getModel().getSizeX();
        } else if (bX < 0)
            bX = 0;
        int bY = this.tileWrapper.getModel().getCoord().getY() + addY;
        if (bY + this.tileWrapper.getModel().getSizeY() > mapGridInf.getRowCnt()) {
            bY = mapGridInf.getRowCnt() - this.tileWrapper.getModel().getSizeY();
        } else if (bY < 0)
            bY = 0;
        nextCoord.setX(bX);
        nextCoord.setY(bY);

        //check if any collision would happen when new coord would be set
        util.GridCoords RelCoord = new util.GridCoords(0, 0);
        util.GridCoords bNextCoord = new util.GridCoords(0,0);
        for (int x = 0; x < this.tileWrapper.getModel().getSizeX() && colide == false; x++) {
            for (int y = 0; y < this.tileWrapper.getModel().getSizeY() && colide == false; y++) {
                RelCoord.setX(x);
                RelCoord.setY(y);

                if(this.tileWrapper.getModel().getCollidableAtPos(RelCoord) == true) {
                    bNextCoord.setX(nextCoord.getX()+x);
                    bNextCoord.setY(nextCoord.getY()+y);
                    for (Models.Tiles.TilePart tilePart : mapController.getColidableTiles(bNextCoord)) {
                        colide = true;
                        //possible to check and execute colide action :TODO add colide actions eg talk, set door open, etc
                        break;
                    }
                }
            }
        }
        if (colide == false) {
            success = mapController.moveTileInTileList(nextCoord, this.tileWrapper);
            if(success == false)
                ;//TODO: handle unexpected beheviour
        }
        return success | directionChanged;
        //mapController.updateView(); //TODO: put observers on object image change
        //mapView.revalidate();
        //mapView.repaint();
    }
    public class PathPart{
        private final int steps;
        private final int ticks;
        private final Models.Tiles.Tile.Directions direction;
        public PathPart(int steps, int ticks, Models.Tiles.Tile.Directions direction){
            this.steps=steps;
            this.ticks=ticks;
            this.direction=direction;
        }
        public int getSteps(){
            return steps;
        }
        public int getTicks(){
            return ticks;
        }
        public Models.Tiles.Tile.Directions getDirection(){
            return direction;
        }
    }
    private int ticks =0;
    private int pathPartIdx =0;
    private int stepsDone =0;
    private List<PathPart> pathPartList = new ArrayList<PathPart>();
    public void addPad(int steps, int ticks, Models.Tiles.Tile.Directions direction){
        pathPartList.add(new PathPart(steps, ticks, direction));
    }
    private Boolean executePad(){
        Boolean modelChanged = false;
        util.GridCoords prevCoord = new util.GridCoords(this.tileWrapper.getModel().getCoord().getX(),this.tileWrapper.getModel().getCoord().getY());
        int addX=0;
        int addY=0;
        if(pathPartList.size() == 0)
            return false;//no pad set

        if(pathPartList.size()<= pathPartIdx)
            pathPartIdx = 0; // end of pad reached. Reset pad ptr

        ticks++;
        if(ticks>=pathPartList.get(pathPartIdx).getTicks()) {
            ticks = 0;
            if(pathPartList.get(pathPartIdx).getSteps()!=stepsDone) { //steps property might be 0. Dont't move but set direction
                switch(pathPartList.get(pathPartIdx).getDirection()){
                    case LEFT:
                        addX=-1;
                        break;
                    case RIGHT:
                        addX=1;
                        break;
                    case UP:
                        addY=-1;
                        break;
                    case DOWN: default:
                        addY=1;
                        break;
                }
            }
            modelChanged|=moveTile(pathPartList.get(pathPartIdx).getDirection(),addX,addY);
            if(this.tileWrapper.getModel().getCoord().equals(prevCoord) == false)
                stepsDone++;
            if(stepsDone>=pathPartList.get(pathPartIdx).getSteps()){
                stepsDone = 0;
                pathPartIdx++;
            }
        }
        return modelChanged;
    }
    public Boolean execute(String argument){
        Boolean modelChanged = false;
        modelChanged |= executePad();
        return modelChanged;
    }
/*
    public void moveRight() {
        moveTile(Models.Tiles.Tile.Directions.RIGHT, 1, 0);
    }

    public void moveLeft() {
        moveTile(Models.Tiles.Tile.Directions.LEFT, -1, 0);
    }

    public void moveUp() {
        moveTile(Models.Tiles.Tile.Directions.UP, 0, -1);
    }

    public void moveDown() {
        moveTile(Models.Tiles.Tile.Directions.DOWN, 0, 1);
    }*/
}
