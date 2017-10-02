package V2.Models;

import V2.Models.Tiles.Tile;
import util.GridCoords;
//import V2.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Map {
    //List<Integer> num = new ArrayList<>();
    //private java.util.Map<GridCoords, List<Tile>> tileList = new HashMap<GridCoords, List<Tile>>();
    private java.util.Map<GridCoords, java.util.Map<Tile.Index, Tile>> tileList = new HashMap<GridCoords,  java.util.Map<Tile.Index, Tile>>();
    private GridCoords playerCoord;
    private V2.Models.Tiles.Tile playerTile;
    private int rowCnt=0;
    private int columnCnt =0;

    public Map(){

    }

    /**
     * To get tiles which are colidable at given gridCoords
     * @param gridCoords
     * @return Tiles which are colidable at given gridCoords
     */
    public List<V2.Models.Tiles.Tile> getColidableTiles(GridCoords gridCoords){
        GridCoords bAbsCoord = new GridCoords(0,0);
        GridCoords bRelCoord = new GridCoords(0,0);
        List<V2.Models.Tiles.Tile> bTiles = new ArrayList<V2.Models.Tiles.Tile>();
        int cnt = 0;
        for(bAbsCoord.setX(0); bAbsCoord.getX()<rowCnt;bAbsCoord.setX(bAbsCoord.getX()+1)) {
            for(bAbsCoord.setY(0); bAbsCoord.getY()<columnCnt;bAbsCoord.setY(bAbsCoord.getY()+1)) {

                for (V2.Models.Tiles.Tile tile:getFromTileList(bAbsCoord).values()) {
                    bRelCoord.setX(bAbsCoord.getX()-gridCoords.getX());
                    bRelCoord.setY(bAbsCoord.getY()-gridCoords.getY());
                    if(tile.getCollidableAtPos(bRelCoord) == true) {
                        bTiles.add(cnt++, tile);
                    }
                }
            }
        }
        return bTiles;
    }

    public void setPlayerCoord(GridCoords playerCoord){
        this.playerCoord=playerCoord;
    }
    public GridCoords getPlayerCoord(){
        return this.playerCoord;
    }
    public void setPlayerTile(V2.Models.Tiles.Tile playerTile){
        this.playerTile=playerTile;
    }
    public V2.Models.Tiles.Tile getPlayerTile(){
        return this.playerTile;
    }
    public void addInTileList(GridCoords gridCoord, V2.Models.Tiles.Tile tile){
        //List<Tile> b = tileList.get(gridCoord);
        java.util.Map<Tile.Index, Tile> b= tileList.get(gridCoord);// = new HashMap<Tile.Index, Tile>(Tile.Index.getMaxValue());

        if(b==null) b = new HashMap<Tile.Index, Tile>(Tile.Index.getMaxValue());
        //if(b.containsKey(tile.getIndex().getValue()))
            b.put(tile.getIndex(),tile);
        //else {
        //    int i = tile.getIndex().getValue();
        //    b.add(tile.getIndex().getValue(), tile);
        //}
        tileList.put(gridCoord, b);
    }

    public java.util.Map<Tile.Index, Tile> getFromTileList(GridCoords gridCoord){
        if(tileList.containsKey(gridCoord)==false)
            return new HashMap<Tile.Index, Tile>();
        return tileList.get(gridCoord);
    }

    public Boolean deleteFromTileList(GridCoords gridCoord, V2.Models.Tiles.Tile tile){
        Boolean deleted =true;
        java.util.Map<Tile.Index, Tile> b = tileList.get(gridCoord);
        if(b.containsKey(tile.getIndex())==false || b.get(tile.getIndex())==null) deleted = false;
        else {
            b.remove(tile.getIndex());
            tileList.put(gridCoord, b);
        }
        return deleted;
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