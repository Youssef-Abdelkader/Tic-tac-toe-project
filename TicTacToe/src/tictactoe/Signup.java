package tictactoe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Signup extends AnchorPane {

    protected final TextField nameText;
    protected final TextField passwordText;
    protected final Label nameLable;
    protected final Label passwordLable;
    protected final Label title;
    protected final Button signUp;
    protected final Button backButton;

    public Signup(Stage stage) {

        nameText = new TextField();
        passwordText = new TextField();
        nameLable = new Label();
        passwordLable = new Label();
        title = new Label();
        signUp = new Button();
        backButton = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        nameText.setLayoutX(236.0);
        nameText.setLayoutY(149.0);

        passwordText.setLayoutX(236.0);
        passwordText.setLayoutY(229.0);

        nameLable.setLayoutX(189.0);
        nameLable.setLayoutY(127.0);
        nameLable.setText("Name");
        nameLable.setFont(new Font("Centaur", 18.0));

        passwordLable.setLayoutX(188.0);
        passwordLable.setLayoutY(198.0);
        passwordLable.setPrefHeight(25.0);
        passwordLable.setPrefWidth(62.0);
        passwordLable.setText("Password");
        passwordLable.setFont(new Font("Centaur", 18.0));

        title.setAlignment(javafx.geometry.Pos.CENTER);
        title.setLayoutX(143.0);
        title.setLayoutY(55.0);
        title.setPrefHeight(17.0);
        title.setPrefWidth(335.0);
        title.setText("Shick-Shack-Shock");
        title.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        title.setTextFill(javafx.scene.paint.Color.valueOf("#c90707"));
        title.setFont(new Font("Copperplate Gothic Light", 24.0));

        signUp.setLayoutX(274.0);
        signUp.setLayoutY(283.0);
        signUp.setMnemonicParsing(false);
        signUp.setPrefHeight(28.0);
        signUp.setPrefWidth(73.0);
        signUp.setText("Sign Up");
        signUp.setFont(new Font("Centaur", 14.0));
        signUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomeOnline home = new HomeOnline(stage);
                Scene scene = new Scene(home);
                stage.setScene(scene);
            }
        });

        backButton.setLayoutX(44.0);
        backButton.setLayoutY(30.0);
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

        getChildren().add(nameText);
        getChildren().add(passwordText);
        getChildren().add(nameLable);
        getChildren().add(passwordLable);
        getChildren().add(title);
        getChildren().add(signUp);
        getChildren().add(backButton);

    }
}
