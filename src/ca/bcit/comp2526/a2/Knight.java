package ca.bcit.comp2526.a2;

/**
 * <p>
 * Knight.
 * 
 * A knight that is used in the chess game.
 * </p>
 * 
 * @author Piyotr Kao
 * @version 1.0
 */
public class Knight extends ChessPiece {
    
    /**
     * <p>
     * Unique serial id for this Class.
     * </p>
     */
    private static final long serialVersionUID = -1045750227581289784L;

    private static final String b_path = "chessImage/B_Knight.png";

    private static final String w_path = "chessImage/Knight.png";

    private int alliance;
    
    /**
     * <p>
     * A constructor for the knight. Takes in an integer for the alliance of the
     * knight.
     * </p>
     * 
     * @param side
     *            The side the knight is on.
     */
    public Knight(int side, int row, int col) {
        super(row, col);
        alliance = side;
        System.out.println("Knight created.");
    }

    /**
     * <p>
     * A method to move the knight. Currently unused.
     * </p>
     */
    protected void move(Tile t1, Tile t2) {
        System.out.println("Knight moved.");
    }

    /**
     * <p>
     * A method for the knight to eat another piece. Currently unused.
     * </p>
     */
    protected void eat() {
        System.out.println("Knight ate another piece.");
    }

    /**
     * <p>
     * A method that determines whether or not the knight is dead. Currently
     * unused.
     * </p>
     */
    protected void die() {
        System.out.println("Knight died.");
    }
    
    /**
     * <p>
     * A method to check whether or not the move that is about to be made is
     * valid. Currently unused.
     * </p>
     * 
     * @param t1
     *            The starting tile
     * @param t2
     *            The destination tile
     */
    protected boolean isValid(Tile t1, Tile t2) {
        boolean validity;
        int moveRow = Math.abs(t1.rowNum - t2.rowNum);
        int moveColumn = Math.abs(t1.colNum - t2.colNum);
        ChessPiece tile2P = t2.getPiece();
        
        if (moveRow == 2 && moveColumn == 1 ||
            moveRow == 1 && moveColumn == 2) {
            validity = true;
        } else {
            validity = false;
        }
        
        if (tile2P != null) {
            if (tile2P.getAlliance() == alliance) {
                validity = false;
            }
        }
        
        if (validity) {
            this.setRow(t2.getIntRow());
            this.setColumn(t2.getIntCol());
        }
        
        return validity;
    }
    
    /**
     * <p>
     * Returns the image path of the knight.
     * </p>
     */
    protected String getPath() {
        if (alliance == 0) {
            return b_path;
        } else {
            return w_path;
        }
    }
    
    /**
     * <p>
     * Returns the alliance that this piece is affiliated with.
     * </p>
     */
    protected int getAlliance() {
        return alliance;
    }
}