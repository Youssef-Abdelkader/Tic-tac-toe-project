package tictactoe.ui.game.screen;

import connection.Connection;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tictactoe.ui.game.looser.LOSERBase;
import tictactoe.ui.game.looser.LOSERController;
import tictactoe.ui.home.online.HomeOnlineController;

public class GamescreenController extends SharedGame {

    private Game game;
    private String score1;
    private String score2;
    private String player1Name;
    private String player2Name;
    private Stage stage;
    private Line winningLine;

    private String me;
    private String opponent;
    
    
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
        this.me = HomeOnlineController.me;
        if(me.equals(name1)){
            opponent = name2;
        }
        else{
             opponent = name1;
        }
        initializeGame();
        listenForMoves();
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
            int row = (pos - 1) / 3;
            int col = (pos - 1) % 3;
            
            char c = ch_ar[row][col];
            // Thread th = new Thread(() -> {
           // player1Name
           
            Connection.sendRequest("Move" + "###" + pos+c+ "###"+ opponent); 
            
            // });
            // th.setDaemon(true);
            // th.start();

            int[] winningPositions = game.calculateWinner();
            if (winningPositions != null) {
                drawWinningLine();
            

            if (winningPositions != null) {
                drawWinningLine(game);
                //drawLine(winningPositions[0], winningPositions[1], winningPositions[2]);
                showWinner(game.getCurrentPlayerSymbol() == 'X' ? player2Name : player1Name);
                disableGrid();
            }

            if (isBoardFull(ch_ar)) {
                System.out.println("------------");
                showDrawMessage();
                disableGrid();
                
            }
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

    }

    private double getCellCenterX(int pos) {
        int col = (pos - 1) % 3;
        return col * 100 + 50;
    }

    private double getCellCenterY(int pos) {
        int row = (pos - 1) / 3;
        return row * 100 + 50;
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
//////////////////////////

    private void listenForMoves() {
        new Thread(() -> {
            try {
               
                while (true) {
                    //it doesn't read 
                    String message = Connection.ear.readLine();
                    System.out.println("--mesage "+ message);
                    if (message != null && message.startsWith("Move###")) {
                        String[] parts = message.split("###");
                        if (parts.length > 1) {
                            String move = parts[1];
                            System.out.println("Move from listen method "+move);
                            Platform.runLater(() -> handleOpponentMove(move));
                        }
                    }
                }
            } catch (IOException ex) {
                System.out.println("Error while listening to server: " + ex.getMessage());
            }
        }).start();
    }

    private void handleOpponentMove(String move) {
        int position = Integer.parseInt(move.substring(0, 1));
        char symbol = move.charAt(1);
        updateGridUI(position, symbol);
    }

    private void updateGridUI(int position, char symbol) {
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;
        ImageView imageView = (ImageView) gridPane0.getChildren().get(row * 3 + col);
        imageView.setImage(new Image(getClass().getResource(
                symbol == 'X' ? "/tictactoe/images/x_game.jpeg" : "/tictactoe/images/o_game.png"
        ).toExternalForm()));
    }
}
//
