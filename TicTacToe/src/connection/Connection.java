package connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
    public static DataOutputStream mouth;

    public static boolean setConnection() {
        boolean connect = false;
        try {
            server = new Socket("127.0.0.1", 5005);
            ear = new DataInputStream(server.getInputStream());
            mouth = new DataOutputStream(server.getOutputStream());
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
        if (mouth != null) {
            try {
                mouth.writeUTF(msg);
            } catch (IOException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static String recieveRequest() {
        String msg = null;
        try {
            msg = ear.readUTF();
        } catch (IOException ex) {
            msg = "1###";

        }
        return msg;
    }

    public static void closeconnection() {
        try {
            mouth.close();
            ear.close();
            server.close();

        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
}
