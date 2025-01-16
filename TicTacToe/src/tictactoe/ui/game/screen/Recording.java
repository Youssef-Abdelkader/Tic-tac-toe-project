
package tictactoe.ui.game.screen;

class Recording {
    
    //private enum Positions{
    //one .. nine
//}
    
    private int position;
    private X_OR_O played_char;
    //private char = played_char.

    public Recording(int position, X_OR_O played_char) {
        this.position = position;
        this.played_char = played_char;
    }
    
    
    //public Recording 
    
   
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public X_OR_O getPlayed_char() {
        return played_char;
    }

    public void setPlayed_char(X_OR_O played_char) {
        this.played_char = played_char;
    }
    
    
}
