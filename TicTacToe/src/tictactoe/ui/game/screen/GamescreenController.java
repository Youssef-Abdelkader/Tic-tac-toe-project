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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tictactoe.TicTacToe;
import tictactoe.ui.game.looser.LOSERBase;
import tictactoe.ui.game.looser.LOSERController;
import tictactoe.ui.home.online.HomeOnlineController;

public class GamescreenController extends game_screenBase {

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
            for (int i = 0; i < ch_ar.length; i++) {
                for (int j = 0; j < ch_ar.length; j++) {
                    System.out.println("row = " + i);
                    System.out.println("col = " + j);
                    System.out.println(ch_ar[i][j]);
                }
            }

            int[] winningPositions = game.calculateWinner();
            if (winningPositions != null) {
                drawWinningLine();
                //drawLine(winningPositions[0], winningPositions[1], winningPositions[2]);
                showWinner(game.getCurrentPlayerSymbol() == 'X' ? player2Name : player1Name);
                disableGrid();
                //sendMoveToServer(game.recToString());
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

//        double startX = getCellCenterX(winningPositions[0]);
//        double startY = getCellCenterY(winningPositions[0]);
//        double endX = getCellCenterX(winningPositions[2]);
//        double endY = getCellCenterY(winningPositions[2]);
        ImageView imageview = getImageView(winningPositions[0]);
        ImageView imageview1 = getImageView(winningPositions[2]);

        Bounds bounds1 = imageview.localToScene(imageview.getBoundsInLocal());
        Bounds bounds2 = imageview1.localToScene(imageview1.getBoundsInLocal());

        double startX = bounds1.getMinX() + bounds1.getWidth() / 2;
        double startY = bounds1.getMinY() + bounds1.getHeight() / 2;
        double endX = bounds2.getMinX() + bounds2.getWidth() / 2;
        double endY = bounds2.getMinY() + bounds2.getHeight() / 2;

        winningLine = new Line(startX, startY, endX, endY);

        winningLine.setStroke(Color.RED);
        winningLine.setStrokeWidth(5);

        this.getChildren().add(winningLine);
        //FileHandler.closeResources();

    }

    private ImageView getImageView(int x) {
        if (x == 1) {
            return boxOne;
        } else if (x == 2) {
            return boxTwo;
        } else if (x == 3) {
            return boxThree;
        } else if (x == 4) {
            return boxFour;
        } else if (x == 5) {
            return boxFive;
        } else if (x == 6) {
            return boxSix;
        } else if (x == 7) {
            return boxSeven;
        } else if (x == 8) {
            return boxEight;
        } else if (x == 9) {
            return boxNine;
        }
        return null;
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
        FileHandler.closeResources();
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
        int row = 0;
        int col = 0;

        // Check if it's a winning row
        if (b == a + 1 && c == a + 2) {
            switch (a) {
                case 1:
                    row = 0;
                    break;
                case 4:
                    row = 1;
                    break;
                case 7:
                    row = 2;
                    break;
            }
            drawRowLine(row);
        } else if (b == a + 3 && c == a + 6) {
            switch (a) {
                case 1:
                    col = 0;
                    break;
                case 2:
                    col = 1;
                    break;
                case 3:
                    col = 2;
                    break;
            }
            drawColumnLine(col);
        } else if (a == 1 && c == 9) {
            drawDiagonalLine(1);
        } else if (a == 3 && c == 7) {
            drawDiagonalLine(2);
        }
    }

//<<<<<<< HEAD
//    private void drawHorizontalLine(int row, int col) {
//        Line line = new Line(0, 0, 0, 0); // Start and end points are initially (0, 0)
//        line.setStroke(Color.BLUEVIOLET);
//        line.setStrokeWidth(5);
//
//        line.endXProperty().bind(gridPane0.widthProperty());
//
//        gridPane0.add(line, col, row);
//    }
//
//    private void drawVerticalLine(int row, int col) {
//        // Calculate the start and end positions of the line based on the grid cell size
//        double startX = col * gridPane0.getColumnConstraints().get(col).getPrefWidth();
//        double startY = row * gridPane0.getRowConstraints().get(row).getPrefHeight();
//        double endX = startX;
//        double endY = startY + gridPane0.getRowConstraints().get(row).getPrefHeight();
//
//        // Create the line with the calculated coordinates
//        Line line = new Line(startX, startY, endX, endY);
//        line.setStroke(Color.BLUEVIOLET);
//        line.setStrokeWidth(5);
//
//        // Optionally, bind to the width and height if you want the line to adjust dynamically
//        // line.endXProperty().bind(gridPane0.widthProperty());
//        line.endYProperty().bind(gridPane0.heightProperty());
//
//        // Add the line to the grid
//        gridPane0.add(line, col, row);
//    }
// Method to draw a line in a row

    private void drawRowLine(int row) {
        Line line = new Line(0, 0, gridPane0.getWidth(), 0);  // Horizontal line
        line.setStroke(Color.RED);
        line.setStrokeWidth(5);

        line.setStartY((row * (gridPane0.getHeight() / 3)) + (gridPane0.getHeight() / 6)); // Middle of row
        line.setEndY(line.getStartY());

        gridPane0.add(line, 0, row);
    }

    private void drawColumnLine(int col) {
        Line line = new Line(0, 0, 0, gridPane0.getHeight());  // Vertical line
        line.setStroke(Color.RED);
        line.setStrokeWidth(5);

        line.setStartX((col * (gridPane0.getWidth() / 3)) + (gridPane0.getWidth() / 6)); // Middle of column
        line.setEndX(line.getStartX());

        gridPane0.add(line, col, 0);
    }

    private void drawDiagonalLine(int diagonalType) {
        Line line = new Line(0, 0, 0, 0);
        line.setStroke(Color.RED);
        line.setStrokeWidth(5);

        if (diagonalType == 1) {
            line.setStartX(0);
            line.setStartY(0);
            line.setEndX(gridPane0.getWidth());
            line.setEndY(gridPane0.getHeight());
        } else if (diagonalType == 2) {
            line.setStartX(gridPane0.getWidth());
            line.setStartY(0);
            line.setEndX(0);
            line.setEndY(gridPane0.getHeight());
        }

        gridPane0.add(line, 0, 0);
    }

}
//