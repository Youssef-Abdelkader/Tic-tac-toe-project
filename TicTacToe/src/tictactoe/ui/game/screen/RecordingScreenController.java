package tictactoe.ui.game.screen;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tictactoe.ui.home.offline.HomeScreen_offline_Controller;

public class RecordingScreenController extends recordingScreenBase {

    Button clickedButton = new Button();

    public static Map<Button, File> myButtons = new HashMap<>();
    private final Stage stage;
    public static Vector<File> recordings_vector = new Vector<File>();

    public RecordingScreenController(Stage stage) {
        this.stage = stage;
        populateVbox();
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomeScreen_offline_Controller home = new HomeScreen_offline_Controller(stage);
                Scene scene = new Scene(home);
                stage.setScene(scene);
            }
        });
    }

    public void populateVbox() {
        for (File f : recordings_vector) {
            Button b = new Button(f.getName());
            myButtons.put(b, f);
            vBox.getChildren().add(b);

            b.setOnAction(event -> {
                Button clickedButton = (Button) event.getSource();
                File file = myButtons.get(b);

                System.out.println("Button clicked for file: " + file.getName());
                handleClick(file);
            });
        }
    }

    public void handleClick(File file) {
        FileInputStream input = null;
        try {
            input = new FileInputStream(file);
            DataInputStream data_input = new DataInputStream(input);
            byte[] fileContent = new byte[(int) file.length()];
            data_input.readFully(fileContent);

            String recorded = new String(fileContent);
            String[] parsed = recorded.split("###");
            for (int i = 0; i < parsed.length; i++) {
                System.out.println("parsed move " + parsed[i]);
            }
            
            GamescreenController.passRecording(parsed);

            GamescreenController gameScreen = new GamescreenController(stage, "Player1", "Player2");
            gameScreen.updateRecGrid();

            Scene scene = new Scene(gameScreen);
            stage.setScene(scene);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(RecordingScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RecordingScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(RecordingScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String[] parseRecordedString(String recorded) {
        System.out.println("recorded " + recorded);
        String cleanedRecorded = recorded.replaceAll(" ", "");
        System.out.println("cleanedRecorded: " + cleanedRecorded);

        String[] parsed = cleanedRecorded.split("###");
        for (int i = 0; i < parsed.length; i++) {
            System.out.println("parsed move " + parsed[i]);
        }

        System.out.println(parsed[0].split("###").toString());
        return parsed;
    }
}
