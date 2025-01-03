package tictactoe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Multi_player_SceneBase extends BorderPane {

    protected final Label multi_scene_label;
    protected final Button backButton;
    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final Label player1_label;
    protected final Label player2_label;
    protected final TextField player1_txt_fied;
    protected final TextField player2_txt_field;
    protected final Button Multi_Start_btn;

    public Multi_player_SceneBase(Stage stage) {

        multi_scene_label = new Label();
        backButton = new Button();
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        player1_label = new Label();
        player2_label = new Label();
        player1_txt_fied = new TextField();
        player2_txt_field = new TextField();
        Multi_Start_btn = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        BorderPane.setAlignment(multi_scene_label, javafx.geometry.Pos.CENTER);
        multi_scene_label.setPrefHeight(53.0);
        multi_scene_label.setPrefWidth(571.0);
        multi_scene_label.setText("                  Shik-Shack-shock");
        multi_scene_label.setTextFill(javafx.scene.paint.Color.valueOf("#f51010"));
        multi_scene_label.setFont(new Font("Copperplate Gothic Light", 24.0));

        backButton.setMnemonicParsing(false);
        backButton.setPrefHeight(17.0);
        backButton.setPrefWidth(46.0);
        backButton.setText("Back");
        backButton.setFont(new Font("Centaur", 12.0));
        multi_scene_label.setGraphic(backButton);
        setTop(multi_scene_label);
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomeScreen_offline home = new HomeScreen_offline(stage);
                Scene scene = new Scene(home);
                stage.setScene(scene);
            }   
        });

        BorderPane.setAlignment(gridPane, javafx.geometry.Pos.CENTER);
        gridPane.setPrefHeight(249.0);
        gridPane.setPrefWidth(530.0);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMaxWidth(300.0);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(203.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMaxWidth(337.0);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(327.0);

        rowConstraints.setMaxHeight(288.0);
        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(161.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMaxHeight(259.0);
        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(168.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        player1_label.setPrefHeight(57.0);
        player1_label.setPrefWidth(223.0);
        player1_label.setText("     Enter Name of Player 1");
        player1_label.setFont(new Font("Constantia", 17.0));

        GridPane.setRowIndex(player2_label, 1);
        player2_label.setPrefHeight(55.0);
        player2_label.setPrefWidth(223.0);
        player2_label.setText("    Enter Name of Player 2");
        player2_label.setFont(new Font("Constantia", 17.0));
        GridPane.setMargin(player2_label, new Insets(0.0, 0.0, 120.0, 0.0));

        GridPane.setColumnIndex(player1_txt_fied, 1);
        GridPane.setMargin(player1_txt_fied, new Insets(0.0));

        GridPane.setColumnIndex(player2_txt_field, 1);
        GridPane.setRowIndex(player2_txt_field, 1);
        GridPane.setMargin(player2_txt_field, new Insets(0.0, 0.0, 120.0, 0.0));

        GridPane.setColumnIndex(Multi_Start_btn, 1);
        GridPane.setRowIndex(Multi_Start_btn, 1);
        Multi_Start_btn.setMnemonicParsing(false);
        Multi_Start_btn.setPrefHeight(31.0);
        Multi_Start_btn.setPrefWidth(109.0);
        Multi_Start_btn.setText("Start");
        Multi_Start_btn.setFont(new Font(18.0));
        GridPane.setMargin(Multi_Start_btn, new Insets(50.0, 0.0, 0.0, 0.0));
        setLeft(gridPane);
        Multi_Start_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game_screenBase game = new game_screenBase(stage);
                Scene scene = new Scene(game);
                stage.setScene(scene);
            }   
        });

        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getChildren().add(player1_label);
        gridPane.getChildren().add(player2_label);
        gridPane.getChildren().add(player1_txt_fied);
        gridPane.getChildren().add(player2_txt_field);
        gridPane.getChildren().add(Multi_Start_btn);

    }
}
