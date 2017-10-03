package V2.Controllers.Tiles;

import V2.Models.Tiles.FixedTile;
import V2.Models.Tiles.Hause;
import V2.Models.Tiles.Human1;
import V2.Models.Tiles.MovableTile;

public class AbstractFactory {
    public class SomeData2 {
        public V2.Models.Tiles.MovableTile getModel(){return model;};
        public void setModel(V2.Models.Tiles.MovableTile model){this.model=model;};

        public V2.Controllers.Tiles.MovableTile getController(){return controller;};
        public void setController(V2.Controllers.Tiles.MovableTile controller){this.controller=controller;};

        private V2.Models.Tiles.MovableTile model;
        private V2.Controllers.Tiles.MovableTile controller;
    }
    MovableTile getMovableTile(String Tile, util.GridCoords coord){
        if(Tile.equalsIgnoreCase("Human1")) {
            return new Human1(coord);
        } else return null;
    }
    FixedTile getFixedTile(String Tile, util.GridCoords coord){
        if(Tile.equalsIgnoreCase("Hause")) {
            return new Hause(coord);
        } else if(Tile.equalsIgnoreCase("Pavement")) {
            return new Pavement(coord);
        } else if(Tile.equalsIgnoreCase("Tree")) {
            return new Tree(coord);
        } else if(Tile.equalsIgnoreCase("Wall")) {
            return new Wall(coord);
        } else return null;

    }
}
