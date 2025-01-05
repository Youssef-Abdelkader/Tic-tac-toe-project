package tictactoe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Single_Player_SceneBase extends BorderPane {

    protected final Label single_player_label;
    protected final Label single_scene_label;
    protected final Button backButton;
    protected final TextField single_player_txt_field;
    protected final Button Single_Start_btn;

    public Single_Player_SceneBase(Stage stage) {

        single_player_label = new Label();
        single_scene_label = new Label();
        backButton = new Button();
        single_player_txt_field = new TextField();
        Single_Start_btn = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        getStyleClass().add("anchor-pane");
        getStylesheets().add("/tictactoe/all.css");

        BorderPane.setAlignment(single_player_label, javafx.geometry.Pos.CENTER);
        single_player_label.setPrefHeight(39.0);
        single_player_label.setPrefWidth(164.0);
        single_player_label.setText("    Enter Your Name ");
        single_player_label.setFont(new Font("Centaur", 17.0));
        setLeft(single_player_label);

        BorderPane.setAlignment(single_scene_label, javafx.geometry.Pos.CENTER);
        single_scene_label.setPrefHeight(153.0);
        single_scene_label.setPrefWidth(554.0);
        single_scene_label.setText("                   Shik-Shack-shock");
        single_scene_label.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        single_scene_label.setTextFill(javafx.scene.paint.Color.valueOf("#e80a0a"));
        single_scene_label.setFont(new Font("Copperplate Gothic Light", 24.0));

        backButton.setMnemonicParsing(false);
        backButton.setText("Back");
        backButton.setFont(new Font("Centaur", 12.0));
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomeScreen_offline home = new HomeScreen_offline(stage);
                Scene scene = new Scene(home);
                stage.setScene(scene);
            }   
        });

        single_scene_label.setGraphic(backButton);
        setTop(single_scene_label);

        BorderPane.setAlignment(single_player_txt_field, javafx.geometry.Pos.CENTER);
        single_player_txt_field.setPrefHeight(26.0);
        single_player_txt_field.setPrefWidth(359.0);
        single_player_txt_field.setTranslateX(70.0);
        BorderPane.setMargin(single_player_txt_field, new Insets(0.0, 100.0, 0.0, 30.0));
        setCenter(single_player_txt_field);

        BorderPane.setAlignment(Single_Start_btn, javafx.geometry.Pos.CENTER);
        Single_Start_btn.setMnemonicParsing(false);
        Single_Start_btn.setPrefHeight(37.0);
        Single_Start_btn.setPrefWidth(111.0);
        Single_Start_btn.setText("Start");
        Single_Start_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game_screenBase game = new game_screenBase(stage);
                Scene scene = new Scene(game);
                stage.setScene(scene);
            }   
        });
        BorderPane.setMargin(Single_Start_btn, new Insets(0.0, 0.0, 80.0, 0.0));
        Single_Start_btn.setFont(new Font(18.0));
        setBottom(Single_Start_btn);

    }
}
