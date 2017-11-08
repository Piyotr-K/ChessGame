package ca.bcit.comp2526.a2;

/**
 * <p>
 * Chess.
 * 
 * The chess class that creates the rest of the game.
 * </p>
 * 
 * @author Piyotr Kao
 * @version 1.0
 */
public class Chess {

    protected static final int row[] = {1, 2, 3, 4, 5, 6, 7, 8};
    
    protected static final char column[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

    /**
     * <p>
     * The ChessBoard that the game takes place on.
     * </p>
     */
    protected static ChessBoard gameBoard;

    /**
     * <p>
     * The total number of tiles in a column and a row.
     * </p>
     */
    public static final int TILE_COUNT = 8;
    
    /**
     * <p>
     * The chess game object.
     * </p>
     */
    protected static Chess game;

    /**
     * <p>
     * The constructor for a new chess object that creates the game.
     * </p>
     */
    public Chess() {
        gameBoard = new ChessBoard("Piyotr Kao A00996404 Chess Game");
    }

    /**
     * <p>
     * The main method of the entire program.
     * </p>
     * 
     * @param args
     *            unused.
     */
    public static void main(String[] args) {
        game = new Chess();
    }
    
}
