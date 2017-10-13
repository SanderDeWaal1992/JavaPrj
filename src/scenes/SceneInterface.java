package scenes;

import javax.swing.*;
import java.awt.event.KeyEvent;

public interface SceneInterface {
    public <T extends JPanel & map.views.Interface>T getView();
    public Boolean handleKey(KeyEvent e);
    //public void updateView();
}
