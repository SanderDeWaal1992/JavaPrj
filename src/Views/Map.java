package Views;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

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
        //util.utilities test = new util.utilities();
        List<Models.Tiles.TilePart> bTilePartList;
        util.GridCoords absCoordsStart = new util.GridCoords(0, 0);
        util.GridCoords absCoords = new util.GridCoords(0, 0);
        int width = this.getWidth();
        int height = this.getHeight();
        int tileWidth = width / mapModel.getViewPortColumnCnt();
        int tileHeight = height / mapModel.getViewPortRowCnt();
        this.setLayout(null);
        if(tileWidth==0 || tileHeight == 0) return;// could happen in very large map

        //set absolute coordinates of map
        absCoordsStart.setX(mapModel.getPlayerTile().getModel().getCoord().getX() - (mapModel.getViewPortColumnCnt() / 2));
        absCoordsStart.setY(mapModel.getPlayerTile().getModel().getCoord().getY() - (mapModel.getViewPortRowCnt() / 2));

        //check absolute coordinates don't cross over map
        if(absCoordsStart.getX()<0 ) absCoordsStart.setX(0);
        if((absCoordsStart.getX()+mapModel.getViewPortColumnCnt())>=mapModel.getColumnCnt()) absCoordsStart.setX(mapModel.getColumnCnt()-mapModel.getViewPortColumnCnt());
        if(absCoordsStart.getY()<0 ) absCoordsStart.setY(0);
        if((absCoordsStart.getY()+mapModel.getViewPortRowCnt())>=mapModel.getRowCnt()) absCoordsStart.setY(mapModel.getRowCnt()-mapModel.getViewPortRowCnt());

        for (util.GridCoords relCoords = new util.GridCoords(0, 0); relCoords.getX() < mapModel.getColumnCnt(); relCoords.setX(relCoords.getX() + 1)) {
            for (relCoords.setY(0); relCoords.getY() < mapModel.getRowCnt(); relCoords.setY(relCoords.getY() + 1)) {

                absCoords.setX(absCoordsStart.getX() + relCoords.getX());
                absCoords.setY(absCoordsStart.getY() + relCoords.getY());
                bTilePartList = mapModel.getFromTileList(absCoords);
                if(bTilePartList == null)
                    continue;
                Collections.sort(bTilePartList, (a, b) -> a.getIndex().compareTo(b.getIndex()));//sort list on index
                for (Models.Tiles.TilePart tilePart : bTilePartList) {
                    if(tilePart == null)
                        continue;
                    g.drawImage(
                            imagePool.getImage(
                                    tilePart.getParentTile().getImgDir() + tilePart.getParentTile().getImg(),
                                    tilePart.getParentTile().getSizeX() * tileWidth,
                                    tilePart.getParentTile().getSizeY() * tileHeight
                            ),
                            relCoords.getX() * tileWidth,
                            relCoords.getY() * tileHeight,
                            (relCoords.getX() + 1) * tileWidth,
                            (relCoords.getY() + 1) * tileHeight,
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

