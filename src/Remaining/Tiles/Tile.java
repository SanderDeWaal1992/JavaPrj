package Remaining.Tiles;

public class Tile <M extends Models.Tiles.Tile,C extends Controllers.Tiles.Tile> {
    private M model;
    private C controller;
    private Boolean setModelPossible = true;
    private Boolean setControllerPossible = true;

    public Tile(){
    }
    public M getModel(){return model;}
    public void setModel(M model){if(setModelPossible)this.model=model; setModelPossible = false;};

    public C getController(){return controller;}
    public void setController(C controller){if(setControllerPossible)this.controller=controller; setControllerPossible = false;};

}
