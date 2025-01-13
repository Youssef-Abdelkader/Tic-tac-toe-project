package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import static server.DataAccessLayer.login;
import templates.Player;

class UserHandler extends Thread {

    DataInputStream input;
    PrintStream output;
    String name;
    String oppoName;
    boolean isPlaying = false;
    int score;
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

    public static UserHandler getUserHandler(String nameFound) {
        UserHandler userFound = null;
        for (UserHandler user : clientsVector) {
            if (user.name == nameFound) {
                userFound = user;
            }
        }
        return userFound;
    }

    public void run() {

        while (true) {
            try {
                String request = input.readLine();

                String[] data = request.split("###");

                switch (data[0]) {

                    case "login": {
                        try {
                            Player player = login(data[1], data[2]);
                            name=data[1];
                            System.out.println(player.getPassword());
                            System.out.println(data[2]);
                            if (player.getName() != null) {
                                if (player.getPassword().equals(data[2])) {
                                    this.output.println(player.getScore());
                                    DataAccessLayer.updateStatus(data[1], 1);
                                } else {
                                    this.output.println("incorrect password");
                                }
                            }else{
                                this.output.println("this name is not exist");
                            }
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                            
                        }
                    }
                    break;

                    case "signup": {
                        boolean signedUp = false;
                        Player player = new Player();
                        player.setName(data[1]);
                        name=data[1];
                        player.setPassword(data[2]);
                        player.setScore(0);
                        player.setStatus(1);

                        try {
                            signedUp = DataAccessLayer.signUp(player);
                        } catch (SQLException ex) {
                            this.output.println("Duplicated name");
                        }

                        if (signedUp) {
                            this.output.println("Signed up successfully");
                        }
                    }
                    break;

                    case "sendList": {
                        sendList();
                        break;

                    }

                    case "sendRequest": {


                        UserHandler user2 = UserHandler.getUserHandler(data[1]); //reciever
                        user2.output.println(name + " wants to play against you");

                        break;


                        //UserHandler user = UserHandler.getUserHandler(data[1]); //sender
//                        UserHandler user2 = UserHandler.getUserHandler(data[2]); //reciever
//                       user2.output.println(data[1] + " wants to play against you");
                        //user2.input.readLine(); (will be handeled in client page)

                    }

                    case "recieveRequest": {
                    }

                    case "history": {
                    }

                    case "move": //will be handeled when the game logic is implemented
                    {
                    }

                    case "logout": {
                    }

                    case "winner": {
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
    public void sendList() {
        Vector<String> available = new Vector<String>();
        for (UserHandler user : clientsVector) {
            if (!user.isPlaying && !(user.name.equals(name))) {
                available.add(user.name + " - Score: " + score);
            }
        }

        output.println("List" + "###" + available);

    }
}
