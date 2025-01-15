package tictactoe.ui.game.screen;

/**
 *
 * @author Menna G
 */
public abstract class Player {

    protected Recording[] recording = new Recording[9];
    protected x_o_character character;

    public abstract void playChar();

    public abstract String RecordGame();

    //
    public Recording[] getRecording() {
        return recording;
        //more implementation to go
        //will get the grid only (?)
        //and seperate rows with commas 
    }

    public void setRecording(Recording[] recording) {
        this.recording = recording;
    }
    
    public void playOnSquare(int position, x_o_character x_o) {
        char c = x_o.getCharacter();
        
        
    }
    
    public void assignXorO(X_OR_O xo){
        character.setCharacter(xo);
    }
    public char getPlayerCharacter(){
        return character.getCharacter();
    }

    

    public String getPlayerName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void assignXorO(char c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
