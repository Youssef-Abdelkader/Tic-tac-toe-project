package tictactoe;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class game_screenBase extends BorderPane {

    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final ColumnConstraints columnConstraints1;
    protected final ColumnConstraints columnConstraints2;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final ImageView imageView;
    protected final ImageView imageView0;
    protected final Label label;
    protected final Label label0;
    protected final GridPane gridPane0;
    protected final ColumnConstraints columnConstraints3;
    protected final ColumnConstraints columnConstraints4;
    protected final ColumnConstraints columnConstraints5;
    protected final RowConstraints rowConstraints1;
    protected final RowConstraints rowConstraints2;
    protected final RowConstraints rowConstraints3;
    protected final ImageView imageView1;
    protected final ImageView imageView2;
    protected final ImageView imageView3;
    protected final ImageView imageView4;
    protected final ImageView imageView5;
    protected final ImageView imageView6;
    protected final ImageView imageView7;
    protected final ImageView imageView8;
    protected final ImageView imageView9;

    public game_screenBase(Stage stage) {

        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        columnConstraints1 = new ColumnConstraints();
        columnConstraints2 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        imageView = new ImageView();
        imageView0 = new ImageView();
        label = new Label();
        label0 = new Label();
        gridPane0 = new GridPane();
        columnConstraints3 = new ColumnConstraints();
        columnConstraints4 = new ColumnConstraints();
        columnConstraints5 = new ColumnConstraints();
        rowConstraints1 = new RowConstraints();
        rowConstraints2 = new RowConstraints();
        rowConstraints3 = new RowConstraints();
        imageView1 = new ImageView();
        imageView2 = new ImageView();
        imageView3 = new ImageView();
        imageView4 = new ImageView();
        imageView5 = new ImageView();
        imageView6 = new ImageView();
        imageView7 = new ImageView();
        imageView8 = new ImageView();
        imageView9 = new ImageView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

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

        GridPane.setRowSpan(imageView, 2);
        imageView.setFitHeight(53.0);
        imageView.setFitWidth(56.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("/images_and_other/OIP.jpeg").toExternalForm()));

        GridPane.setColumnIndex(imageView0, 2);
        GridPane.setRowSpan(imageView0, 2);
        imageView0.setFitHeight(58.0);
        imageView0.setFitWidth(60.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("/images_and_other/371-3713702_new-svg-image-letter-o-red-removebg-preview.png").toExternalForm()));

        GridPane.setColumnIndex(label, 1);
        GridPane.setRowSpan(label, 2);
        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label.setText("Player 1");
        label.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        GridPane.setColumnIndex(label0, 3);
        GridPane.setRowSpan(label0, 2);
        label0.setAlignment(javafx.geometry.Pos.CENTER);
        label0.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label0.setLayoutX(160.0);
        label0.setLayoutY(17.0);
        label0.setText("Player 2");
        BorderPane.setMargin(gridPane, new Insets(10.0));
        setTop(gridPane);

        BorderPane.setAlignment(gridPane0, javafx.geometry.Pos.CENTER);
        gridPane0.setAlignment(javafx.geometry.Pos.CENTER);
        gridPane0.setGridLinesVisible(true);
        gridPane0.setPrefHeight(400.0);
        gridPane0.setPrefWidth(400.0);

        columnConstraints3.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints3.setMinWidth(10.0);
        columnConstraints3.setPrefWidth(100.0);

        columnConstraints4.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints4.setMinWidth(10.0);
        columnConstraints4.setPrefWidth(100.0);

        columnConstraints5.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints5.setMinWidth(10.0);
        columnConstraints5.setPrefWidth(100.0);

        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(30.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints2.setMinHeight(10.0);
        rowConstraints2.setPrefHeight(30.0);
        rowConstraints2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints3.setMinHeight(10.0);
        rowConstraints3.setPrefHeight(30.0);
        rowConstraints3.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane0.setPadding(new Insets(10.0));

        GridPane.setHalignment(imageView1, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(imageView1, javafx.geometry.VPos.CENTER);
        imageView1.setFitHeight(102.0);
        imageView1.setFitWidth(147.0);
        imageView1.setPickOnBounds(true);
        imageView1.setPreserveRatio(true);

        GridPane.setColumnIndex(imageView2, 2);
        GridPane.setHalignment(imageView2, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(imageView2, 2);
        GridPane.setValignment(imageView2, javafx.geometry.VPos.CENTER);
        imageView2.setFitHeight(100.0);
        imageView2.setFitWidth(144.0);
        imageView2.setLayoutX(25.0);
        imageView2.setLayoutY(-1.0);
        imageView2.setPickOnBounds(true);
        imageView2.setPreserveRatio(true);

        GridPane.setColumnIndex(imageView3, 2);
        GridPane.setHalignment(imageView3, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(imageView3, 1);
        GridPane.setValignment(imageView3, javafx.geometry.VPos.CENTER);
        imageView3.setFitHeight(100.0);
        imageView3.setFitWidth(144.0);
        imageView3.setLayoutX(25.0);
        imageView3.setLayoutY(-1.0);
        imageView3.setPickOnBounds(true);
        imageView3.setPreserveRatio(true);

        GridPane.setColumnIndex(imageView4, 2);
        GridPane.setHalignment(imageView4, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(imageView4, javafx.geometry.VPos.CENTER);
        imageView4.setFitHeight(102.0);
        imageView4.setFitWidth(147.0);
        imageView4.setLayoutX(25.0);
        imageView4.setLayoutY(-1.0);
        imageView4.setPickOnBounds(true);
        imageView4.setPreserveRatio(true);

        GridPane.setColumnIndex(imageView5, 1);
        GridPane.setHalignment(imageView5, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(imageView5, 2);
        GridPane.setValignment(imageView5, javafx.geometry.VPos.CENTER);
        imageView5.setFitHeight(101.0);
        imageView5.setFitWidth(146.0);
        imageView5.setLayoutX(25.0);
        imageView5.setLayoutY(-1.0);
        imageView5.setPickOnBounds(true);
        imageView5.setPreserveRatio(true);

        GridPane.setColumnIndex(imageView6, 1);
        GridPane.setHalignment(imageView6, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(imageView6, 1);
        GridPane.setValignment(imageView6, javafx.geometry.VPos.CENTER);
        imageView6.setFitHeight(98.0);
        imageView6.setFitWidth(152.0);
        imageView6.setLayoutX(25.0);
        imageView6.setLayoutY(-1.0);
        imageView6.setPickOnBounds(true);
        imageView6.setPreserveRatio(true);

        GridPane.setColumnIndex(imageView7, 1);
        GridPane.setHalignment(imageView7, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(imageView7, javafx.geometry.VPos.CENTER);
        imageView7.setFitHeight(97.0);
        imageView7.setFitWidth(144.0);
        imageView7.setLayoutX(25.0);
        imageView7.setLayoutY(-1.0);
        imageView7.setPickOnBounds(true);
        imageView7.setPreserveRatio(true);

        GridPane.setHalignment(imageView8, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(imageView8, 2);
        GridPane.setValignment(imageView8, javafx.geometry.VPos.CENTER);
        imageView8.setFitHeight(102.0);
        imageView8.setFitWidth(152.0);
        imageView8.setLayoutX(25.0);
        imageView8.setLayoutY(-1.0);
        imageView8.setPickOnBounds(true);
        imageView8.setPreserveRatio(true);

        GridPane.setHalignment(imageView9, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(imageView9, 1);
        GridPane.setValignment(imageView9, javafx.geometry.VPos.CENTER);
        imageView9.setFitHeight(103.0);
        imageView9.setFitWidth(145.0);
        imageView9.setLayoutX(25.0);
        imageView9.setLayoutY(-1.0);
        imageView9.setPickOnBounds(true);
        imageView9.setPreserveRatio(true);
        setCenter(gridPane0);
        setPadding(new Insets(10.0));

        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getColumnConstraints().add(columnConstraints1);
        gridPane.getColumnConstraints().add(columnConstraints2);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getChildren().add(imageView);
        gridPane.getChildren().add(imageView0);
        gridPane.getChildren().add(label);
        gridPane.getChildren().add(label0);
        gridPane0.getColumnConstraints().add(columnConstraints3);
        gridPane0.getColumnConstraints().add(columnConstraints4);
        gridPane0.getColumnConstraints().add(columnConstraints5);
        gridPane0.getRowConstraints().add(rowConstraints1);
        gridPane0.getRowConstraints().add(rowConstraints2);
        gridPane0.getRowConstraints().add(rowConstraints3);
        gridPane0.getChildren().add(imageView1);
        gridPane0.getChildren().add(imageView2);
        gridPane0.getChildren().add(imageView3);
        gridPane0.getChildren().add(imageView4);
        gridPane0.getChildren().add(imageView5);
        gridPane0.getChildren().add(imageView6);
        gridPane0.getChildren().add(imageView7);
        gridPane0.getChildren().add(imageView8);
        gridPane0.getChildren().add(imageView9);

    }
}
