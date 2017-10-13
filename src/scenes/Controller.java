package scenes;

import javax.swing.*;

public class Controller implements SceneControllerInterface {
    private final Model model;
    private final View view;

    public Controller() {
        model = new Model(this);
        view = new View(this, model);

        setScene("MAPSCENE");

        view.setActionHandler((actionDescription)->actionHandler(actionDescription));

    }

    public void setScene(String scene){
        SceneInterface sceneController;
        if(scene.equalsIgnoreCase("MAPSCENE")) {
            // Create and initialize map
            sceneController = new scenes.map.MapScene();
        } else if(scene.equalsIgnoreCase("EDITMAPSCENE")) {
            // Create and initialize map
            sceneController = new scenes.editMap.EditMapScene();
        } else
            return;
        model.setSceneController(sceneController);
    }
    public void updateView(){
        view.update();
    }
    public void setVisible(Boolean visible){
        view.setVisible(visible);
    }

    private Boolean actionHandler(String actionDescription){
        if (actionDescription.equalsIgnoreCase("SETEditMapScene")) {
            view.setButtonStateEditmap(false);
            view.setButtonStatePlaymap(true);
            setScene("EditMapScene");
        } else if (actionDescription.equalsIgnoreCase("SETPlayMapScene")) {
            view.setButtonStateEditmap(true);
            view.setButtonStatePlaymap(false);
            setScene("MAPSCENE");
        }
        return true;
    }


}
