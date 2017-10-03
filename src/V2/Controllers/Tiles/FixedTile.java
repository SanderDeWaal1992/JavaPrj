package V2.Controllers.Tiles;

public class FixedTile extends Tile{
    private final V2.Models.Tiles.FixedTile tileModel;

    public FixedTile(V2.Models.Tiles.FixedTile tileModel, V2.Controllers.Map mapController, int rowCnt, int columnCnt){
        super( mapController, rowCnt, columnCnt);
        this.tileModel = tileModel;
    }
}
