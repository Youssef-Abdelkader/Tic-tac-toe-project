package server;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author Malak Raaof
 */
public class Main extends Application {


    public Main() {

        
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = new serverscene_Controller(stage);

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
        new Main();
    }

}