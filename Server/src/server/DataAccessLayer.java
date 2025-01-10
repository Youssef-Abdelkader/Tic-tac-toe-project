/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import templates.Player;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.jdbc.ClientDriver;


/**
 *
 * @author habib
 */
public class DataAccessLayer {
    
    public static Connection connection;

    static {
        try {
            DriverManager.registerDriver(new ClientDriver());
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/personData", "root", "root");
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean signUp(Player player) throws SQLException {
        boolean finalResult = false;
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("INSERT INTO PLAYER (USER_ID,USER_NAME,USER_PASSWORD,USER_SCORE) "
                + "VALUES (?,?,?,?)", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, player.getId());
        preparedStatement.setString(2, player.getName());
        preparedStatement.setString(3, player.getPassword());
        preparedStatement.setInt(4, player.getScore());
        //preparedStatement.setInt(5, player.getStatus());
        //preparedStatement.setString(6, person.getPhone());
        int result = preparedStatement.executeUpdate();

        if (result > 0) {
            finalResult = true;
        }
        return finalResult;
    }
}
