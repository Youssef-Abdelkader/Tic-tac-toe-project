package tictactoe.ui.game.screen;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public abstract class game_screenBase extends BorderPane {

    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final ColumnConstraints columnConstraints1;
    protected final ColumnConstraints columnConstraints2;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final RowConstraints rowConstraints2;
    protected final ImageView imageView;
    protected final ImageView imageView0;
    protected final Label label;
    protected final Button recordButton;
    protected final Button forfeitButton;
    protected final Label label0;
    protected final Label label1;
    protected final Label label2;
    protected final GridPane gridPane0;
    protected final ColumnConstraints columnConstraints3;
    protected final ColumnConstraints columnConstraints4;
    protected final ColumnConstraints columnConstraints5;
    protected final RowConstraints rowConstraints3;
    protected final RowConstraints rowConstraints4;
    protected final RowConstraints rowConstraints5;

    protected final ImageView boxOne;
    protected final ImageView boxTwo;
    protected final ImageView boxThree;
    protected final ImageView boxFour;
    protected final ImageView boxFive;
    protected final ImageView boxSix;
    protected final ImageView boxSeven;
    protected final ImageView boxEight;
    protected final ImageView boxNine;

    public game_screenBase(Stage stage) {

        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        columnConstraints1 = new ColumnConstraints();
        columnConstraints2 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        rowConstraints2 = new RowConstraints();
        imageView = new ImageView();
        imageView0 = new ImageView();
        label = new Label();
        recordButton = new Button();
        forfeitButton = new Button();
        label0 = new Label();
        label1 = new Label();
        label2 = new Label();
        gridPane0 = new GridPane();
        columnConstraints3 = new ColumnConstraints();
        columnConstraints4 = new ColumnConstraints();
        columnConstraints5 = new ColumnConstraints();
        rowConstraints3 = new RowConstraints();
        rowConstraints4 = new RowConstraints();
        rowConstraints5 = new RowConstraints();

        boxOne = new ImageView();
        boxTwo = new ImageView();
        boxThree = new ImageView();
        boxFour = new ImageView();
        boxFive = new ImageView();
        boxSix = new ImageView();
        boxSeven = new ImageView();
        boxEight = new ImageView();
        boxNine = new ImageView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        getStyleClass().add("anchor-pane");
        getStylesheets().add("/tictactoe/styles/background.css");

        BorderPane.setAlignment(gridPane, javafx.geometry.Pos.CENTER);
        gridPane.setAlignment(javafx.geometry.Pos.CENTER);
        gridPane.setHgap(10.0);
        gridPane.setPrefHeight(60.0);
        gridPane.setPrefWidth(556.0);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMaxWidth(114.0);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(50.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMaxWidth(311.0);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(308.0);

        columnConstraints1.setHalignment(javafx.geometry.HPos.LEFT);
        columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints1.setMaxWidth(132.0);
        columnConstraints1.setMinWidth(10.0);
        columnConstraints1.setPrefWidth(56.0);

        columnConstraints2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints2.setMaxWidth(125.0);
        columnConstraints2.setMinWidth(10.0);
        columnConstraints2.setPrefWidth(125.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(30.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(30.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints2.setMinHeight(10.0);
        rowConstraints2.setPrefHeight(30.0);
        rowConstraints2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        GridPane.setRowSpan(imageView, 2);
        imageView.setFitHeight(53.0);
        imageView.setFitWidth(56.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("/tictactoe/images/x_game.jpeg").toExternalForm()));

        GridPane.setColumnIndex(imageView0, 2);
        GridPane.setRowSpan(imageView0, 2);
        imageView0.setFitHeight(58.0);
        imageView0.setFitWidth(60.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("/tictactoe/images/o_game.png").toExternalForm()));

        GridPane.setColumnIndex(label, 3);
        GridPane.setRowSpan(label, 2);
        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label.setLayoutX(160.0);
        label.setLayoutY(17.0);
        label.setText("Menna");
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setFont(new Font("Comic Sans MS Bold", 34.0));

        GridPane.setColumnIndex(recordButton, 1);
        GridPane.setRowIndex(recordButton, 2);
        recordButton.setMnemonicParsing(false);
        recordButton.getStyleClass().add("record_btn");
        recordButton.getStylesheets().add("/tictactoe/styles/background.css");
        recordButton.setText("Record");
        recordButton.setTranslateX(200.0);
        recordButton.setFont(new Font("Centaur", 14.0));

        GridPane.setColumnIndex(forfeitButton, 1);
        GridPane.setRowIndex(forfeitButton, 2);
        forfeitButton.setMnemonicParsing(false);
        forfeitButton.getStyleClass().add("forfiet_btn");
        forfeitButton.getStylesheets().add("/tictactoe/styles/background.css");
        forfeitButton.setText("Forfeit");
        forfeitButton.setTranslateX(120.0);
        forfeitButton.setFont(new Font("Centaur", 14.0));

        GridPane.setColumnIndex(label0, 3);
        GridPane.setRowIndex(label0, 2);
        label0.setText("Score  40");
        label0.setTextFill(javafx.scene.paint.Color.WHITE);
        label0.setTranslateY(15.0);
        label0.setFont(new Font("Cambria Bold", 18.0));

        GridPane.setColumnIndex(label1, 1);
        GridPane.setRowSpan(label1, 2);
        label1.setAlignment(javafx.geometry.Pos.CENTER);
        label1.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label1.setText("Habiba");
        label1.setTextFill(javafx.scene.paint.Color.WHITE);
        label1.setFont(new Font("Comic Sans MS Bold", 34.0));

        GridPane.setColumnIndex(label2, 1);
        GridPane.setRowIndex(label2, 3);
        label2.setText("Score  40");
        label2.setTextFill(javafx.scene.paint.Color.WHITE);
        label2.setFont(new Font("Cambria Bold", 18.0));
        BorderPane.setMargin(gridPane, new Insets(10.0));
        setTop(gridPane);

        BorderPane.setAlignment(gridPane0, javafx.geometry.Pos.CENTER);
        gridPane0.setAlignment(javafx.geometry.Pos.CENTER);
        gridPane0.setPrefHeight(400.0);
        gridPane0.setPrefWidth(400.0);
        gridPane0.setStyle("-fx-opacity: .8; -fx-border-radius: 20;");
        gridPane0.getStyleClass().add("game_grid");
        gridPane0.getStylesheets().add("/tictactoe/styles/background.css");

        columnConstraints3.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints3.setMinWidth(10.0);
        columnConstraints3.setPrefWidth(100.0);

        columnConstraints4.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints4.setMinWidth(10.0);
        columnConstraints4.setPrefWidth(100.0);

        columnConstraints5.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints5.setMinWidth(10.0);
        columnConstraints5.setPrefWidth(100.0);

        rowConstraints3.setMinHeight(10.0);
        rowConstraints3.setPrefHeight(30.0);
        rowConstraints3.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints4.setMinHeight(10.0);
        rowConstraints4.setPrefHeight(30.0);
        rowConstraints4.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints5.setMinHeight(10.0);
        rowConstraints5.setPrefHeight(30.0);
        rowConstraints5.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane0.setPadding(new Insets(10.0));

        GridPane.setHalignment(boxOne, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(boxOne, javafx.geometry.VPos.CENTER);
        boxOne.setFitHeight(102.0);
        boxOne.setFitWidth(147.0);
        boxOne.setPickOnBounds(true);
        boxOne.setPreserveRatio(true);

        GridPane.setColumnIndex(boxTwo, 1);
        GridPane.setHalignment(boxTwo, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(boxTwo, javafx.geometry.VPos.CENTER);
        boxTwo.setFitHeight(97.0);
        boxTwo.setFitWidth(144.0);
        boxTwo.setLayoutX(25.0);
        boxTwo.setLayoutY(-1.0);
        boxTwo.setPickOnBounds(true);
        boxTwo.setPreserveRatio(true);

        GridPane.setColumnIndex(boxThree, 2);
        GridPane.setHalignment(boxThree, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(boxThree, javafx.geometry.VPos.CENTER);
        boxThree.setFitHeight(102.0);
        boxThree.setFitWidth(147.0);
        boxThree.setLayoutX(25.0);
        boxThree.setLayoutY(-1.0);
        boxThree.setPickOnBounds(true);
        boxThree.setPreserveRatio(true);

        GridPane.setHalignment(boxFour, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(boxFour, 1);
        GridPane.setValignment(boxFour, javafx.geometry.VPos.CENTER);
        boxFour.setFitHeight(103.0);
        boxFour.setFitWidth(145.0);
        boxFour.setLayoutX(25.0);
        boxFour.setLayoutY(-1.0);
        boxFour.setPickOnBounds(true);
        boxFour.setPreserveRatio(true);

        GridPane.setColumnIndex(boxFive, 1);
        GridPane.setHalignment(boxFive, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(boxFive, 1);
        GridPane.setValignment(boxFive, javafx.geometry.VPos.CENTER);
        boxFive.setFitHeight(98.0);
        boxFive.setFitWidth(152.0);
        boxFive.setLayoutX(25.0);
        boxFive.setLayoutY(-1.0);
        boxFive.setPickOnBounds(true);
        boxFive.setPreserveRatio(true);

        GridPane.setColumnIndex(boxSix, 2);
        GridPane.setHalignment(boxSix, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(boxSix, 1);
        GridPane.setValignment(boxSix, javafx.geometry.VPos.CENTER);
        boxSix.setFitHeight(100.0);
        boxSix.setFitWidth(144.0);
        boxSix.setLayoutX(25.0);
        boxSix.setLayoutY(-1.0);
        boxSix.setPickOnBounds(true);
        boxSix.setPreserveRatio(true);

        GridPane.setHalignment(boxSeven, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(boxSeven, 2);
        GridPane.setValignment(boxSeven, javafx.geometry.VPos.CENTER);
        boxSeven.setFitHeight(102.0);
        boxSeven.setFitWidth(152.0);
        boxSeven.setLayoutX(25.0);
        boxSeven.setLayoutY(-1.0);
        boxSeven.setPickOnBounds(true);
        boxSeven.setPreserveRatio(true);

        GridPane.setColumnIndex(boxEight, 1);
        GridPane.setHalignment(boxEight, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(boxEight, 2);
        GridPane.setValignment(boxEight, javafx.geometry.VPos.CENTER);
        boxEight.setFitHeight(101.0);
        boxEight.setFitWidth(146.0);
        boxEight.setLayoutX(25.0);
        boxEight.setLayoutY(-1.0);
        boxEight.setPickOnBounds(true);
        boxEight.setPreserveRatio(true);

        GridPane.setColumnIndex(boxNine, 2);
        GridPane.setHalignment(boxNine, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(boxNine, 2);
        GridPane.setValignment(boxNine, javafx.geometry.VPos.CENTER);
        boxNine.setFitHeight(100.0);
        boxNine.setFitWidth(144.0);
        boxNine.setLayoutX(25.0);
        boxNine.setLayoutY(-1.0);
        boxNine.setPickOnBounds(true);
        boxNine.setPreserveRatio(true);
        setCenter(gridPane0);
        setPadding(new Insets(10.0));

        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getColumnConstraints().add(columnConstraints1);
        gridPane.getColumnConstraints().add(columnConstraints2);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        gridPane.getRowConstraints().add(rowConstraints2);
        gridPane.getChildren().add(imageView);
        gridPane.getChildren().add(imageView0);
        gridPane.getChildren().add(label);
        gridPane.getChildren().add(recordButton);
        gridPane.getChildren().add(forfeitButton);
        gridPane.getChildren().add(label0);
        gridPane.getChildren().add(label1);
        gridPane.getChildren().add(label2);

        gridPane0.getColumnConstraints().add(columnConstraints3);
        gridPane0.getColumnConstraints().add(columnConstraints4);
        gridPane0.getColumnConstraints().add(columnConstraints5);
        gridPane0.getRowConstraints().add(rowConstraints3);
        gridPane0.getRowConstraints().add(rowConstraints4);
        gridPane0.getRowConstraints().add(rowConstraints5);

        gridPane0.getChildren().add(boxOne);
        gridPane0.getChildren().add(boxTwo);
        gridPane0.getChildren().add(boxThree);
        gridPane0.getChildren().add(boxFour);
        gridPane0.getChildren().add(boxFive);
        gridPane0.getChildren().add(boxSix);
        gridPane0.getChildren().add(boxSeven);
        gridPane0.getChildren().add(boxEight);
        gridPane0.getChildren().add(boxNine);

    // Calculate the start and end positions of the line based on the grid cell size
   
    Line line = new Line(0, 0, 0, 0);
    line.setStroke(Color.BLUEVIOLET);
    line.setStrokeWidth(5);

    // Optionally, bind to the width and height if you want the line to adjust dynamically
    // line.endXProperty().bind(gridPane0.widthProperty());
    line.endYProperty().bind(gridPane0.heightProperty());

    // Add the line to the grid
    gridPane0.add(line, 0, 1);

    }
}
