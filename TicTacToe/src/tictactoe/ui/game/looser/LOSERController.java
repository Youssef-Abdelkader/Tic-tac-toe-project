package tictactoe.ui.game.looser;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import tictactoe.TicTacToe;
import tictactoe.ui.game.screen.Game_Screen_Controller_pc;
import tictactoe.ui.game.screen.GamescreenController;
import tictactoe.ui.game.screen.game_screenBase;
import tictactoe.ui.game.screen.game_screenBase;
import tictactoe.ui.home.online.HomeOnline;
import tictactoe.ui.home.offline.HomeScreen_offline;
import tictactoe.ui.home.offline.HomeScreen_offline_Controller;
import tictactoe.ui.home.online.HomeOnlineController;

public class LOSERController extends LOSERBase {

    private final MediaPlayer mediaPlayer;

    //SINGLE PLAYER CONSTRUCTOR
    public LOSERController(Stage stage, String name) {
        super();

        String score = "11";
        Media media = new Media(getClass().getResource("/resources/loser.mp4").toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        videoView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);

        btnPA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              
                    game_screenBase game = new Game_Screen_Controller_pc(stage, name);
                    Scene scene = new Scene(game);
                    stage.setScene(scene);
                    mediaPlayer.pause();
                
            }
        });

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
    public LOSERController(Stage stage, String name1, String name2) {
        super();

        String score = "11";
        Media media = new Media(getClass().getResource("/resources/loser.mp4").toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        videoView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);

        btnPA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game_screenBase game = new GamescreenController(stage, name1, name2);
                Scene scene = new Scene(game);
                stage.setScene(scene);
                mediaPlayer.pause();
            }
        });

        btnEX.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (TicTacToe.online == false) {
                    HomeScreen_offline_Controller home = new HomeScreen_offline_Controller(stage);

                    // HomeScreen_offline home = new HomeScreen_offline_Controller(stage);
                    Scene scene = new Scene(home);
                    stage.setScene(scene);
                } else {
                    HomeOnlineController home = new HomeOnlineController(stage,name1,score);
                    Scene scene = new Scene(home);
                    stage.setScene(scene);
                }
                mediaPlayer.pause();
            }
        });
    }
}
