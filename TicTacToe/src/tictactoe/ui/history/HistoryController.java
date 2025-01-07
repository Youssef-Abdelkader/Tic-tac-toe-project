/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.ui.history;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tictactoe.ui.home.online.HomeOnline;
import tictactoe.ui.home.online.HomeOnlineController;

/**
 * FXML Controller class
 *
 * @author habib
 */
public class HistoryController extends History{

    /**
     * Initializes the controller class.
     */

    public HistoryController(Stage stage) {
        super(stage);
        history_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomeOnlineController home = new HomeOnlineController(stage);
                Scene scene = new Scene(home);
                stage.setScene(scene);
            }   
        });
    }

    /**
     * Initializes the controller class.
     */
      
    
}
