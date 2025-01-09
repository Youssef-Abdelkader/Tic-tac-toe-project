package tictactoe;

import tictactoe.ui.home.offline.HomeScreen_offline;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tictactoe.ui.game.winner.WINNERController;


public class TicTacToe extends Application {

    public static boolean online = false;

    @Override
    public void start(Stage stage) throws Exception {

         Parent root = new HomeScreen_offline(stage);





         //Parent root = new WINNERController(stage);


        //Signup() - History();

        //AnchorPane root = new WINNERBase();
        //Parent root = new HomeScreen_offline();
        //  Parent root = new game_screenBase();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        /*public void start(Stage primaryStage) throws Exception {
        // Set the initial scene to the serverscene (this will be your main menu)
        serverscene serverScene = new serverscene();
        Scene scene = new Scene(serverScene);

        primaryStage.setTitle("Tic Tac Toe Game");
        primaryStage.setScene(scene);
        primaryStage.show();

    }*/

    
}
    public static void main(String[] args) {
        launch(args);
    }
}
