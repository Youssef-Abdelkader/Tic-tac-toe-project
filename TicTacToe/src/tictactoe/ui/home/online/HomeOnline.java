package tictactoe.ui.home.online;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public abstract class HomeOnline extends AnchorPane {


    protected final AnchorPane helloBackground;
    protected final Label helloLabel;
    protected final AnchorPane scoreTitleBackground;
    protected final Label scoreTitle;
    protected final AnchorPane scoreBackground;
    protected final Button historyButton;
    protected final Button backButton;
    protected final AnchorPane playerBackground;
    protected final Label playerLabel;
    protected final ListView listView;
    protected final Label scoreLabel;

    public HomeOnline(Stage stage) {

        helloBackground = new AnchorPane();
        helloLabel = new Label();
        scoreTitleBackground = new AnchorPane();
        scoreTitle = new Label();
        scoreBackground = new AnchorPane();
        historyButton = new Button();
        backButton = new Button();
        playerBackground = new AnchorPane();
        playerLabel = new Label();
        listView = new ListView();
        scoreLabel = new Label();

        setPrefHeight(400.0);
        setPrefWidth(600.0);
        getStyleClass().add("coverBackground");
        getStylesheets().add("/tictactoe/styles/homeonline.css");

        helloBackground.setLayoutX(207.0);
        helloBackground.setLayoutY(30.0);

        helloLabel.setAlignment(javafx.geometry.Pos.CENTER);
        helloLabel.setLayoutX(-24.0);
        helloLabel.setPrefHeight(35.0);
        helloLabel.setPrefWidth(102.0);
        helloLabel.getStyleClass().add("helloBackground");
        helloLabel.setText("Hello!");
        helloLabel.setTextFill(javafx.scene.paint.Color.valueOf("#1e9413"));
        helloLabel.setFont(new Font("Berlin Sans FB Demi Bold", 30.0));

        scoreTitleBackground.setLayoutX(199.0);
        scoreTitleBackground.setLayoutY(75.0);
        scoreTitleBackground.setPrefHeight(27.0);
        scoreTitleBackground.setPrefWidth(68.0);

        scoreTitle.setAlignment(javafx.geometry.Pos.CENTER);
        scoreTitle.setLayoutX(23.0);
        scoreTitle.setPrefHeight(29.0);
        scoreTitle.setPrefWidth(62.0);
        scoreTitle.getStyleClass().add("scoreBackground");
        scoreTitle.setText("Score");
        scoreTitle.setTextFill(javafx.scene.paint.Color.valueOf("#1e9413"));
        scoreTitle.setFont(new Font("Berlin Sans FB", 20.0));

        scoreBackground.setLayoutX(299.0);
        scoreBackground.setLayoutY(104.0);

        historyButton.setLayoutX(509.0);
        historyButton.setLayoutY(27.0);
        historyButton.setMnemonicParsing(false);
        historyButton.getStyleClass().add("historyButton");
        historyButton.setText("History");
        historyButton.setFont(new Font("Centaur", 18.0));

        backButton.setLayoutX(32.0);
        backButton.setLayoutY(27.0);
        backButton.setMnemonicParsing(false);
        backButton.getStyleClass().add("backButton");
        backButton.setText("Back");
        backButton.setFont(new Font("Centaur", 14.0));

        playerBackground.setLayoutX(302.0);
        playerBackground.setLayoutY(30.0);

        playerLabel.setAlignment(javafx.geometry.Pos.CENTER);
        playerLabel.setPrefHeight(35.0);
        playerLabel.setPrefWidth(86.0);
        playerLabel.getStyleClass().add("playerBackground");
        playerLabel.setText("player");
        playerLabel.setTextFill(javafx.scene.paint.Color.valueOf("#1e9413"));
        playerLabel.setFont(new Font("Berlin Sans FB", 25.0));

        listView.setLayoutX(64.0);
        listView.setLayoutY(122.0);
        listView.setOpacity(0.9);
        listView.setPrefHeight(243.0);
        listView.setPrefWidth(474.0);
        listView.getStyleClass().add("listView");
        listView.getItems().addAll("item1","item2");
       

        scoreLabel.setAlignment(javafx.geometry.Pos.CENTER);
        scoreLabel.setLayoutX(291.0);
        scoreLabel.setLayoutY(76.0);
        scoreLabel.setPrefHeight(27.0);
        scoreLabel.setPrefWidth(37.0);
        scoreLabel.getStyleClass().add("scoreBackground");
        scoreLabel.setText("50");
        scoreLabel.setTextFill(javafx.scene.paint.Color.valueOf("#1e9413"));
        scoreLabel.setFont(new Font("Berlin Sans FB", 20.0));

        helloBackground.getChildren().add(helloLabel);
        getChildren().add(helloBackground);
        scoreTitleBackground.getChildren().add(scoreTitle);
        getChildren().add(scoreTitleBackground);
        getChildren().add(scoreBackground);
        getChildren().add(historyButton);
        getChildren().add(backButton);
        playerBackground.getChildren().add(playerLabel);
        getChildren().add(playerBackground);
        getChildren().add(listView);
        getChildren().add(scoreLabel);

    }
}
