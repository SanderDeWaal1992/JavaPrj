package Models;

import Models.Tiles.TilePart;
import util.GridCoords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Map implements MapGridInf{
    private java.util.Map<GridCoords, ArrayList<TilePart>> tileList = new HashMap<GridCoords,  ArrayList<Models.Tiles.TilePart>>();

    private GridCoords playerCoord;
    private Remaining.Tiles.Tile playerTile;
    private int rowCnt=0;
    private int columnCnt =0;
    private int viewPortRowCnt = 0;
    private int viewPortColumnCnt = 0;
    public Map(){

    }

    /**
     * To get tiles which are colidable at given gridCoords
     * @param gridCoords
     * @return Tiles which are colidable at given gridCoords
     */
    public List<Models.Tiles.TilePart> getColidableTiles(GridCoords gridCoords){
        List<Models.Tiles.TilePart> bTileParts = new ArrayList<Models.Tiles.TilePart>();

        for (Models.Tiles.TilePart tilePart:getFromTileList(gridCoords)) {
            if(tilePart.getCollidable() == true) {
                bTileParts.add(tilePart);
            }
        }
        return bTileParts;
    }

    public void setPlayerTile(Remaining.Tiles.Tile playerTile){
        this.playerTile=playerTile;
    }
    public Remaining.Tiles.Tile getPlayerTile(){
        return playerTile;
    }

    public void addInTileList(Remaining.Tiles.Tile tile){
        ArrayList<Models.Tiles.TilePart> bTilePartList;
        if(tile==null)
            return;
        GridCoords absGridCoord = new GridCoords(tile.getModel().getCoord().getX(),tile.getModel().getCoord().getY());
        for (GridCoords relGridCoord = new GridCoords(0,0); relGridCoord.getX() < tile.getModel().getSizeX(); relGridCoord.setX(relGridCoord.getX()+1), absGridCoord.setX(absGridCoord.getX()+1)) {
            absGridCoord.setY(tile.getModel().getCoord().getY());
            for (relGridCoord.setY(0); relGridCoord.getY() < tile.getModel().getSizeY(); relGridCoord.setY(relGridCoord.getY()+1), absGridCoord.setY(absGridCoord.getY()+1)) {
                bTilePartList = tileList.get(absGridCoord);// = new HashMap<Tile.Index, Tile>(Tile.Index.getMaxValue());

                if (bTilePartList == null) bTilePartList = new ArrayList<Models.Tiles.TilePart>();
                if(tile.getModel().getTilePart(relGridCoord)!=null) bTilePartList.add(tile.getModel().getTilePart(relGridCoord));
                tileList.put(new GridCoords(absGridCoord.getX(),absGridCoord.getY()), bTilePartList);
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
    public void setViewPortRowCnt(int viewPortRowCnt){
        this.viewPortRowCnt = viewPortRowCnt;
    }
    public int getViewPortRowCnt(){
        return viewPortRowCnt;
    }
    public void setViewPortColumnCnt(int viewPortColumnCnt){
        this.viewPortColumnCnt=viewPortColumnCnt;
    }
    public int getViewPortColumnCnt(){
        return viewPortColumnCnt;
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