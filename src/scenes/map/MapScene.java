package scenes.map;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MapScene implements scenes.SceneInterface {
    private scenes.SceneInterface mapSceneController;
    private JPanel panel;
    public MapScene(){
        mapSceneController = new map.controllers.Map();

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(mapSceneController.getView());



        //mapSceneController.requestFocusInWindow();
    }


    public Boolean handleKey(KeyEvent e) {
        return mapSceneController.handleKey(e);
    }

    @Override
    public JPanel getView() {
        return panel;
    }

    public void updateView() {
        ;
    }
}
