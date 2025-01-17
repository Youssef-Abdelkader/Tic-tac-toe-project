package tictactoe.ui.home.online;

import connection.Connection;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;


import java.util.Vector;
import javafx.application.Platform;
import connection.Connection;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import tictactoe.TicTacToe;
import tictactoe.ui.game.screen.OnlinePlayer;
import tictactoe.ui.game.screen.Player;
import tictactoe.ui.history.HistoryController;
import tictactoe.ui.home.offline.HomeScreen_offline_Controller;
public class HomeOnlineController extends HomeOnline {

    Player onl_player = new OnlinePlayer();
    static Thread thread;
    public HomeOnlineController(Stage stage,String userName,String userScore) {
        super(stage);
        playerLabel.setText(userName);
        scoreLabel.setText(userScore);
        
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
        thread.setDaemon(true);
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

        if (thread == null || !(thread.isAlive())) {
            thread = new Thread(() -> {
                while (true) {
                    String recieve = Connection.recieveRequest();
                    String rec[] = recieve.split("###");
                    switch (rec[0]) {
                        case "List": {
                            Platform.runLater(
                                    () -> {
                                        if (rec[0].equals("List")) {
                                            String[] players = rec[1].replace("[", "").replace("]", "").split(", ");
                                            listView.getItems().clear();
                                            for (String player : players) {
                                                listView.getItems().add(player.trim());
                                            }
                                        }

                                    });
                        }

                        case "logout": {

                            //Connection.closeconnection();

                        }
                        case "Accept": {

                        }
                        case "Decline": {

                        }
                        /*case "invitation": {

                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation Dialog");
                                alert.setHeaderText("Please Confirm");
                                alert.setContentText(rec[1] + " Wants to Play Against You");

                                // Customize the buttons
                                ButtonType acceptButton = new ButtonType("Accept");
                                ButtonType declineButton = new ButtonType("Decline");

                                // Add the buttons to the alert
                                alert.getButtonTypes().setAll(acceptButton, declineButton);

                                // Show the alert and wait for a response
                                Optional<ButtonType> result = alert.showAndWait();

                                // Handle button clicks
                                if (result.isPresent()) {
                                    if (result.get() == acceptButton) {
                                        System.out.println("You accepted!");
                                        Connection.sendRequest("GetInvitation" + "###" + "Accepted" + rec[1]);
                                    } else if (result.get() == declineButton) {
                                        System.out.println("You declined!");
                                        Connection.sendRequest("GetInvitation" + "###" + "Refused" + rec[1]);

                                    }
                            );
                        }
                        


                            }*/
                }
            }     
        } );
        thread.setDaemon(true);
        thread.start();
    }

        historyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //onl_player -> to be implemented;
                
                HistoryController history = new HistoryController(stage , retrievePlayerHistory((OnlinePlayer) onl_player));
                Scene scene = new Scene(history);
                stage.setScene(scene);

                //onl_player -> to be implemented;

                
                //HistoryController history = new HistoryController(stage , retrievePlayerHistory(onl_player));
                //Scene scene = new Scene(history);
                //stage.setScene(scene);
            }
        });
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                TicTacToe.online = false;
                Connection.sendRequest("logout");
                HomeScreen_offline_Controller home = new HomeScreen_offline_Controller(stage);

                Scene scene = new Scene(home);
                stage.setScene(scene);
            }
        });


        listView.setOnMouseClicked(((event) -> {
            String[] player = listView.getSelectionModel().getSelectedItem().split(" - Score: ");
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

    }
    public Vector<Vector<String>> retrievePlayerHistory(OnlinePlayer onl_player) {

    

        //boolean retflag = false;
        Vector<Vector<String>> history = new Vector<>();
        if (Connection.setConnection()) {
            Connection.sendRequest("History_request###" + onl_player.getUser_name());
            String response = Connection.recieveRequest();
            if (response != null && response.startsWith("History_response")) {
                String[] historyEntries = response.split("###");
                Vector<String> gameIds = new Vector<>();
                Vector<String> player1s = new Vector<>();
                Vector<String> player2s = new Vector<>();
                Vector<String> winners = new Vector<>();
                Vector<String> recordings = new Vector<>();
                String placeholder = null;
                for (int i = 1; i < historyEntries.length; i++) { // Skip "History_response"
                    if ("Game".equals(historyEntries[i])) {
                        placeholder = "Game";
                    } else if ("PlayerName1".equals(historyEntries[i])) {
                        placeholder = "PlayerName1";
                    } else if ("PlayerName2".equals(historyEntries[i])) {
                        placeholder = "PlayerName2";
                    } else if ("winner".equals(historyEntries[i])) {
                        placeholder = "winner";
                    } else if ("recording".equals(historyEntries[i])) {
                        placeholder = "recording";
                    } else {
                        switch (placeholder) {
                            case "Game":
                                gameIds.add(historyEntries[i]);
                                break;
                            case "PlayerName1":
                                player1s.add(historyEntries[i]);
                                break;
                            case "PlayerName2":
                                player2s.add(historyEntries[i]);
                                break;
                            case "winner":
                                winners.add(historyEntries[i]);
                                break;
                            case "recording":
                                recordings.add(historyEntries[i]);
                                break;
                            default:
                                break;
                        }
                    }
                }
                history.add(gameIds);
                history.add(player1s);
                history.add(player2s);
                history.add(winners);
                history.add(recordings);
            }
        }
        return history;

    }

    }






