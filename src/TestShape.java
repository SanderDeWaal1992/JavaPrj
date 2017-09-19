import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class TestShape extends JPanel {
    int width, height, lineWidth, devideX, devideY;
    int tileWidth;
    int tileHeight;

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

        this.removeAll();
        for(int i=0;i<devideX;i++) {
            for(int y=0;y<devideY;y++) {
                Tiles.Container Tile = new Tiles.Container(null, mouseListener);
                //Tile.setPreferredSize(new Dimension(tileWidth, tileHeight));
                Tile.setSize(tileWidth,tileHeight);
                Tile.setLocation(tileWidth * i, tileHeight*y);
                this.add(Tile);
            }
        }
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
