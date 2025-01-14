package Classes;

public class Game {

    Player player1 = null;
    Player player2 = null;

    // Board game_board;
    Board squares;

    boolean online;

    public void startGame() {
        if (online) {
            player1 = new OnlinePlayer();
            player2 = new OnlinePlayer();
        } else {
            player1 = new OfflinePlayer();
            player2 = new OfflinePlayer();
        }
        // game_board = new Board();
        player1.assignXorO(X_OR_O.X);
        player2.assignXorO(X_OR_O.O);
    }

    public boolean placeXO(int pos, char c) {
        boolean placed = false;
        
        if (pos > 0 && pos < 4) {
            Board.setCols(pos - 1);
            Board.setRows(0);
            Board.setGrid(Board.getRows(), Board.getCols(), c);
            placed = true;

        } else if (pos > 3 && pos < 7) {
            Board.setCols(pos - 4);
            Board.setRows(1);
            Board.setGrid(Board.getRows(), Board.getCols(), c);
            placed = true;
        } else if (pos > 6 && pos < 10) {
            Board.setCols(pos - 7);
            Board.setRows(2);
            Board.setGrid(Board.getRows(), Board.getCols(), c);
            placed = true;
        }

        return placed;
    }

    public Player calculateWinner() {
        Player winner;
        char mygrid[][] = Board.getGrid();
        char winning_char = 0 ;
        //String gameplay;
        for (int rows = 0, cols = 0; rows < 3 && cols < 3; rows++, cols++) {
            //for (int cols = 0; cols < 3; cols++) {
            if (mygrid[rows][0] == mygrid[rows][1] && mygrid[rows][1] == mygrid[rows][2]) {
                //gameplay.concat(mygrid[rows][cols]);
                winning_char = mygrid[rows][cols];
                break;
            }
            if (mygrid[0][cols] == mygrid[1][cols] && mygrid[1][cols] == mygrid[2][cols]) {
                winning_char = mygrid[rows][cols];
                break;
            }
            if (mygrid[0][0] == mygrid[1][1] && mygrid[1][1] == mygrid[2][2]) {
                winning_char = mygrid[rows][cols];
                break;
            }
            if (mygrid[0][2] == mygrid[1][1] && mygrid[1][1] == mygrid[2][0]) {
                winning_char = mygrid[rows][cols];
                break;
            }
        }
        if (player1.getPlayerCharacter() == winning_char) {
           return player1;
        } else  {
            return player2;
        }
        //return winner;
    }

}
