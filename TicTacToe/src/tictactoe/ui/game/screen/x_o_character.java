package tictactoe.ui.game.screen;

/**
 *
 * @author Menna G
 */
public class x_o_character {

    //boolean assigned = false;

    X_OR_O x_char = X_OR_O.X;
    X_OR_O o_char = X_OR_O.O;
    
    char c;
    boolean isXAssigned = false;
    boolean isOAssigned = false;
    
    public void setCharacter(X_OR_O xo){
        if(xo.equals(xo.X)&&!isXAssigned){
            c='x';
            isXAssigned = true;
        }
        else if(xo.equals(xo.O)&&!isOAssigned){
            c='o';
            isOAssigned = false;
        }
    }
    public char getCharacter(){
        return c;
    }

}
