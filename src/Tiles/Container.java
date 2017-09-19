package Tiles;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseListener;

public class Container extends JPanel {
    Border blackline = BorderFactory.createLineBorder(Color.black);
    Class content = null;
    public Container(Class defContent,MouseListener mouseListener){
        super();
        //this.setPreferredSize(new Dimension(100, 100));
        //this.setSize(100,100);
        this.setLayout(new BorderLayout());
        setContent(defContent);
        this.addMouseListener(mouseListener);
        this.setBorder(blackline);
        //this.setLayout(null);
        this.setTransferHandler(new TransferHandler("content"));

        //this.setSize(100,100);
        this.revalidate();
        this.repaint();
    }
    public <T extends Tile> void setContent(Class<T> newContent){
        //this.removeAll();
        //this.add(new Tree());
        //this.revalidate();
        //this.repaint();
    //public void setContent (Class newContent){
        //if(!newContent.isAssignableFrom(Tile.class))
        //    return;

        content = newContent;
        if(content !=null) {
            //this.add(new Tree(100,100));
            this.removeAll();

            try {
                //this.add((Tile)content.newInstance("",100,100));
                this.add((Tile)newContent.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            //content.revalidate();
            //content.repaint();
        }
        this.revalidate();
        this.repaint();
    }
    public Class getContent (){
        return content;
        //return contentClass;
    }
}
