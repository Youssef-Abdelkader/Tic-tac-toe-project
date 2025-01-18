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

    public static boolean writeToFile(String data) {
        if (file == null) {
            System.err.println("File is null. Please pass a valid file.");
            return false;
        }

       
        boolean flag = false;

        try { 
            data_out.writeUTF(data+"\n");
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
            flag = true; 
            System.out.println("Data written to file: " + file.getAbsolutePath());

       

        return flag;
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
