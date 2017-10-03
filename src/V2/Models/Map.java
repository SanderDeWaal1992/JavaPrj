package V2.Models;

import V2.Models.Tiles.Tile;
import com.sun.org.apache.xpath.internal.operations.Bool;
import util.GridCoords;
//import V2.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Map {
    //List<Integer> num = new ArrayList<>();
    //private java.util.Map<GridCoords, List<Tile>> tileList = new HashMap<GridCoords, List<Tile>>();



    //private java.util.Map<GridCoords, java.util.Map<Tile.Index, Tile>> tileList = new HashMap<GridCoords,  java.util.Map<Tile.Index, Tile>>();
    private java.util.Map<GridCoords, List<Tile.TilePart>> tileList = new HashMap<GridCoords,  List<Tile.TilePart>>();

    private GridCoords playerCoord;
    private V2.Models.Tiles.MovableTile playerModelTile;
    private V2.Controllers.Tiles.MovableTile playerControllerTile;
    private int rowCnt=0;
    private int columnCnt =0;

    public Map(){

    }

    /**
     * To get tiles which are colidable at given gridCoords
     * @param gridCoords
     * @return Tiles which are colidable at given gridCoords
     */
    public List<V2.Models.Tiles.Tile.TilePart> getColidableTiles(GridCoords gridCoords){
        //GridCoords bAbsCoord = new GridCoords(0,0);
        //GridCoords bRelCoord = new GridCoords(0,0);
        List<V2.Models.Tiles.Tile.TilePart> bTileParts = new ArrayList<V2.Models.Tiles.Tile.TilePart>();

        for (V2.Models.Tiles.Tile.TilePart tilePart:getFromTileList(gridCoords)) {
            //bRelCoord.setX(bAbsCoord.getX()-gridCoords.getX());
            //bRelCoord.setY(bAbsCoord.getY()-gridCoords.getY());
            if(tilePart.getCollidable() == true) {
                bTileParts.add(tilePart);
            }
        }

        /*int cnt = 0;
        for(bAbsCoord.setX(0); bAbsCoord.getX()<rowCnt;bAbsCoord.setX(bAbsCoord.getX()+1)) {
            for(bAbsCoord.setY(0); bAbsCoord.getY()<columnCnt;bAbsCoord.setY(bAbsCoord.getY()+1)) {

                for (V2.Models.Tiles.Tile tile:getFromTileList(bAbsCoord)) {
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
    public void setPlayerModelTile(V2.Models.Tiles.MovableTile playerModelTile){
        this.playerModelTile=playerModelTile;
    }
    public void setPlayerControllerTile(V2.Controllers.Tiles.MovableTile playerControllerTile){
        this.playerControllerTile=playerControllerTile;
    }
    public V2.Models.Tiles.MovableTile getPlayerModelTile(){
        return this.playerModelTile;
    }
    public V2.Controllers.Tiles.MovableTile getPlayerControllerTile(){
        return this.playerControllerTile;
    }

    public void addInTileList(V2.Models.Tiles.Tile tile){
        GridCoords absGridCoord = new GridCoords(tile.getCoord().getX(),tile.getCoord().getY());
        for (GridCoords relGridCoord = new GridCoords(0,0); relGridCoord.getX() < tile.getSizeX(); relGridCoord.setX(relGridCoord.getX()+1), absGridCoord.setX(absGridCoord.getX()+1)) {
            absGridCoord.setY(tile.getCoord().getY());
            for (relGridCoord.setY(0); relGridCoord.getY() < tile.getSizeY(); relGridCoord.setY(relGridCoord.getY()+1), absGridCoord.setY(absGridCoord.getY()+1)) {
                List<Tile.TilePart> b = tileList.get(absGridCoord);// = new HashMap<Tile.Index, Tile>(Tile.Index.getMaxValue());

                if (b == null) b = new ArrayList<Tile.TilePart>();
                //if(b.containsKey(tile.getIndex().getValue()))
                b.add(tile.getTilePart(relGridCoord));
                //else {
                //    int i = tile.getIndex().getValue();
                //    b.add(tile.getIndex().getValue(), tile);
                //}
                tileList.put(new GridCoords(absGridCoord.getX(),absGridCoord.getY()), b);
                //List<Tile.TilePart> debug = getFromTileList(new GridCoords(1,1));
                //if(debug.contains(b))
                    //System.out.print("Hello World");
            }
        }
    }

    public List<Tile.TilePart> getFromTileList(GridCoords gridCoord){
        if(tileList.containsKey(gridCoord)==false)
            return new ArrayList<Tile.TilePart>();
        return tileList.get(gridCoord);
    }

    public Boolean deleteFromTileList(GridCoords gridCoord, V2.Models.Tiles.Tile tile){
        Boolean deleted =true;
        Boolean atLeastOne = false;
        GridCoords absGridCoord = new GridCoords(gridCoord.getX(),gridCoord.getY());
        for (GridCoords relGridCoord = new GridCoords(0,0); relGridCoord.getX() < tile.getSizeX() && deleted != false; relGridCoord.setX(relGridCoord.getX()+1), absGridCoord.setX(absGridCoord.getX()+1)) {
            absGridCoord.setY(gridCoord.getY());
            for (relGridCoord.setY(0); relGridCoord.getY() < tile.getSizeY() && deleted != false; relGridCoord.setY(relGridCoord.getY()+1), absGridCoord.setY(absGridCoord.getY()+1)) {
                List<Tile.TilePart> oldList = tileList.get(absGridCoord);
                List<Tile.TilePart> newList = new ArrayList<Tile.TilePart>();

                if (oldList == null){ deleted = false; continue;}
                for(Tile.TilePart tilePart: oldList){
                    if (tilePart.getParentTile()==tile) {
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