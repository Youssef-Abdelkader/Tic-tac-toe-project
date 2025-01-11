package connection;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
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
            ear = new DataInputStream(server.getInputStream());
            mouth = new PrintStream(server.getOutputStream());
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
}
