package tictactoe;

import connection.Connection;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.Socket;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import tictactoe.ui.home.offline.HomeScreen_offline_Controller;

public class TicTacToe extends Application {
    public static boolean online = false;
    Socket server;
    DataInputStream ear;
    PrintStream mouth;
    // Declare MediaPlayer as a static variable
    public static MediaPlayer mediaPlayer;

    @Override
    public void start(Stage stage) throws Exception {
       
        Parent root = new HomeScreen_offline_Controller(stage);

        Image icon = new Image(getClass().getResourceAsStream("/tictactoe/images/icon.jpg")); // Correct path
        if (icon.isError()) {
            System.err.println("Error loading icon: " + icon.getException().getMessage());
        }
        stage.getIcons().add(icon);

        String soundTrackPath = getClass().getResource("/resources/soundtrack.mp3").toString(); // Correct path
        Media media = new Media(soundTrackPath);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); 
        //mediaPlayer.play(); 

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(e -> {
            Connection.sendRequest("logout");
            mediaPlayer.stop();

        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}