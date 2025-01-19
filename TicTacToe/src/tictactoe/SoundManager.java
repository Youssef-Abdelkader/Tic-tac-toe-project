package tictactoe;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundManager {
    private static SoundManager instance;
    private MediaPlayer mediaPlayer;

    private SoundManager() {
        // Private constructor to prevent instantiation
    }

    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    public void playSoundtrack(String path) {
        Media media = new Media(getClass().getResource(path).toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the soundtrack
        mediaPlayer.play();
    }

    public void pauseSoundtrack() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public void resumeSoundtrack() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    public void stopSoundtrack() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}