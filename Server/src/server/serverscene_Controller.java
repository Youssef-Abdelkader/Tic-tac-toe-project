
package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class serverscene_Controller extends serverscene {

    ServerSocket serverSocket;

    public serverscene_Controller(Stage stage) {
        super(stage);

        Start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Serverscene2Base server = new Serverscene2Base(stage);
                Scene scene = new Scene(server);
                stage.setScene(scene);

                // Start the server on a separate thread
                new Thread(() -> {
                    try {
                        serverSocket = new ServerSocket(5005);
                        System.out.println("Server started on port 5005...");
                        while (true) {
                            Socket socket = serverSocket.accept();
                            System.out.println("New client connected.");
                            new UserHandler(socket);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }).start();
            }
        });
    }
}

class UserHandler extends Thread {

    DataInputStream input;
    PrintStream output;
    static Vector<UserHandler> clientsVector = new Vector<>();

    public UserHandler(Socket socket) {
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new PrintStream(socket.getOutputStream());
            clientsVector.add(this);
            start(); // Start the thread for this user
        } catch (IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        while (true) {
            try {
                String str = input.readLine();
                if (str == null) break; // Exit if client disconnects
                sendMessageToAll(str);
            } catch (IOException ex) {
                Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
                break; // Exit the loop if an error occurs
            }
        }
        // Cleanup after client disconnects
        clientsVector.remove(this);
        try {
            input.close();
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void sendMessageToAll(String msg) {
        System.out.println("Broadcasting message: " + msg); // Debug log
        for (UserHandler user : clientsVector) {
            user.output.println(msg); // Send to each client
        }
    }
}
