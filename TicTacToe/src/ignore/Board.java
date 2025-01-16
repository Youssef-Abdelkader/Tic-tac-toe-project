package ignore;

class Board {

    private static final int rows = 3;
    private static final int cols = 3;
    public static char[][] grid = new char[rows][cols];

    private static int row_in;
    private static int col_in;

    /*public enum BoardSquares {
        SQUARE_1(null),
        SQUARE_2(null),
        SQUARE_3(null),
        SQUARE_4(null),
        SQUARE_5(null),
        SQUARE_6(null),
        SQUARE_7(null),
        SQUARE_8(null),
        SQUARE_9(null);

        private x_o_character gameChar;

        BoardSquares(x_o_character x_o) {
            this.gameChar = x_o;
        }

        public x_o_character getGameChar() {
            return gameChar;
        }

        public void setGameChar(x_o_character x_o) {
            this.gameChar = x_o;
        }

        public static BoardSquares getSQUARE_1() {
            return SQUARE_1;
        }

        public static BoardSquares getSQUARE_2() {
            return SQUARE_2;
        }

        public static BoardSquares getSQUARE_3() {
            return SQUARE_3;
        }

        public static BoardSquares getSQUARE_4() {
            return SQUARE_4;
        }

        public static BoardSquares getSQUARE_5() {
            return SQUARE_5;
        }

        public static BoardSquares getSQUARE_6() {
            return SQUARE_6;
        }

        public static BoardSquares getSQUARE_7() {
            return SQUARE_7;
        }

        public static BoardSquares getSQUARE_8() {
            return SQUARE_8;
        }

        public static BoardSquares getSQUARE_9() {
            return SQUARE_9;
        }
        
        public static String getAllRows() {
            SQUARE_1.gameChar.
        }
        
    }*/
    public static int getRows() {
        return row_in;
    }

    public static void setRows(int rows) {
        row_in = rows;
    }

    public static int getCols() {
        return col_in;
    }

    public static void setCols(int cols) {
        col_in = cols;
    }

    public static char[][] getGrid() {
        return grid;
    }

    public static void setGrid(int row, int col, char c) { //starts from 1
        grid[row - 1][col - 1] = c;
        // Board.grid = grid;
    }

    char c = '\0';
}
