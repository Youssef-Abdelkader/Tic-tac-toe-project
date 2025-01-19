package tictactoe.ui.game.winner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import tictactoe.TicTacToe;
import tictactoe.ui.game.screen.Game_Screen_Controller_pc;
import tictactoe.ui.game.screen.GamescreenController;
import tictactoe.ui.game.screen.GamescreenController_Multi;
import tictactoe.ui.game.screen.game_screenBase;
import tictactoe.ui.home.online.HomeOnlineController;

public class WINNERController extends WINNERBase {

    private MediaPlayer mediaPlayer;

    // SINGLE PLAYER CONSTRUCTOR
    public WINNERController(Stage stage, String name) {
        super();

        // Initialize the MediaPlayer with the winner video
        Media media = new Media(getClass().getResource("/resources/winner.mp4").toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        videoView.setMediaPlayer(mediaPlayer);

        // Set the video to auto-play
        mediaPlayer.setAutoPlay(true);

        // Pause the MP3 soundtrack when the winner video starts playing
        TicTacToe.mediaPlayer.pause();

        // Play Again button event handler
        btnPA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Transition to the game screen
               Game_Screen_Controller_pc gameScreen = new Game_Screen_Controller_pc(stage, name,"PC");
                Scene scene = new Scene(gameScreen);
                stage.setScene(scene);

                // Pause the video when transitioning
                mediaPlayer.pause();

                // Resume the MP3 soundtrack
                TicTacToe.mediaPlayer.play();
            }
        });

        // Exit button event handler
        btnEX.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Close the stage (exit the application)
                stage.close();

                // Resume the MP3 soundtrack
                TicTacToe.mediaPlayer.play();
            }
        });
    }

    // MULTI PLAYER CONSTRUCTOR
    public WINNERController(Stage stage, String name1, String name2) {
        super();

        // Initialize the MediaPlayer with the winner video
        Media media = new Media(getClass().getResource("/resources/winner.mp4").toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        videoView.setMediaPlayer(mediaPlayer);

        // Set the video to auto-play
        mediaPlayer.setAutoPlay(true);

        // Pause the MP3 soundtrack when the winner video starts playing
        TicTacToe.mediaPlayer.pause();

        // Play Again button event handler
        btnPA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Transition to the game screen
                game_screenBase gameScreen = new GamescreenController_Multi(stage, name1, name2);
                Scene scene = new Scene(gameScreen);
                stage.setScene(scene);

                // Pause the video when transitioning
                mediaPlayer.pause();

                // Resume the MP3 soundtrack
                TicTacToe.mediaPlayer.play();
            }
        });

        // Exit button event handler
        btnEX.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Close the stage (exit the application)
                stage.close();

                // Resume the MP3 soundtrack
                TicTacToe.mediaPlayer.play();
            }
        });
    }
    
    public WINNERController(Stage stage, String name1, String name2, String score1, String score2) {
        super();

        Media media = new Media(getClass().getResource("/resources/winner.mp4").toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        videoView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);

        // Pause the MP3 soundtrack when the MP4 video starts playing
        //TicTacToe.mediaPlayer.pause();

        btnPA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GamescreenController game = new GamescreenController(stage, name1, name2, score1, score2, 'X');
                Scene scene = new Scene(game);
                stage.setScene(scene);
                mediaPlayer.pause();
                //TicTacToe.mediaPlayer.play(); // Resume the MP3 soundtrack
            }
        });

        btnEX.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                HomeOnlineController home = new HomeOnlineController(stage, name1, score1);
                Scene scene = new Scene(home);
                stage.setScene(scene);

                mediaPlayer.pause();
                //TicTacToe.mediaPlayer.play(); // Resume the MP3 soundtrack
            }
        });
    }
}