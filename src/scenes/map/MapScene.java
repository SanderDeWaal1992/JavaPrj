package scenes.map;

import javax.swing.*;

public class MapScene implements scenes.SceneInterface {
    private scenes.SceneInterface mapSceneController;
    private JPanel panel;
    public MapScene(){
        mapSceneController = new map.controllers.Map();

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(mapSceneController.getView());
    }

    @Override
    public JPanel getView() {
        return panel;
    }
}
