package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import templates.Player;

class UserHandler extends Thread {

    DataInputStream input;
    PrintStream output;
    static Vector<UserHandler> clientsVector = new Vector<>();

    public UserHandler(Socket socket) {
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new PrintStream(socket.getOutputStream());
            clientsVector.add(this);
            //System.out.println(input.readLine());
            start(); // Start the thread for this user
        } catch (IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        while (true) {
            try {
                String request = input.readLine();
                String[] split = request.split("###");

                switch (split[0]) {
                    case "login":
                        {}
                    
                    case"signup":
                        {
                            Player player = new Player();
                            player.setName(split[1]);
                            player.setPassword(split[2]);
                        }

                }

                //if (request == null) break; // Exit if client disconnects
                //sendMessageToAll(request);
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

    /*void sendMessageToAll(String msg) {
        System.out.println("Broadcasting message: " + msg); // Debug log
        for (UserHandler user : clientsVector) {
            user.output.println(msg); // Send to each client
        }*/
}
