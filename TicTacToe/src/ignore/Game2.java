package ignore;

import oracle.jrockit.jfr.Recording;
import tictactoe.ui.game.screen.OfflinePlayer;
import tictactoe.ui.game.screen.OnlinePlayer;
import tictactoe.ui.game.screen.Player;
import tictactoe.ui.game.screen.X_OR_O;

public class Game2 {

    Player player1 = null;
    Player player2 = null;

    // Board game_board;
    Board2 squares;

    Recording[] game_record = new Recording[9]; 
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
            Board2.setCols(pos - 1);
            Board2.setRows(0);
            Board2.setGrid(Board2.getRows(), Board2.getCols(), c);
            placed = true;

        } else if (pos > 3 && pos < 7) {
            Board2.setCols(pos - 4);
            Board2.setRows(1);
            Board2.setGrid(Board2.getRows(), Board2.getCols(), c);
            placed = true;
        } else if (pos > 6 && pos < 10) {
            Board2.setCols(pos - 7);
            Board2.setRows(2);
            Board2.setGrid(Board2.getRows(), Board2.getCols(), c);
            placed = true;
        }

        return placed;
    }

    public Player calculateWinner() {
        Player winner;
        char mygrid[][] = Board2.getGrid();
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
    
    //private Recording recordGame(boolean record){
    //    Recording recording = new Recording();
    //}

}
