package tictactoe.ui.game.winner;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public abstract class WINNERBase extends AnchorPane {

    protected final MediaView videoView;
    protected final Label lbl1;
    protected final Label lbl2;
    protected final Label score;
    protected final Button btnPA;
    protected final Button btnEX;

    public WINNERBase() {
        videoView = new MediaView();
        lbl1 = new Label();
        lbl2 = new Label();
        score = new Label();
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
        
        videoView.setLayoutX(200.0);
        videoView.setLayoutY(100.0);

        lbl1.setLayoutX(324.0);
        lbl1.setLayoutY(42.0);
        lbl1.setText("You Are The");
        lbl1.setTextFill(javafx.scene.paint.Color.valueOf("#cc2727"));
        lbl1.setFont(new Font(26.0));

        lbl2.setLayoutX(262.0);
        lbl2.setLayoutY(80.0);
        lbl2.setText("WINNER");
        lbl2.setTextFill(javafx.scene.paint.Color.valueOf("#c91c1c"));
        lbl2.setFont(new Font(71.0));

        score.setLayoutX(346.0);
        score.setLayoutY(213.0);
        score.setText("New Score = ");
        score.setTextFill(javafx.scene.paint.Color.valueOf("#ec2a2a"));
        score.setFont(new Font(16.0));

        btnPA.setLayoutX(339.0);
        btnPA.setLayoutY(269.0);
        btnPA.setText("Play Again?");
        
        btnEX.setLayoutX(319.0);
        btnEX.setLayoutY(323.0);
        btnEX.setText("Exit");

        getChildren().addAll(videoView, lbl1, lbl2, score, btnPA, btnEX);
    }
}
