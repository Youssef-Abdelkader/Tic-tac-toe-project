package tictactoe;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class scenecontroller {
    
    private Stage stage;
    private Scene scene;

    // Cache for scenes
    private Parent serverScene;
    private Parent serverScene2;
    private Parent Login;
    private Parent Signup;
    private Stage s, s1;

    public void switchToScene2(ActionEvent event) {
        // Initialize the scene if it's null
        if (serverScene2 == null) {
            serverScene2 = new Serverscene2Base(s1); // Lazy initialization for Scene 2 (Pie Chart)
        }
        updateScene(event, serverScene2);
    }

    public void switchToScene1(ActionEvent event) {
        // Initialize the scene if it's null
        if (serverScene == null) {
           serverScene = new serverscene(s); // Lazy initialization for Scene 1 (Main Menu)
        }
        updateScene(event, serverScene);
    }

    private void updateScene(ActionEvent event, Parent root) {
        try {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.println("Error switching scenes: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
