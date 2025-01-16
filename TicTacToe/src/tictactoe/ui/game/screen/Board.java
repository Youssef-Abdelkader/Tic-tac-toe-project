package tictactoe.ui.game.screen;

public class Board {
    private char[][] grid;

    public Board() {
        grid = new char[3][3]; // Initialize a 3x3 grid
    }

    public void setGrid(int row, int col, char value) {
        grid[row][col] = value;
    }

    public char[][] getGrid() {
        return grid;
    }

    public boolean isCellEmpty(int row, int col) {
        return grid[row][col] == 0;
    }
}