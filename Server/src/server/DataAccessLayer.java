/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import templates.Player;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.jdbc.ClientDriver;
import templates.Game;

/**
 *
 * @author habib
 */
public class DataAccessLayer {

    public static Connection connection;

    static {
        try {
            DriverManager.registerDriver(new ClientDriver());
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/Game", "root", "root");
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean signUp(Player player) throws SQLException {
        boolean finalResult = false;
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("INSERT INTO PLAYER (USER_NAME,USER_PASSWORD,USER_SCORE,USER_STATUS) "
                + "VALUES (?,?,?,?)", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setString(1, player.getName());
        preparedStatement.setString(2, player.getPassword());
        preparedStatement.setInt(3, player.getScore());
        preparedStatement.setInt(4, player.getStatus());

        int result = preparedStatement.executeUpdate();

        if (result > 0) {
            finalResult = true;
        }
        return finalResult;
    }

    public static Player login(String name, String password) throws SQLException {

        Player player = new Player();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PLAYER WHERE USER_NAME = ?",
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();

        int score = resultSet.getInt("USER_SCORE");
        int status = resultSet.getInt("USER_STATUS");

        player.setName(name);
        player.setPassword(password);
        player.setScore(score);
        player.setStatus(status);

        return player;
    }

    
    public static void logout(String name) throws SQLException {
        
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE STATUS INTO 0 FROM PLAYER WHERE USER_NAME = ?",
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1, name);
        preparedStatement.executeQuery();

    }
    
    public static void sendRequest(String nameOne, String nameTwo) throws SQLException {

        UserHandler user = UserHandler.getUserHandler(nameTwo);
        user.output.println(nameOne + " wants to play against you");

    }

    public static String acceptRequest(String senderName, String recieverName, String response) throws SQLException {

        String message;

        PreparedStatement preparedStatementOne = connection.prepareStatement("SELECT * FROM PLAYER WHERE USER_NAME = ?",
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatementOne.setString(1, senderName);
        ResultSet resultSet = preparedStatementOne.executeQuery();

        int senderScore = resultSet.getInt("USER_SCORE");

        PreparedStatement preparedStatementTwo = connection.prepareStatement("SELECT * FROM PLAYER WHERE USER_NAME = ?",
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatementTwo.setString(1, recieverName);
        resultSet = preparedStatementTwo.executeQuery();
        int recieverScore = resultSet.getInt("USER_SCORE");

        int gameId = 1;
        Game game = new Game();
        game.setGame_Id(gameId);
        game.setPlayer1(senderName);
        game.setPlayer2(recieverName);
        gameId++;
        message = response + "###" + recieverName + "###" + recieverScore + "###" + senderName + "###" + senderScore;

        return message;
    }
    
    public static Vector<String> retriveHistory(String playerName) throws SQLException {
        Vector<String>data = new Vector<String>();
        
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM GAME WHERE USER_NAME1 OR USER_NAME2 = ?",
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setString(1, playerName);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while(resultSet.next()){
            data.add(resultSet.getString("GAME_ID")+"###"+
                    resultSet.getString("USER_NAME1")+"###"+
                    resultSet.getString("USER_NAME2")+"###"+
                    resultSet.getString("WINNER_NAME")+"###"+
                    resultSet.getString("RECORD"));
        }
        return data;
    }
}
