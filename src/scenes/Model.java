package scenes;

public class Model {
    private SceneInterface sceneController;
    private SceneControllerInterface sceneHandlerController;
    public Model(SceneControllerInterface sceneHandlerController){
        this.sceneHandlerController = sceneHandlerController;
    }

    public void setSceneController(SceneInterface sceneController){
        this.sceneController= sceneController;
        sceneHandlerController.updateView();
    };
    public SceneInterface getSceneController(){
        return sceneController;
    };
}
