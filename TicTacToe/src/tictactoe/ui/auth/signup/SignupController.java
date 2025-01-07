/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.ui.auth.signup;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tictactoe.ui.home.offline.HomeScreen_offline;
import tictactoe.ui.home.online.HomeOnlineController;

/**
 *
 * @author yosef
 */
public class SignupController extends Signup {
    
    public SignupController(Stage stage) {
        super(stage);
        
        signUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomeOnlineController home = new HomeOnlineController(stage);
                Scene scene = new Scene(home);
                stage.setScene(scene);
            }
        });
        
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomeScreen_offline home = new HomeScreen_offline(stage);
                Scene scene = new Scene(home);
                stage.setScene(scene);
            }   
        });
    }
    
}
