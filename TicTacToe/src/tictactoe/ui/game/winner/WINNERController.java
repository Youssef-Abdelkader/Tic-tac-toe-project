package tictactoe.ui.game.winner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import tictactoe.ui.game.screen.GamescreenController;
import tictactoe.ui.game.screen.game_screenBase;

public class WINNERController extends WINNERBase {

    private MediaPlayer mediaPlayer;
    
     //SINGLE PLAYER CONSTRUCTOR

    public WINNERController(Stage stage, String name) {
        super();

        // Initialize the MediaPlayer with the winner video
        Media media = new Media(getClass().getResource("/resources/winner.mp4").toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        videoView.setMediaPlayer(mediaPlayer);

        // Set the video to auto-play
        mediaPlayer.setAutoPlay(true);

        // Play Again button event handler
        btnPA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Transition to the game screen
                game_screenBase gameScreen = new GamescreenController(stage, name);
                Scene scene = new Scene(gameScreen);
                stage.setScene(scene);

                // Pause the video when transitioning
                mediaPlayer.pause();
            }
        });

        // Exit button event handler
        btnEX.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Close the stage (exit the application)
                stage.close();
            }
        });
    }
    
    
    //MULTI PLAYER CONSTRUCTOR
    
    public WINNERController(Stage stage, String name1, String name2) {
        super();

        // Initialize the MediaPlayer with the winner video
        Media media = new Media(getClass().getResource("/resources/winner.mp4").toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        videoView.setMediaPlayer(mediaPlayer);

        // Set the video to auto-play
        mediaPlayer.setAutoPlay(true);

        // Play Again button event handler
        btnPA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Transition to the game screen
                game_screenBase gameScreen = new GamescreenController(stage, name1, name2);
                Scene scene = new Scene(gameScreen);
                stage.setScene(scene);

                // Pause the video when transitioning
                mediaPlayer.pause();
            }
        });

        // Exit button event handler
        btnEX.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Close the stage (exit the application)
                stage.close();
            }
        });
    }
}
