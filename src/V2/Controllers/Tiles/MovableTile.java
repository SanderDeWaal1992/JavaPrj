package V2.Controllers.Tiles;

//import V2.Models.Tiles.Tile;

public class MovableTile extends Tile{
    private final V2.Models.Tiles.MovableTile tileModel;

    public MovableTile(V2.Models.Tiles.MovableTile tileModel, V2.Controllers.Map mapController, int rowCnt, int columnCnt){
        super( mapController, rowCnt, columnCnt);
        this.tileModel=tileModel;
    }

    public void moveTile(V2.Models.Tiles.MovableTile.Directions direction, int addX, int addY) {
        this.tileModel.setDirection(direction);
        util.GridCoords nextCoord = new util.GridCoords(tileModel.getCoord().getX(),tileModel.getCoord().getY());
        Boolean colide = false;

        //check image won't get outside the map
        int bX = this.tileModel.getCoord().getX() + addX;
        if ((bX + this.tileModel.getSizeX() > columnCnt)) {
            bX = columnCnt - this.tileModel.getSizeX();
        } else if (bX < 0)
            bX = 0;
        int bY = this.tileModel.getCoord().getY() + addY;
        if (bY + this.tileModel.getSizeY() > rowCnt) {
            bY = rowCnt - this.tileModel.getSizeY();
        } else if (bY < 0)
            bY = 0;
        nextCoord.setX(bX);
        nextCoord.setY(bY);

        //check if any collision would happen when new coord would be set
        util.GridCoords RelCoord = new util.GridCoords(0, 0);
        util.GridCoords bNextCoord = new util.GridCoords(0,0);
        for (int x = 0; x < this.tileModel.getSizeX() && colide == false; x++) {
            for (int y = 0; y < this.tileModel.getSizeY() && colide == false; y++) {
                RelCoord.setX(x);
                RelCoord.setY(y);

                if(this.tileModel.getCollidableAtPos(RelCoord) == true) {
                    bNextCoord.setX(nextCoord.getX()+x);
                    bNextCoord.setY(nextCoord.getY()+y);
                    for (V2.Models.Tiles.Tile.TilePart tilePart : mapController.getColidableTiles(bNextCoord)) {
                        colide = true;
                        //possible to check and execute colide action :TODO add colide actions eg talk, set door open, etc
                        break;
                    }
                }
            }
        }
        if (colide == false) {
            Boolean success = mapController.moveTileInTileList(nextCoord, this.tileModel);
            if(success == false)
                ;//TODO: handle unexpected beheviour
        }
        mapController.updateView(); //TODO: put observers on object image change
        //mapView.revalidate();
        //mapView.repaint();
    }
/*
    public void moveRight() {
        moveTile(V2.Models.Tiles.Tile.Directions.RIGHT, 1, 0);
    }

    public void moveLeft() {
        moveTile(V2.Models.Tiles.Tile.Directions.LEFT, -1, 0);
    }

    public void moveUp() {
        moveTile(V2.Models.Tiles.Tile.Directions.UP, 0, -1);
    }

    public void moveDown() {
        moveTile(V2.Models.Tiles.Tile.Directions.DOWN, 0, 1);
    }*/
}
