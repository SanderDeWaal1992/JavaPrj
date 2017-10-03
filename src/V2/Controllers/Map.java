package V2.Controllers;

import V2.Models.Tiles.MovableTile;
import V2.Models.Tiles.Tile;
import com.sun.org.apache.xpath.internal.operations.Bool;
import util.GridCoords;

import java.util.List;

public final class Map {
    private V2.Views.Map mapView;
    private V2.Models.Map mapModel;
    //private V2.Models.Tiles.Human1 player;

    public Map(V2.Models.Map mapModel) {
        util.GridCoords bCoord = new GridCoords(0, 0);
        this.mapView = new V2.Views.Map(this, mapModel);
        this.mapModel = mapModel;
        mapModel.setRowCnt(50);
        mapModel.setColumnCnt(50);

        mapModel.setPlayerModelTile(new V2.Models.Tiles.Human1(bCoord));
        mapModel.setPlayerControllerTile(new V2.Controllers.Tiles.MovableTile(mapModel.getPlayerModelTile(), this, mapModel.getRowCnt(), mapModel.getColumnCnt()));

        //fill map with tiles
        //:TODO put this in model and add array storage read/write
        for (int x = 0; x < mapModel.getColumnCnt(); x++) {
            for (int y = 0; y < mapModel.getRowCnt(); y++) {
                bCoord.setX(x);
                bCoord.setY(y);
                mapModel.addInTileList(new V2.Models.Tiles.Pavement(bCoord));
            }
        }
        mapModel.addInTileList( mapModel.getPlayerModelTile());

        bCoord.setX(1);
        bCoord.setY(1);
        mapModel.addInTileList(new V2.Models.Tiles.Tree(bCoord));
        mapView.revalidate();
        mapView.repaint();
    }
    /*
    private void movePlayer(Tile.Directions direction, int addX, int addY) {
        mapModel.getPlayerTile().setImg(direction.toString() + ".png");
        //util.GridCoords PlayerCoord = mapModel.getPlayerCoord();
        util.GridCoords PlayerNextCoord = new util.GridCoords(mapModel.getPlayerCoord().getX(),mapModel.getPlayerCoord().getY());
        Boolean colide = false;

        //check image won't get outside the map
        int bX = mapModel.getPlayerCoord().getX() + addX;
        if ((bX + mapModel.getPlayerTile().getSizeX() > mapModel.getColumnCnt())) {
            bX = mapModel.getColumnCnt() - mapModel.getPlayerTile().getSizeX();
        } else if (bX < 0)
            bX = 0;
        int bY = mapModel.getPlayerCoord().getY() + addY;
        if ((bY + mapModel.getPlayerTile().getSizeY() > mapModel.getRowCnt())) {
            bY = mapModel.getRowCnt() - mapModel.getPlayerTile().getSizeY();
        } else if (bY < 0)
            bY = 0;
        PlayerNextCoord.setX(bX);
        PlayerNextCoord.setY(bY);

        //check if any collision would happen when new playercoord would be set
        util.GridCoords RelPlayerCoord = new util.GridCoords(0, 0);
        util.GridCoords bPlayerNextCoord = new util.GridCoords(0,0);
        for (int x = 0; x<mapModel.getPlayerTile().getSizeX() && colide == false; x++) {
            for (int y = 0; y < mapModel.getPlayerTile().getSizeY() && colide == false; y++) {
                RelPlayerCoord.setX(x);
                RelPlayerCoord.setY(y);

                if(mapModel.getPlayerTile().getCollidableAtPos(RelPlayerCoord) == true) {
                    bPlayerNextCoord.setX(PlayerNextCoord.getX()+x);
                    bPlayerNextCoord.setY(PlayerNextCoord.getY()+y);
                    for (V2.Models.Tiles.Tile.TilePart tilePart : mapModel.getColidableTiles(bPlayerNextCoord)) {
                        colide = true;
                        //possible to check and execute colide action :TODO add colide actions eg talk, set door open, etc
                        break;
                    }
                }
            }
        }
        if (colide == false) {
            //remove old player tile
            mapModel.deleteFromTileList(mapModel.getPlayerCoord(),mapModel.getPlayerTile());

            //set and add new player tile
            mapModel.setPlayerCoord(PlayerNextCoord);
            mapModel.addInTileList(mapModel.getPlayerCoord(), mapModel.getPlayerTile());
        }
        mapView.revalidate();
        mapView.repaint();
    }*/

    public void moveRight() {
        mapModel.getPlayerControllerTile().moveTile(MovableTile.Directions.RIGHT, 1, 0);
    }

    public void moveLeft() {
        mapModel.getPlayerControllerTile().moveTile(MovableTile.Directions.LEFT, -1, 0);
    }

    public void moveUp() {
        mapModel.getPlayerControllerTile().moveTile(MovableTile.Directions.UP, 0, -1);
    }

    public void moveDown() {
        mapModel.getPlayerControllerTile().moveTile(MovableTile.Directions.DOWN, 0, 1);
    }

    public V2.Views.Map getView() {
        return mapView;
    }

    public List<Tile.TilePart> getColidableTiles(GridCoords gridCoords){
        return mapModel.getColidableTiles(gridCoords);
    }

     public Boolean moveTileInTileList(util.GridCoords nextCoord, V2.Models.Tiles.Tile tile){
         Boolean success = true;

         //remove old tile
         success &= mapModel.deleteFromTileList(tile.getCoord(), tile);

         if(success == true) {
             //set and add new tile
             tile.setCoord(nextCoord);
             mapModel.addInTileList(tile);

         }

         return success;
     }
     public void updateView(){
         mapView.revalidate();
         mapView.repaint();
     }
}









































