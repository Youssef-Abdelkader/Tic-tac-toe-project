
package tictactoe.ui.game.screen;

public enum X_OR_O {
    X('x'),
    O('o');

    private final char value;

    private X_OR_O(char value) {
        this.value = value;
    }

    public char toChar() {
        return value;
    }
    
     public static X_OR_O getEnum(char c) {
        for (X_OR_O e : values()) {
            if (e.toChar() == Character.toLowerCase(c)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Invalid character for X_OR_O: " + c);
    }
}