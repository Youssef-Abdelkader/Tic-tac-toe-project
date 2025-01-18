package connection;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class Connection {

    public static Socket server;
    public static DataInputStream ear;
    public static PrintStream mouth;

    public static boolean setConnection() {
        boolean connect = false;
        try {
            server = new Socket("127.0.0.1", 5005);
            ear = new DataInputStream(server.getInputStream()); //in
            mouth = new PrintStream(server.getOutputStream()); //out
            connect = true;
        } catch (IOException ex) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Couldn't connect to Server");
                alert.showAndWait();
            });
        }
        return connect;
    }

    public static void sendRequest(String msg) {
        mouth.println(msg);
    }

    public static String recieveRequest() {
        String msg = null;
        try {
            msg = ear.readLine();
        } catch (IOException ex) {
            msg="1###";
            
        }
        return msg;
    }
    public static void closeconnection()
    {
        mouth.close();
        try {
            ear.close();
            server.close();
        } catch (IOException ex) {
            
        }
        
    }
}
