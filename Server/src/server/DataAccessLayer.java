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


public class DataAccessLayer {

    public static Connection connection;
    public static int gameId = 1;

    private String url = "jdbc:derby://localhost:1527/Game"; //url
    
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

        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE PLAYER SET USER_STATUS = 0 WHERE USER_NAME = ?",
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();

    }

    public static void sendRequest(String senderName, String recieverName) throws SQLException {

        UserHandler user = UserHandler.getUserHandler(recieverName);
        user.output.println(senderName + " wants to play against you");

    }

    public static String acceptRequest(String senderName, String recieverName) throws SQLException {

        String message;
        int senderScore=0;
        int recieverScore=0;

        PreparedStatement preparedStatementOne = connection.prepareStatement("SELECT * FROM PLAYER WHERE USER_NAME = ?",
        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatementOne.setString(1, senderName);
        ResultSet resultSet = preparedStatementOne.executeQuery();

        if (resultSet.next()) {
        senderScore = resultSet.getInt("USER_SCORE");
         }
        PreparedStatement preparedStatementTwo = connection.prepareStatement("SELECT * FROM PLAYER WHERE USER_NAME = ?",
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatementTwo.setString(1, recieverName);
        resultSet = preparedStatementTwo.executeQuery();
        if (resultSet.next()) {
        recieverScore = resultSet.getInt("USER_SCORE");
         }
        Game game = new Game();
        game.setGame_Id(String.valueOf(gameId)); // Convert int to String
        game.setPlayer1(senderName);
        game.setPlayer2(recieverName);
        gameId++;
        message = recieverName + "###" + recieverScore + "###" + senderName + "###" + senderScore;

        return message;
    }
//Need to somehow communicate with the server from the other project : tic tac toe

    public static Vector<Vector<String>> retriveHistory(String playerName) throws SQLException {
        Vector<Vector<String>> history_2d_vector = new Vector<>();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM GAME WHERE USER_NAME1 = ? OR USER_NAME2 = ?",
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        );

        preparedStatement.setString(1, playerName);
        preparedStatement.setString(2, playerName);
        ResultSet resultSet = preparedStatement.executeQuery();

        Vector<String> gameIds = new Vector<>();
        Vector<String> u_names1 = new Vector<>();
        Vector<String> u_names2 = new Vector<>();
        Vector<String> winners = new Vector<>();
        Vector<String> records = new Vector<>();

        while (resultSet.next()) {
            gameIds.add(resultSet.getString("GAME_ID"));
            u_names1.add(resultSet.getString("USER_NAME1"));
            u_names2.add(resultSet.getString("USER_NAME2"));
            winners.add(resultSet.getString("WINNER_NAME"));
            records.add(resultSet.getString("RECORD"));
        }

        history_2d_vector.add(gameIds);
        history_2d_vector.add(u_names1);
        history_2d_vector.add(u_names2);
        history_2d_vector.add(winners);
        history_2d_vector.add(records);

        return history_2d_vector;
    }
/////////

    public static void addWinner(String winnerName, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE GAME SET WINNER_NAME = ? WHERE GAME_ID = ?",
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY
        );

        preparedStatement.setString(1, winnerName);
        preparedStatement.setString(2, String.valueOf(id)); // Convert int to String
        preparedStatement.executeUpdate();
    }

    public static void updateStatus(String name, int status) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE PLAYER SET USER_STATUS = ? WHERE USER_NAME = ?",
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setInt(1, status);
        preparedStatement.setString(2, name);
        preparedStatement.executeUpdate();
    }
    
    public static int getOnlineMemberCount() throws SQLException {
    String query = "SELECT COUNT(*) AS onlineCount FROM PLAYER WHERE USER_STATUS = 1";
    PreparedStatement preparedStatement = connection.prepareStatement(query,
            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    
    ResultSet resultSet = preparedStatement.executeQuery();
    
    int onlineCount = 0;
    if (resultSet.next()) {
        onlineCount = resultSet.getInt("onlineCount");
    }
    
    resultSet.close();
    preparedStatement.close();
    
    return onlineCount;
}
    public static int getOfflineMemberCount() throws SQLException {
    String query = "SELECT COUNT(*) AS offlineCount FROM PLAYER WHERE USER_STATUS = 0";
    PreparedStatement preparedStatement = connection.prepareStatement(query,
            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    
    ResultSet resultSet = preparedStatement.executeQuery();
    
    int offlineCount = 0;
    if (resultSet.next()) {
        offlineCount = resultSet.getInt("offlineCount");
    }
    
    // Clean up resources
    resultSet.close();
    preparedStatement.close();
    
    return offlineCount;
}

}
