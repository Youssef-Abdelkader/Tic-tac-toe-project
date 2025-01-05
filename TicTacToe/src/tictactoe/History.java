package tictactoe;

import java.net.URL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class History extends AnchorPane {

    protected final Label historyLable;
    protected final Label gameIdLable;
    protected final Label opponentLable;
    protected final Label label;
    protected final Label label0;
    protected final Label label1;
    protected final Label label2;
    protected final Label label3;
    protected final Label label4;
    protected final Button replayButton1;
    protected final Button replayButton2;
    protected final Button replayButton3;
    protected final Button homeButton;

    public History(Stage stage) {

        historyLable = new Label();
        gameIdLable = new Label();
        opponentLable = new Label();
        label = new Label();
        label0 = new Label();
        label1 = new Label();
        label2 = new Label();
        label3 = new Label();
        label4 = new Label();
        replayButton1 = new Button();
        replayButton2 = new Button();
        replayButton3 = new Button();
        homeButton = new Button();

        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        getStylesheets().add("/tictactoe/history.css");
        getStyleClass().add("anchor-pane");
        getStylesheets().add("/tictactoe/all.css");

        historyLable.setLayoutX(253.0);
        historyLable.setLayoutY(59.0);
        historyLable.setText("Hisory");
        historyLable.setTextFill(javafx.scene.paint.Color.valueOf("#cd0808"));
        historyLable.setFont(new Font("Centaur", 32.0));

        gameIdLable.setLayoutX(62.0);
        gameIdLable.setLayoutY(99.0);
        gameIdLable.setText("Game Id");
        gameIdLable.setFont(new Font("Centaur", 26.0));

        opponentLable.setLayoutX(270.0);
        opponentLable.setLayoutY(99.0);
        opponentLable.setText("Opponent");
        opponentLable.setFont(new Font("Centaur", 26.0));

        label.setLayoutX(80.0);
        label.setLayoutY(142.0);
        label.setText("2345");
        label.setFont(new Font("Centaur", 24.0));

        label0.setLayoutX(80.0);
        label0.setLayoutY(184.0);
        label0.setText("5678");
        label0.setFont(new Font("Centaur", 24.0));

        label1.setLayoutX(80.0);
        label1.setLayoutY(230.0);
        label1.setText("1290");
        label1.setFont(new Font("Centaur", 24.0));

        label2.setLayoutX(288.0);
        label2.setLayoutY(142.0);
        label2.setText("Menna");
        label2.setFont(new Font("Centaur", 24.0));

        label3.setLayoutX(284.0);
        label3.setLayoutY(184.0);
        label3.setText("Youssef");
        label3.setFont(new Font("Centaur", 24.0));

        label4.setLayoutX(287.0);
        label4.setLayoutY(230.0);
        label4.setText("Habiba");
        label4.setFont(new Font("Centaur", 24.0));

        replayButton1.setLayoutX(433.0);
        replayButton1.setLayoutY(137.0);
        replayButton1.setMnemonicParsing(false);
        replayButton1.setText("Replay");
        replayButton1.setFont(new Font("Centaur", 14.0));

        replayButton2.setDisable(true);
        replayButton2.setLayoutX(433.0);
        replayButton2.setLayoutY(179.0);
        replayButton2.setMnemonicParsing(false);
        replayButton2.setText("Replay");
        replayButton2.setFont(new Font("Centaur", 14.0));

        replayButton3.setLayoutX(433.0);
        replayButton3.setLayoutY(225.0);
        replayButton3.setMnemonicParsing(false);
        replayButton3.setText("Replay");
        replayButton3.setFont(new Font("Centaur", 14.0));

        homeButton.setLayoutX(267.0);
        homeButton.setLayoutY(316.0);
        homeButton.setMnemonicParsing(false);
        homeButton.setText("Home");
        homeButton.setFont(new Font("Centaur", 14.0));
        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomeOnline home = new HomeOnline(stage);
                Scene scene = new Scene(home);
                stage.setScene(scene);
            }   
        });

        getChildren().add(historyLable);
        getChildren().add(gameIdLable);
        getChildren().add(opponentLable);
        getChildren().add(label);
        getChildren().add(label0);
        getChildren().add(label1);
        getChildren().add(label2);
        getChildren().add(label3);
        getChildren().add(label4);
        getChildren().add(replayButton1);
        getChildren().add(replayButton2);
        getChildren().add(replayButton3);
        getChildren().add(homeButton);

    }
}
