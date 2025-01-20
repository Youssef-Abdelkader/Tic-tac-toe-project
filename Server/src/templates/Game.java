package templates;

import java.util.UUID;

public class Game {
    private String Game_Id; 
    private String player1;
    private String player2;
    private String Record;
    private String winnerName;

    public Game() {
        this.Game_Id = UUID.randomUUID().toString(); 
    }

    public Game(String player1, String player2, String Record, String winnerName) {
        this.Game_Id = UUID.randomUUID().toString(); 
        this.player1 = player1;
        this.player2 = player2;
        this.Record = Record;
        this.winnerName = winnerName;
    }

    public String getGame_Id() {
        return Game_Id;
    }

    public void setGame_Id(String Game_Id) {
        this.Game_Id = Game_Id;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getRecord() {
        return Record;
    }

    public void setRecord(String Record) {
        this.Record = Record;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }
}