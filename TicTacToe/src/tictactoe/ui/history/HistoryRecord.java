/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.ui.history;

/**
 *
 * @author Menna G
 */
public class HistoryRecord {
    private final String gameId;
    private final String opponent;
    private final String winner;
    private final String recording;

    public HistoryRecord(String gameId, String opponent, String winner, String recording) {
        this.gameId = gameId;
        this.opponent = opponent;
        this.winner = winner;
        this.recording = recording;
    }

    public String getGameId() {
        return gameId;
    }

    public String getOpponent() {
        return opponent;
    }

    public String getWinner() {
        return winner;
    }

    public String getRecording() {
        return recording;
    }
}
