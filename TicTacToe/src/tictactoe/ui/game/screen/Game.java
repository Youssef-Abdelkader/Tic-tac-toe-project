package tictactoe.ui.game.screen;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Game {

    private Player player1;
    private Player player2;

    public boolean isRec_flag() {
        return rec_flag;
    }

    public void setRec_flag(boolean rec_flag) {
        this.rec_flag = rec_flag;
    }
    private Board squares;
    private boolean online;
    private char currentPlayerSymbol;
    private Recording[] rec = new Recording[9];
    private boolean rec_flag;
    private int counter = 0;

    public Game(boolean online) {
        this.online = online;
        this.squares = new Board();
        this.currentPlayerSymbol = 'X';
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }


    /*Game() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    public boolean placeXO(int pos) {
        int row = (pos - 1) / 3;
        int col = (pos - 1) % 3;

        if (pos >= 1 && pos <= 9 && squares.isCellEmpty(row, col)) {

            squares.setGrid(row, col, currentPlayerSymbol);

            if (rec_flag || online) {
                Recording r = new Recording(pos, X_OR_O.getEnum(currentPlayerSymbol));
                if (rec_flag) {
                    FileHandler.writeToFile("recording object " + r.getPosition() + r.getPlayed_char().toChar());
                    rec[counter] = r;
                    counter++;
                } else if (online) {
                    sendMoveToServer("recording object " + r.getPosition() + r.getPlayed_char().toChar());
                }
            }
            switchPlayer();

            return true;
        }
        return false;
    }

    public int[] calculateWinner() {
        char[][] mygrid = squares.getGrid();

        for (int row = 0; row < 3; row++) {
            if (mygrid[row][0] != 0 && (mygrid[row][0] == mygrid[row][1]) && (mygrid[row][1] == mygrid[row][2])) {
                return new int[]{row * 3 + 1, row * 3 + 2, row * 3 + 3};
            }
        }
        for (int col = 0; col < 3; col++) {
            if (mygrid[0][col] != 0 && (mygrid[0][col] == mygrid[1][col]) && (mygrid[1][col] == mygrid[2][col])) {
                return new int[]{col + 1, col + 4, col + 7};
            }
        }
        if (mygrid[0][0] != 0 && (mygrid[0][0] == mygrid[1][1]) && (mygrid[1][1] == mygrid[2][2])) {
            return new int[]{1, 5, 9};
        }
        if (mygrid[0][2] != 0 && (mygrid[0][2] == mygrid[1][1]) && (mygrid[1][1] == mygrid[2][0])) {
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

    public Recording[] getRec() {
        return rec;
    }

    public String recToString() {
        String s = null;
        for (int i = 0; i < rec.length; i++) {
            String carxo = rec[i].getPlayed_char().toString() + "###";
            String rowcol = String.valueOf(rec[i].getPosition()) + "###";
            s = carxo + rowcol + "---";
            System.out.println("recording " + s);
        }
        return s;
    }
    //error
    /*public void setRec(Recording[] rec) {
        this.rec = rec;
    }*/

        private void sendMoveToServer(String recToString) {
        try {
            Socket socket = new Socket("127.0.0.1", 5005);  // Assuming the server is on localhost and port 5005
            System.out.println("Connected to server");

            PrintWriter mouth = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader ear = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            mouth.println("Recording###" + recToString);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String message;
                        while ((message = ear.readLine()) != null) {
                            System.out.println("Received from server: " + message);
                        }
                    } catch (IOException ex) {
                        System.out.println("Error while listening to server: " + ex.getMessage());
                    }
                }
            }).start();

        } catch (IOException ex) {
            System.out.println("Error while connecting to server: " + ex.getMessage());
        }
    }

}
