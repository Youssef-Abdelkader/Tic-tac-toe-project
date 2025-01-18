package tictactoe.ui.game.screen;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tictactoe.ui.game.looser.LOSERBase;
import tictactoe.ui.game.looser.LOSERController;

public class Game_Screen_Controller_pc extends SharedGame {

    private char[][] board;
    private boolean isPlayerTurn;
    private String player1Name;
    private String player2Name;
    private Stage stage;
    private Game game;

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
        this.game = new Game(); // Instantiate the Game object
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
            game.placeXO(pos);
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
        if (isBoardFull(board)) {
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

   

    private boolean checkAndHandleGameOver() {
        char winner = checkWinner('X') ? 'X' : (checkWinner('O') ? 'O' : ' ');
        if (winner != ' ') {
            int[] winningPositions = game.calculateWinner(); // Use calculateWinner from Game
            if (winningPositions != null) {
                drawWinningLine(game); // Draw the winning line (inherited from SharedGame)
            }
            showWinner(winner == 'X' ? player1Name : player2Name);
            disableGrid();
            return true;
        }
        if (isBoardFullPC(board)) {
            showDrawMessage();
            disableGrid();
            return true;
        }
        return false;
    }

    private void updateGridUI(int pos, char symbol) {
        int row = (pos - 1) / 3;
        int col = (pos - 1) % 3;
        ImageView imageView = (ImageView) gridPane0.getChildren().get(row * 3 + col);
        imageView.setImage(new Image(getClass().getResource(
                symbol == 'X' ? "/tictactoe/images/x_game.jpeg" : "/tictactoe/images/o_game.png"
        ).toExternalForm()));
    }

    

    private void disableGrid() {
        for (int i = 0; i < 9; i++) {
            ImageView imageView = (ImageView) gridPane0.getChildren().get(i);
            imageView.setDisable(true);
        }
    }
}