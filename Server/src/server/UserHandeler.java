package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
    DataOutputStream output;
    String name;
    String oppoName;
    boolean isPlaying = false;
    int score;
    String[] data;
    static Vector<UserHandler> clientsVector = new Vector<>();

    public UserHandler(Socket socket) {
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            addClient();
            //System.out.println(input.readLine());
            start(); // Start the thread for this user
        } catch (IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static UserHandler getUserHandler(String nameFound) {
        UserHandler userFound = null;
        for (UserHandler user : clientsVector) {
            if (user.name.equals(nameFound)) {
                userFound = user;
            }
        }
        return userFound;
    }

    public void run() {

        while (true) {
            try {
                String request = input.readUTF();

                data = request.split("###");

                switch (data[0]) {

                    case "login": {
                        try {
                            Player player = login(data[1], data[2]);
                            name = data[1];
                            System.out.println(player.getPassword());
                            System.out.println(data[2]);
                            if (player.getName() != null) {
                                if (player.getPassword().equals(data[2])) {
                                    score=player.getScore();
                                    this.output.writeUTF(String.valueOf(player.getScore()));
                                    DataAccessLayer.updateStatus(data[1], 1);
                                    try {
                                        Serverscene2Base.updatePiechart(DataAccessLayer.getOnlineMemberCount(), DataAccessLayer.getOfflineMemberCount());

                                    } catch (SQLException ex) {
                                        Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                    this.output.writeUTF("incorrect password");
                                }
                            } else {
                                this.output.writeUTF("this name is not exist");
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
                        name = data[1];
                        player.setPassword(data[2]);
                        player.setScore(0);
                        player.setStatus(1);

                        try {
                            signedUp = DataAccessLayer.signUp(player);
                            try {
                                Serverscene2Base.updatePiechart(DataAccessLayer.getOnlineMemberCount(), DataAccessLayer.getOfflineMemberCount());

                            } catch (SQLException ex) {
                                Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } catch (SQLException ex) {
                            this.output.writeUTF("Duplicated name###");
                        }

                        if (signedUp) {
                            
                            this.output.writeUTF("Signed up successfully###"+0);
                        }
                    }
                    break;

                    case "sendList": {
                        sendList();
                        break;

                    }

                    case "sendRequest": {

                        UserHandler user2 = UserHandler.getUserHandler(data[1]); //reciever
                        System.out.println(user2);
                        user2.output.writeUTF("invitation" + "###" + name + "###" + score);

                        


                        break;

                        //UserHandler user = UserHandler.getUserHandler(data[1]); //sender
//                        UserHandler user2 = UserHandler.getUserHandler(data[2]); //reciever
//                       user2.output.println(data[1] + " wants to play against you");
                        //user2.input.readLine(); (will be handeled in client page)
                    }

                    case "recieveRequest": {

                        //LUKAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
                        // sending game data to database + client to initiate the game
                    }

                    case "History_request": {
                        try {
                            Vector<Vector<String>> history_list = DataAccessLayer.retriveHistory(data[1]);
                            StringBuilder historyResponse = new StringBuilder("History_response");

                            historyResponse.append("###Game");
                            for (String s : history_list.elementAt(0)) {
                                historyResponse.append("###" + s);
                            }
                            historyResponse.append("###PlayerName1");
                            for (String s : history_list.elementAt(1)) {
                                historyResponse.append("###" + s);
                            }
                            historyResponse.append("###PlayerName2");
                            for (String s : history_list.elementAt(2)) {
                                historyResponse.append("###" + s);
                            }
                            historyResponse.append("###winner");
                            for (String s : history_list.elementAt(3)) {
                                historyResponse.append("###" + s);
                            }
                            historyResponse.append("###recording");
                            for (String s : history_list.elementAt(4)) {
                                historyResponse.append("###" + s);
                            }

                            this.output.writeUTF(historyResponse.toString());
                        } catch (SQLException ex) {
                            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
                            this.output.writeUTF("Error retrieving history");
                        }
                        break;
                    }

                    case "move": //will be handeled when the game logic is implemented
                    {
                    }

                    case "logout": {

                        try {
                            DataAccessLayer.logout(this.name);
                            output.writeUTF("logout###success");
                            clientsVector.remove(this);
                            input.close();
                            output.close();

                            if (!(this.name.equals(null))) {
                                try {

                                    DataAccessLayer.logout(this.name);
                                    output.writeUTF("logout###success");
                                    removeClient();
                                    input.close();
                                    output.close();
                                } catch (SQLException ex) {
                                    Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    output.writeUTF("logout###failure");
                                } catch (IOException ex) {
                                    Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                break;
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    case "winner": {

                        try {
                            // Extract the winner's name and game ID from the message
                            String winnerName = data[1]; // winnerName is the second part of the message
                            int gameId = Integer.parseInt(data[2]); // gameId is the third part of the message

                            // Call the DataAccessLayer to update the database
                            DataAccessLayer.addWinner(winnerName, gameId);

                            // Notify the client that the winner was recorded successfully
                            output.writeUTF("winner###success");
                        } catch (SQLException ex) {
                            // Log the error and notify the client if the database update fails
                            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
                            output.writeUTF("winner###failure");
                        }
                        break;

                    }
                    
                     //Connection.sendRequest("GetInvitation" + "###" + "Accepted" + "###" + rec[1]);

                    case "GetInvitation": {
                        getInvitation();
                        
                        break;
                    }
                    case "back": {
                        input.close();
                        output.close();
                        
                         break;

                    }

                }

                //if (request == null) break; // Exit if client disconnects
                //sendMessageToAll(request);
            } catch (IOException ex) {
                removeClient();
                try {
                    input.close();
                    output.close();
                } catch (IOException e) {
                    Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
                }

                break; // Exit the loop if an error occurs
            }

        }
        // Cleanup after client disconnects

    }

    /*void sendMessageToAll(String msg) {
        System.out.println("Broadcasting message: " + msg); // Debug log
        for (UserHandler user : clientsVector) {
            user.output.println(msg); // Send to each client
        }*/
    public void sendList() {
        Vector<String> available = new Vector<String>();
        System.out.println(clientsVector.size());
        for (UserHandler client : clientsVector) {
            if (!client.isPlaying) {
                available.add(client.name + " - Score: " + client.score);
            }
        }
        sendListToAll(available);

    }

    public void sendListToAll(Vector<String> Online) {
        for (UserHandler client : clientsVector) {
            Vector<String> temp = new Vector<String>(Online);
            temp.remove(client.name + " - Score: " + client.score);
            try {
                client.output.writeUTF("List" + "###" + temp);
            } catch (IOException ex) {
                Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void removeClient() {
        clientsVector.remove(this);

        sendList();

        try {
            Serverscene2Base.updatePiechart(DataAccessLayer.getOnlineMemberCount(), DataAccessLayer.getOfflineMemberCount());
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addClient() {
        clientsVector.add(this);

    }
    
    //Connection.sendRequest("GetInvitation" + "###" + "Accepted" + "###" + rec[1])

    public void getInvitation() {
        
        if(data[1].equals("Accepted")){
            
            this.isPlaying=true;
            UserHandler opp = getUserHandler(data[2]);
            opp.isPlaying=true;
            this.oppoName = data[2];
            opp.oppoName=this.name;
            
            try {
                try {
                    opp.output.writeUTF("Accepted###"+DataAccessLayer.acceptRequest(this.name, this.oppoName));
                } catch (IOException ex) {
                    Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(this.name + this.oppoName);
                System.out.println("-----------------");
                System.out.println(DataAccessLayer.acceptRequest(this.name, this.oppoName));
            } catch (SQLException ex) {
                Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            sendList();
        }
        else if(data[1].equals("Refused")){
            UserHandler opp = getUserHandler(data[2]);
            try {
                opp.output.writeUTF("Refused");
            } catch (IOException ex) {
                Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
