package tictactoe.ui.player.singleplayer;

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
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Single_Player_Scene extends AnchorPane {

    protected final TextField textField;
    protected final AnchorPane enter_name_anchor;
    protected final Label enter_name_label;
    protected final Button back_btn;
    protected final AnchorPane label_anchor;
    protected final Label title_label;
    protected final Button start_btn;

    public Single_Player_Scene() {

        textField = new TextField();
        enter_name_anchor = new AnchorPane();
        enter_name_label = new Label();
        back_btn = new Button();
        label_anchor = new AnchorPane();
        title_label = new Label();
        start_btn = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        getStyleClass().add("anchor-pane");
        getStylesheets().add("/tictactoe/styles/Single_player.css");

        textField.setLayoutX(222.0);
        textField.setLayoutY(185.0);
        textField.setPrefHeight(31.0);
        textField.setPrefWidth(239.0);
        textField.setText("Me");
        textField.getStyleClass().add("listview");
        textField.getStylesheets().add("/tictactoe/styles/Single_player.css");

        enter_name_anchor.setLayoutX(27.0);
        enter_name_anchor.setLayoutY(181.0);
        enter_name_anchor.setPrefHeight(40.0);
        enter_name_anchor.setPrefWidth(161.0);
        enter_name_anchor.getStyleClass().add("labelAnchorPane");
        enter_name_anchor.getStylesheets().add("/tictactoe/styles/Single_player.css");

        enter_name_label.setLayoutX(24.0);
        enter_name_label.setLayoutY(176.0);
        enter_name_label.setPrefHeight(49.0);
        enter_name_label.setPrefWidth(225.0);
        enter_name_label.setText("    Enter Your Name");
        enter_name_label.setTextFill(javafx.scene.paint.Color.valueOf("#15b530"));
        enter_name_label.setFont(new Font("Berlin Sans FB", 18.0));

        back_btn.setLayoutX(14.0);
        back_btn.setLayoutY(15.0);
        back_btn.setMnemonicParsing(false);
        back_btn.setPrefHeight(31.0);
        back_btn.setPrefWidth(53.0);
        back_btn.getStyleClass().add("btn");
        back_btn.getStylesheets().add("/tictactoe/styles/Single_player.css");
        back_btn.setText("Back");
        
       

        label_anchor.setLayoutX(143.0);
        label_anchor.setLayoutY(30.0);
        label_anchor.setPrefHeight(56.0);
        label_anchor.setPrefWidth(294.0);
        label_anchor.getStyleClass().add("labelAnchorPane");
        label_anchor.getStylesheets().add("/tictactoe/styles/Single_player.css");

        title_label.setLayoutX(129.0);
        title_label.setLayoutY(34.0);
        title_label.setPrefHeight(49.0);
        title_label.setPrefWidth(356.0);
        title_label.setText("     Shick-Shack-Shock");
        title_label.setTextFill(javafx.scene.paint.Color.valueOf("#0abc49"));
        title_label.setFont(new Font("Berlin Sans FB Demi Bold", 30.0));

        start_btn.setLayoutX(223.0);
        start_btn.setLayoutY(302.0);
        start_btn.setMnemonicParsing(false);
        start_btn.setPrefHeight(31.0);
        start_btn.setPrefWidth(121.0);
        start_btn.getStyleClass().add("btn");
        start_btn.getStylesheets().add("/tictactoe/styles/Single_player.css");
        start_btn.setText("Start");
        
        

        getChildren().add(textField);
        getChildren().add(enter_name_anchor);
        getChildren().add(enter_name_label);
        getChildren().add(back_btn);
        getChildren().add(label_anchor);
        getChildren().add(title_label);
        getChildren().add(start_btn);

    }
}
