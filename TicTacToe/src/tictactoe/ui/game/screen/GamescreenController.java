package tictactoe.ui.game.screen;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tictactoe.ui.game.looser.LOSERBase;
import tictactoe.ui.game.looser.LOSERController;

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

        recordButton.setOnAction(event -> recordButton.setDisable(true));
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
                drawWinningLine(/*winningPositions*/);
                showWinner(game.getCurrentPlayerSymbol() == 'X' ? player2Name : player1Name);
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
    int row = 0;
    int col = 0;

    // Check if it's a winning row
    if (b == a + 1 && c == a + 2) { 
        switch (a) {
            case 1: row = 0; break;
            case 4: row = 1; break;
            case 7: row = 2; break;
        }
        drawRowLine(row);
    } 

    else if (b == a + 3 && c == a + 6) { 
        switch (a) {
            case 1: col = 0; break;
            case 2: col = 1; break;
            case 3: col = 2; break;
        }
        drawColumnLine(col);
    } 
    else if (a == 1 && c == 9) {
        drawDiagonalLine(1);  
    } 
    else if (a == 3 && c == 7) {
        drawDiagonalLine(2);  
    }
}

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
