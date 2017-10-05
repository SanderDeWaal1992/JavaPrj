package scenes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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

    public View(SceneControllerInterface sceneHandlerController, Model model) {
        this.model = model;
        this.sceneHandlerController = sceneHandlerController;

        // Create and initialize application window
        frame = new JFrame("scene wrapper window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(650, 440);
        //frame.setVisible(true);
        frame.setLocation(1930, 10);

    }

    private void setPanel(){
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        //initialise content pane
        model.getSceneController().getView().setMinimumSize(new Dimension(400, 400));
        model.getSceneController().getView().setPreferredSize(new Dimension(400, 400));
        model.getSceneController().getView().setFocusable(true);

        //initialise menu panel
        MenuPanel = new JPanel();
        MenuPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        MenuPanel.setPreferredSize(new Dimension(400, 50));
        MenuPanel.setMinimumSize(new Dimension(400, 50));
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

        panel.add(MenuPanel);
        panel.add(contentPanel);
        frame.setContentPane(panel);

        frame.setVisible(true);
        //model.getSceneController().getView().requestFocusInWindow();
    }
    public void update(){
        contentPanel = model.getSceneController().getView();
        if(panel==null) setPanel();
        contentPanel.requestFocusInWindow();
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
