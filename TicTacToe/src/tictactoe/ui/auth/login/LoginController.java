package tictactoe.ui.auth.login;

import connection.Connection;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import tictactoe.TicTacToe;
import tictactoe.ui.auth.signup.SignupController;
import tictactoe.ui.game.screen.OnlinePlayer;
import tictactoe.ui.home.offline.HomeScreen_offline_Controller;
import tictactoe.ui.home.online.HomeOnlineController;

public class LoginController extends Login {

    private Thread thread;

    OnlinePlayer player = new OnlinePlayer();
    
    public LoginController(Stage stage) {
        super(stage);

        signUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SignupController signup = new SignupController(stage);
                Scene scene = new Scene(signup);
                stage.setScene(scene);
            }
        });

        //get the player username
        //find that player's data from the database
        //use this data to initialize player object
        //pass it to homeonline screen
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (thread != null && thread.isAlive()) {
                    thread.interrupt(); // Stop any active listener thread
                }

                //check if text feilds are empty
                if (!isEmpty()) {
                    //get username and password from 
                    String name = nameText.getText();
                    String password = passwordText.getText();
                    
                    player.setUser_name(name);
                    player.setPassword(password);
                    HomeOnlineController.SetPlayer(player);
                    //organize the message that will be sent to server
                    String message = "login###" + name + "###" + password;

                    try {
                        //sending message to server
                        Connection.mouth.writeUTF(message);
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    // Start a thread to listen for messages from the server
                    thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                boolean isRunning = true;
                                while (isRunning) {
                                    String message = Connection.ear.readUTF();
                                    switch (message) {
                                        case "this name is not exist":

                                             {
                                                if (Platform.isFxApplicationThread() == false) {
                                                    Platform.runLater(() -> {
                                                        System.out.println(message);
                                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                                        alert.setTitle("Name not found");
                                                        alert.setContentText("Please sign up");
                                                        alert.showAndWait();
                                                    });
                                                }
                                            }
                                            isRunning = false;
                                            break;
                                        case "incorrect password": {
                                            if (Platform.isFxApplicationThread() == false) {
                                                Platform.runLater(() -> {
                                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                                    alert.setTitle("Incorrect password");
                                                    alert.setContentText("Please try again");
                                                    alert.showAndWait();
                                                });
                                            }
//                                            System.out.println(message);
                                            isRunning = false;
                                            break;
                                        }

                                        default: {
                                            if (Platform.isFxApplicationThread() == false) {
                                                Platform.runLater(() -> {
//                                                    System.out.println(message);
                                                    HomeOnlineController home = new HomeOnlineController(stage,name,message);
                                                    home.SetPlayer(player);
                                                    Scene scene = new Scene(home);
                                                    stage.setScene(scene);
                                                });
                                            }
                                        }
                                        isRunning = false;
                                        break;
                                    }
                                }
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                    });
                    thread.start();

                }
            }
        });

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TicTacToe.online = false;
                HomeScreen_offline_Controller home = new HomeScreen_offline_Controller(stage);
                Scene scene = new Scene(home);
                stage.setScene(scene);
                Connection.sendRequest("back");
            }
        });
    }

    public boolean isEmpty() {
        boolean check = false;
        if (nameText.getText().isEmpty()) {
            nameError.setVisible(true);
            check = true;
        } else {
            nameError.setVisible(false);
        }
        if (passwordText.getText().isEmpty()) {
            passwordError.setVisible(true);
            check = true;
        } else {
            passwordError.setVisible(false);
        }
        return check;
    }
}
