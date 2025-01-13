/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.ui.home.offline;

import connection.Connection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tictactoe.TicTacToe;
import tictactoe.ui.auth.login.LoginController;
import tictactoe.ui.player.multiplayer.Multi_player_SceneController;
import tictactoe.ui.player.singleplayer.Single_player_sceneController;

/**
 *
 * @author yosef
 */
public class HomeScreen_offline_Controller extends HomeScreen_offline {

    public HomeScreen_offline_Controller(Stage stage) {
        super(stage);

        OnlineBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TicTacToe.online = true;

                boolean connected = Connection.setConnection();
                if (connected) {
                    LoginController account = new LoginController(stage);
                    Scene scene = new Scene(account);
                    stage.setScene(scene);
                }

                // Start a thread to listen for messages from the server
                /*new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while (true) {
                                    String user = ear.readLine();
                                    //System.out.println(user);
                                    if (user != null) {
                                        Platform.runLater(new Runnable() {
                                            @Override
                                            public void run() {
                                                System.out.println(user);
                                            }
                                        });
                                    }
                                }
                            } catch (IOException ex) {
                                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }).start();
                    ******THIS CODE WILL BE USED IN EVERY BUTTON HANDLER*******/
            }
        });

        MultiPBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Multi_player_SceneController multi = new Multi_player_SceneController(stage);
                Scene scene = new Scene(multi);
                stage.setScene(scene);
            }
        });

        singlePBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Single_player_sceneController single = new Single_player_sceneController(stage);
                Scene scene = new Scene(single);
                stage.setScene(scene);
            }
        });
    }

}
