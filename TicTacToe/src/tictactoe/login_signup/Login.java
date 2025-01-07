package tictactoe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Login extends AnchorPane {

    protected final TextField nameText;
    protected final TextField passwordText;
    protected final Label nameLable;
    protected final Label passwordLable;
    protected final Label registerLable;
    protected final Label title;
    protected final Button signUp;
    protected final Button loginButton;
    protected final Button backButton;

    public Login(Stage stage) {

        nameText = new TextField();
        passwordText = new TextField();
        nameLable = new Label();
        passwordLable = new Label();
        registerLable = new Label();
        title = new Label();
        signUp = new Button();
        loginButton = new Button();
        backButton = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        getStyleClass().add("anchor-pane");
        getStylesheets().add("/tictactoe/all.css");

        nameText.setLayoutX(252.0);
        nameText.setLayoutY(138.0);

        passwordText.setLayoutX(252.0);
        passwordText.setLayoutY(206.0);

        nameLable.setLayoutX(82.0);
        nameLable.setLayoutY(140.0);
        nameLable.setText("Name");
        nameLable.setFont(new Font("Centaur", 18.0));

        passwordLable.setLayoutX(71.0);
        passwordLable.setLayoutY(206.0);
        passwordLable.setPrefHeight(25.0);
        passwordLable.setPrefWidth(62.0);
        passwordLable.setText("Password");
        passwordLable.setFont(new Font("Centaur", 18.0));

        registerLable.setLayoutX(43.0);
        registerLable.setLayoutY(255.0);
        registerLable.setText("Don't have an account?");
        registerLable.setFont(new Font("Centaur", 14.0));

        title.setAlignment(javafx.geometry.Pos.CENTER);
        title.setLayoutX(133.0);
        title.setLayoutY(67.0);
        title.setPrefHeight(17.0);
        title.setPrefWidth(335.0);
        title.setText("Shick-Shack-Shock");
        title.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        title.setTextFill(javafx.scene.paint.Color.valueOf("#c90707"));
        title.setFont(new Font("Copperplate Gothic Light", 24.0));

        signUp.setLayoutX(301.0);
        signUp.setLayoutY(253.0);
        signUp.setMnemonicParsing(false);
        signUp.setPrefHeight(17.0);
        signUp.setPrefWidth(51.0);
        signUp.setText("Sign Up");
        signUp.setFont(new Font("Centaur", 11.0));
        signUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Signup signup = new Signup(stage);
                Scene scene = new Scene(signup);
                stage.setScene(scene);
            }
        });

        loginButton.setLayoutX(273.0);
        loginButton.setLayoutY(325.0);
        loginButton.setMnemonicParsing(false);
        loginButton.setText("Log In");
        loginButton.setFont(new Font("Centaur", 14.0));
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomeOnline home = new HomeOnline(stage);
                Scene scene = new Scene(home);
                stage.setScene(scene);
            }
        });

        backButton.setLayoutX(45.0);
        backButton.setLayoutY(33.0);
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
        setOpaqueInsets(new Insets(0.0));

        getChildren().add(nameText);
        getChildren().add(passwordText);
        getChildren().add(nameLable);
        getChildren().add(passwordLable);
        getChildren().add(registerLable);
        getChildren().add(title);
        getChildren().add(signUp);
        getChildren().add(loginButton);
        getChildren().add(backButton);

    }
}
