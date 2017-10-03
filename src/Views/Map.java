package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

public class Map extends JPanel {
    private JPanel panel;
    private Models.Map mapModel;
    private Controllers.Map mapController;

    public Map(Controllers.Map mapController, Models.Map mapModel) {
        super();
        this.mapController = mapController;
        this.mapModel = mapModel;

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A:
                        mapController.moveLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                        mapController.moveRight();
                        break;
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W:
                        mapController.moveUp();
                        break;
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        mapController.moveDown();
                        break;
                    default:
                        System.out.println("unknown key");
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    /*public void Rebuild(){
        int width = this.getWidth();
        int height = this.getHeight();
        int tileWidth = width/model.getColumnCnt();
        int tileHeight = height/model.getRowCnt();
        //this.setLayout(new GridLayout(model.getColumnCnt(),model.getRowCnt()));
        this.setLayout(null);
        for (util.GridCoords coords = new util.GridCoords(0,0); coords.getX() < model.getColumnCnt(); coords.setX(coords.getX()+1)) {
            for (coords.setY(0); coords.getY() < model.getRowCnt(); coords.setY(coords.getY()+1)) {
                for(Models.Tiles.Tile tile: model.getFromTileList(coords)){
                    util.utilities.getImage(tile.getImgDir()+tile.getImg(), tile.getSizeX()*tileWidth, tile.getSizeY()*tileHeight)
                }
            }
        }
    }*/


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draws the image to the canvas
        //g.drawImage(img, 0, 0, null);
        int width = this.getWidth();
        int height = this.getHeight();
        int tileWidth = width / mapModel.getColumnCnt();
        int tileHeight = height / mapModel.getRowCnt();
        //this.setLayout(new GridLayout(model.getColumnCnt(),model.getRowCnt()));
        this.setLayout(null);
        //for(Models.Tiles.Tile.Index index: Models.Tiles.Tile.Index.values()) {
        for (util.GridCoords coords = new util.GridCoords(0, 0); coords.getX() < mapModel.getColumnCnt(); coords.setX(coords.getX() + 1)) {
            for (coords.setY(0); coords.getY() < mapModel.getRowCnt(); coords.setY(coords.getY() + 1)) {
                //if(mapModel.getFromTileList(coords).containsKey(index)){
                //Models.Tiles.Tile tile = mapModel.getFromTileList(coords).get(index);
                for (Models.Tiles.Tile.Index index : Models.Tiles.Tile.Index.values()) {
                    for (Models.Tiles.Tile.TilePart tilePart : mapModel.getFromTileList(coords)) {
                        if (tilePart.getIndex() != index) continue;
                        try {
                            g.drawImage(
                                    util.utilities.getImage(
                                            tilePart.getParentTile().getImgDir() + tilePart.getParentTile().getImg(),
                                            tilePart.getParentTile().getSizeX() * tileWidth,
                                            tilePart.getParentTile().getSizeY() * tileHeight
                                    ),
                                    coords.getX() * tileWidth,
                                    coords.getY() * tileHeight,
                                    (coords.getX() + 1) * tileWidth,
                                    (coords.getY() + 1) * tileHeight,
                                    tilePart.getRelCoord().getX() * tileWidth,
                                    tilePart.getRelCoord().getY() * tileHeight,
                                    (tilePart.getRelCoord().getX() + 1) * tileWidth,
                                    (tilePart.getRelCoord().getY() + 1) * tileHeight,
                                    null);
                            /*g.drawImage(
                                    util.utilities.getImage(
                                            tilePart.getParentTile().getImgDir() + tilePart.getParentTile().getImg(),
                                            tilePart.getParentTile().getSizeX() * tileWidth,
                                            tilePart.getParentTile().getSizeY() * tileHeight
                                    ),
                                    coords.getX() * tileWidth,
                                    coords.getY() * tileHeight,
                                    null);*/
                        } catch (java.io.FileNotFoundException e) {
                            try {
                                g.drawImage(util.utilities.getImage("./src/media/errorTile.png", tileWidth, tileHeight), coords.getX() * tileWidth, coords.getY() * tileHeight, null);
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            }
            //}
        }
    }
}

