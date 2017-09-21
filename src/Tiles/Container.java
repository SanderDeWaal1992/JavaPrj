package Tiles;

import util.GridCoords;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

interface LocationInGrid {
    public GridCoords getLocationInGrid();
    public void setLocationInGrid(int h,int v);
}

public class Container extends JComponent implements LocationInGrid{
    Border blackline = BorderFactory.createLineBorder(Color.black);
    Tiles.Tile content[] = {null, null};
    Boolean fixedContent = false;
    private GridCoords locationInGrid  = new GridCoords(0, 0);

    public Container(Tiles.Tile defContent,MouseListener mouseListener, Boolean setable){
        super();
        this.setLayout(new BorderLayout());
        //this.addMouseListener(mouseListener);
        //this.setTransferHandler(new TransferHandler("content"));
        setContent(defContent, 0);
        fixedContent = !setable;

        this.setBorder(blackline);
        this.revalidate();
        this.repaint();
    }
    //public <T extends Tile> void setContent(Class<T> newContent, int index){
    public void setContent(Tiles.Tile newContent){
        setContent(newContent, 0);
    }
    public void setContent(Tiles.Tile newContent, int index){
        if(fixedContent)
            return;
        if(content.length>index && index>=0) {
            if(newContent==null || content[index] != newContent) {
                content[index] = null;
            }
        }else
            new ArrayIndexOutOfBoundsException();
        //if(newContent == null)
        //content = newContent;
        //this.removeAll();


        if(newContent != null) {
            //Tile bTile;
            //try {
                //bTile = (Tile)newContent.newInstance();
        //newContent.
            for(MouseListener ml: newContent.getMouseListeners()) //delete all mouselisteners. TODO: only delete the mouselisteners added in the Container class
                newContent.removeMouseListener(ml);
            newContent.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {}
                    @Override
                    public void mousePressed(MouseEvent e) {
                        e.setSource(Container.this);
                        Container.this.processMouseEvent(e);
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {}
                    @Override
                    public void mouseEntered(MouseEvent e) {}
                    @Override
                    public void mouseExited(MouseEvent e) {}
                });
                content[index] = newContent;
                this.add(newContent);
            /*} catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }*/
        }
        this.revalidate();
        this.repaint();
    }
    public Tiles.Tile getContent () {
        return getContent(0);
    }
    public Tiles.Tile getContent (int index){
        if(content.length<=index || index<0) new ArrayIndexOutOfBoundsException();
        return content[index];
    }

    public GridCoords getLocationInGrid(){
        return locationInGrid;
    }
    public void setLocationInGrid(int h,int v){
        locationInGrid.setX(h); locationInGrid.setY(v);
    }

}
