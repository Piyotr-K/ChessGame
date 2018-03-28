package ca.bcit.comp2526.a2;

/**
 * <p>
 * King.
 * 
 * A King that is used in the chess game.
 * </p>
 * 
 * @author Piyotr Kao
 * @version 1.0
 */
public class King extends ChessPiece {

    /**
     * <p>
     * A serial id for saving purposes.
     * </p>
     */
    private static final long serialVersionUID = 9115776966479280794L;

    static final String b_path = "chessImage/B_King.png";

    static final String w_path = "chessImage/King.png";
    
    private int alliance;

    /**
     * <p>
     * A constructor for the king. Takes in an integer for the alliance of the
     * king.
     * </p>
     * 
     * @param side
     *            The side the king is on.
     */
    public King(int side, int row, int col) {
        super(row, col);
        alliance = side;
        System.out.println("King created.");
    }

    /**
     * <p>
     * A method to move the king. Currently unused.
     * </p>
     */
    protected void move(Tile t1, Tile t2) {
        System.out.println("King moved.");
    }

    /**
     * <p>
     * A method for the king to eat another piece. Currently unused.
     * </p>
     */
    protected void eat() {
        System.out.println("King ate another piece.");
    }

    /**
     * <p>
     * A method that determines whether or not the king is dead. Currently
     * unused.
     * </p>
     */
    protected void die() {
        System.out.println("King died.");
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
        boolean validity = false;
        int moveRow = Math.abs(t1.rowNum - t2.rowNum);
        int moveColumn = Math.abs(t1.colNum - t2.colNum);
        ChessPiece tile2P = t2.getPiece();
        
        if (moveRow == 1 && moveColumn == 1 ||
                moveRow == 1 || moveColumn == 1) {
            validity = true;
        }
        
        if (tile2P != null) {
            if (tile2P.getAlliance() == alliance) {
                validity = false;
            } else {
                this.eat();
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
     * Returns the image path of the king.
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