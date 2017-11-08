package ca.bcit.comp2526.a2;

/**
 * <p>
 * Bishop.
 * 
 * A Bishop that is used in the chess game.
 * </p>
 * 
 * @author Piyotr Kao
 * @version 1.0
 */
public class Bishop extends ChessPiece {

    /**
     * <p>
     * Unique serial id for saving.
     * </p>
     */
    private static final long serialVersionUID = -4987517504695655199L;

    private static final String b_path = "chessImage/B_Bishop.png";

    private static final String w_path = "chessImage/Bishop.png";

    private int alliance;

    /**
     * <p>
     * A constructor for the bishop. Takes in an integer for the alliance of the
     * bishop.
     * </p>
     * 
     * @param side
     *            The side the bishop is on.
     */
    public Bishop(int side, int row, int col) {
        super(row, col);
        alliance = side;
        System.out.println("Bishop created.");
    }

    /**
     * <p>
     * A method to move the bishop. Currently unused.
     * </p>
     */
    protected void move(Tile t1, Tile t2) {
        System.out.println("Bishop moved.");
    }

    /**
     * <p>
     * A method for the bishop to eat another piece. Currently unused.
     * </p>
     */
    protected void eat() {
        System.out.println("Bishop ate another piece.");
    }

    /**
     * <p>
     * A method that determines whether or not the bishop is dead. Currently
     * unused.
     * </p>
     */
    protected void die() {
        System.out.println("Bishop died.");
    }

    /**
     * <p>
     * A method to check whether or not the move that is about to be made is
     * valid.
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

        if (!checkIntercept(t1, t2)) {
            if (moveRow == moveColumn) {
                validity = true;
            } else {
                validity = false;
            }
        } else {
            validity = false;
        }

        if (tile2P != null) {
            if (tile2P.getAlliance() == alliance) {
                validity = false;
            } else {
                validity = true;
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
     * Returns the image path of the bishop.
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

    /**
     * <p>
     * Returns whether or not a piece is blocking the way of the piece.
     * </p>
     * 
     * @param t1
     *            Starting tile.
     * @param t2
     *            Destination tile.
     * @return Returns true if there is a piece blocking it and False if there
     *         is no piece block it.
     */
    protected boolean checkIntercept(Tile t1, Tile t2) {
        Tile temp;
        if (t1.rowNum > t2.rowNum) {
            if (t1.colNum > t2.colNum) {
                for (int i = 0; i < Chess.TILE_COUNT; i++) {
                    for (int j = 0; j < Chess.TILE_COUNT; j++) {
                        temp = ChessBoard.tiles[i][j];
                        if ((temp.rowNum < t1.rowNum && temp.rowNum > t2.rowNum)
                                && (temp.colNum < t1.colNum && temp.colNum > t2.colNum)
                                && (t1.rowNum - temp.rowNum == t1.colNum - temp.colNum)) {
                            if (temp.getPiece() != null) {
                                return true;
                            }
                        }
                    }
                }
            } else {
                for (int i = 0; i < Chess.TILE_COUNT; i++) {
                    for (int j = 0; j < Chess.TILE_COUNT; j++) {
                        temp = ChessBoard.tiles[i][j];
                        if ((temp.rowNum < t1.rowNum && temp.rowNum > t2.rowNum)
                                && (temp.colNum > t1.colNum && temp.colNum < t2.colNum)
                                && (t1.rowNum - temp.rowNum == t2.colNum - temp.colNum)) {
                            if (temp.getPiece() != null) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        } else if (t1.rowNum < t2.rowNum) {
            if (t1.colNum > t2.colNum) {
                for (int i = 0; i < Chess.TILE_COUNT; i++) {
                    for (int j = 0; j < Chess.TILE_COUNT; j++) {
                        temp = ChessBoard.tiles[i][j];
                        if ((temp.rowNum > t1.rowNum && temp.rowNum < t2.rowNum)
                                && (temp.colNum < t1.colNum && temp.colNum > t2.colNum)
                                && (t2.rowNum - temp.rowNum == t1.colNum - temp.colNum)) {
                            if (temp.getPiece() != null) {
                                return true;
                            }
                        }
                    }
                }
            } else {
                for (int i = 0; i < Chess.TILE_COUNT; i++) {
                    for (int j = 0; j < Chess.TILE_COUNT; j++) {
                        temp = ChessBoard.tiles[i][j];
                        if ((temp.rowNum > t1.rowNum && temp.rowNum < t2.rowNum)
                                && (temp.colNum > t1.colNum && temp.colNum < t2.colNum)
                                && (t2.rowNum - temp.rowNum == t2.colNum - temp.colNum)) {
                            if (temp.getPiece() != null) {
                                return true;
                            }
                        }
                    }
                }

            }
            return false;
        }
        return false;
    }
}