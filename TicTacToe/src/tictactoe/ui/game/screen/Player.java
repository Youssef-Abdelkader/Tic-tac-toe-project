package tictactoe.ui.game.screen;

/**
 *
 * @author Menna G
 */
public abstract class Player {

    protected Recording[] recording = new Recording[9];
    protected x_o_character character;
    Game game = new Game();
    boolean record = false;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isRecord() {
        return record;
    }

    public void setRecord(boolean record) {
        this.record = record;
    }

    public void playChar(int pos){
       if(pos>0&&pos<10){
           game.placeXO(pos/*, character.getCharacter()*/); //from game that exoists in the same package.
       }
    }

   // public abstract String RecordGame();

    //no more than a retrieval method 
    public Recording[] getRecording() {
        return recording;
     }

    public void setRecording(Recording[] recording) {
        this.recording = recording;
    }
   //to delete 
    public void playOnSquare(int position, x_o_character x_o) {
        char c = x_o.getCharacter(); 
        game.placeXO(position); // may result into a logical error
    }
    //
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
