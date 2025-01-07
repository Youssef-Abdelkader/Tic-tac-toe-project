package tictactoe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tictactoe.login_signup.LoginController;

public class HomeScreen_offline extends BorderPane {

    protected final Label label;
    protected final ImageView imageView;
    protected final ImageView imageView0;
    protected final VBox vBox;
    protected final Button singlePBtn;
    protected final Button MultiPBtn;
    protected final Button OnlineBtn;

    public HomeScreen_offline(Stage stage) {

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
        getStylesheets().add("/tictactoe/all.css");

        BorderPane.setAlignment(label, javafx.geometry.Pos.CENTER);
        label.setPrefHeight(115.0);
        label.setPrefWidth(353.0);
        label.setText("SHICK SHACK SHOCK");
        label.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        label.setFont(new Font("Comic Sans MS Bold", 32.0));
        setTop(label);
        setPadding(new Insets(30.0, 10.0, 10.0, 10.0));

        BorderPane.setAlignment(imageView, javafx.geometry.Pos.CENTER);
        imageView.setFitHeight(150.0);
        imageView.setFitWidth(200.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("x.png").toExternalForm()));
        setLeft(imageView);

        BorderPane.setAlignment(imageView0, javafx.geometry.Pos.CENTER);
        imageView0.setFitHeight(150.0);
        imageView0.setFitWidth(200.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("o.png").toExternalForm()));
        setRight(imageView0);

        BorderPane.setAlignment(vBox, javafx.geometry.Pos.CENTER);
            vBox.setAlignment(javafx.geometry.Pos.CENTER);
        vBox.setMinHeight(USE_PREF_SIZE);
        vBox.setPrefHeight(268.0);
        vBox.setPrefWidth(180.0);
        vBox.setSpacing(15.0);

        singlePBtn.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        singlePBtn.setMaxWidth(Double.MAX_VALUE);
        singlePBtn.setMnemonicParsing(false);
        singlePBtn.setStyle("-fx-border-radius: 10;");
        singlePBtn.setText("Single player");
        singlePBtn.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        singlePBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Single_Player_SceneBase single = new Single_Player_SceneBase(stage);
                Scene scene = new Scene(single);
                stage.setScene(scene);
            }   
        });

        MultiPBtn.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        MultiPBtn.setLayoutX(10.0);
        MultiPBtn.setLayoutY(10.0);
        MultiPBtn.setMaxWidth(Double.MAX_VALUE);
        MultiPBtn.setMnemonicParsing(false);
        MultiPBtn.setStyle("-fx-border-radius: 10;");
        MultiPBtn.setText("Multi player");
        MultiPBtn.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
         MultiPBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Multi_player_SceneBase multi = new Multi_player_SceneBase(stage);
                Scene scene = new Scene(multi);
                stage.setScene(scene);
            }   
        });

        OnlineBtn.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        OnlineBtn.setLayoutX(10.0);
        OnlineBtn.setLayoutY(42.0);
        OnlineBtn.setMaxWidth(Double.MAX_VALUE);
        OnlineBtn.setMnemonicParsing(false);
        OnlineBtn.setStyle("-fx-border-radius: 10;");
        OnlineBtn.setText("online");
        OnlineBtn.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        OnlineBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TicTacToe.online = true;
                LoginController account = new LoginController(stage);
                Scene scene = new Scene(account);
                stage.setScene(scene);
            }   
        });
        vBox.setPadding(new Insets(20.0, 0.0, 0.0, 0.0));
        setCenter(vBox);

        vBox.getChildren().add(singlePBtn);
        vBox.getChildren().add(MultiPBtn);
        vBox.getChildren().add(OnlineBtn);

    }
}
