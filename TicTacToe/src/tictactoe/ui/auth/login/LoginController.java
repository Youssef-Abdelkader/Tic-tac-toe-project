/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.ui.auth.login;

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
import tictactoe.ui.home.online.HomeOnlineController;

/**
 *
 * @author yosef
 */
public class LoginController extends Login {
    
    Socket server;
    DataInputStream ear;
    PrintStream mouth;
    
    
    public LoginController(Stage stage) {
        super(stage);
        
        try {
            server = new Socket("127.0.0.1", 5005);
            ear = new DataInputStream(server.getInputStream());
            mouth = new PrintStream(server.getOutputStream());

            mouth.println("Hello");
            // Start a thread to listen for messages from the server
            new Thread(new Runnable() {
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

        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

       
        
        
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
                
                
                
                
                //*************************
                
                String user = nameText.getText();
                if (!user.isEmpty()) {
                    //txta.appendText("You: " + message + "\n");
                    mouth.println(user);
                   
            }
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
