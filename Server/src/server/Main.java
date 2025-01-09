package server;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Malak Raaof
 */
public class Main extends Application {

    ServerSocket serverSocket;

    public Main() {

        try {
            serverSocket = new ServerSocket(5005);
            while (true) {
                Socket socket = serverSocket.accept();
                new UserHandler(socket);
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = new serverscene_Controller(stage);

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
        new Main();
    }

}

class UserHandler extends Thread {

    DataInputStream input;
    PrintStream output;
    static Vector<UserHandler> clientsVector = new Vector<UserHandler>();

    public UserHandler(Socket socket) {
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new PrintStream(socket.getOutputStream());
            UserHandler.clientsVector.add(this);
            start();
        } catch (IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        while (true) {
            try {
                String str = input.readLine();
                sendMessageToAll(str);
            } catch (IOException ex) {
                Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void sendMessageToAll(String msg) {
        System.out.println("Broadcasting message: " + msg); // Debug log
        for (UserHandler user : clientsVector) {
            user.output.println(msg); // Send to each client
        }
    }

    /*
void sendMessageToAll (String msg)
{
for (ChatHandler ch : clientsVector){
//for(int i=0; i<clientsVector.size(); i++){
 ch.ps.println(msg);
}
}
     */
}
