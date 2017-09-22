import Tiles.Container;
import Tiles.MovableTile;
import Tiles.Player;
import util.GridCoords;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class ObjectGrid extends JPanel {
    private int width, height, lineWidth, devideX, devideY;
    private int tileWidth;
    private int tileHeight;
    private Map<GridCoords, Tiles.Container> objectMap = new HashMap<GridCoords, Container>();

    private Tiles.Container getPlayerContainer(){
        Tiles.Container playerContainer = null;
        for (Map.Entry<GridCoords, Tiles.Container> me : objectMap.entrySet()) {
            //Tiles.Container b = (Tiles.Container) me.getValue();
            //Tiles.Tile b = me.getValue().getContentObject(1);
            if (me.getValue().getContentObject(1) instanceof Tiles.Player) {
                playerContainer = me.getValue();
            }
            //System.out.println(e.getKey() + ": " + e.getValue());

        }
        return playerContainer;
    }
    public ObjectGrid(int width, int height, int lineWidth, int devideX, int devideY, MouseListener mouseListener){
        super();
        this.setLayout(null);
        this.width=width;
        this.height=height;
        this.lineWidth=lineWidth;
        this.devideX=devideX;
        this.devideY=devideY;

        tileWidth=(width- lineWidth) / devideX;
        tileHeight=(height- lineWidth) / devideY;


        /*MouseListener ml = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                JComponent jc = (JComponent)e.getSource();
                TransferHandler th = jc.getTransferHandler();
                th.exportAsDrag(jc, e, TransferHandler.COPY);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        };*/

        this.removeAll();
        for(int i=0;i<devideX;i++) {
            for(int y=0;y<devideY;y++) {
                Tiles.Container Tile = new Tiles.Container(Tiles.Pavement.class, true);
                Tile.setLocationInGrid(i,y);

                Tile.addMouseListener(mouseListener);
                //Tile.addMouseListener(ml);
                Tile.setTransferHandler(new TransferHandler("content"));
                //Tile.setPreferredSize(new Dimension(tileWidth, tileHeight));
                Tile.setSize(tileWidth,tileHeight);
                Tile.setLocation(tileWidth * i, tileHeight*y);

                objectMap.put(Tile.getLocationInGrid(), Tile);
                this.add(Tile);
            }
        }


        //this.setFocusable(true);
        //this.grabFocus();
        this.revalidate();
        this.repaint();
    }

    public void HandleKey(KeyEvent e) {
        Tiles.Container playerContainer;
        GridCoords nextGridCoords = new GridCoords(0, 0), currentGridCoords = new GridCoords(0, 0);
        if ((playerContainer = getPlayerContainer()) == null) {
            return;

         /*for (Component c : ObjectGrid.this.getComponents()) {
             if (c instanceof Tiles.Player) {
                 player = (Tiles.Player)c;
             }
         }*/

        } else {
            Tiles.Tile bPlayerTile = playerContainer.getContentObject(1);
            if (bPlayerTile instanceof Tiles.Player) {
                Tiles.Player PlayerTile = (Player) bPlayerTile;
                //playerContainer.getContentObject()
                currentGridCoords.setX(playerContainer.getLocationInGrid().getX());
                currentGridCoords.setY(playerContainer.getLocationInGrid().getY());
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        PlayerTile.setDirection(MovableTile.Directions.Left);

                        nextGridCoords.setY(currentGridCoords.getY());
                        if ((nextGridCoords.setX(currentGridCoords.getX() - 1)) < 0)
                            nextGridCoords.setX(0);
                        break;
                    case KeyEvent.VK_RIGHT:
                        PlayerTile.setDirection(MovableTile.Directions.Right);

                        nextGridCoords.setY(currentGridCoords.getY());
                        if ((nextGridCoords.setX(currentGridCoords.getX() + 1)) > devideX - 1)
                            nextGridCoords.setX(devideX - 1);
                        break;
                    case KeyEvent.VK_UP:
                        PlayerTile.setDirection(MovableTile.Directions.Up);

                        nextGridCoords.setX(currentGridCoords.getX());
                        if ((nextGridCoords.setY(currentGridCoords.getY() - 1)) < 0)
                            nextGridCoords.setY(0);
                        break;
                    case KeyEvent.VK_DOWN:
                        PlayerTile.setDirection(MovableTile.Directions.Down);

                        nextGridCoords.setX(currentGridCoords.getX());
                        if ((nextGridCoords.setY(currentGridCoords.getY() + 1)) > devideY + 1)
                            nextGridCoords.setY(devideY + 1);
                        break;
                    default:
                        System.out.println("unknown key");
                        break;
                }
                Tiles.Container bTile = objectMap.get(nextGridCoords);
                if (bTile == null || bTile.getContentObject(0) == null || bTile.getContentObject(0).getCollisable() == true) {
                    //nextGridCoords = currentGridCoords;
                    nextGridCoords.setX(currentGridCoords.getX());
                    nextGridCoords.setY(currentGridCoords.getY());
                }

                bTile = objectMap.get(nextGridCoords);
                playerContainer.deleteObject(1);
                bTile.setObject(bPlayerTile, bPlayerTile.getIndex());
            }

        }
    }
    //public void paintComponent(Graphics g) {

        /*g.setColor(Color.gray);
        for(int i=0;i<=devideX;i++) {
            g.fillRect((width - lineWidth) / devideX * i, 0, lineWidth, height-lineWidth);
        }
        for(int i=0;i<=devideY;i++) {
            g.fillRect(0, (height- lineWidth) / devideY * i, width-lineWidth, lineWidth);
        }*/


    //}

}
