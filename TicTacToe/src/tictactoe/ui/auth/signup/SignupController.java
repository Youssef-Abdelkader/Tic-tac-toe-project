package tictactoe.ui.auth.signup;

import connection.Connection;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tictactoe.ui.home.offline.HomeScreen_offline;
import tictactoe.ui.home.offline.HomeScreen_offline_Controller;
import tictactoe.ui.home.online.HomeOnlineController;
import javafx.scene.control.Alert;
import tictactoe.TicTacToe;

public class SignupController extends Signup {

    public SignupController(Stage stage) {
        super(stage);

        signUp.setOnAction(new EventHandler<ActionEvent>() {
            boolean isRunning = true;

            @Override
            public void handle(ActionEvent event) {
                //check if text feilds are empty
                if (!isEmpty()) {
                    //get username and password from 
                    String name = nameText.getText();
                    String password = passwordText.getText();

                    //organize the message that will be sent to server
                    String message = "signup###" + name + "###" + password;

                    //sending message to server
                    Connection.mouth.println(message);

                    // Start a thread to listen for messages from the server
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {

                                while (isRunning) {
                                    String message = Connection.ear.readLine();
                                    String[] data = message.split("###");
                                    if ("Duplicated name".equals(data[0])) {
                                        System.out.println(message);
                                        if (Platform.isFxApplicationThread() == false) {
                                            Platform.runLater(() -> {
                                                System.out.println(message);
                                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                                alert.setTitle("Duplicated Name");
                                                alert.setContentText("Please retry");
                                                alert.showAndWait();
                                                isRunning = false;
                                            });
                                        }
                                    } else {
                                        if (Platform.isFxApplicationThread() == false) {
                                            Platform.runLater(() -> {
                                                HomeOnlineController home = new HomeOnlineController(stage,name,data[1]);
                                                Scene scene = new Scene(home);
                                                stage.setScene(scene);
                                                isRunning = false;
                                            });
                                        }
                                    }
                                }

                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                    }).start();

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
