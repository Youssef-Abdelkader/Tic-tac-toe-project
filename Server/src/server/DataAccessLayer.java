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
    public static int gameId = 1;

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

        if (resultSet.next()) {
            int score = resultSet.getInt("USER_SCORE");
            int status = resultSet.getInt("USER_STATUS");
            String realPassword = resultSet.getString("USER_PASSWORD");

            if (resultSet.getFetchSize() > 0) {
                player.setName(name);
                player.setPassword(realPassword);
                player.setScore(score);
                player.setStatus(status);
            }
        }

        return player;
    }

    public static void logout(String name) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE STATUS INTO 0 FROM PLAYER WHERE USER_NAME = ?",
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1, name);
        preparedStatement.executeQuery();

    }

    public static void sendRequest(String senderName, String recieverName) throws SQLException {

        UserHandler user = UserHandler.getUserHandler(recieverName);
        user.output.println(senderName + " wants to play against you"); //output of server to communicate with reciever

    }

    public static String acceptRequest(String senderName, String recieverName) throws SQLException {

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

        Game game = new Game();
        game.setGame_Id(gameId);
        game.setPlayer1(senderName);
        game.setPlayer2(recieverName);
        gameId++;
        message = recieverName + "###" + recieverScore + "###" + senderName + "###" + senderScore;

        return message;
    }
//Need to somehow communicate with the server from the other project : tic tac toe

    public static Vector<String> retriveHistory(String playerName) throws SQLException {

        Vector<String> data = new Vector<String>();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM GAME WHERE USER_NAME1 OR USER_NAME2 = ?",
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setString(1, playerName);
        ResultSet resultSet = preparedStatement.executeQuery();

        //ead
        while (resultSet.next()) {
            data.add(resultSet.getString("GAME_ID")); 
            data.add(resultSet.getString("USER_NAME1")); 
            data.add(resultSet.getString("USER_NAME2")); 
            data.add(resultSet.getString("WINNER_NAME")); 
            data.add(resultSet.getString("RECORD")); 
        }
        return data;
    }
/////////

    public static void addWinner(String winnerName, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE WINNER_NAME INTO ? FROM GAME WHERE GAME_ID = ?",
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setString(1, winnerName);
        preparedStatement.setInt(2, id);
        preparedStatement.executeQuery();
    }

    public static void updateStatus(String name, int status) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE PLAYER SET USER_STATUS = ? WHERE USER_NAME = ?",
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setInt(1, status);
        preparedStatement.setString(2, name);
        preparedStatement.executeQuery();
    }

}
