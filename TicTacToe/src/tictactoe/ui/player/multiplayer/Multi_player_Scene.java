package tictactoe.ui.player.multiplayer;

import tictactoe.ui.game.screen.game_screenBase;
import tictactoe.ui.home.offline.HomeScreen_offline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public  class Multi_player_Scene extends AnchorPane {

    protected final AnchorPane title_anchor;
    protected final Label label;
    protected final Button back_btn;
    protected final AnchorPane label1_anchor;
    protected final Label label0;
    protected final AnchorPane label2_anchor;
    protected final Label label1;
    protected final TextField player1_textfield;
    protected final TextField player2_textfield;
    protected final Button start_btn;

    public Multi_player_Scene(Stage stage) {

        title_anchor = new AnchorPane();
        label = new Label();
        back_btn = new Button();
        label1_anchor = new AnchorPane();
        label0 = new Label();
        label2_anchor = new AnchorPane();
        label1 = new Label();
        player1_textfield = new TextField();
        player2_textfield = new TextField();
        start_btn = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        getStyleClass().add("anchor-pane");
        getStylesheets().add("/tictactoe/styles/Multi_player.css");

        title_anchor.setLayoutX(152.0);
        title_anchor.setLayoutY(21.0);
        title_anchor.setPrefHeight(57.0);
        title_anchor.setPrefWidth(271.0);
        title_anchor.getStyleClass().add("labelAnchorPane");
        title_anchor.getStylesheets().add("/tictactoe/styles/Multi_player.css");

        label.setLayoutX(16.0);
        label.setLayoutY(3.0);
        label.setPrefHeight(50.0);
        label.setPrefWidth(275.0);
        label.setText(" Shick-Shack-Shock");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#0fb245"));
        label.setFont(new Font("Berlin Sans FB Demi Bold", 30.0));

        back_btn.setLayoutX(14.0);
        back_btn.setLayoutY(21.0);
        back_btn.setMnemonicParsing(false);
        back_btn.getStyleClass().add("btn");
        back_btn.getStylesheets().add("/tictactoe/styles/Multi_player.css");
        back_btn.setText("Back");
        
        back_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomeScreen_offline home = new HomeScreen_offline(stage);
                Scene scene = new Scene(home);
                stage.setScene(scene);
            }   
        });

        label1_anchor.setLayoutX(25.0);
        label1_anchor.setLayoutY(149.0);
        label1_anchor.setPrefHeight(38.0);
        label1_anchor.setPrefWidth(175.0);
        label1_anchor.getStyleClass().add("labelAnchorPane");
        label1_anchor.getStylesheets().add("/tictactoe/styles/Multi_player.css");

        label0.setLayoutX(10.0);
        label0.setLayoutY(4.0);
        label0.setPrefHeight(31.0);
        label0.setPrefWidth(157.0);
        label0.setText("Enter Player1 Name");
        label0.setTextFill(javafx.scene.paint.Color.valueOf("#0dbf25"));
        label0.setFont(new Font("Berlin Sans FB", 18.0));

        label2_anchor.setLayoutX(14.0);
        label2_anchor.setLayoutY(227.0);
        label2_anchor.setPrefHeight(50.0);
        label2_anchor.setPrefWidth(178.0);

        label1.setLayoutX(11.0);
        label1.setLayoutY(16.0);
        label1.setPrefHeight(38.0);
        label1.setPrefWidth(175.0);
        label1.getStyleClass().add("labelAnchorPane");
        label1.getStylesheets().add("/tictactoe/styles/Multi_player.css");
        label1.setText("  Enter Player2 Name ");
        label1.setTextFill(javafx.scene.paint.Color.valueOf("#0fc327"));
        label1.setFont(new Font("Berlin Sans FB", 18.0));

        player1_textfield.setLayoutX(232.0);
        player1_textfield.setLayoutY(146.0);
        player1_textfield.setPrefHeight(31.0);
        player1_textfield.setPrefWidth(214.0);
        player1_textfield.getStyleClass().add("listview");
        player1_textfield.getStylesheets().add("/tictactoe/styles/Multi_player.css");

        player2_textfield.setLayoutX(232.0);
        player2_textfield.setLayoutY(237.0);
        player2_textfield.setPrefHeight(31.0);
        player2_textfield.setPrefWidth(214.0);
        player2_textfield.getStyleClass().add("listview");
        player2_textfield.getStylesheets().add("/tictactoe/styles/Multi_player.css");

        start_btn.setLayoutX(241.0);
        start_btn.setLayoutY(331.0);
        start_btn.setMnemonicParsing(false);
        start_btn.setPrefHeight(31.0);
        start_btn.setPrefWidth(100.0);
        start_btn.getStyleClass().add("btn");
        start_btn.getStylesheets().add("/tictactoe/styles/Multi_player.css");
        start_btn.setText("Start");
        start_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game_screenBase game = new game_screenBase(stage);
                Scene scene = new Scene(game);
                stage.setScene(scene);
            }   
        });

        title_anchor.getChildren().add(label);
        getChildren().add(title_anchor);
        getChildren().add(back_btn);
        label1_anchor.getChildren().add(label0);
        getChildren().add(label1_anchor);
        label2_anchor.getChildren().add(label1);
        getChildren().add(label2_anchor);
        getChildren().add(player1_textfield);
        getChildren().add(player2_textfield);
        getChildren().add(start_btn);

    }
}
