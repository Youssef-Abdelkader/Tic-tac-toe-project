package tictactoe.ui.auth.signup;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public abstract class Signup extends AnchorPane {

    protected final TextField nameText;
    protected final TextField passwordText;
    protected final AnchorPane nameBackground;
    protected final AnchorPane passwordBackground;
    protected final AnchorPane titleBackground;
    protected final Label title;
    protected final Button signUp;
    protected final Button backButton;
    protected final Label nameLable;
    protected final Label passwordLable;

    public Signup(Stage stage) {

        nameText = new TextField();
        passwordText = new TextField();
        nameBackground = new AnchorPane();
        passwordBackground = new AnchorPane();
        titleBackground = new AnchorPane();
        title = new Label();
        signUp = new Button();
        backButton = new Button();
        nameLable = new Label();
        passwordLable = new Label();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        getStyleClass().add("coverPage");
        getStylesheets().add("/tictactoe/styles/signUp.css");

        nameText.setLayoutX(264.0);
        nameText.setLayoutY(148.0);
        nameText.getStyleClass().add("nameText");

        passwordText.setLayoutX(264.0);
        passwordText.setLayoutY(211.0);
        passwordText.getStyleClass().add("passwordText");

        nameBackground.setLayoutX(68.0);
        nameBackground.setLayoutY(149.0);

        passwordBackground.setLayoutX(57.0);
        passwordBackground.setLayoutY(216.0);

        titleBackground.setLayoutX(133.0);
        titleBackground.setLayoutY(33.0);

        title.setAlignment(javafx.geometry.Pos.CENTER);
        title.setLayoutY(-7.0);
        title.setPrefHeight(17.0);
        title.setPrefWidth(335.0);
        title.getStyleClass().add("titleBackground");
        title.setText("Shick-Shack-Shock");
        title.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        title.setTextFill(javafx.scene.paint.Color.valueOf("#1e9413"));
        title.setFont(new Font("Berlin Sans FB Demi Bold", 30.0));

        signUp.setLayoutX(264.0);
        signUp.setLayoutY(301.0);
        signUp.setMnemonicParsing(false);
        signUp.setPrefHeight(28.0);
        signUp.setPrefWidth(73.0);
        signUp.getStyleClass().add("signUp");
        signUp.setText("Sign Up");
        signUp.setFont(new Font("Centaur", 14.0));

        backButton.setLayoutX(31.0);
        backButton.setLayoutY(27.0);
        backButton.setMnemonicParsing(false);
        backButton.getStyleClass().add("backButton");
        backButton.setText("Back");
        backButton.setFont(new Font("Centaur", 14.0));

        nameLable.setAlignment(javafx.geometry.Pos.CENTER);
        nameLable.setLayoutX(159.0);
        nameLable.setLayoutY(148.0);
        nameLable.setPrefHeight(25.0);
        nameLable.setPrefWidth(62.0);
        nameLable.getStyleClass().add("nameBackground");
        nameLable.setText("Name");
        nameLable.setTextFill(javafx.scene.paint.Color.valueOf("#1e9413"));
        nameLable.setFont(new Font("Berlin Sans FB", 18.0));

        passwordLable.setAlignment(javafx.geometry.Pos.CENTER);
        passwordLable.setLayoutX(158.0);
        passwordLable.setLayoutY(211.0);
        passwordLable.setPrefHeight(25.0);
        passwordLable.setPrefWidth(81.0);
        passwordLable.getStyleClass().add("passwordBackground");
        passwordLable.setText("Password");
        passwordLable.setTextFill(javafx.scene.paint.Color.valueOf("#1e9413"));
        passwordLable.setFont(new Font("Berlin Sans FB", 18.0));

        getChildren().add(nameText);
        getChildren().add(passwordText);
        getChildren().add(nameBackground);
        getChildren().add(passwordBackground);
        titleBackground.getChildren().add(title);
        getChildren().add(titleBackground);
        getChildren().add(signUp);
        getChildren().add(backButton);
        getChildren().add(nameLable);
        getChildren().add(passwordLable);

    }
}
