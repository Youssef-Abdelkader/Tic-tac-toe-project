package tictactoe.ui.game.looser;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public abstract class LOSERBase extends AnchorPane {

    protected final MediaView videoView;
    protected final Label lbl1;
    protected final Label lbl2;
    
    protected final Button btnPA;
    protected final Button btnEX;

    public LOSERBase() {
        videoView = new MediaView();
        lbl1 = new Label();
        lbl2 = new Label();
        
        btnPA = new Button();
        btnEX = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(450.0);
        setPrefWidth(800.0);

        AnchorPane.setBottomAnchor(videoView, 0.0);
        AnchorPane.setLeftAnchor(videoView, 0.0);
        AnchorPane.setRightAnchor(videoView, 0.0);
        AnchorPane.setTopAnchor(videoView, 0.0);

        // Label 1: "You Are The"
        lbl1.setLayoutX(321.0);
        lbl1.setLayoutY(41.0);
        lbl1.setPrefHeight(76.0);
        lbl1.setPrefWidth(150.0);
        lbl1.setText("You Are The");
        lbl1.setTextFill(javafx.scene.paint.Color.valueOf("#ff4d4d")); 
        lbl1.setFont(new Font("Arial", 24.0)); 

       
        lbl2.setLayoutX(294.0);
        lbl2.setLayoutY(79.0);
        lbl2.setPrefHeight(152.0);
        lbl2.setPrefWidth(211.0);
        lbl2.setText("LOSER");
        lbl2.setTextFill(javafx.scene.paint.Color.valueOf("#ff4d4d")); 
        lbl2.setFont(new Font("Verdana Bold", 50.0)); 
    
        btnPA.setLayoutX(342.0);
        btnPA.setLayoutY(274.0);
        btnPA.setMnemonicParsing(false);
        btnPA.setText("Play Again?");
        btnPA.setStyle("-fx-background-color: linear-gradient(to bottom, #6a11cb, #2575fc); " +
                       "-fx-text-fill: white; " +
                       "-fx-font-family: 'Segoe UI'; " +
                       "-fx-font-size: 16px; " +
                       "-fx-padding: 10px; " +
                       "-fx-background-radius: 20;");

       
        btnEX.setLayoutX(315.0);
        btnEX.setLayoutY(323.0);
        btnEX.setMnemonicParsing(false);
        btnEX.setPrefHeight(26.0);
        btnEX.setPrefWidth(159.0);
        btnEX.setText("Exit");

        btnEX.setStyle("-fx-background-color: linear-gradient(to bottom, #ff7e5f, #ff4d4d); " +
                       "-fx-text-fill: white; " +
                       "-fx-font-family: 'Segoe UI'; " +
                       "-fx-font-size: 16px; " +
                       "-fx-padding: 10px; " +
                       "-fx-background-radius: 20;");

        

        getChildren().addAll(videoView, lbl1, lbl2, btnPA, btnEX);

        
        videoView.fitWidthProperty().bind(widthProperty());
        videoView.fitHeightProperty().bind(heightProperty());
        videoView.setPreserveRatio(true);
    }

    public LOSERBase(Stage stage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
