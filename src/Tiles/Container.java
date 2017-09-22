package Tiles;

import util.GridCoords;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

interface LocationInGrid {
    public GridCoords getLocationInGrid();
    public void setLocationInGrid(int h,int v);
}

public class Container extends JComponent implements LocationInGrid{
    private Border blackline = BorderFactory.createLineBorder(Color.black);
    private Tiles.Tile content[] = {null, null};
    private Class dragableContent = null;
    private Boolean fixedContent = false;
    private GridCoords locationInGrid  = new GridCoords(0, 0);

    public Container(Class defContent, Boolean setable){
        super();
        this.setLayout(new OverlayLayout(this));
        //this.addMouseListener(mouseListener);
        //this.setTransferHandler(new TransferHandler("content"));
        setContent(defContent);
        fixedContent = !setable;

        this.setBorder(blackline);
        this.revalidate();
        this.repaint();
    }
    public void setSize(int width, int height){
        super.setSize(width, height);
        paintContent();
    }
    /*public void setPreferredSize(Dimension dimension){
        super.setPreferredSize(dimension);

    }*/
    public <T extends Tile> void setObject(T newContent, int index) {
//for(MouseListener ml: newContent.getMouseListeners()) //delete all mouselisteners. TODO: only delete the mouselisteners added in the Container class
        //    newContent.removeMouseListener(ml);
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
        content[index]=newContent;

        paintContent();

    }
    private Boolean validateContentIndex(int index){
        return (content.length>index && index>=0);
    }
    public <T extends Tile> void deleteObject(int index) {
        if(!validateContentIndex(index)) return;
        content[index]=null;
        paintContent();
    }
    private void paintContent(){
        int i = 0;
        int l = this.getWidth()/2-2;
        this.removeAll();


        for(Tile object: content){
            if(object!=null) {

                //object.setLocation(0,0);
                if(i==1) object.setAlignmentX(10);


                object.setSize(l, l);
                //object.setAlignmentX(10);
                object.setBounds(0, 0,
                        l, l);

                this.add(object);        object.revalidate();

                i++;
            }
        }
        this.revalidate();
        this.repaint();
    }
    public <T extends Tile> void setContent(Class<T> newContent){
    //public void setContent(Tiles.Tile newContent){
        if(fixedContent)
            return;
        dragableContent = newContent;
        setContent2(newContent, 0);
    }
    public class MyClass {
        public MyClass(Integer l, Integer i) {
            System.out.println(" hi"+l+" " +i);
        }
    }
    public <T extends Tile> void setContent2(Class<T> newContent, int index){
        if(!validateContentIndex(index)) new ArrayIndexOutOfBoundsException();

        //public void setContent(Tiles.Tile newContent, int index){

        //if(content.length>index && index>=0) {
            /*if(newContent==null || content[index] != newContent) {
                content[index] = null;
            }*/
        //}else
        //    new ArrayIndexOutOfBoundsException();
        //if(newContent == null)
        //content = newContent;


        if(newContent != null) {
            Tile bTile;
            try {
                Class bMyClass= MyClass.class;
                Class bList[] = new Class[2];
                bList[0]=int.class;
                bList[1]=int.class;
                Constructor bConstructor;

                bConstructor = newContent.getDeclaredConstructor(bList);

                bTile = (Tile) bConstructor.newInstance(this.getWidth(),this.getHeight());

                //bTile = (Tile) newContent.newInstance();
                setObject(bTile, bTile.getIndex());
                //newContent.
                //for(MouseListener ml: newContent.getMouseListeners()) //delete all mouselisteners. TODO: only delete the mouselisteners added in the Container class
                //    newContent.removeMouseListener(ml);
                /*bTile.addMouseListener(new MouseAdapter() {
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
                });*/
                //content[index] = bTile;
                //dragableContent = bTile;
                //this.add(bTile);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }
    public Class getContent () {
        return dragableContent;
        //return getContent2(0);
    }
    public Tiles.Tile getContentObject(int index){
        if(!validateContentIndex(index)) return null;
        return content[index];
    }

    public GridCoords getLocationInGrid(){
        return locationInGrid;
    }
    public void setLocationInGrid(int h,int v){
        locationInGrid.setX(h); locationInGrid.setY(v);
    }

}
