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
import tictactoe.ui.game.screen.GamescreenController;
import tictactoe.ui.game.screen.OnlinePlayer;
import tictactoe.ui.game.screen.Player;
import tictactoe.ui.game.screen.game_screenBase;
import tictactoe.ui.history.HistoryController;
import tictactoe.ui.home.offline.HomeScreen_offline_Controller;

public class HomeOnlineController extends HomeOnline {

    static Thread thread;

    String oppName;
    String oppScore;
    public static OnlinePlayer onl_player;

    public HomeOnlineController(Stage stage, String userName, String userScore) {

        super(stage);
        stage.setOnCloseRequest(e -> {
            Connection.sendRequest("logout");

        });
        playerLabel.setText(userName);
        scoreLabel.setText(userScore);

        listView.setOnMouseClicked(((event) -> {

            String[] player = listView.getSelectionModel().getSelectedItem().split(" - Score: ");

            System.out.println(Arrays.toString(player));


            oppName = player[0];
            oppScore = player[1];
            System.out.println("Name: " + oppName);
            System.out.println("Score: " + oppScore);

           
            Thread th = new Thread(() -> {
                Connection.sendRequest("sendRequest" + "###" + oppName);
            });
            th.setDaemon(true);
            th.start();
//clear list view when history
        }));

        Connection.sendRequest("sendList");


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
                            break;
                        }

                        case "logout": {

                            //Connection.closeconnection();
                            break;
                        }

                        case "Accepted": {

                            if (Platform.isFxApplicationThread() == false) {
                                Platform.runLater(() -> {
                                    System.out.println("Challenge has been accepted");
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Challenge Accepted");
                                    alert.setContentText("Challenge has been accepted");
                                    alert.initOwner(stage);
                                    alert.showAndWait();
                                    System.out.println(rec[3] + rec[1] + rec[2] + rec[4]);
                                    System.out.println("------------------------------");
                                    game_screenBase game = new GamescreenController(stage, rec[3], rec[1], rec[2], rec[4]);

                                    Scene scene = new Scene(game);
                                    stage.setScene(scene);
                                });
                                  
                            }
                              break;
                        }
                            
                        case "Refused": {
                            break;

                        }

                        case "invitation": {

                            if (!Platform.isFxApplicationThread()) {
                                Platform.runLater(() -> {
                                    System.out.println(rec[1]);
                                    System.out.println("--------------------");
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Confirmation Dialog");
                                    alert.setHeaderText("Challenge Request");
                                    alert.setContentText(rec[1] + " Wants to Play Against You");
                                    alert.initOwner(stage);

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
                                            Connection.sendRequest("GetInvitation" + "###" + "Accepted" + "###" + rec[1]);

                                            game_screenBase game = new GamescreenController(stage, playerLabel.getText(), rec[1], scoreLabel.getText(), rec[2]);
                                            System.out.println(playerLabel.getText() + oppName + scoreLabel.getText() + oppScore);
                                            Scene scene = new Scene(game);
                                            stage.setScene(scene);


                                        } else if (result.get() == declineButton) {
                                            System.out.println("You declined!");
                                            Connection.sendRequest("GetInvitation" + "###" + "Refused" + "###" + rec[1]);

                                        }

                                    }

                                });
                            }

                            break;

                        } //END OF CASE INVITATION

                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
        }

        historyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //onl_player -> to be implemented;
                
                // Assuming listView is your ListView instance
                listView.getItems().clear();

                HistoryController history = new HistoryController(stage, retrievePlayerHistory(GetPlayer()),playerLabel.getText(),scoreLabel.getText()); //initializes a new online player
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

    }


    public Vector<Vector<String>> retrievePlayerHistory(OnlinePlayer onl_player) {

        //boolean retflag = false;
        Vector<Vector<String>> history = new Vector<>();

        Connection.sendRequest("History_request###" + onl_player.getUser_name());
        Thread th = new Thread(() -> {
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
        });
        
        th.start();
        return history;

    }

    public static void SetPlayer(OnlinePlayer player){
        onl_player = player;
    }
    public static OnlinePlayer GetPlayer(){
        return onl_player;
    }
            
}
