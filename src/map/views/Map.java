package map.views;


import tiles.models.TilePart;
import remaining.ImagePool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.List;

public class Map extends JPanel implements Interface{
    //private JPanel panel;
    private map.models.Map mapModel;
    private map.controllers.Map mapController;
    private ImagePool imagePool = new ImagePool();

    public Map(map.controllers.Map mapController, map.models.Map mapModel) {
        super();
        this.mapController = mapController;
        this.mapModel = mapModel;
    }

    public int getTileWidth(){
        return this.getWidth()/ mapModel.getViewPortColumnCnt();
    }
    public int getTileHeight(){
        return this.getHeight()/ mapModel.getViewPortRowCnt();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //remaining.utilities test = new remaining.utilities();
        List<TilePart> bTilePartList;
        remaining.GridCoords absCoords = new remaining.GridCoords(0, 0);
        int tileWidth = getTileWidth();
        int tileHeight = getTileHeight();
        //this.setLayout(null);
        if(tileWidth==0 || tileHeight == 0) return;// could happen in very large map

        for (remaining.GridCoords relCoords = new remaining.GridCoords(0, 0); relCoords.getX() < mapModel.getColumnCnt(); relCoords.setX(relCoords.getX() + 1)) {
            for (relCoords.setY(0); relCoords.getY() < mapModel.getRowCnt(); relCoords.setY(relCoords.getY() + 1)) {

                absCoords.setX(mapModel.getViewportCoordsStart().getX() + relCoords.getX());
                absCoords.setY(mapModel.getViewportCoordsStart().getY() + relCoords.getY());
                bTilePartList = mapModel.getFromTileList(absCoords);
                if(bTilePartList == null)
                    continue;
                Collections.sort(bTilePartList, (a, b) -> a.getIndex().compareTo(b.getIndex()));//sort list on index
                for (TilePart tilePart : bTilePartList) {
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

