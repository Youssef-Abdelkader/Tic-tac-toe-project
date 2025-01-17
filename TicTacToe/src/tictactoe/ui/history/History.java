package tictactoe.ui.history;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public /*abstract*/ class History extends BorderPane {

    protected final Label label;
    protected final Button home;
    protected final ScrollPane scrollPane;
    protected final TableView tableView;
    protected final TableColumn GameId_col;
    protected final TableColumn Opponent_col;
    protected final TableColumn winer_col;
    protected final TableColumn recording_col;

    public History(Stage stage) {

        label = new Label();
        home = new Button();
        scrollPane = new ScrollPane();
        tableView = new TableView();
        GameId_col = new TableColumn();
        Opponent_col = new TableColumn();
        winer_col = new TableColumn();
        recording_col = new TableColumn();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        getStyleClass().add("anchor-pane");
        getStylesheets().add("/tictactoe/styles/history.css");

        BorderPane.setAlignment(label, javafx.geometry.Pos.CENTER);
        label.setText("History");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#1e9413"));
        BorderPane.setMargin(label, new Insets(0.0, 0.0, 20.0, 0.0));
        label.setFont(new Font("Berlin Sans FB Demi Bold", 36.0));
        label.getStyleClass().add("labelAnchorPane");
        setTop(label);

        BorderPane.setAlignment(home, javafx.geometry.Pos.CENTER);
        home.setMnemonicParsing(false);
        home.getStyleClass().add("home");
        home.setText("Home");
        BorderPane.setMargin(home, new Insets(10.0, 0.0, 0.0, 0.0));
        home.setFont(new Font("Centaur", 16.0));
        setBottom(home);

        BorderPane.setAlignment(scrollPane, javafx.geometry.Pos.CENTER);
        scrollPane.setStyle("-fx-background-radius: 20;");

        tableView.setMaxHeight(Double.MAX_VALUE);
        tableView.setMaxWidth(Double.MAX_VALUE);
        tableView.setPrefHeight(263.0);
        tableView.getStyleClass().add("tableview");
        tableView.setPrefWidth(557.0);

        GameId_col.setPrefWidth(75.0);
        GameId_col.setText("Game ID");

        Opponent_col.setPrefWidth(115.0);
        Opponent_col.setText("Player 2");

        winer_col.setPrefWidth(113.0);
        winer_col.setText("Winner");

        recording_col.setPrefWidth(116.0);
        recording_col.setText("Recording");
        scrollPane.setContent(tableView);
        setCenter(scrollPane);
        setOpaqueInsets(new Insets(20.0));
        setPadding(new Insets(20.0));

        tableView.getColumns().add(GameId_col);
        tableView.getColumns().add(Opponent_col);
        tableView.getColumns().add(winer_col);
        tableView.getColumns().add(recording_col);

    }
}
