package Models.Tiles;

public class TilePart {
    private Boolean collidable = true;
    private Tile.Index index = Tile.Index.GROUND;
    private Tile parentTile = null; //TODO: should be of type Remaining.Tiles.Tile
    private util.GridCoords relCoord;
    public TilePart(Boolean collidable, Tile.Index index, Tile parentTile, util.GridCoords relCoord){
        setCollidable(collidable);
        setIndex(index);
        this.parentTile = parentTile;
        this.relCoord = relCoord;
    }
    public Boolean getCollidable() {
        return collidable;
    };
    private void setCollidable(Boolean collidable) {
        this.collidable=collidable;
    };

    private void setIndex(Tile.Index index){
        this.index=index;
    }
    public Tile.Index getIndex(){
        return this.index;
    }

    public Tile getParentTile(){
        return this.parentTile;
    }
    public util.GridCoords getRelCoord(){
        return this.relCoord;
    }
}
