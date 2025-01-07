package tictactoe.ui.home.online;

import tictactoe.ui.home.offline.HomeScreen_offline;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tictactoe.ui.history.History;

public  class HomeOnline extends AnchorPane {

    protected final Label label;
    protected final Label label0;
    protected final Label scoreLabel;
    protected final Button historyButton;
    protected final Label label1;
    protected final Label label2;
    protected final Label label3;
    protected final Label status1;
    protected final Label status2;
    protected final Label status3;
    protected final Button requestButton1;
    protected final Button requestButton2;
    protected final Button requestButton3;
    protected final Button backButton;

    public HomeOnline(Stage stage) {

        label = new Label();
        label0 = new Label();
        scoreLabel = new Label();
        historyButton = new Button();
        label1 = new Label();
        label2 = new Label();
        label3 = new Label();
        status1 = new Label();
        status2 = new Label();
        status3 = new Label();
        requestButton1 = new Button();
        requestButton2 = new Button();
        requestButton3 = new Button();
        backButton = new Button();

        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        getStylesheets().add("/tictactoe/styles/homeonline.css");
        getStyleClass().add("anchor-pane");
        getStylesheets().add("/tictactoe/styles/all.css");

        label.setLayoutX(50.0);
        label.setLayoutY(81.0);
        label.setText("Hello! Habiba");
        label.setFont(new Font("Centaur", 18.0));

        label0.setLayoutX(420.0);
        label0.setLayoutY(81.0);
        label0.setText("Score");
        label0.setFont(new Font("Centaur", 18.0));

        scoreLabel.setLayoutX(479.0);
        scoreLabel.setLayoutY(81.0);
        scoreLabel.setText("50");
        scoreLabel.setFont(new Font("Centaur", 18.0));

        historyButton.setLayoutX(263.0);
        historyButton.setLayoutY(101.0);
        historyButton.setMnemonicParsing(false);
        historyButton.setText("History");
        historyButton.setFont(new Font("Centaur", 18.0));
         historyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                History history = new History(stage);
                Scene scene = new Scene(history);
                stage.setScene(scene);
            }
        });

        label1.setLayoutX(77.0);
        label1.setLayoutY(205.0);
        label1.setText("Menna");
        label1.setFont(new Font("Centaur", 16.0));

        label2.setLayoutX(74.0);
        label2.setLayoutY(251.0);
        label2.setText("Youssef");
        label2.setFont(new Font("Centaur", 16.0));

        label3.setLayoutX(58.0);
        label3.setLayoutY(297.0);
        label3.setText("Abdelrahman");
        label3.setFont(new Font("Centaur", 16.0));

        status1.setLayoutX(280.0);
        status1.setLayoutY(205.0);
        status1.setText("Online");
        status1.setFont(new Font("Centaur", 16.0));

        status2.setLayoutX(279.0);
        status2.setLayoutY(251.0);
        status2.setText("Offline");
        status2.setFont(new Font("Centaur", 16.0));

        status3.setLayoutX(280.0);
        status3.setLayoutY(297.0);
        status3.setText("Online");
        status3.setFont(new Font("Centaur", 16.0));

        requestButton1.setLayoutX(386.0);
        requestButton1.setLayoutY(202.0);
        requestButton1.setMnemonicParsing(false);
        requestButton1.setPrefHeight(25.0);
        requestButton1.setPrefWidth(68.0);
        requestButton1.setText("Request");
        requestButton1.setFont(new Font("Centaur", 14.0));
         requestButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                requestButton1.setDisable(true);
                requestButton1.setText("Wait");
            }
        });

           
        requestButton2.setDisable(true);
        requestButton2.setLayoutX(386.0);
        requestButton2.setLayoutY(248.0);
        requestButton2.setMnemonicParsing(false);
        requestButton2.setPrefHeight(25.0);
        requestButton2.setPrefWidth(68.0);
        requestButton2.setText("Request");
        requestButton2.setFont(new Font("Centaur", 14.0));

        requestButton3.setDisable(true);
        requestButton3.setLayoutX(386.0);
        requestButton3.setLayoutY(294.0);
        requestButton3.setMnemonicParsing(false);
        requestButton3.setPrefHeight(27.0);
        requestButton3.setPrefWidth(68.0);
        requestButton3.setText("Request");
        requestButton3.setFont(new Font("Centaur", 14.0));

        backButton.setLayoutX(32.0);
        backButton.setLayoutY(27.0);
        backButton.setMnemonicParsing(false);
        backButton.setText("Back");
        backButton.setFont(new Font("Centaur", 14.0));
         backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomeScreen_offline home = new HomeScreen_offline(stage);
                Scene scene = new Scene(home);
                stage.setScene(scene);
            }
        });

        getChildren().add(label);
        getChildren().add(label0);
        getChildren().add(scoreLabel);
        getChildren().add(historyButton);
        getChildren().add(label1);
        getChildren().add(label2);
        getChildren().add(label3);
        getChildren().add(status1);
        getChildren().add(status2);
        getChildren().add(status3);
        getChildren().add(requestButton1);
        getChildren().add(requestButton2);
        getChildren().add(requestButton3);
        getChildren().add(backButton);

    }
}
