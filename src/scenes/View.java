package scenes;

import scenes.map.MapScene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.Function;

import static java.awt.Component.LEFT_ALIGNMENT;

public class View {
    SceneControllerInterface sceneHandlerController;
    Model model;
    JFrame frame;
    JButton b1, b2;
    JPanel panel;
    JPanel contentPanel;
    JPanel MenuPanel;
    KeyListener keylistener;
    public View(SceneControllerInterface sceneHandlerController, Model model) {
        this.model = model;
        this.sceneHandlerController = sceneHandlerController;

        // Create and initialize application window
        frame = new JFrame("scene wrapper window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setMinimumSize(new Dimension(650, 450));
        frame.getContentPane().setPreferredSize(new Dimension(650, 450));
        frame.pack();
        //frame.setVisible(true);
        frame.setLocation(1930, 10);
        keylistener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {
                Boolean handled = model.getSceneController().handleKey(e);
            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        };

    }

    private void setPanel(){
        final int menuPanelHeight = 50;
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        //initialise content pane
        model.getSceneController().getView().setMinimumSize(new Dimension(650, frame.getContentPane().getHeight()-menuPanelHeight));
        model.getSceneController().getView().setPreferredSize(new Dimension(650, frame.getContentPane().getHeight()-menuPanelHeight));


        //initialise menu panel
        MenuPanel = new JPanel();
        MenuPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        MenuPanel.setPreferredSize(new Dimension(650, menuPanelHeight));
        MenuPanel.setMinimumSize(new Dimension(650, menuPanelHeight));
        b1 = new JButton("Edit map");
        b1.setVerticalTextPosition(AbstractButton.CENTER);
        b1.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        //b1.setMnemonic(KeyEvent.VK_D);
        b1.addActionListener(R->actionPerformed(R));
        b1.setActionCommand("SETEditMapScene");
        MenuPanel.add(b1);

        b2 = new JButton("Play map");
        b2.setVerticalTextPosition(AbstractButton.CENTER);
        b2.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        //b2.setMnemonic(KeyEvent.VK_D);
        b2.setActionCommand("SETPlayMapScene");
        b2.addActionListener(R->actionPerformed(R));
        MenuPanel.add(b2);

        //frame.setVisible(false);
        //panel.add(MenuPanel);

        //panel.add(model.getSceneController().getView());
        //frame.setContentPane(panel);

        //frame.setVisible(true);
        //panel.setVisible(false);
        //panel.removeAll();
        //frame.removeAll();
        //b1.setAlignmentX(LEFT_ALIGNMENT);
        //contentPanel = model.getSceneController().getView();
        //contentPanel = new JPanel();

        panel.add(MenuPanel);
        panel.add(contentPanel);
        frame.setContentPane(panel);
        frame.revalidate();

        //contentPanel.revalidate();
        //contentPanel.repaint();
        frame.setVisible(true);

        //model.getSceneController().getView().requestFocusInWindow();
    }
    public void update(){


        //if(panel==null){
            contentPanel = model.getSceneController().getView();

        model.getSceneController().getView().setMinimumSize(new Dimension(650, frame.getContentPane().getHeight()-50));
        model.getSceneController().getView().setPreferredSize(new Dimension(650, frame.getContentPane().getHeight()-50));
            setPanel();
       // } //else {
            /*panel.remove(contentPanel);
            contentPanel = null;
            panel.revalidate();
            contentPanel = model.getSceneController().getView();
            panel.add(contentPanel);*/
       /* contentPanel = null;
        panel.removeAll();
        contentPanel = model.getSceneController().getView();
        panel.add(MenuPanel);
        panel.add(contentPanel);
        panel.revalidate();*/

        //}
        panel.setFocusable(true);

        panel.removeKeyListener(keylistener);
        panel.addKeyListener(keylistener);
        panel.requestFocusInWindow();
        contentPanel.revalidate();
        contentPanel.repaint();
        panel.revalidate();
        panel.repaint();
        frame.revalidate();
        frame.repaint();

    }

    public void actionPerformed(ActionEvent e) {
        Boolean result = actionHandler.apply(e.getActionCommand());
    }


    public void setButtonStateEditmap(Boolean state){
        b1.setEnabled(state);
    };
    public void setButtonStatePlaymap(Boolean state){
        b2.setEnabled(state);
    };

    private Function<String, Boolean> actionHandler;
    public void setActionHandler(Function<String, Boolean> actionHandler){
        this.actionHandler = actionHandler;
    }

    public void setVisible(Boolean visible){
        //frame.setVisible(true);
    };

}
