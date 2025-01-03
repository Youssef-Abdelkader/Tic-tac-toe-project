package tictactoe;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class HomeScreen_offline extends BorderPane {

    protected final Label label;
    protected final ImageView imageView;
    protected final ImageView imageView0;
    protected final VBox vBox;
    protected final Button singlePBtn;
    protected final Button MultiPBtn;
    protected final Button OnlineBtn;

    public HomeScreen_offline() {

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

        BorderPane.setAlignment(label, javafx.geometry.Pos.CENTER);
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
        imageView.setImage(new Image(getClass().getResource("/images_and_other/COLOURBOX2165692-removebg-preview.png").toExternalForm()));
        setLeft(imageView);

        BorderPane.setAlignment(imageView0, javafx.geometry.Pos.CENTER);
        imageView0.setFitHeight(150.0);
        imageView0.setFitWidth(200.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("/images_and_other/red-3d-symbol-with-bevel-letter-o_417879-52098-removebg-preview.png").toExternalForm()));
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

        MultiPBtn.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        MultiPBtn.setLayoutX(10.0);
        MultiPBtn.setLayoutY(10.0);
        MultiPBtn.setMaxWidth(Double.MAX_VALUE);
        MultiPBtn.setMnemonicParsing(false);
        MultiPBtn.setStyle("-fx-border-radius: 10;");
        MultiPBtn.setText("Multi player");
        MultiPBtn.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        OnlineBtn.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        OnlineBtn.setLayoutX(10.0);
        OnlineBtn.setLayoutY(42.0);
        OnlineBtn.setMaxWidth(Double.MAX_VALUE);
        OnlineBtn.setMnemonicParsing(false);
        OnlineBtn.setStyle("-fx-border-radius: 10;");
        OnlineBtn.setText("online");
        OnlineBtn.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        vBox.setPadding(new Insets(20.0, 0.0, 0.0, 0.0));
        setCenter(vBox);

        vBox.getChildren().add(singlePBtn);
        vBox.getChildren().add(MultiPBtn);
        vBox.getChildren().add(OnlineBtn);

    }
}
