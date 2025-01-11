/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package templates;

/**
 *
 * @author yosef
 */
public class Game {
    public int Game_Id;
    public String player1;
    public String player2; 
    public String Record;
    public String winnerName;

    public Game() {
    }

    public Game(int Game_Id, String player1, String player2, String Record, String winnerName) {
        this.Game_Id = Game_Id;
        this.player1 = player1;
        this.player2 = player2;
        this.Record = Record;
        this.winnerName = winnerName;
    }

  
    public int getGame_Id() {
        return Game_Id;
    }

    public void setGame_Id(int Game_Id) {
        this.Game_Id = Game_Id;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public String getWinnerName() {
        return winnerName;
    }


    public String getRecord() {
        return Record;
    }

    public void setRecord(String Record) {
        this.Record = Record;
    }

 
    
    
}
