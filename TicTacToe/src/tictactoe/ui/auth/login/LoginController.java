/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.ui.auth.login;

import connection.Connection;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tictactoe.ui.home.offline.HomeScreen_offline;
import tictactoe.ui.auth.signup.SignupController;
import tictactoe.ui.home.offline.HomeScreen_offline_Controller;
import tictactoe.ui.home.online.HomeOnlineController;

/**
 *
 * @author yosef
 */
public class LoginController extends Login {

    public LoginController(Stage stage) {
        super(stage);

        signUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SignupController signup = new SignupController(stage);
                Scene scene = new Scene(signup);
                stage.setScene(scene);
            }
        });

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomeOnlineController home = new HomeOnlineController(stage);
                Scene scene = new Scene(home);
                stage.setScene(scene);

                String user = nameText.getText();
                String userpass = passwordText.getText();
                String con = "login" + "###" + user + "###" + userpass;
                if (!con.isEmpty()) {
                    //txta.appendText("You: " + message + "\n");
                    Connection.mouth.println(con);
                }
            }
        });

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomeScreen_offline_Controller home = new HomeScreen_offline_Controller(stage);
                Scene scene = new Scene(home);
                stage.setScene(scene);
            }
        });
    }

}
