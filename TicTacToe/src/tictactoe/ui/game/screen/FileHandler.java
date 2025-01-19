/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.ui.game.screen;

/**
 *
 * @author Menna G
 */
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.stage.FileChooser;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileHandler {
    
    static FileOutputStream fos;
    static DataOutputStream data_out;
    static File file;
    
    public static void initializeFile() {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showSaveDialog(null);

        try {
            fos = new FileOutputStream(selectedFile, true); // Append mode
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        data_out = new DataOutputStream(fos);
             
        if (selectedFile == null) {
            System.out.println("No file was selected.");
        } else {
            System.out.println("File selected: " + selectedFile.getAbsolutePath());
        }

        file = selectedFile; 
    }

    public static boolean writeToFile(String position, char symbol) {
    if (file == null) {
        System.err.println("File is null. Please pass a valid file.");
        return false;
    }

    try {
        String moverec = position + symbol + "###";
        data_out.writeBytes(moverec); 
        data_out.flush(); 
        System.out.println("Move written to file: " + position + symbol);
        return true;
    } catch (IOException ex) {
        Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}
    public static void closeResources(){
        try {
            data_out.close();
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
}
