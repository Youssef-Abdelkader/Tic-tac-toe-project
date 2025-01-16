
package Classes;

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
    
    //i am hurt
    //i am struggling
    //i am behind
    //i need help
    //but i don't have the courage to ask
    //i got here to learn
    //but i learnt that im behind
    //now, i am starting to doubt myself more than before

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
