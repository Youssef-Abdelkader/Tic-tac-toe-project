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
import tictactoe.ui.game.screen.game_screenBase;
import tictactoe.ui.home.offline.HomeScreen_offline;
import tictactoe.ui.home.offline.HomeScreen_offline_Controller;
import tictactoe.ui.home.online.HomeOnlineController;

public class WINNERController extends WINNERBase {

    private MediaPlayer mediaPlayer;
    
     //SINGLE PLAYER CONSTRUCTOR

    public WINNERController(Stage stage, String name) {
        super();

        String score = "11";
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
                Game_Screen_Controller_pc gameScreen = new Game_Screen_Controller_pc(stage, name,"PC");
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
                if (TicTacToe.online == false) {

                    HomeScreen_offline home = new HomeScreen_offline_Controller(stage);

                    Scene scene = new Scene(home);
                    stage.setScene(scene);
                } else {
                    HomeOnlineController home = new HomeOnlineController(stage,name,score);
                    Scene scene = new Scene(home);
                    stage.setScene(scene);
                }
                mediaPlayer.pause();
                
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
                game_screenBase gameScreen = new GamescreenController_Multi(stage, name1, name2);
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
