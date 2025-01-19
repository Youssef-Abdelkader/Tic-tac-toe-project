package tictactoe.ui.game.screen;

import java.lang.String;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public abstract class recordingScreenBase extends BorderPane {

    protected final Label label;
    protected final Button button;
    protected final ScrollPane scrollPane;
    protected final VBox vBox;

    public recordingScreenBase() {

        label = new Label();
        button = new Button();
        scrollPane = new ScrollPane();
        vBox = new VBox();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        getStyleClass().add("anchor-pane");
        getStylesheets().add("/tictactoe/styles/background.css");

        BorderPane.setAlignment(label, javafx.geometry.Pos.CENTER);
        label.setText("Previous games");

        label.getStyleClass().add("homescreen_title");
        label.getStylesheets().add("/tictactoe/styles/background.css");
        setTop(label);
        label.setFont(new Font("Berlin Sans FB Demi Bold", 36.0));
        label.setStyle("-fx-text-fill: #15b530;");

        BorderPane.setAlignment(button, javafx.geometry.Pos.CENTER);
        button.setMnemonicParsing(false);
        button.setText("Home");
        button.setFont(new Font("Cambria", 16.0));
        button.getStylesheets().add("/tictactoe/styles/background.css");
        button.setStyle("-fx-border-radius: 10;");
        button.getStyleClass().add("history_btn");
        setBottom(button);

        BorderPane.setAlignment(scrollPane, javafx.geometry.Pos.CENTER);
        scrollPane.setNodeOrientation(javafx.geometry.NodeOrientation.LEFT_TO_RIGHT);
        scrollPane.setPrefHeight(278.0);
        scrollPane.setPrefWidth(376.0);
        BorderPane.setMargin(scrollPane, new Insets(20.0));

        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        vBox.setMaxHeight(Double.MAX_VALUE);
        vBox.setMaxWidth(Double.MAX_VALUE);
        vBox.setMinHeight(USE_PREF_SIZE);
        vBox.setMinWidth(USE_PREF_SIZE);
        vBox.setPrefHeight(240.0);
        vBox.setPrefWidth(375.0);
        vBox.setPadding(new Insets(20.0));
        scrollPane.setContent(vBox);
        setCenter(scrollPane);
        setPadding(new Insets(20.0));

    }
}
