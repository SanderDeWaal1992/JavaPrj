package Tiles;

public class Player extends MovableTile{
    Player(int width, int height){
        super("./src/media/player/", width, height);
        this.setIndex(1);
        this.setToolTipString("This your player.");

    }
}
