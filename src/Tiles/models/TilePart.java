package Tiles.models;

public class TilePart {
    private Boolean collidable = true;
    private Tile.Index index = Tile.Index.GROUND;
    private final Tile parentTile; //TODO: should be of type Tiles.mcWrapper.Tile
    private final util.GridCoords relCoord;
    public TilePart(Boolean collidable, Tile.Index index, Tile parentTile, util.GridCoords relCoord){
        setCollidable(collidable);
        setIndex(index);
        this.parentTile = parentTile;
        this.relCoord = new util.GridCoords(relCoord.getX(), relCoord.getY());
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
    public final util.GridCoords getRelCoord(){
        return this.relCoord;
    }
}
