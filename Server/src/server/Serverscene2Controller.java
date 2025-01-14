/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author yosef
 */
public class Serverscene2Controller extends Serverscene2Base {

    //Thread th = new Thread();
    //Stage stage;
    public Serverscene2Controller(Stage stage, Thread th) {
        super(stage);
        //this.th = th;
        //this.stage = stage;
        //stopServer();
        stop_server_btn.addEventHandler(ActionEvent.ACTION, (event) -> {
            serverscene_Controller server = new serverscene_Controller(stage);
            Scene scene = new Scene(server);
            stage.setScene(scene);
            try {
                //System.out.println("went to scene 2 but not stopped");
                serverscene_Controller.serverSocket.close();
                th.stop();
            } catch (IOException ex) {
                Logger.getLogger(Serverscene2Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("server stopped ");
        });
        
        stage.setOnCloseRequest((event)->{
            
             serverscene_Controller.closeServer();
        });
    }
}
