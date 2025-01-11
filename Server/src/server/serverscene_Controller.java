
package server;


import com.sun.corba.se.spi.activation.Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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

                

                // Start the server on a separate thread
                Thread th = new Thread(() -> {
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
                });
                th.start();
                Serverscene2Controller c = new Serverscene2Controller(stage,th);
                
                //Serverscene2Base server = new Serverscene2Base(stage);
                Scene scene = new Scene(c);
                stage.setScene(scene);
            }
        });
    }
}


