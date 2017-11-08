package ca.bcit.comp2526.a2;

import java.io.Serializable;

/**
 * <p>
 * Class
 * 
 * Chess Pieces will implement these.
 * </p>
 * @author Piyotr Kao
 * @version 1.0
 * 
 */
public abstract class ChessPiece implements Serializable {
    
    private int row;
    private int column;
    
    public ChessPiece(int row, int column) {
        this.row = row;
        this.column = column;
    }
    
    public void setRow(int row) {
        this.row = row;
    }
    
    public void setColumn(int column) {
        this.column = column;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getColumn() {
        return column;
    }
    /**
     * <p>
     * A serial id for saving purposes.
     * </p>
     */
    private static final long serialVersionUID = 5502974172052698765L;

    /**
     * <p>
     * A move method for the chess piece.
     * </p>
     */
    protected abstract void move(Tile t1, Tile t2);
    
    /**
     * <p>
     * An eat method for the chess piece to eat another piece.
     * </p>
     */
    protected abstract void eat();
    
    /**
     * <p>
     * A method that kills the current chess piece.
     * </p>
     */
    protected abstract void die();
    
    /**
     * <p>
     * Detects whether or not a move is valid.
     * </p>
     */
    protected abstract boolean isValid(Tile t1, Tile t2);
    
    /**
     * <p>
     * Gets the path to the image of the file.
     * </p>
     * @return
     *       Returns the path as a string.
     */
    protected abstract String getPath();
    
    /**
     * <p>
     * Returns the alliance that the piece is affiliated with.
     * </p>
     * @return
     *      0 for black
     *      1 for white
     */
    protected abstract int getAlliance();
}
