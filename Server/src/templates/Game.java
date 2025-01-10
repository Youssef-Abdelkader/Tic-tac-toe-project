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
    public int player_id1;
    public int player_id2; 
    public String Record;
    public int Winner_Id;

    public Game(int Game_Id, int player_id1, int player_id2, String Record, int Winner_Id) {
        this.Game_Id = Game_Id;
        this.player_id1 = player_id1;
        this.player_id2 = player_id2;
        this.Record = Record;
        this.Winner_Id = Winner_Id;
    }

    public int getGame_Id() {
        return Game_Id;
    }

    public void setGame_Id(int Game_Id) {
        this.Game_Id = Game_Id;
    }

    public int getPlayer_id1() {
        return player_id1;
    }

    public void setPlayer_id1(int player_id1) {
        this.player_id1 = player_id1;
    }

    public int getPlayer_id2() {
        return player_id2;
    }

    public void setPlayer_id2(int player_id2) {
        this.player_id2 = player_id2;
    }

    public String getRecord() {
        return Record;
    }

    public void setRecord(String Record) {
        this.Record = Record;
    }

    public int getWinner_Id() {
        return Winner_Id;
    }

    public void setWinner_Id(int Winner_Id) {
        this.Winner_Id = Winner_Id;
    }
    
    
}
