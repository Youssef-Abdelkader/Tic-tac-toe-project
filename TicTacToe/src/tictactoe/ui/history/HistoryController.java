package tictactoe.ui.history;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Vector;
import javafx.event.ActionEvent;
import java.util.Vector;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tictactoe.ui.home.online.HomeOnlineController;

public class HistoryController extends History {

    public HistoryController(Stage stage, Vector<Vector<String>> history,String name,String score) {
        super(stage);
        //populateTable(history); // Populate the table

        home.addEventHandler(ActionEvent.ACTION, (event) -> {
            HomeOnlineController cont = new HomeOnlineController(stage,name,score);
            Scene scene = new Scene(cont);
            stage.setScene(scene);
        });
    }

    // Method to populate the table with history data
    public void populateTable(Vector<Vector<String>> history) {
        ObservableList<HistoryRecord> data = FXCollections.observableArrayList();

        Vector<String> gameIds = history.get(0);
        Vector<String> player2s = history.get(2); // Player2 is stored here
        Vector<String> winners = history.get(3);
        Vector<String> recordings = history.get(4);

        for (int i = 0; i < gameIds.size(); i++) {
            data.add(new HistoryRecord(
                    gameIds.get(i),
                    player2s.get(i),
                    winners.get(i),
                    recordings.get(i)
            ));
            System.out.println("data added");

        }

        // Link the table columns to the data fields in the HistoryRecord class
        GameId_col.setCellValueFactory(new PropertyValueFactory<>("gameId"));
        Opponent_col.setCellValueFactory(new PropertyValueFactory<>("opponent"));
        winer_col.setCellValueFactory(new PropertyValueFactory<>("winner"));
        recording_col.setCellValueFactory(new PropertyValueFactory<>("recording"));

        // Populate the table view
        tableView.setItems(data);
    }
}


