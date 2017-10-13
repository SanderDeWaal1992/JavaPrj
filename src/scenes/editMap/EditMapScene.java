package scenes.editMap;


import remaining.Combine2Objects;
import remaining.GridCoords;
import tiles.mcWrapper.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;

public class EditMapScene implements scenes.SceneInterface {
    private Map mapSceneController;
    private ArrayList<Combine2Objects<String, String>> tileList = new ArrayList<Combine2Objects<String, String>>();

    private tiles.mcWrapper.Tile TargetTile;

    public EditMapScene() {
        mapSceneController = new Map();
        tileList.add(new Combine2Objects<String, String>("FIXEDTILE", "Tree_1"));
        tileList.add(new Combine2Objects<String, String>("FIXEDTILE", "House_1"));
        tileList.add(new Combine2Objects<String, String>("MOVABLETILE", "Water_1"));
        tileList.add(new Combine2Objects<String, String>("FIXEDTILE", "Human_1"));
    }

    private JPanel panel = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {

            this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

            int sizeX = 50;//mapSceneController.getView().getTileWidth();
            int sizeY = 50;//mapSceneController.getView().getTileHeight();


            this.removeAll();

            EditMap editPanel = new EditMap(3, 3);
            editPanel.setMaximumSize(new Dimension(sizeX * 3, this.getHeight()));
            editPanel.setPreferredSize(new Dimension(sizeX * 3, this.getWidth()));


            //tiles.factories.FactoryProducer.getFactory("FIXEDTILE").getFixedTile("Tree_1", new remaining.GridCoords(0,0), mapSceneController, mapSceneController.getMapModel());
            editPanel.addInTileList(tiles.factories.FactoryProducer.getFactory("FIXEDTILE").getFixedTile("Tree_1", new remaining.GridCoords(), mapSceneController, mapSceneController.getMapModel()));
            editPanel.addInTileList(tiles.factories.FactoryProducer.getFactory("FIXEDTILE").getFixedTile("House_1", new remaining.GridCoords(), mapSceneController, mapSceneController.getMapModel()));
            editPanel.addInTileList(tiles.factories.FactoryProducer.getFactory("MOVABLETILE").getMovableTile("Water_1", new remaining.GridCoords(), mapSceneController, mapSceneController.getMapModel()));
            editPanel.addInTileList(tiles.factories.FactoryProducer.getFactory("FIXEDTILE").getFixedTile("Human_1", new remaining.GridCoords(), mapSceneController, mapSceneController.getMapModel()));

        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent me){
                if(TargetTile!=null && (me.getX()<=mapSceneController.getView().getWidth() || me.getY()<=mapSceneController.getView().getHeight())) {
                    int xInMap = me.getX();
                    int yInMap = me.getY();
                    mapSceneController.moveTileInTileList(mapSceneController.getCoords(xInMap, yInMap), TargetTile);
                    //mapSceneController.updateView();
                }
            }
        });
        this.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {

            }
            public void mousePressed(MouseEvent me) {
                if(me.getX()<=mapSceneController.getView().getWidth() && me.getY()<=mapSceneController.getView().getHeight()) { // press in mapPanel
                    TargetTile = null;
                } else{ //press in menuPanel
                    TargetTile = editPanel.getTile(me.getX()-mapSceneController.getView().getWidth(), me.getY());
                }
            }
        });

            this.add(mapSceneController.getView());
            this.add(editPanel);

            editPanel.revalidate();
            editPanel.repaint();
            //this.revalidate();
            //this.repaint();
            super.paintComponent(g);
        }
    };

    public Boolean handleKey(KeyEvent e) {
        return mapSceneController.handleKey(e);
    }

    @Override
    public JPanel getView() {
        return panel;
    }

}


