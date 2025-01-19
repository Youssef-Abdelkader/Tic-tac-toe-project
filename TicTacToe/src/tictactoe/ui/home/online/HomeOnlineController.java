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
    public static boolean isOnHome = true;
    String oppName;
    String oppScore;
    public static OnlinePlayer onl_player;
    public static String me;

    public HomeOnlineController(Stage stage, String userName, String userScore) {

        super(stage);
        me = userName;
        isOnHome = true;
        stage.setOnCloseRequest(e -> {
            Connection.sendRequest("logout");

        });
        playerLabel.setText(userName);
        scoreLabel.setText(userScore);

        listView.setOnMouseClicked(((event) -> {

            String[] player = listView.getSelectionModel().getSelectedItem().split(" - Score: ");

            oppName = player[0];
            oppScore = player[1];

            Thread th = new Thread(() -> {
                Connection.sendRequest("sendRequest" + "###" + oppName);
            });
            th.setDaemon(true);
            th.start();
        }));

        Connection.sendRequest("sendList");

        if (thread == null || !(thread.isAlive())) {
            thread = new Thread(() -> {
                while (isOnHome) {
                    String recieve = Connection.recieveRequest();
                    System.out.println("recieve " + recieve);
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
                            
                            isOnHome = false;
                            break;
                        }

                        case "Accepted": {

                            isOnHome = false;

                            if (Platform.isFxApplicationThread() == false) {
                                Platform.runLater(() -> {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Challenge Accepted");
                                    alert.setContentText("Challenge has been accepted");
                                    alert.initOwner(stage);
                                    alert.showAndWait();
                                    game_screenBase game = new GamescreenController(stage, rec[1], rec[3], rec[4], rec[2], 'O');
                                    Scene scene = new Scene(game);
                                    stage.setScene(scene);
                                });

                            }
                            break;
                        }

                        case "invitation": {

                            if (!Platform.isFxApplicationThread()) {
                                Platform.runLater(() -> {

                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Confirmation Dialog");
                                    alert.setHeaderText("Challenge Request");
                                    alert.setContentText(rec[1] + " Wants to Play Against You");
                                    alert.initOwner(stage);

                                    ButtonType acceptButton = new ButtonType("Accept");
                                    ButtonType declineButton = new ButtonType("Decline");

                                    alert.getButtonTypes().setAll(acceptButton, declineButton);

                                    Optional<ButtonType> result = alert.showAndWait();

                                    if (result.isPresent()) {
                                        if (result.get() == acceptButton) {

                                            System.out.println("You accepted!");
                                            isOnHome = false;

                                            Connection.sendRequest("GetInvitation" + "###" + "Accepted" + "###" + rec[1]);

                                            game_screenBase game = new GamescreenController(stage, rec[1], playerLabel.getText(), rec[2], scoreLabel.getText(), 'X');

                                            Scene scene = new Scene(game);
                                            stage.setScene(scene);

                                        } else if (result.get() == declineButton) {

                                            Connection.sendRequest("GetInvitation" + "###" + "Refused" + "###" + rec[1]);

                                        }

                                    }

                                });
                            }

                            break;

                        }

                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
        }

        historyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                // Assuming listView is your ListView instance
                listView.getItems().clear();
                Connection.sendRequest("close###");

                HistoryController history = new HistoryController(stage, retrievePlayerHistory(GetPlayer()), playerLabel.getText(), scoreLabel.getText()); //initializes a new online player
                Scene scene = new Scene(history);
                stage.setScene(scene);

            }
        });
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                isOnHome = false;

                TicTacToe.online = false;
                Connection.sendRequest("logout");
                HomeScreen_offline_Controller home = new HomeScreen_offline_Controller(stage);

                Scene scene = new Scene(home);
                stage.setScene(scene);
            }
        });

    }

    public Vector<Vector<String>> retrievePlayerHistory(OnlinePlayer onl_player) {

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

    public static void SetPlayer(OnlinePlayer player) {
        onl_player = player;
    }

    public static OnlinePlayer GetPlayer() {
        return onl_player;
    }

}
