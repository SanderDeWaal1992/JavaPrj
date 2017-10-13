package scenes.editMap;

import remaining.GridCoords;
import tiles.mcWrapper.Tile;
import tiles.models.TilePart;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EditMap extends JPanel {
    int xCount, yCount;
    public class combine<T1, T2>{
        private T1 object1;
        private T2 object2;
        public combine( T1 object1, T2 object2){
            this.object1 = object1;
            this.object2 = object2;
        }
        public T1 getObject1(){
            return object1;
        }
        public T2 getObject2(){
            return object2;
        }
    }
    //private java.util.Map<GridCoords, tiles.mcWrapper.Tile> tileList = new HashMap<GridCoords,  tiles.mcWrapper.Tile>();
    private Map<remaining.GridCoords, combine<JLabel, tiles.mcWrapper.Tile>> tileList = new HashMap<remaining.GridCoords, combine<JLabel,  tiles.mcWrapper.Tile>>();

    //map.models.Map mapModel = new map.models.Map();
    private JPanel panel = new JPanel();
    public EditMap(int xCount, int yCount){
        super();
        this.xCount=xCount;
        this.yCount=yCount;

        panel.setLayout(new GridBagLayout());
        //this.setLayout(new GridBagLayout());

        this.add(panel);
        //update();
    }


    public void paintComponent(Graphics g){
        int sizeX = getTileXSize();
        int sizeY = getTileYSize();
        panel.setBackground(Color.red);

        for (combine<JLabel, tiles.mcWrapper.Tile> entry : tileList.values()) {
            //JLabel key = entry.getKey();
            //tiles.mcWrapper.Tile value = entry.getValue();
            entry.getObject1().setIcon(new ImageIcon(getImageOfTile(entry.getObject2().getModel(), sizeX, sizeY)));

            // ...
        }
/*
        remaining.GridCoords bCoord = new remaining.GridCoords();
        int sizeX = this.getWidth()/xCount;
        int sizeY = sizeX;
        //JLabel Tree_1 = new JLabel();
        JLabel b;// = new JLabel();
        this.setBackground(Color.red);
        //this.removeAll();
        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridwidth = 1;
        for (int y = 0; y < yCount; y++) {
            for (int x = 0; x < xCount; x++) {
                bCoord.setX(x);
                bCoord.setY(y);
                c.gridx=x;
                c.gridy=y;
                if(tileList.containsKey(bCoord)){
                    b = new JLabel();
                    b.setIcon(new ImageIcon(getImageOfTile(tileList.get(bCoord).getModel(), sizeX, sizeY)));
                    //b.setBackground(Color.green);
                    tileList.get(bCoord);
                    this.add(b,c);

                }
            }
        }*/
        super.paintComponent(g);
    }

    public tiles.mcWrapper.Tile getTile(int x, int y){
        remaining.GridCoords coord=new remaining.GridCoords(x/getTileXSize(), y/getTileYSize());

        if(tileList.containsKey(coord))
            return tileList.get(coord).getObject2();
        else
            return null;
    }

    public int getTileXSize(){
        return this.getWidth()/xCount;
    }
    public int getTileYSize(){
        return getTileXSize();
    }


    private int tileCnt = 0;
    private GridBagConstraints c = new GridBagConstraints();

    public void addInTileList(tiles.mcWrapper.Tile tile) {
        remaining.GridCoords bCoord;
        c.fill = GridBagConstraints.FIRST_LINE_START;
        //c.weightx = 0.5;
        //c.gridwidth = 1;
        int y, x;

        y=tileCnt/xCount;
        x=tileCnt%xCount;
        tileCnt++;
        bCoord = new remaining.GridCoords(x, y);
        //for (int y = 0; y < yCount; y++) {
        //    for (int x = 0; x < xCount; x++) {
        //        bCoord.setX(x);
        //        bCoord.setY(y);
                c.gridx=x;
                c.gridy=y;
                //if (!tileList.containsValue(bCoord)) {
                    JLabel b = new JLabel();
                    //tileList.put(new remaining.GridCoords(bCoord.getX(), bCoord.getY()), tile);
                    tileList.put(bCoord, new combine(b, tile));
                    panel.add(b,c);

         //           return;
                //}
         //   }
        //}
    }

    private BufferedImage getImageOfTile(String tileName, int sizeX, int sizeY){
        tiles.models.Tile tileModel=tiles.models.TileFactory.getTile(tileName);
        return getImageOfTile(tileModel,sizeX,sizeY);
    }
    private BufferedImage getImageOfTile(tiles.models.Tile TileModel, int sizeX, int sizeY){
        BufferedImage image=null;
        try {
            image = remaining.utilities.getImage(TileModel.getImgDir()+TileModel.getImg(), sizeX, sizeY);
        } catch (FileNotFoundException e) {
            try {
                image = remaining.utilities.getImage("./src/media/errorTile.png", sizeX, sizeY);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        return image;
    }


}
