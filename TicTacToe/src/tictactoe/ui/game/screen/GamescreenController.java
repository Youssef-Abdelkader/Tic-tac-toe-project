package tictactoe.ui.game.screen;

import connection.Connection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tictactoe.ui.game.looser.LOSERBase;
import tictactoe.ui.game.looser.LOSERController;
import tictactoe.ui.home.online.HomeOnlineController;

public class GamescreenController extends game_screenBase {

    private Game game;
    private String player1Name;
    private String player2Name;
    private Stage stage;

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
            game.setRec_flag(true);
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
            for (int i = 0; i < ch_ar.length; i++) {
                for (int j = 0; j < ch_ar.length; j++) {
                    System.out.println("row = " + i);
                    System.out.println("col = " + j);
                    System.out.println(ch_ar[i][j]);
                }
            }

            int[] winningPositions = game.calculateWinner();
            if (winningPositions != null) {
                //drawWinningLine(/*winningPositions*/);
                drawLine(winningPositions[0], winningPositions[1], winningPositions[2]);
                showWinner(game.getCurrentPlayerSymbol() == 'X' ? player2Name : player1Name);
                disableGrid();
                sendRecToServer(game.recToString());
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
    private void drawWinningLine() {
        int[] winningPositions = game.calculateWinner();
        if (winningPositions == null || winningPositions.length != 3) {
            System.err.println("Invalid winning positions!");
            return;
        }

        double startX = getCellCenterX(winningPositions[0]);
        double startY = getCellCenterY(winningPositions[0]);
        double endX = getCellCenterX(winningPositions[2]);
        double endY = getCellCenterY(winningPositions[2]);

        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(Color.RED);
        line.setStrokeWidth(5);
        gridPane0.getChildren().add(line);
        ////
        Line line1 = new Line(0, 0, 0, 0);
        line.setStroke(Color.BLUE);
        line.setStrokeWidth(5);

        line.endXProperty().bind(gridPane0.widthProperty().subtract(boxOne.getFitWidth() / 2));

        line.setStartY(gridPane0.getHeight() / 2);
        line.setEndY(gridPane0.getHeight() / 2);

        gridPane0.add(line1, 0, 1); //col //row
    }

    private double getCellCenterX(int pos) {
        int col = (pos - 1) % 3;
        return col * 100 + 50; // Assuming each cell is 100x100 pixels
    }

    private double getCellCenterY(int pos) {
        int row = (pos - 1) / 3;
        return row * 100 + 50; // Assuming each cell is 100x100 pixels
    }

    private void showWinner(String winnerName) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(winnerName + " wins!");
        alert.showAndWait();
    }

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

    private void drawLine(int a, int b, int c) {
        double startX = 0, startY = 0, endX = 0, endY = 0;
        ImageView img_st = null;
        ImageView img_end = null;
        int row = 0;
        int col = 0;

        if (b == a + 1 && c == a + 2) { //row win
            row = (a - 1) / 3;
            drawHorizontalLine(row, col);

        } else if (b == a + 3 && c == a + 6) {
            // Winning in a column
            col = (a - 1) % 3;
            drawVerticalLine(row, col);
            /*switch (col) {
                case 0:
                    img_st = boxOne;
                    img_end = boxSeven;
                    break;
                case 1:
                    img_st = boxTwo;
                    img_end = boxEight;
                    break;
                case 2:
                    img_st = boxThree;
                    img_end = boxNine;
            }*/
        } else if (a == 1 && c == 9) {

            img_st = boxOne;
            img_end = boxNine;

        } else if (a == 3 && c == 7) {
            img_st = boxThree;
            img_end = boxSeven;
        }
//        Point2D sceneCoords = img_st.localToScene(img_st.getX(), img_st.getY());

        //startX = sceneCoords.getX();
        //startY = sceneCoords.getY();
        //Point2D sceneCoords2 = img_end.localToScene(img_end.getX(), img_end.getY());
        //endX = sceneCoords2.getX();
        // endY = sceneCoords2.getY();
        // tester = "start x= "+startX+" start y= "+startY+"\nend x= "+endX+" end y= "+end;

        /* else {
        // No valid win condition
        return;
        }*/
        // Create the line and set its properties
        //Line line = new Line(startX, startY, endX, endY);
        // line.setStroke(Color.BLUE);
        // line.setStrokeWidth(5);
        // Add the line to the gridPane0
        //gridPane0.add(line, col, row);
    }

private void sendRecToServer(String recToString) {
    try {
        Socket socket = new Socket("127.0.0.1", 5005);  // Assuming the server is on localhost and port 5005
        System.out.println("Connected to server");

        PrintWriter mouth = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader ear = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        mouth.println("Recording###"+recToString);

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

    private void drawHorizontalLine(int row, int col) {
        Line line = new Line(0, 0, 0, 0); // Start and end points are initially (0, 0)
        line.setStroke(Color.BLUEVIOLET);
        line.setStrokeWidth(5);

        line.endXProperty().bind(gridPane0.widthProperty());

        gridPane0.add(line, col, row);
    }

    private void drawVerticalLine(int row, int col) {
        // Calculate the start and end positions of the line based on the grid cell size
        double startX = col * gridPane0.getColumnConstraints().get(col).getPrefWidth();
        double startY = row * gridPane0.getRowConstraints().get(row).getPrefHeight();
        double endX = startX;
        double endY = startY + gridPane0.getRowConstraints().get(row).getPrefHeight();

        // Create the line with the calculated coordinates
        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(Color.BLUEVIOLET);
        line.setStrokeWidth(5);

        // Optionally, bind to the width and height if you want the line to adjust dynamically
        // line.endXProperty().bind(gridPane0.widthProperty());
        line.endYProperty().bind(gridPane0.heightProperty());

        // Add the line to the grid
        gridPane0.add(line, col, row);
    }

}
//
