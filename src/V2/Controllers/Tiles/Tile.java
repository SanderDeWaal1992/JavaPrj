package V2.Controllers.Tiles;

public abstract class Tile {
    protected final V2.Controllers.Map mapController;
    protected final int rowCnt;
    protected final int columnCnt;

    public Tile(V2.Controllers.Map mapController, int rowCnt, int columnCnt){
        this.mapController=mapController;
        this.rowCnt=rowCnt;
        this.columnCnt=columnCnt;
    }
}
