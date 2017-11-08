package ca.bcit.comp2526.a2;

import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * <p>
 * A tile on the chess board.
 * </p>
 * 
 * @author Piyotr Kao
 * @version 1.0
 */
public class Tile extends JPanel {
    /**
     * <p>
     * Auto-generated default id for this JPanel.
     * </p>
     */
    private static final long serialVersionUID = 3024930294032490L;
    private ChessPiece piece;
    private Color color;

    private JLabel label = new JLabel();
    private static Rectangle localRectangle;
    
    private static Image img;
    private static Image icon;
    
    protected String id = "";
    protected int rowNum;
    protected char colNum;
    
    private static int tileCount = 0;
    private static int rowCounter = 7;

    /**
     * <p>
     * The tile constructor that builds a tile and sets an id for the tile.
     * This tile does not have a piece on it to start with.
     * @param color
     *          The color of the tile.
     */
    public Tile(Color color) {
        this.color = color;
        this.setBackground(color);

        Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int i = (int) (1.0 / Chess.TILE_COUNT * Math.min(localDimension.width * 0.75, localDimension.height * 0.75));
        Rectangle localRectangle = new Rectangle();
        localRectangle.setBounds(((localDimension.width - i) / 2), ((localDimension.height - i) / 2), i, i);
        setBounds(localRectangle);

        setId();

        if (tileCount < Chess.TILE_COUNT - 1) {
            tileCount++;
        } else {
            tileCount = 0;
            rowCounter--;
        }
    }

    /**
     * <p>
     * A second tile constructor that takes in two parameters, one is the color and the other one
     * is the piece that is currently on the tile.
     * @param color
     *          The color of the tile.
     * @param p1
     *          The piece on the tile.
     */
    public Tile(Color color, ChessPiece p1) {
        piece = p1;

        this.color = color;
        this.setBackground(color);
        Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int i = (int) (1.0 / Chess.TILE_COUNT * Math.min(localDimension.width * 0.75, localDimension.height * 0.75));
        localRectangle = new Rectangle();
        localRectangle.setBounds(((localDimension.width - i) / 2), ((localDimension.height - i) / 2), i, i);
        setBounds(localRectangle);

        DrawImage(piece.getPath(), localRectangle.width - 20, localRectangle.height - 20);
        setId();

        if (tileCount < Chess.TILE_COUNT - 1) {
            tileCount++;
        } else {
            tileCount = 0;
            rowCounter--;
        }
    }

    /**
     * <p>
     * A method that draws the piece on the tile, from the path given, then resized to
     * fit the tile.
     * </p>
     * @param fileInput
     *          The path to the file as a string.
     * @param width
     *          The scaled width.
     * @param height
     *          The scaled height.
     */
    private void DrawImage(String fileInput, int width, int height) {
        try {
            this.getImage(fileInput);
            icon = img.getScaledInstance(width, height, 1);
            label.setIcon(new ImageIcon(icon));
        } catch (IOException e) {
            System.out.println("File not found!");
        } catch (NullPointerException e) {
            System.out.println("Null Pointer!");
        }
        this.add(label);
    }

    /**
     * <p>
     * A method that returns the Chess piece on the tile.
     * </p>
     * @return
     *      The piece on the tile.
     */
    public ChessPiece getPiece() {
        return piece;
    }

    /**
     * <p>
     * A method that decides displays whether or not there is a piece on the tile.
     * For debugging purposes mostly.
     * </p>
     * @return
     */
    public boolean pieceOnTile() {
        if (piece == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * <p>
     * Places desired piece on the current tile through a parameter.
     * After placing the piece, re-draws the tile to update the graphics.
     * </p>
     * @param placed
     *          The piece to be placed on the current tile.
     */
    public void PlacePiece(ChessPiece placed) {
        if (piece != null) {
            piece.setColumn(-1);
            piece.setRow(-1);
        }
        piece = placed;
        try {
            DrawImage(piece.getPath(), localRectangle.width - 20, localRectangle.height - 20);
        } catch (NullPointerException e) {
            /*Ignore error*/
        }
        this.validate();
        this.repaint();
    }
    
    /**
     * <p>
     * Places desired piece on the current tile through a parameter.
     * After placing the piece, re-draws the tile to update the graphics.
     * </p>
     * @param placed
     *          The piece to be placed on the current tile.
     */
    public void refreshPiece() {
        try {
            DrawImage(piece.getPath(), localRectangle.width - 20, localRectangle.height - 20);
        } catch (NullPointerException e) {
            /*Ignore error*/
        }
        this.validate();
        this.repaint();
    }

    /**
     * <p>
     * A paintComponent method that overrides the parent method.
     * </p>
     * 
     * @param g
     *          Graphics object.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    /**
     * <p>
     * Gets the current color of the tile.
     * </p>
     * @return
     *          The color of the tile.
     */
    public Color getColor() {
        return color;
    }

    /**
     * <p>
     * Sets the id of the current tile to the appropriate tile.
     * a8 to h1
     * </p>
     */
    private void setId() {
        id += "" + Chess.column[tileCount] + Chess.row[rowCounter];
        rowNum = Chess.row[rowCounter];
        colNum = Chess.column[tileCount];
    }

    /**
     * <p>
     * Returns the id of the current tile as a string.
     * </p>
     * @return
     *          The id of the tile.
     */
    public String getId() {
        return id;
    }

    /**
     * <p>
     * Removes the current piece on the tile.
     * </p>
     */
    public void removePiece() {
        piece = null;
        this.remove(label);
        this.validate();
        this.repaint();
    }

    /**
     * <p>
     * A method to get the image from a path.
     * If no image is found an error is thrown.
     * </p>
     * @param filePath
     *          The path to the image.
     * @throws IOException
     *          If there is no image found, this error is thrown.
     */
    private void getImage(String filePath) throws IOException {
        img = ImageIO.read(new File(filePath));
    }
    
    public int getIntCol() {
        return Math.abs(97 - this.colNum);
    }
    
    public int getIntRow() {
        return 8 - this.rowNum;
    }

}
