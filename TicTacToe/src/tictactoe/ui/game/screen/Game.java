package tictactoe.ui.game.screen;

public class Game {

    private Player player1;
    private Player player2;
    private Board squares;
    private boolean online;
    private char currentPlayerSymbol;

    public Game(boolean online) {
        this.online = online;
        this.squares = new Board();
        this.currentPlayerSymbol = 'X';
    }

    /*Game() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    public boolean placeXO(int pos) {
        int row = (pos - 1) / 3;
        int col = (pos - 1) % 3;

        if (pos >= 1 && pos <= 9 && squares.isCellEmpty(row, col)) {
            squares.setGrid(row, col, currentPlayerSymbol);
            switchPlayer();
            return true;
        }
        return false;
    }

    public int[] calculateWinner() {
        char[][] mygrid = squares.getGrid();

        // Check rows
        for (int row = 0; row < 3; row++) {
            if (mygrid[row][0] != 0 && mygrid[row][0] == mygrid[row][1] && mygrid[row][1] == mygrid[row][2]) {
                return new int[]{row * 3 + 1, row * 3 + 2, row * 3 + 3};
            }
        }
        // Check columns
        for (int col = 0; col < 3; col++) {
            if (mygrid[0][col] != 0 && mygrid[0][col] == mygrid[1][col] && mygrid[1][col] == mygrid[2][col]) {
                return new int[]{col + 1, col + 4, col + 7};
            }
        }
        // Check diagonals
        if (mygrid[0][0] != 0 && mygrid[0][0] == mygrid[1][1] && mygrid[1][1] == mygrid[2][2]) {
            return new int[]{1, 5, 9};
        }
        if (mygrid[0][2] != 0 && mygrid[0][2] == mygrid[1][1] && mygrid[1][1] == mygrid[2][0]) {
            return new int[]{3, 5, 7};
        }
        return null;
    }

    private void switchPlayer() {
        currentPlayerSymbol = (currentPlayerSymbol == 'X') ? 'O' : 'X';
    }

    public char getCurrentPlayerSymbol() {
        return currentPlayerSymbol;
    }

    public void resetGame() {
        squares = new Board();
        currentPlayerSymbol = 'X';
    }

    public Board getSquares() {
        return squares;
    }

    public void setSquares(Board squares) {
        this.squares = squares;
    }
    
    
}