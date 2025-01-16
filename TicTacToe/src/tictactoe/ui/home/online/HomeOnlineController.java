/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.ui.home.online;

import connection.Connection;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import tictactoe.ui.history.HistoryController;
import tictactoe.ui.home.offline.HomeScreen_offline;
import tictactoe.ui.home.offline.HomeScreen_offline_Controller;

/**
 * FXML Controller class
 *
 * @author habib
 */
public class HomeOnlineController extends HomeOnline {

    public HomeOnlineController(Stage stage) {
        super(stage);
        
         listView.setOnMouseClicked(((event) -> {

            String[] player = listView.getSelectionModel().getSelectedItem().split(" - Score: ");
            
            System.out.println(Arrays.toString(player));
            
            String name = player[0];
            String score = player[1];

            System.out.println("Name: " + name);
            System.out.println("Score: " + score);
            Thread th = new Thread(() -> {
                Connection.sendRequest("sendRequest" + "###" + name);
            });
            th.setDaemon(true);
            th.start();

        }));
        
        
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //boolean isRunning = true;
                    while (true) {
                        String message = Connection.ear.readLine();
                        System.out.println(message + "\n");
                        String[] data = message.split("###");
                        if (data[1].equals(" wants to play against you")) { // Use equals() for string comparison

                           if (!Platform.isFxApplicationThread()) {
                                Platform.runLater(() -> {
                                    System.out.println("Inside Platform.runLater()");  // Debugging line NOT PRINTED*******************
                                    System.out.println(message); // NOT PRINTED*******************
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Confirmation Dialog");
                                    alert.setHeaderText("Challenge Request");
                                    alert.setContentText("Are you sure you want to play against " + data[0]);

                                    // Set custom button types
                                    alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.NO);

                                    // Show the alert and handle the response
                                    alert.showAndWait().ifPresent(response -> {
                                        if (response == ButtonType.OK) {
                                            System.out.println("User chose OK.");
                                            Connection.mouth.println("recieveRequest" + "###" + data[0] + "###" + playerLabel.getText() + "###" + "200OK");
                                        } else if (response == ButtonType.NO) {
                                            System.out.println("User chose NO.");
                                            Connection.mouth.println("recieveRequest" + "###" + data[0] + "###" + playerLabel.getText() + "###" + "404NO");
                                        }
                                    });
                                });
                            }

                            //isRunning = false; // Stop the loop after handling the request
                        }
                        
                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        //thread.setDaemon(true);
        thread.start();

        
        Connection.sendRequest("sendList");
        Thread thread2 = new Thread(() -> { // REMEMBER TO CHECK IF THIS THREAD IS NEEDED OR NOT *******************************
            String recieve = Connection.recieveRequest();
            
            System.out.println(recieve + "\n"); //NOT PRINTED*******************
            
            String rec[] = recieve.split("###");
            
            System.out.println(Arrays.toString(rec)); //NOT PRINTED*******************

            if (rec[0].equals("List")) {
                String[] players = rec[1].replace("[", "").replace("]", "").split(", ");
                listView.getItems().clear();
                for (String player : players) {
                    listView.getItems().add(player.trim());
                }
            }
        });
        thread2.setDaemon(true);
        thread2.start();

        historyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HistoryController history = new HistoryController(stage);
                Scene scene = new Scene(history);
                stage.setScene(scene);
            }
        });

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                HomeScreen_offline_Controller home = new HomeScreen_offline_Controller(stage);

                // HomeScreen_offline home = new HomeScreen_offline_Controller(stage);
                Scene scene = new Scene(home);
                stage.setScene(scene);
            }
        });

       
    }

}
