import Tiles.Container;
import util.GridCoords;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class TestShape extends JPanel {
    private int width, height, lineWidth, devideX, devideY;
    private int tileWidth;
    private int tileHeight;
    private Map<GridCoords, Tiles.Container> objectMap = new HashMap<GridCoords, Container>();

    private Tiles.Container getPlayerContainer(){
        Tiles.Container playerContainer = null;
        for (Map.Entry<GridCoords, Tiles.Container> me : objectMap.entrySet()) {
            //Tiles.Container b = (Tiles.Container) me.getValue();
            if (me.getValue().getContent(1) instanceof Tiles.Player) {
                playerContainer = me.getValue();
            }
            //System.out.println(e.getKey() + ": " + e.getValue());

        }
        return playerContainer;
    }
    public TestShape(int width, int height, int lineWidth, int devideX, int devideY,MouseListener mouseListener){
        super();
        this.setLayout(null);
        this.width=width;
        this.height=height;
        this.lineWidth=lineWidth;
        this.devideX=devideX;
        this.devideY=devideY;

        tileWidth=(width- lineWidth) / devideX;
        tileHeight=(height- lineWidth) / devideY;


        MouseListener ml = new MouseListener() {

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
        };

        this.removeAll();
        for(int i=0;i<devideX;i++) {
            for(int y=0;y<devideY;y++) {
                Tiles.Container Tile = new Tiles.Container(null, mouseListener, true);
                Tile.setLocationInGrid(i,y);

                Tile.addMouseListener(mouseListener);
                Tile.addMouseListener(ml);
                Tile.setTransferHandler(new TransferHandler("content"));
                //Tile.setPreferredSize(new Dimension(tileWidth, tileHeight));
                Tile.setSize(tileWidth,tileHeight);
                Tile.setLocation(tileWidth * i, tileHeight*y);

                objectMap.put(Tile.getLocationInGrid(), Tile);
                this.add(Tile);
            }
        }

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                Tiles.Container playerContainer;
                GridCoords nextGridCoords = new GridCoords(0,0), currentGridCoords;
                if((playerContainer = getPlayerContainer())==null)
                    return;

                /*for (Component c : TestShape.this.getComponents()) {
                    if (c instanceof Tiles.Player) {
                        player = (Tiles.Player)c;
                    }
                }*/

                else {
                    currentGridCoords = playerContainer.getLocationInGrid();
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT:
                            nextGridCoords.setY(currentGridCoords.getY());
                            if((nextGridCoords.setX(currentGridCoords.getX() - 1))<0)
                                nextGridCoords.setX(0);
                            break;
                        case KeyEvent.VK_RIGHT:
                            nextGridCoords.setY(currentGridCoords.getY());
                            if((nextGridCoords.setX(currentGridCoords.getX() + 1))>devideX)
                                nextGridCoords.setX(devideX);
                            break;
                        case KeyEvent.VK_UP:
                            nextGridCoords.setX(currentGridCoords.getX());
                            if((nextGridCoords.setY(currentGridCoords.getY() - 1))<0)
                                nextGridCoords.setY(0);
                            break;
                        case KeyEvent.VK_DOWN:
                            nextGridCoords.setX(currentGridCoords.getX());
                            if((nextGridCoords.setY(currentGridCoords.getY() + 1))>devideY)
                                nextGridCoords.setY(devideY);
                            break;
                        default:
                            System.out.println("unknown key");
                            break;
                    }
                    Tiles.Container bTile = objectMap.get(nextGridCoords);
                    if(bTile.getContent(0).getCollisable() == true)
                        nextGridCoords=currentGridCoords;
                    Tiles.Tile bPlayerTile = playerContainer.getContent(1);
                    if(bPlayerTile instanceof Tiles.Player)
                        bTile.setContent(bPlayerTile,1);



                }
            }
        });

        this.revalidate();
        this.repaint();
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
