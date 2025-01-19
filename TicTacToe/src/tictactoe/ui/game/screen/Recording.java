package tictactoe.ui.game.screen;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;

class Recording {

    private int position;
    private X_OR_O played_char;
    //private char = played_char.

    public Recording(int position, X_OR_O played_char) {
        this.position = position;
        this.played_char = played_char;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public X_OR_O getPlayed_char() {
        return played_char;
    }

    public void setPlayed_char(X_OR_O played_char) {
        this.played_char = played_char;
    }

    public int[] getRowAndCol() {
        int ar[] = new int[2];
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;
        ar[0] = row;
        ar[1] = col;
        return ar;
    }

    /*    public static boolean writeToFile(String s) {
    boolean flag = false;
    try {
    FileChooser fc = new FileChooser();
    File file = fc.showSaveDialog(null);
    if (file != null) {
    
    FileOutputStream fos = new FileOutputStream(file, true); // Enable appending
    DataOutputStream output = new DataOutputStream(fos);
    output.writeUTF(s);
    flag = true;
    }
    } catch (IOException ex) {
    Logger.getLogger(Recording.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return flag;
    }*/
}
