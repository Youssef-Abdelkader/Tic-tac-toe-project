package tictactoe.ui.game.screen;

import java.util.Optional;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import tictactoe.ui.game.winner.WINNERController;

public class SharedGame extends game_screenBase {

    private String score1;
    private String score2;
    private String player1Name;
    private String player2Name;
    private Stage stage;
    private Line winningLine;

    public SharedGame(Stage stage) {
        super(stage);
    }

    public void drawWinningLine(Game game) {
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

    public ImageView getImageView(int x) {
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

    public void showWinner(String winnerName) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(winnerName + " wins!");
        alert.initOwner(stage);
        alert.showAndWait();
        FileHandler.closeResources();
    }

    public void showDrawMessage() {
        Platform.runLater(() -> {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);
            alert.setContentText("It's a draw!");
            FileHandler.closeResources();
            alert.showAndWait();

        });
    }

    public boolean isBoardFull(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == 0) {
                    System.out.println(cell);
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isBoardFullPC(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == ' ') {
                    System.out.println(cell);
                    return false;
                }
            }
        }
        return true;
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

}
