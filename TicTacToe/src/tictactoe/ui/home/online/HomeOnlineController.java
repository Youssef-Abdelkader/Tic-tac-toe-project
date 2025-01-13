/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.ui.home.online;


import connection.Connection;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
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
                HistoryController history = new HistoryController(stage);
                Scene scene = new Scene(history);
                stage.setScene(scene);
            }
        });

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                HomeScreen_offline home = new HomeScreen_offline_Controller(stage);

                

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

}
