package tictactoe;

import connection.Connection;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.Socket;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import tictactoe.ui.game.winner.WINNERController;
import tictactoe.ui.history.History;
import tictactoe.ui.home.offline.HomeScreen_offline_Controller;

import tictactoe.ui.home.offline.HomeScreen_offline_Controller;

public class TicTacToe extends Application {

    public static boolean online = false;
    Socket server;
    DataInputStream ear;
    PrintStream mouth;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = new HomeScreen_offline_Controller(stage);

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(e -> {
            Connection.sendRequest("logout");

        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}
