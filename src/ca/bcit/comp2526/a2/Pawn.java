package ca.bcit.comp2526.a2;

/**
 * <p>
 * Pawn.
 * 
 * A pawn that is used in the chess game.
 * </p>
 * 
 * @author Piyotr Kao
 * @version 1.0
 */
public class Pawn extends ChessPiece {

    /**
     * <p>
     * A serial id for saving purposes.
     * </p>
     */
    private static final long serialVersionUID = -3858405519733548681L;

    private static final String b_path = "chessImage/B_Pawn.png";

    private static final String w_path = "chessImage/Pawn.png";
    
    private int alliance;
    
    private int firstMove = 0;
    
    /**
     * <p>
     * A constructor for the pawn. Takes in an integer for the alliance of the
     * pawn.
     * </p>
     * 
     * @param side
     *            The side the pawn is on.
     */
    public Pawn(int side, int row, int col) {
        super(row, col);
        alliance = side;
        this.firstMove = 0;
        System.out.println("Pawn created.");
    }

    /**
     * <p>
     * A method to move the pawn. Currently unused.
     * </p>
     */
    protected void move(Tile t1, Tile t2) {
        System.out.println("Pawn moved.");
    }

    /**
     * <p>
     * A method for the pawn to eat another piece. Currently unused.
     * </p>
     */
    protected void eat() {
        System.out.println("Pawn ate another piece.");
    }

    /**
     * <p>
     * A method that determines whether or not the pawn is dead. Currently
     * unused.
     * </p>
     */
    protected void die() {
        System.out.println("Pawn died.");
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
        int moveRow = (t1.rowNum - t2.rowNum);
        int moveColumn = (t1.colNum - t2.colNum);
        ChessPiece tile2P = t2.getPiece();
        
        if (firstMove == 0) {
            
            if (Math.abs(moveRow) > 0 && Math.abs(moveRow) <= 2 && Math.abs(moveColumn) == 0) {
                firstMove = 1;
                validity = true;
            } else {
                validity = false;
            }
            
        } else {
            
            if (alliance == 0) {
                if (moveRow == 1 && moveColumn == 0) {
                    validity = true;
                } else {
                    validity = false;
                }
            } else {
                if (moveRow == -1 && moveColumn == 0) {
                    validity = true;
                } else {
                    validity = false;
                }
            }
        }
        
        if (tile2P != null) {
            if (tile2P.getAlliance() == alliance) {
                validity = false;
            } else if (Math.abs(moveColumn) == 1 && Math.abs(moveRow) == 1){
                validity = true;
            } else if (Math.abs(moveColumn) == 0 && Math.abs(moveRow) == 1) {
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
     * Returns the image path of the pawn.
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
    
    protected int getMove() {
        return firstMove;
    }
    
    protected void setFirst(int move) {
        this.firstMove = move;
    }
}
