import util.GridCoords;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class testFormR01 {
    private JTextPane helloThisIsATextPane;
    private JSlider slider1;
    private JPanel panel1;
    public testFormR01(){
        super();


        System.out.println("It works4");

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        //new ObjectGrid();
    }

    public static void main(String[] args){

        MouseListener ml = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                JComponent jc = (JComponent)e.getSource();
                TransferHandler th = jc.getTransferHandler();
                th.exportAsDrag(jc, e, TransferHandler.COPY);
                //Tiles.Tile b=((Tiles.Container)jc).getContent();

            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        };

        JSplitPane splitPane;

        System.out.println("It works1");
        System.out.println("It works2");
        System.out.println("It works3");

        //new ObjectGrid(frame.getGraphics());
        //frame.setContentPane(new ObjectGrid(400,400, 4, 5,5));


        /*//ImageIcon icon = createImageIcon("images/Cat.gif");
        SizeDisplayer sd1 = new SizeDisplayer("left", frame);
        sd1.setMinimumSize(new Dimension(400,400));
        sd1.setFont(font);

        //icon = createImageIcon("images/Dog.gif");
        SizeDisplayer sd2 = new SizeDisplayer("right", frame);
        sd2.setMinimumSize(new Dimension(400,400));
        sd2.setFont(font);*/

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setEnabled(true);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel ts= new JPanel();
        ts.setMinimumSize(new Dimension(200, 1200));
        ts.setPreferredSize(new Dimension(200, 1200));
        //ts.add(new Tiles.Container(new Tiles.Tree(100,100), ml));
        //ts.add(new Tiles.Container(new Tiles.Water(100,100), ml));
        //ts.add(new Tiles.Container(new Tiles.Wall(100,100), ml));
        ts.setLayout(new FlowLayout());
        Tiles.Container buf0 = new Tiles.Container(Tiles.Pavement.class, false);
        buf0.addMouseListener(ml);
        buf0.setTransferHandler(new TransferHandler("content"));
        buf0.setPreferredSize(new Dimension(100, 100));
        ts.add(buf0);
        Tiles.Container buf4 = new Tiles.Container(Tiles.Player.class, false);
        buf4.addMouseListener(ml);
        buf4.setTransferHandler(new TransferHandler("content"));
        buf4.setPreferredSize(new Dimension(100, 100));
        ts.add(buf4);
        Tiles.Container buf1 = new Tiles.Container(Tiles.Tree.class, false);
        buf1.addMouseListener(ml);
        buf1.setTransferHandler(new TransferHandler("content"));
        buf1.setPreferredSize(new Dimension(100, 100));
        ts.add(buf1);
        Tiles.Container buf2 = new Tiles.Container(Tiles.Water.class, false);
        buf2.addMouseListener(ml);
        buf2.setTransferHandler(new TransferHandler("content"));
        buf2.setPreferredSize(new Dimension(100, 100));
        ts.add(buf2);
        Tiles.Container buf3 = new Tiles.Container(Tiles.Wall.class, false);
        buf3.addMouseListener(ml);
        buf3.setTransferHandler(new TransferHandler("content"));
        buf3.setPreferredSize(new Dimension(100, 100));
        ts.add(buf3);



        //ts.add(new Tiles.Container(Tiles.Water.class, ml));
        //ts.add(new Tiles.Container(Tiles.Wall.class, ml));
        ts.revalidate();
        ts.repaint();
        scrollPane.setViewportView(ts);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(200/600);
        splitPane.setOneTouchExpandable(true);
        splitPane.setContinuousLayout(true);

        ObjectGrid ts2= new ObjectGrid(400,400, 4, 5,5, ml);
        ts2.setMinimumSize(new Dimension(400, 400));
        ts2.setPreferredSize(new Dimension(400, 400));

        /**BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("./src/media/tree1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        myPicture = resize(myPicture, 400/5, 400/5);*/



        //Tiles.Container label1 = new Tiles.Container(Tiles.Tree.class, ml);//new Tiles.Tree(400/5, 400/5);
        //label2.setBounds(250, 130, 100, 100);
        //label1.addMouseListener(ml);
        //label1.setTransferHandler(new TransferHandler("toolTipString"));
        //ts2.add(label1);

        //Tiles.Container label2 = new Tiles.Container(Tiles.Wall.class, ml);//new Tiles.Wall(400/5, 400/5);
        //label2.setBounds(250, 130, 100, 100);
        //label2.addMouseListener(ml);
        //label2.setTransferHandler(new TransferHandler("toolTipString"));
        //ts2.add(label2);

        //Tiles.Container label3 = new Tiles.Container(Tiles.Water.class, ml);//new Tiles.Water(400/5, 400/5);
        //label3.addMouseListener(ml);
        //label3.setTransferHandler(new TransferHandler("toolTipString"));
        //ts2.add(label3);


        splitPane.add(ts2);
        splitPane.add(scrollPane);

        JFrame frame = new JFrame("testFormR01");
        //frame.setContentPane(new testFormR01().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(650,440);
        frame.setVisible(true);
        frame.setLocation(1930,10);
        frame.setContentPane(splitPane);
        //frame.add(createControlPanel(), BorderLayout.PAGE_END);


        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN )
                    ts2.HandleKey(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
}
