/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.ui.player.multiplayer;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tictactoe.ui.game.screen.GamescreenController;
import tictactoe.ui.game.screen.GamescreenController_Multi;
import tictactoe.ui.game.screen.game_screenBase;
import tictactoe.ui.home.offline.HomeScreen_offline;
import tictactoe.ui.home.offline.HomeScreen_offline_Controller;

/**
 * FXML Controller class
 *
 * @author A.Atia
 */
public class Multi_player_SceneController extends Multi_player_Scene {

    private Stage stage;
   

    public Multi_player_SceneController(Stage stage) {
        this.stage = stage;
        listen();
    }

    private void listen() {
        start_btn.setOnAction((ActionEvent event) -> {
            goToGame();
        });
        back_btn.setOnAction((ActionEvent event) -> {
            goToHome();
        });
    }

    private void goToGame() {
        game_screenBase game = new GamescreenController_Multi(stage,player1_textfield.getText(), player2_textfield.getText());
        Scene scene = new Scene(game);
        stage.setScene(scene);

    }

    private void goToHome() {


        HomeScreen_offline_Controller home = new HomeScreen_offline_Controller(stage);
      //  HomeScreen_offline home = new HomeScreen_offline_Controller(stage);

        Scene scene = new Scene(home);
        stage.setScene(scene);

    }

}
