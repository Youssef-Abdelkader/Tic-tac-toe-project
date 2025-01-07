package tictactoe.ui.game.winner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import tictactoe.ui.game.screen.game_screenBase;

public class WINNERController extends WINNERBase {

    private MediaPlayer mediaPlayer;

    public WINNERController(Stage stage) {
        super();

        Media media = new Media(getClass().getResource("/resources/winner.mp4").toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        videoView.setMediaPlayer(mediaPlayer);

        mediaPlayer.setAutoPlay(true);

        btnPA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game_screenBase gameScreen = new game_screenBase(stage);
                Scene scene = new Scene(gameScreen);
                stage.setScene(scene);

                mediaPlayer.pause();
            }
        });

        btnEX.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
    }
}
