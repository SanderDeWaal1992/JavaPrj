package tiles.models;

public class TilePart {
    private Boolean collidable = true;
    private Tile.Index index = Tile.Index.GROUND;
    private final Tile parentTile; //TODO: should be of type tiles.mcWrapper.Tile
    private final remaining.GridCoords relCoord;
    public TilePart(Boolean collidable, Tile.Index index, Tile parentTile, remaining.GridCoords relCoord){
        setCollidable(collidable);
        setIndex(index);
        this.parentTile = parentTile;
        this.relCoord = new remaining.GridCoords(relCoord.getX(), relCoord.getY());
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
    public final remaining.GridCoords getRelCoord(){
        return this.relCoord;
    }
}
