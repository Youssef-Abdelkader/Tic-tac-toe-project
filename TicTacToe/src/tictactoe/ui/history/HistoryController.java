package tictactoe.ui.history;

import java.util.Vector;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tictactoe.ui.home.online.HomeOnlineController;

public class HistoryController extends History {

    public HistoryController(Stage stage, Vector<Vector<String>> playerHistory) {
        super(stage);

        // Use the playerHistory data to populate the UI (e.g., a TableView or ListView)
        // Example: Assuming you have a TableView or ListView in the History class
        // historyTableView.getItems().addAll(playerHistory);

        history_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomeOnlineController home = new HomeOnlineController(stage);
                Scene scene = new Scene(home);
                stage.setScene(scene);
            }
        });
    }
}