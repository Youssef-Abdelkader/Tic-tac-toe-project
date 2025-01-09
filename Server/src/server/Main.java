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

    int ip = 5005;
    ServerSocket serverSocket;

    public Main() {

        try {
            serverSocket = new ServerSocket(ip);
            while (true) {
                Socket s = serverSocket.accept();
                new ServerThread(s);
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = new serverscene_Controller(stage);

        //Parent root = new WINNERController(stage);
        //Signup() - History();
        //AnchorPane root = new WINNERBase();
        //Parent root = new HomeScreen_offline();
        //  Parent root = new game_screenBase();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        /*public void start(Stage primaryStage) throws Exception {
        // Set the initial scene to the serverscene (this will be your main menu)
        serverscene serverScene = new serverscene();
        Scene scene = new Scene(serverScene);

        primaryStage.setTitle("Tic Tac Toe Game");
        primaryStage.setScene(scene);
        primaryStage.show();

    }*/

    }

    public static void main(String[] args) {
        launch(args);
        new Main();
    }

}

class ServerThread extends Thread {

    DataInputStream dis;
    PrintStream ps;
    static Vector<ServerThread> clientsVector = new Vector<ServerThread>();

    public ServerThread(Socket cs) {
        try {
            dis = new DataInputStream(cs.getInputStream());
            ps = new PrintStream(cs.getOutputStream());
            ServerThread.clientsVector.add(this);
            start();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        while (true) {
            try {
                String str = dis.readLine();
                sendMessageToAll(str);
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void sendMessageToAll(String msg) {
        System.out.println("Broadcasting message: " + msg); // Debug log
        for (ServerThread ch : clientsVector) {
            ch.ps.println(msg); // Send to each client
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
