package Controllers.Tiles;

//import Models.Tiles.Tile;

import Models.MapGridInf;

public class MovableTile extends Tile{
    private final Wrappers.Tiles.Tile tileWrapper;

    public MovableTile(Wrappers.Tiles.Tile tileWrapper, Controllers.Map mapController, MapGridInf mapGridInf){
        super( mapController, mapGridInf);
        this.tileWrapper=tileWrapper;
    }

    public void moveTile(Models.Tiles.MovableTile.Directions direction, int addX, int addY) {
        this.tileWrapper.getModel().setDirection(direction);
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
            Boolean success = mapController.moveTileInTileList(nextCoord, this.tileWrapper);
            if(success == false)
                ;//TODO: handle unexpected beheviour
        }
        mapController.updateView(); //TODO: put observers on object image change
        //mapView.revalidate();
        //mapView.repaint();
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
