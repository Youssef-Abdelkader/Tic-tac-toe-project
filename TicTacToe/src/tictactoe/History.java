package tictactoe;

import java.lang.String;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class History extends AnchorPane {

    protected final AnchorPane labelAnchorPane;
    protected final Label historyLable;
    protected final ListView listview;
    protected final Button history_btn;

    public History(Stage stage) {

        labelAnchorPane = new AnchorPane();
        historyLable = new Label();
        listview = new ListView();
        history_btn = new Button();

        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        getStyleClass().add("anchor-pane");
        getStylesheets().add("/tictactoe/history.css");

        labelAnchorPane.setLayoutX(235.0);
        labelAnchorPane.setLayoutY(14.0);
        labelAnchorPane.getStyleClass().add("labelAnchorPane");
        labelAnchorPane.getStylesheets().add("/tictactoe/background.css");

        historyLable.setAlignment(javafx.geometry.Pos.CENTER);
        historyLable.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        historyLable.setPrefWidth(130.0);
        historyLable.getStylesheets().add("/tictactoe/history.css");
        historyLable.setText("History");
        historyLable.setTextFill(javafx.scene.paint.Color.valueOf("#1e9413"));
        historyLable.setFont(new Font("Berlin Sans FB Bold", 36.0));

        listview.setLayoutX(111.0);
        listview.setLayoutY(78.0);
        listview.setOpacity(0.9);
        listview.setPrefHeight(245.0);
        listview.setPrefWidth(380.0);
        listview.getStyleClass().add("listview");
        listview.getStylesheets().add("/tictactoe/background.css");

        history_btn.setLayoutX(274.0);
        history_btn.setLayoutY(344.0);
        history_btn.setMnemonicParsing(false);
        history_btn.getStyleClass().add("history_btn");
        history_btn.getStylesheets().add("/tictactoe/background.css");
        history_btn.setText("Home");
        history_btn.setFont(new Font("Centaur", 14.0));
        history_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomeOnline home = new HomeOnline(stage);
                Scene scene = new Scene(home);
                stage.setScene(scene);
            }   
        });

        labelAnchorPane.getChildren().add(historyLable);
        getChildren().add(labelAnchorPane);
        getChildren().add(listview);
        getChildren().add(history_btn);

    }
}
