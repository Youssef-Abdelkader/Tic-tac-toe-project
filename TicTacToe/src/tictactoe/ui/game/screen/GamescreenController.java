/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.ui.game.screen;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tictactoe.ui.game.looser.LOSERBase;
import tictactoe.ui.game.looser.LOSERController;

/**
 *
 * @author yosef
 */
public class GamescreenController  extends game_screenBase{
    
    public GamescreenController(Stage stage, String name) {
        super(stage);
        
        
        
         
        forfeitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LOSERBase lose = new LOSERController(stage, name);
                Scene scene = new Scene(lose);
                stage.setScene(scene);
            }   
        });
        
        
    
        label1.setText(name);
    

          recordButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               recordButton.setDisable(true);
            }   
        });
    }
    
}
