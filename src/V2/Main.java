package V2;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){
        // Create and initialise map view
        /*V2.Views.Map mapView = new V2.Views.Map();
        mapView.setMinimumSize(new Dimension(400, 400));
        mapView.setPreferredSize(new Dimension(400, 400));*/

        // Create and initialize map model
        V2.Models.Map mapModel = new V2.Models.Map();

        // Create and initialize map controller
        V2.Controllers.Map mapController = new V2.Controllers.Map(mapModel);

        // Initialise map view
        V2.Views.Map mapView = mapController.getView();
        mapView.setMinimumSize(new Dimension(400, 400));
        mapView.setPreferredSize(new Dimension(400, 400));
        mapView.setFocusable(true);


        // Create and initialize application window
        JFrame frame = new JFrame("frame 1 name");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(650,440);
        frame.setVisible(true);
        frame.setLocation(1930,10);
        frame.setContentPane(mapView);
        mapView.requestFocusInWindow();
    }
}
