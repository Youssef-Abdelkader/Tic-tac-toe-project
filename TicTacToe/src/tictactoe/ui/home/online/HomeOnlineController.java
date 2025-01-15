package tictactoe.ui.home.online;

import connection.Connection;
import java.util.Vector;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tictactoe.ui.game.screen.OnlinePlayer;
import tictactoe.ui.game.screen.Player;
import tictactoe.ui.history.HistoryController;
import tictactoe.ui.home.offline.HomeScreen_offline_Controller;

public class HomeOnlineController extends HomeOnline {

    Player onl_player = new OnlinePlayer();

    public HomeOnlineController(Stage stage) {
        super(stage);
        Connection.sendRequest("sendList");
        Thread thread = new Thread(() -> {
            String recieve = Connection.recieveRequest();
            String rec[] = recieve.split("###");

            if (rec[0].equals("List")) {
                String[] players = rec[1].replace("[", "").replace("]", "").split(", ");
                listView.getItems().clear();
                for (String player : players) {
                    listView.getItems().add(player.trim());
                }
            }
        });
        thread.setDaemon(true);
        thread.start();

        historyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Cast onl_player to OnlinePlayer
                HistoryController history = new HistoryController(stage, retrievePlayerHistory((OnlinePlayer) onl_player));
                Scene scene = new Scene(history);
                stage.setScene(scene);
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