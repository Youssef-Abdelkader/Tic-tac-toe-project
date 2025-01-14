
package tictactoe.ui.game.screen;

/**
 *
 * @author Menna G
 */
public class OnlinePlayer extends Player{
 private String user_name;

    public OnlinePlayer() {
        
    }

    
    @Override
    public void playChar() {

    }

    @Override
    public String RecordGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
    

