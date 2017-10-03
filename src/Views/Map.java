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
    private ImagePool imagePool = new ImagePool();

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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        util.utilities test = new util.utilities();
        int width = this.getWidth();
        int height = this.getHeight();
        int tileWidth = width / mapModel.getColumnCnt();
        int tileHeight = height / mapModel.getRowCnt();
        this.setLayout(null);
        for (util.GridCoords coords = new util.GridCoords(0, 0); coords.getX() < mapModel.getColumnCnt(); coords.setX(coords.getX() + 1)) {
            for (coords.setY(0); coords.getY() < mapModel.getRowCnt(); coords.setY(coords.getY() + 1)) {
                for (Models.Tiles.Tile.Index index : Models.Tiles.Tile.Index.values()) {
                    for (Models.Tiles.TilePart tilePart : mapModel.getFromTileList(coords)) {
                        if (tilePart.getIndex() != index) continue;

                        g.drawImage(
                                imagePool.getImage(
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

                    }
                }
            }
        }
    }
}

