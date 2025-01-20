package tictactoe.ui.home.offline;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tictactoe.TicTacToe;
import tictactoe.ui.auth.login.LoginController;
import tictactoe.ui.player.multiplayer.Multi_player_SceneController;
import tictactoe.ui.player.singleplayer.Single_player_sceneController;

public abstract class HomeScreen_offline extends BorderPane {

    protected final StackPane stackPane;
    protected final Label label;
    protected final ImageView imageView;
    protected final ImageView imageView0;
    protected final VBox vBox;
    protected final Button singlePBtn;
    protected final Button MultiPBtn;
    protected final Button OnlineBtn;

    public HomeScreen_offline(Stage stage) {

        stackPane = new StackPane();
        label = new Label();
        imageView = new ImageView();
        imageView0 = new ImageView();
        vBox = new VBox();
        singlePBtn = new Button();
        MultiPBtn = new Button();
        OnlineBtn = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        getStyleClass().add("anchor-pane");
        getStylesheets().add("/tictactoe/styles/background.css");

        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setPrefHeight(40.0);
        label.setPrefWidth(406.0);
        label.getStyleClass().add("homescreen_title");
        label.getStylesheets().add("/tictactoe/styles/background.css");
        label.setText("SHICK SHACK SHOCK");
        label.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        label.setFont(new Font("Berlin Sans FB Demi Bold", 36.0));
        label.setStyle("-fx-text-fill: #15b530;");
        setTop(stackPane);
        setPadding(new Insets(20.0, 10.0, 10.0, 10.0));

        BorderPane.setAlignment(imageView, javafx.geometry.Pos.CENTER);
        imageView.setFitHeight(150.0);
        imageView.setFitWidth(200.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("/tictactoe/images/x.png").toExternalForm()));
        setLeft(imageView);
        BorderPane.setMargin(imageView, new Insets(100.0, 0.0, 0.0, 0.0)); // Move image X downward

        BorderPane.setAlignment(imageView0, javafx.geometry.Pos.CENTER);
        imageView0.setFitHeight(150.0);
        imageView0.setFitWidth(200.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("/tictactoe/images/o.png").toExternalForm()));
        setRight(imageView0);
        BorderPane.setMargin(imageView0, new Insets(100.0, 0.0, 0.0, 0.0)); // Move image O downward

        BorderPane.setAlignment(vBox, javafx.geometry.Pos.CENTER);
        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        vBox.setMaxWidth(USE_PREF_SIZE);
        vBox.setMinHeight(USE_PREF_SIZE);
        vBox.setPrefHeight(268.0);
        vBox.setPrefWidth(220.0);
        vBox.setSpacing(15.0);
        vBox.setPadding(new Insets(110.0, 0.0, 0.0, 0.0)); // Move all buttons downward

        singlePBtn.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        singlePBtn.setMaxWidth(Double.MAX_VALUE);
        singlePBtn.setMnemonicParsing(false);
        singlePBtn.setStyle("-fx-border-radius: 10;");
        singlePBtn.getStyleClass().add("history_btn");
        singlePBtn.getStylesheets().add("/tictactoe/styles/background.css");
        singlePBtn.setText("Vs Computer");
        singlePBtn.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        MultiPBtn.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        MultiPBtn.setLayoutX(10.0);
        MultiPBtn.setLayoutY(10.0);
        MultiPBtn.setMaxWidth(Double.MAX_VALUE);
        MultiPBtn.setMnemonicParsing(false);
        MultiPBtn.setStyle("-fx-border-radius: 10;");
        MultiPBtn.getStyleClass().add("history_btn");
        MultiPBtn.getStylesheets().add("/tictactoe/styles/background.css");
        MultiPBtn.setText("Multi player");
        MultiPBtn.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        OnlineBtn.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        OnlineBtn.setLayoutX(10.0);
        OnlineBtn.setLayoutY(42.0);
        OnlineBtn.setMaxWidth(Double.MAX_VALUE);
        OnlineBtn.setMnemonicParsing(false);
        OnlineBtn.setStyle("-fx-border-radius: 10;");
        OnlineBtn.getStyleClass().add("history_btn");
        OnlineBtn.getStylesheets().add("/tictactoe/styles/background.css");
        OnlineBtn.setText("online");
        OnlineBtn.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        stackPane.getChildren().add(label);
        vBox.getChildren().add(singlePBtn);
        vBox.getChildren().add(MultiPBtn);
        vBox.getChildren().add(OnlineBtn);
        setCenter(vBox);

        

        
    }
}
