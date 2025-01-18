package tictactoe.ui.game.screen;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import tictactoe.ui.game.looser.LOSERBase;
import tictactoe.ui.game.looser.LOSERController;

public class Game_Screen_Controller_pc extends game_screenBase {

    private char[][] board;
    private boolean isPlayerTurn;
    private String player1Name;
    private String player2Name;
    private Stage stage;

    public Game_Screen_Controller_pc(Stage stage, String name) {
        super(stage);
        this.stage = stage;
        this.player1Name = name;
        this.board = new char[][]{
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };
        this.isPlayerTurn = true; // Player starts
        initializeGame();
    }

    private void initializeGame() {
        if (gridPane0 == null) {
            System.err.println("gridPane0 is null!");
            return;
        }

        label1.setText(player1Name);

        // Initialize grid click listeners
        for (int i = 0; i < 9; i++) {
            ImageView imageView = (ImageView) gridPane0.getChildren().get(i);
            if (imageView == null) {
                System.err.println("ImageView at index " + i + " is null!");
                continue;
            }
            int pos = i + 1;
            imageView.setOnMouseClicked(event -> handlePlayerMove(pos));
        }

        forfeitButton.setOnAction(event -> {
            LOSERBase lose = new LOSERController(stage, player1Name);
            Scene scene = new Scene(lose);
            stage.setScene(scene);
        });

        recordButton.setOnAction(event -> recordButton.setDisable(true));
    }

    private void handlePlayerMove(int pos) {
        if (isPlayerTurn && placeXO(pos, 'X')) {
            updateGridUI(pos, 'X');
            if (checkAndHandleGameOver()) {
                return;
            }

            // PC's turn
            int[] pcMove = getPCMove();
            if (pcMove[0] != -1) {
                int pcPos = pcMove[0] * 3 + pcMove[1] + 1;
                placeXO(pcPos, 'O');
                updateGridUI(pcPos, 'O');
                checkAndHandleGameOver();
            }
        }
    }

    private boolean placeXO(int pos, char symbol) {
        int row = (pos - 1) / 3;
        int col = (pos - 1) % 3;

        if (board[row][col] == ' ') {
            board[row][col] = symbol;
            isPlayerTurn = !isPlayerTurn;
            return true;
        }
        return false;
    }

    private int[] getPCMove() {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = {-1, -1};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = 'O';
                    int score = minimax(false);
                    board[i][j] = ' ';
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new int[]{i, j};
                    }
                }
            }
        }
        return bestMove;
    }

    private int minimax(boolean isMaximizing) {
        if (checkWinner('O')) {
            return 1;
        }
        if (checkWinner('X')) {
            return -1;
        }
        if (isBoardFull()) {
            return 0;
        }

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = isMaximizing ? 'O' : 'X';
                    int score = minimax(!isMaximizing);
                    board[i][j] = ' ';
                    bestScore = isMaximizing ? Math.max(score, bestScore) : Math.min(score, bestScore);
                }
            }
        }
        return bestScore;
    }

    private boolean checkWinner(char player) {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player)
                    || (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player)
                || (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    private boolean isBoardFull() {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkAndHandleGameOver() {
        char winner = checkWinner('X') ? 'X' : (checkWinner('O') ? 'O' : ' ');
        if (winner != ' ') {
            drawWinningLine(getWinningPositions(winner));
            showWinner(winner == 'X' ? player1Name : player2Name);
            disableGrid();
            return true;
        }
        if (isBoardFull()) {
            showDrawMessage();
            disableGrid();
            return true;
        }
        return false;
    }

    private int[] getWinningPositions(char winner) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == winner && board[i][1] == winner && board[i][2] == winner) {
                return new int[]{i * 3 + 1, i * 3 + 2, i * 3 + 3};
            }
        }
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == winner && board[1][i] == winner && board[2][i] == winner) {
                return new int[]{i + 1, i + 4, i + 7};
            }
        }
        // Check diagonals
        if (board[0][0] == winner && board[1][1] == winner && board[2][2] == winner) {
            return new int[]{1, 5, 9};
        }
        if (board[0][2] == winner && board[1][1] == winner && board[2][0] == winner) {
            return new int[]{3, 5, 7};
        }
        return null;
    }

    private void updateGridUI(int pos, char symbol) {
        int row = (pos - 1) / 3;
        int col = (pos - 1) % 3;
        ImageView imageView = (ImageView) gridPane0.getChildren().get(row * 3 + col);
        imageView.setImage(new Image(getClass().getResource(
                symbol == 'X' ? "/tictactoe/images/x_game.jpeg" : "/tictactoe/images/o_game.png"
        ).toExternalForm()));
    }

    private void drawWinningLine(int[] winningPositions) {
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

    private void showDrawMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("It's a draw!");
        FileHandler.closeResources();
        alert.showAndWait();
    }

    private void disableGrid() {
        for (int i = 0; i < 9; i++) {
            ImageView imageView = (ImageView) gridPane0.getChildren().get(i);
            imageView.setDisable(true);
        }
    }
}
