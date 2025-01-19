package tictactoe.ui.game.screen;

import connection.Connection;
import java.util.Optional;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import tictactoe.TicTacToe;
import tictactoe.ui.game.looser.LOSERBase;
import tictactoe.ui.game.looser.LOSERController;
import tictactoe.ui.game.winner.WINNERController;
import tictactoe.ui.home.offline.HomeScreen_offline_Controller;
import tictactoe.ui.home.online.HomeOnlineController;

public class SharedGame extends game_screenBase {

    private String player;
    private String opponent;
    private Stage stage;
    private Line winningLine;
    private String score1;
    private String score2;

     public SharedGame(Stage stage) {
        super(stage);
    }
     
    public SharedGame(Stage stage, String player, String opponent) {
        super(stage);
        this.stage = stage;
        this.player = player;
        this.opponent = opponent;
    }

    public SharedGame(Stage stage, String player, String opponent, String score1, String score2) {
        super(stage);
        this.stage = stage;
        this.player = player;
        this.opponent = opponent;
        this.score1 = score1;
        this.score2 = score2;
        scoreOne.setText("Score: " + this.score1);
        scoreTwo.setText("Score: " + this.score2);
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

        if (winnerName.equals(opponent)) {

            if (TicTacToe.online == true) {
                Connection.sendRequest("score###win###" + player);
                int score = Integer.parseInt(score2);
                score += 4;
                String finalScore = Integer.toString(score);
                WINNERController win = new WINNERController(stage, player, opponent, finalScore, score1);
                Scene scene = new Scene(win);
                stage.setScene(scene);
            } else {
                WINNERController win = new WINNERController(stage, player, opponent);
                Scene scene = new Scene(win);
                stage.setScene(scene);
            }
        } else {
            if (TicTacToe.online == true) {
                
                LOSERBase lose = new LOSERController(stage, player, opponent, score2, score1);
                Scene scene = new Scene(lose);
                stage.setScene(scene);
            } else {
                LOSERBase lose = new LOSERController(stage, player, opponent);
                Scene scene = new Scene(lose);
                stage.setScene(scene);
            }
        }
    }

    public void showDrawMessage() {
        Platform.runLater(() -> {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);
            alert.initOwner(stage);
            alert.setContentText("It's a draw!");
            ButtonType exitButton = new ButtonType("Exit");
            ButtonType playAgainButton = new ButtonType("Play Again");

            alert.getButtonTypes().setAll(playAgainButton, exitButton);

            Optional<ButtonType> result = alert.showAndWait();
            Connection.sendRequest("score###draw###" + player + "###" + opponent);

            if (result.isPresent()) {

                int scoreTwo = Integer.parseInt(score2);
                scoreTwo += 2;
                String finalScore2 = Integer.toString(scoreTwo);

                int scoreOne = Integer.parseInt(score1);
                scoreOne += 2;
                String finalScore1 = Integer.toString(scoreOne);
                if (result.get() == playAgainButton) {
                    if (TicTacToe.online == true) {
                        GamescreenController game = new GamescreenController(stage, player, opponent, finalScore2, finalScore1, 'X');
                        Scene scene = new Scene(game);
                        stage.setScene(scene);
                    } else {
                        game_screenBase gameScreen = new GamescreenController_Multi(stage, player, opponent);
                        Scene scene = new Scene(gameScreen);
                        stage.setScene(scene);
                    }
                } else {
                    if (TicTacToe.online == true) {

                        if (player.equals(HomeOnlineController.me)) {
                            HomeOnlineController home = new HomeOnlineController(stage, player, finalScore2);
                            Scene scene = new Scene(home);
                            stage.setScene(scene);
                        } else {
                            HomeOnlineController home = new HomeOnlineController(stage, opponent, finalScore1);
                            Scene scene = new Scene(home);
                            stage.setScene(scene);
                        }
                    } else {
                        HomeScreen_offline_Controller home = new HomeScreen_offline_Controller(stage);
                        Scene scene = new Scene(home);
                        stage.setScene(scene);
                    }
                }
            }

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

                    return false;
                }
            }
        }
        return true;
    }
}
