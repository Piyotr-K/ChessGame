package ca.bcit.comp2526.a2;

/**
 * <p>
 * Queen.
 * 
 * A Queen that is used in the chess game.
 * </p>
 * 
 * @author Piyotr Kao
 * @version 1.0
 */
public class Queen extends ChessPiece {

    /**
     * <p>
     * A serial id for saving purposes.
     * </p>
     */
    private static final long serialVersionUID = -176991172191067184L;

    private static final String b_path = "chessImage/B_Queen.png";

    private static final String w_path = "chessImage/Queen.png";
    
    private int alliance;
    
    /**
     * <p>
     * A constructor for the Queen. Takes in an integer for the alliance of the
     * Queen.
     * </p>
     * 
     * @param side
     *            The side the Queen is on.
     */
    public Queen(int side, int row, int col) {
        super(row, col);
        alliance = side;
        System.out.println("Queen created.");
    }

    /**
     * <p>
     * A method to move the Queen. Currently unused.
     * </p>
     */
    protected void move(Tile t1, Tile t2) {
        System.out.println("Queen moved.");
    }

    /**
     * <p>
     * A method for the Queen to eat another piece. Currently unused.
     * </p>
     */
    protected void eat() {
        System.out.println("Queen ate another piece.");
    }

    /**
     * <p>
     * A method that determines whether or not the Queen is dead. Currently
     * unused.
     * </p>
     */
    protected void die() {
        System.out.println("Queen died.");
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
        
        if (!checkIntercept(t1, t2)) {
            if (moveRow == moveColumn ||
                moveRow >= 0 && moveColumn == 0 ||
                moveRow == 0 && moveColumn >= 0) {
                validity = true;
            } else {
                validity = false;
            }
            
            if (tile2P != null) {
                if (tile2P.getAlliance() == alliance) {
                    validity = false;
                }
            }
        } else {
            validity = false;
        }
        
        if (validity) {
            this.setRow(t2.getIntRow());
            this.setColumn(t2.getIntCol());
        }
        
        return validity;
    }
    
    /**
     * <p>
     * Returns the image path of the Queen.
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
        if (t1.rowNum == t2.rowNum) {
            if (t1.colNum > t2.colNum) {
                for (int i = 0; i < Chess.TILE_COUNT; i++) {
                    for (int j = 0; j < Chess.TILE_COUNT; j++) {
                        temp = ChessBoard.tiles[i][j];
                        if (temp.colNum > t2.colNum
                                && temp.colNum < t1.colNum 
                                && temp.rowNum == t1.rowNum) {
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
                        if (temp.colNum < t2.colNum
                                && temp.colNum > t1.colNum
                                && temp.rowNum == t1.rowNum) {
                            if (temp.getPiece() != null) {
                                return true;
                            }
                        }
                    }
                }

            }
            return false;
        } else if (t1.colNum == t2.colNum) {
            if (t1.rowNum > t2.rowNum) {
                for (int i = 0; i < Chess.TILE_COUNT; i++) {
                    for (int j = 0; j < Chess.TILE_COUNT; j++) {
                        temp = ChessBoard.tiles[i][j];
                        if (temp.rowNum > t2.rowNum
                                && temp.rowNum < t1.rowNum
                                && temp.colNum == t1.colNum) {
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
                        if (temp.rowNum < t2.rowNum
                                && temp.rowNum > t1.rowNum
                                && temp.colNum == t1.colNum) {
                            if (temp.getPiece() != null) {
                                return true;
                            }
                        }
                    }
                }

            }
            return false;
        } else if (t1.rowNum > t2.rowNum) {
            if (t1.colNum > t2.colNum) {
                for (int i = 0; i < Chess.TILE_COUNT; i++) {
                    for (int j = 0; j < Chess.TILE_COUNT; j++) {
                        temp = ChessBoard.tiles[i][j];
                        // Checks for diagonal obstacles.
                        if ((temp.rowNum < t1.rowNum && temp.rowNum > t2.rowNum)
                                && (temp.colNum < t1.colNum && temp.colNum > t2.colNum)
                                && (t1.rowNum - temp.rowNum == t1.colNum - temp.colNum)) {
                            if (temp.getPiece() != null) {
                                return true;
                            }
                        }
                        // Checks for straight line obstacles.
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