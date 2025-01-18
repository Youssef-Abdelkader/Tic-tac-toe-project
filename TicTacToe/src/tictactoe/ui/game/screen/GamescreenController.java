package tictactoe.ui.game.screen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tictactoe.ui.game.looser.LOSERBase;
import tictactoe.ui.game.looser.LOSERController;
import tictactoe.ui.game.winner.WINNERController;

public class GamescreenController extends SharedGame {

    private Game game;
    private String score1;
    private String score2;
    private String player1Name;
    private String player2Name;
    private Stage stage;
    private Line winningLine;

    public GamescreenController(Stage stage, String name) {

        super(stage);

        this.stage = stage;
        this.player1Name = name;
        this.player2Name = "PC";
        initializeGame();
    }

    public GamescreenController(Stage stage, String name1, String name2) {
        super(stage);
        this.stage = stage;
        this.player1Name = name1;
        this.player2Name = name2;
        initializeGame();
    }

    public GamescreenController(Stage stage, String name1, String name2, String score1, String score2) {
        super(stage);
        this.stage = stage;
        this.player1Name = name1;
        this.player2Name = name2;
        this.score1 = score1;
        this.score2 = score2;
        initializeGame();
    }

    private void initializeGame() {
        if (gridPane0 == null) {
            System.err.println("gridPane0 is null!");
            return;
        }
        game = new Game(false);
        label1.setText(player1Name);
        label.setText(player2Name);

        for (int i = 0; i < 9; i++) {
            ImageView imageView = (ImageView) gridPane0.getChildren().get(i);

            if (imageView == null) {
                System.err.println("ImageView at index " + i + " is null!");
                continue;
            }
            int pos = i + 1;

            imageView.setOnMouseClicked(event -> handleGridClick(pos));
        }

        forfeitButton.setOnAction(event -> {
            LOSERBase lose = new LOSERController(stage, player1Name, player2Name);
            Scene scene = new Scene(lose);
            stage.setScene(scene);
        });

        //recordButton.setOnAction(event -> recordButton.setDisable(true));
        recordButton.addEventHandler(ActionEvent.ACTION, (event) -> {
            FileHandler.initializeFile();

            game.setRec_flag(true);
            //create file
            recordButton.setDisable(true);
        });
    }

    private void handleGridClick(int pos) {
        System.out.println("posHandle:" + pos);
        System.out.println("-----------------");
        if (game.placeXO(pos)) {
            System.out.println("pos " + pos);
            updateGridUI(pos);
            char[][] ch_ar = game.getSquares().getGrid();
            

            int[] winningPositions = game.calculateWinner();
            if (winningPositions != null) {
                drawWinningLine(game);
                //drawLine(winningPositions[0], winningPositions[1], winningPositions[2]);
                showWinner(game.getCurrentPlayerSymbol() == 'X' ? player2Name : player1Name);
                disableGrid();
                sendRecToServer(game.recToString());
            }

            if (isBoardFull(ch_ar)) {
                System.out.println("------------");
                showDrawMessage();
                disableGrid();
                
            }
        }
    }

    private void updateGridUI(int pos) {
        int row = (pos - 1) / 3;
        int col = (pos - 1) % 3;
        System.out.println("update:");
        System.out.println(row * 3 + col);
        ImageView imageView = (ImageView) gridPane0.getChildren().get(row * 3 + col);
        imageView.setImage(new Image(getClass().getResource(
                game.getCurrentPlayerSymbol() == 'X' ? "/tictactoe/images/o_game.png" : "/tictactoe/images/x_game.jpeg"
        ).toExternalForm()));
    }

    //logical error
    private void disableGrid() {
        for (int i = 0; i < 9; i++) {
            ImageView imageView = (ImageView) gridPane0.getChildren().get(i);
            imageView.setDisable(true);
        }
    }

    private void resetGame() {
        game.resetGame();
        for (int i = 0; i < 9; i++) {
            ImageView imageView = (ImageView) gridPane0.getChildren().get(i);
            imageView.setImage(null);
        }
    }

    private void sendRecToServer(String recToString) {
        try {
            Socket socket = new Socket("127.0.0.1", 5005);  // Assuming the server is on localhost and port 5005
            System.out.println("Connected to server");

            PrintWriter mouth = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader ear = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            mouth.println("Recording###" + recToString);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String message;
                        while ((message = ear.readLine()) != null) {
                            System.out.println("Received from server: " + message);
                        }
                    } catch (IOException ex) {
                        System.out.println("Error while listening to server: " + ex.getMessage());
                    }
                }
            }).start();

        } catch (IOException ex) {
            System.out.println("Error while connecting to server: " + ex.getMessage());
        }
    }

}
