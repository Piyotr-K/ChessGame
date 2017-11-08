package ca.bcit.comp2526.a2;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * <p>
 * MoveListener.
 * 
 * A class that implements a MouseListener that detects the users' mouse events.
 * </p>
 * 
 * @author Lel
 * @version 1.0
 */
public class MoveListener implements MouseListener {
    
    private static ChessPiece selected;
    private Tile oldTile;
    private static int moving = 0;
    private static int store = 0;
    /**
     * <p>
     * Taking turns, setting this to 1 will begin a normal game of chess and
     * setting this to 2 will turn off turn taking.
     * 2 is mainly for debugging purposes.
     * </p>
     */
    protected static int turn = 1;
    
    protected static boolean beginning = false;
    /**
     * <p>
     * A method that detects a user's mouse click and then stores the tile into
     * a variable. On the second click, moves the piece over to the new tile and
     * then clears the old tile.
     * </p>
     * 
     * @param e
     *            A click event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        Tile pressedSquare = (Tile) e.getSource();
        boolean valid = true;

        getOldTile(pressedSquare);

        if (oldTile.getPiece() != null) {
            valid = oldTile.getPiece().isValid(oldTile, pressedSquare);

            if (oldTile.getPiece().getAlliance() == turn || turn == 2) {
                if (moving == 0) {
                    selected = pressedSquare.getPiece();
                    System.out.println("Tile from: " + pressedSquare.getId());
                    moving = 1;
                } else if (moving == 1 && valid) {
                    oldTile.removePiece();
                    pressedSquare.PlacePiece(selected);
                    System.out.println("Tile to: " + pressedSquare.getId());
                    oldTile.setBackground(oldTile.getColor());
                    pressedSquare.setBackground(pressedSquare.getColor());
                    moving = 0;
                    takeTurn();
                } else {
                    System.out.println("Tile to: " + pressedSquare.getId());
                    oldTile.setBackground(oldTile.getColor());
                    pressedSquare.setBackground(pressedSquare.getColor());
                    moving--;
                }
            } else {
                if (turn == 0) {
                    System.out.println("\nIt's black's turn!\n");
                    store = 0;
                    pressedSquare.setBackground(pressedSquare.getColor());
                } else {
                    System.out.println("\nIt's white's turn!\n");
                    store = 0;
                    pressedSquare.setBackground(pressedSquare.getColor());
                }
            }

        } else {
            System.out.println("No Piece found!");
            store = 0;
            pressedSquare.setBackground(pressedSquare.getColor());
        }
    }

    /**
     * <p>
     * A method that is purely for aesthetic purposes. Sets the background color
     * of the tile to green when the user clicks on the tile.
     * </p>
     */
    @Override
    public void mousePressed(MouseEvent e) {
        Tile pressedSquare = (Tile) e.getSource();
        //oldTileAesthetic(pressedSquare);
        pressedSquare.setBackground(Color.GREEN);
    }

    /**
     * <p>
     * A method that is purely for aesthetic purposes. Sets the background color
     * of the tile back to the original color when the user releases.
     * </p>
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        //oldTile.setBackground(oldTile.getColor());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    private void takeTurn() {
        if (turn == 2) {
            return;
        } else if (turn == 1) {
            turn = 0;
        } else {
            turn = 1;
        }
    }

    private void getOldTile(Tile t1) {
        if (store == 0) {
            oldTile = t1;
            store = 1;
        } else if (store == 1) {
            store = 0;
        }
    }
    
    protected void resetTurn() {
        turn = 1;
    }
    
    protected void setTurn(int turn) {
        MoveListener.turn = turn;
    }

}