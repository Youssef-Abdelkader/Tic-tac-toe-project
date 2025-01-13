/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.ui.player.singleplayer;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tictactoe.ui.game.screen.GamescreenController;
import tictactoe.ui.game.screen.game_screenBase;
import tictactoe.ui.home.offline.HomeScreen_offline;
import tictactoe.ui.home.offline.HomeScreen_offline_Controller;

/**
 * FXML Controller class
 *
 * @author A.Atia
 */

public class Single_player_sceneController extends Single_Player_Scene{
    private Stage stage;
   
    

    public Single_player_sceneController(Stage stage) {
        this.stage=stage;
        listen();
        
    }
    private void listen()
    {
         back_btn.setOnAction((ActionEvent event) -> {
             goToHome();
         });
         
         start_btn.setOnAction((ActionEvent event) -> {
             goToGame();
         });
    }
    private void goToGame() {
        //System.out.println(textField.getText());
        game_screenBase game = new GamescreenController(stage,textField.getText());
        Scene scene = new Scene(game);
        stage.setScene(scene);

    }

    private void goToHome() {

<<<<<<< HEAD
        HomeScreen_offline home = new HomeScreen_offline_Controller(stage);
=======
        HomeScreen_offline home = new HomeScreen_offline_Controller (stage);
>>>>>>> 4899b0d4a36d1e9fd893462c7d714c6bdae74cd7
        Scene scene = new Scene(home);
        stage.setScene(scene);

    }
    

   
}
