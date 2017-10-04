package Models;

import Models.Tiles.TilePart;
import util.GridCoords;
//import Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Map implements MapGridInf{
    //List<Integer> num = new ArrayList<>();
    //private java.util.Map<GridCoords, List<Tile>> tileList = new HashMap<GridCoords, List<Tile>>();



    //private java.util.Map<GridCoords, java.util.Map<Tile.Index, Tile>> tileList = new HashMap<GridCoords,  java.util.Map<Tile.Index, Tile>>();
    private java.util.Map<GridCoords, ArrayList<TilePart>> tileList = new HashMap<GridCoords,  ArrayList<Models.Tiles.TilePart>>();

    private GridCoords playerCoord;
    private Remaining.Tiles.Tile playerTile;
    private int rowCnt=0;
    private int columnCnt =0;

    public Map(){

    }

    /**
     * To get tiles which are colidable at given gridCoords
     * @param gridCoords
     * @return Tiles which are colidable at given gridCoords
     */
    public List<Models.Tiles.TilePart> getColidableTiles(GridCoords gridCoords){
        //GridCoords bAbsCoord = new GridCoords(0,0);
        //GridCoords bRelCoord = new GridCoords(0,0);
        List<Models.Tiles.TilePart> bTileParts = new ArrayList<Models.Tiles.TilePart>();

        for (Models.Tiles.TilePart tilePart:getFromTileList(gridCoords)) {
            //bRelCoord.setX(bAbsCoord.getX()-gridCoords.getX());
            //bRelCoord.setY(bAbsCoord.getY()-gridCoords.getY());
            if(tilePart.getCollidable() == true) {
                bTileParts.add(tilePart);
            }
        }

        /*int cnt = 0;
        for(bAbsCoord.setX(0); bAbsCoord.getX()<rowCnt;bAbsCoord.setX(bAbsCoord.getX()+1)) {
            for(bAbsCoord.setY(0); bAbsCoord.getY()<columnCnt;bAbsCoord.setY(bAbsCoord.getY()+1)) {

                for (Models.Tiles.Tile tile:getFromTileList(bAbsCoord)) {
                    bRelCoord.setX(bAbsCoord.getX()-gridCoords.getX());
                    bRelCoord.setY(bAbsCoord.getY()-gridCoords.getY());
                    if(tile.getCollidableAtPos(bRelCoord) == true) {
                        bTiles.add(cnt++, tile);
                    }
                }
            }
        }*/
        return bTileParts;
    }

    //public void setPlayerCoord(GridCoords playerCoord){
    //    this.playerCoord=playerCoord;
    //}
    //public GridCoords getPlayerCoord(){
    //    return this.playerCoord;
    //}
    /*public void setPlayerModelTile(Models.Tiles.MovableTile playerModelTile){
        this.playerModelTile=playerModelTile;
    }
    public void setPlayerControllerTile(Controllers.Tiles.MovableTile playerControllerTile){
        this.playerControllerTile=playerControllerTile;
    }
    public Models.Tiles.MovableTile getPlayerModelTile(){
        return this.playerModelTile;
    }
    public Controllers.Tiles.MovableTile getPlayerControllerTile(){
        return this.playerControllerTile;
    }

    */
    public void setPlayerTile(Remaining.Tiles.Tile playerTile){
        this.playerTile=playerTile;
    }
    public Remaining.Tiles.Tile getPlayerTile(){
        return playerTile;
    }

    public void addInTileList(Remaining.Tiles.Tile tile){
        ArrayList<Models.Tiles.TilePart> bTilePartList;
        GridCoords absGridCoord = new GridCoords(tile.getModel().getCoord().getX(),tile.getModel().getCoord().getY());
        for (GridCoords relGridCoord = new GridCoords(0,0); relGridCoord.getX() < tile.getModel().getSizeX(); relGridCoord.setX(relGridCoord.getX()+1), absGridCoord.setX(absGridCoord.getX()+1)) {
            absGridCoord.setY(tile.getModel().getCoord().getY());
            for (relGridCoord.setY(0); relGridCoord.getY() < tile.getModel().getSizeY(); relGridCoord.setY(relGridCoord.getY()+1), absGridCoord.setY(absGridCoord.getY()+1)) {
                bTilePartList = tileList.get(absGridCoord);// = new HashMap<Tile.Index, Tile>(Tile.Index.getMaxValue());

                if (bTilePartList == null) bTilePartList = new ArrayList<Models.Tiles.TilePart>();
                //if(b.containsKey(tile.getIndex().getValue()))
                bTilePartList.add(tile.getModel().getTilePart(relGridCoord));
                //else {
                //    int i = tile.getIndex().getValue();
                //    b.add(tile.getIndex().getValue(), tile);
                //}
                tileList.put(new GridCoords(absGridCoord.getX(),absGridCoord.getY()), bTilePartList);
                //List<Tile.TilePart> debug = getFromTileList(new GridCoords(1,1));
                //if(debug.contains(b))
                    //System.out.print("Hello World");
            }
        }
    }

    public List<Models.Tiles.TilePart> getFromTileList(GridCoords gridCoord){
        if(tileList.containsKey(gridCoord)==false)
            return new ArrayList<Models.Tiles.TilePart>();
        return (List<Models.Tiles.TilePart>)tileList.get(gridCoord).clone();
    }

    public Boolean deleteFromTileList(GridCoords gridCoord, Remaining.Tiles.Tile tile){
        Boolean deleted =true;
        Boolean atLeastOne = false;
        GridCoords absGridCoord = new GridCoords(gridCoord.getX(),gridCoord.getY());
        for (GridCoords relGridCoord = new GridCoords(0,0); relGridCoord.getX() < tile.getModel().getSizeX() && deleted != false; relGridCoord.setX(relGridCoord.getX()+1), absGridCoord.setX(absGridCoord.getX()+1)) {
            absGridCoord.setY(gridCoord.getY());
            for (relGridCoord.setY(0); relGridCoord.getY() < tile.getModel().getSizeY() && deleted != false; relGridCoord.setY(relGridCoord.getY()+1), absGridCoord.setY(absGridCoord.getY()+1)) {
                ArrayList<Models.Tiles.TilePart> oldList = tileList.get(absGridCoord);
                ArrayList<Models.Tiles.TilePart> newList = new ArrayList<Models.Tiles.TilePart>();

                if (oldList == null){ deleted = false; continue;}
                for(Models.Tiles.TilePart tilePart: oldList){
                    if (tilePart.getParentTile()==tile.getModel()) {
                        atLeastOne = true;
                        //oldList.remove(tilePart);
                    }else{
                        newList.add(tilePart);
                    }
                }
                tileList.put(new GridCoords(absGridCoord.getX(),absGridCoord.getY()), newList);
            }
        }
        return deleted && atLeastOne;
    }

    public void setRowCnt(int rowCnt){
        this.rowCnt = rowCnt;
    }
    public int getRowCnt(){
        return rowCnt;
    }
    public void setColumnCnt(int columnCnt){
        this.columnCnt=columnCnt;
    }
    public int getColumnCnt(){
        return columnCnt;
    }



}

//TODO: In elk tilePart object de imagePart opslaan met zowel index etc.
//TODO:
//TODO:    Ipv een tilearry in de mapModel gewoon een tilePartArray waarbij de tileparts en de tiles staan
//TODO:
//TODO:
//TODO:public class struct{
//TODO:    public TilePart tilepart;
//TODO:    public Tile tile;
//TODO:}
//TODO:
//TODO:    private java.util.Map<GridCoords, List<struct>> tileList = new HashMap<GridCoords,  List<struct>>();
//en dan dat het gewoon ge-add wordt. Niet op volgorde van de index
//Als de player moet verplaatsen dan de oude hit uit tilearray en nieuwe toevoegen

//Taak 2 voor na bovenstaande; MovableTile maken en StaticTile met een aparte file in de model en één in de controller. In de Controller dan ook een add en delete object functie opnemen