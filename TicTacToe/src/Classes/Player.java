
package Classes;

/**
 *
 * @author Menna G
 */
public abstract class Player {

    protected enum game_char {
        X, O;
    }
    //protected boolean x_game;
    protected Recording[] recording = new Recording[9]; 
    
    public abstract void setGameChar();
}
