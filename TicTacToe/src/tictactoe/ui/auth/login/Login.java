package tictactoe.ui.auth.login;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public abstract class Login extends AnchorPane {

    protected final TextField nameText;
    protected final TextField passwordText;
    protected final AnchorPane nameBackground;
    protected final AnchorPane passwordBackground;
    protected final AnchorPane registerBackground;
    protected final AnchorPane titleBackground;
    protected final Label title;
    protected final Button signUp;
    protected final Button loginButton;
    protected final Button backButton;
    protected final Label nameLable;
    protected final Label passwordLable;
    protected final Label registerLable;

    public Login(Stage stage) {

        nameText = new TextField();
        passwordText = new TextField();
        nameBackground = new AnchorPane();
        passwordBackground = new AnchorPane();
        registerBackground = new AnchorPane();
        titleBackground = new AnchorPane();
        title = new Label();
        signUp = new Button();
        loginButton = new Button();
        backButton = new Button();
        nameLable = new Label();
        passwordLable = new Label();
        registerLable = new Label();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        getStyleClass().add("coverBackground");
        getStylesheets().add("/tictactoe/styles/login.css");

        nameText.setLayoutX(252.0);
        nameText.setLayoutY(138.0);
        nameText.getStyleClass().add("nameText");

        passwordText.setLayoutX(252.0);
        passwordText.setLayoutY(206.0);
        passwordText.getStyleClass().add("passwordText");

        nameBackground.setLayoutX(82.0);
        nameBackground.setLayoutY(145.0);
        nameBackground.setPrefWidth(78.0);

        passwordBackground.setLayoutX(71.0);
        passwordBackground.setLayoutY(206.0);

        registerBackground.setLayoutX(193.0);
        registerBackground.setLayoutY(276.0);

        titleBackground.setLayoutX(133.0);
        titleBackground.setLayoutY(28.0);
        titleBackground.getStyleClass().add("titleBackground");
        titleBackground.getStylesheets().add("/tictactoe/styles/login.css");

        title.setAlignment(javafx.geometry.Pos.CENTER);
        title.setPrefHeight(25.0);
        title.setPrefWidth(335.0);
        title.setText("Shick-Shack-Shock");
        title.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        title.setTextFill(javafx.scene.paint.Color.valueOf("#1e9413"));
        title.setFont(new Font("Berlin Sans FB Demi Bold", 30.0));

        signUp.setLayoutX(346.0);
        signUp.setLayoutY(310.0);
        signUp.setMnemonicParsing(false);
        signUp.setPrefHeight(30.0);
        signUp.setPrefWidth(69.0);
        signUp.getStyleClass().add("signUp");
        signUp.setText("Sign Up");
        signUp.setFont(new Font("Centaur", 11.0));

        loginButton.setLayoutX(481.0);
        loginButton.setLayoutY(168.0);
        loginButton.setMnemonicParsing(false);
        loginButton.getStyleClass().add("loginButton");
        loginButton.setText("Log In");
        loginButton.setFont(new Font("Centaur", 14.0));

        backButton.setLayoutX(27.0);
        backButton.setLayoutY(25.0);
        backButton.setMnemonicParsing(false);
        backButton.setPrefHeight(40.0);
        backButton.setPrefWidth(55.0);
        backButton.getStyleClass().add("backButton");
        backButton.setText("Back");
        backButton.setFont(new Font("Centaur", 14.0));

        nameLable.setAlignment(javafx.geometry.Pos.CENTER);
        nameLable.setLayoutX(135.0);
        nameLable.setLayoutY(144.0);
        nameLable.setPrefHeight(21.0);
        nameLable.setPrefWidth(78.0);
        nameLable.getStyleClass().add("nameBackground");
        nameLable.setText("Name");
        nameLable.setTextFill(javafx.scene.paint.Color.valueOf("#1e9413"));
        nameLable.setFont(new Font("Berlin Sans FB", 18.0));

        passwordLable.setAlignment(javafx.geometry.Pos.CENTER);
        passwordLable.setLayoutX(127.0);
        passwordLable.setLayoutY(207.0);
        passwordLable.setPrefHeight(25.0);
        passwordLable.setPrefWidth(99.0);
        passwordLable.getStyleClass().add("passwordBackground");
        passwordLable.setText("Password");
        passwordLable.setTextFill(javafx.scene.paint.Color.valueOf("#1e9413"));
        passwordLable.setFont(new Font("Berlin Sans FB", 18.0));

        registerLable.setAlignment(javafx.geometry.Pos.CENTER);
        registerLable.setLayoutX(178.0);
        registerLable.setLayoutY(318.0);
        registerLable.setPrefHeight(21.0);
        registerLable.setPrefWidth(149.0);
        registerLable.getStyleClass().add("registerBackground");
        registerLable.setText("Don't have an account?");
        registerLable.setTextFill(javafx.scene.paint.Color.valueOf("#1e9413"));
        registerLable.setFont(new Font("Berlin Sans FB", 14.0));
        setOpaqueInsets(new Insets(0.0));

        getChildren().add(nameText);
        getChildren().add(passwordText);
        getChildren().add(nameBackground);
        getChildren().add(passwordBackground);
        getChildren().add(registerBackground);
        titleBackground.getChildren().add(title);
        getChildren().add(titleBackground);
        getChildren().add(signUp);
        getChildren().add(loginButton);
        getChildren().add(backButton);
        getChildren().add(nameLable);
        getChildren().add(passwordLable);
        getChildren().add(registerLable);

    }
}
