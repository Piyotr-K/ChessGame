package ca.bcit.comp2526.a2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * <p>
 * The chess board where the chess pieces will be.
 * </p>
 * 
 * @author Piyotr Kao
 * @version 1.0
 */
public class ChessBoard extends JFrame {

    private static final long serialVersionUID = 7042601066591539414L;
    
    protected static Tile[][] tiles;
    
    private ChessPiece[][] pieces;
    
    private static MoveListener moveListener;
    
    private static final int BLACK = 0;
    
    private static final int WHITE = 1;
    
    private final JMenuBar menuBar;
    
    private final JMenu fileMenu;
    
    private static Save saveBtn;
    
    private static Load loadBtn;
    
    private final Reset resetBtn;
    
    /**
     * <p>
     * Chess Board constructor that creates the chess board.
     * </p>
     * @param name
     *        The title of the game window.
     */
    public ChessBoard(String name) {
        super(name);
        tiles = new Tile[Chess.TILE_COUNT][Chess.TILE_COUNT];
        pieces = new ChessPiece[4][Chess.TILE_COUNT];        // [4][8]
        moveListener = new MoveListener();
        this.setLayout(new GridLayout(Chess.TILE_COUNT, Chess.TILE_COUNT));
        createPieces();
        createBoard();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        saveBtn = new Save();
        
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        menuBar.add(fileMenu);
        
        JMenuItem menuItem1 = new JMenuItem("Save");
        menuItem1.setMnemonic('S');
        menuItem1.addActionListener(saveBtn);
        
        loadBtn = new Load();
        
        JMenuItem menuItem2 = new JMenuItem("Load");
        menuItem2.setMnemonic('L');
        menuItem2.addActionListener(loadBtn);
        
        resetBtn = new Reset();
        
        JMenuItem menuItem3 = new JMenuItem("Reset");
        menuItem3.setMnemonic('R');
        menuItem3.addActionListener(resetBtn);
        
        fileMenu.add(menuItem3);
        fileMenu.add(menuItem1);
        fileMenu.add(menuItem2);
        
        this.setJMenuBar(menuBar);
        
        this.pack();
        this.onScreen();
        this.setVisible(true);
    }
    
    /**
     * <p>
     * A method that creates the chess board, has two loops that create the tiles necessary
     * and adds the necessary pieces to the board.
     * </p>
     */
    public void createBoard() {
        // An integer to keep track of which part of the array to place.
        int placedRows = 0;
        
        for (int i = 0; i < Chess.TILE_COUNT; ++i) {
            for (int j = 0; j < Chess.TILE_COUNT; ++j) {
                if ((i + j) % 2 == 0) {
                    if ((i ==  0) || (i == 7)) {
                        // Place the royalty.
                        tiles[i][j] = new Tile(Color.decode("#926702"), pieces[placedRows][j]);
                        this.add(tiles[i][j]);
                    } else if ((i == 1) || (i == 6)){
                        // Place Pawns.
                        tiles[i][j] = new Tile(Color.decode("#926702"), pieces[placedRows][j]);
                        this.add(tiles[i][j]);
                    } else {
                        // Place empty tile.
                        tiles[i][j] = new Tile(Color.decode("#926702"));
                        this.add(tiles[i][j]);
                    }
                } else {
                    if ((i ==  0) || (i == 7)) {
                        // Place the royalty.
                        tiles[i][j] = new Tile(Color.decode("#FADE9C"), pieces[placedRows][j]);
                        this.add(tiles[i][j]);
                    } else if ((i == 1) || (i == 6)) {
                        // Place Pawns.
                        tiles[i][j] = new Tile(Color.decode("#FADE9C"), pieces[placedRows][j]);
                        this.add(tiles[i][j]);
                    } else {
                        // Place empty tile.
                        tiles[i][j] = new Tile(Color.decode("#FADE9C"));
                        this.add(tiles[i][j]);
                    }
                }
                tiles[i][j].addMouseListener(moveListener);
            }
            
            if (i == 0) {
                placedRows++;
            } else if (i == 5) {
                placedRows += 2;
            } else if (i == 6) {
                placedRows--;
            }
        }
    }
    
    /**
     * <p>
     * A method to determine where the chess board shows up on screen.
     * </p>
     */
    private void onScreen() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int i = (int) Math.min(dimension.width * 0.75, dimension.height * 0.75);
        Rectangle rectangle = new Rectangle();
        rectangle.width = i;
        rectangle.height = i;
        rectangle.x = (dimension.width - i) / 2;
        rectangle.y = (dimension.height - i) / 2;
        this.setBounds(rectangle);
    }
    
    /**
     * <p>
     * Creates the pieces that are used in the chess game.
     * Creates the set of black royalty pieces first, then the black pawns,
     * the creates the set of white royalty pieces and the white pawns.
     * </p>
     */
    private void createPieces() {
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                for (int o = 0; o < Chess.TILE_COUNT; ++o) {
                    switch(o) {
                        case 0: pieces[i][o] = new Rook(BLACK, i, o);
                        break;
                        case 1: pieces[i][o] = new Knight(BLACK, i, o);
                        break;
                        case 2: pieces[i][o] = new Bishop(BLACK, i, o);
                        break;
                        case 3: pieces[i][o] = new Queen(BLACK, i, o);
                        break;
                        case 4: pieces[i][o] = new King(BLACK, i, o);
                        break;
                        case 5: pieces[i][o] = new Bishop(BLACK, i, o);
                        break;
                        case 6: pieces[i][o] = new Knight(BLACK, i, o);
                        break;
                        case 7: pieces[i][o] = new Rook(BLACK, i, o);
                    }
                }
            } else if (i == 1){
                for (int j = 0; j < Chess.TILE_COUNT; ++j) {
                    pieces[i][j] = new Pawn(BLACK, i, j);
                }
            } else if (i == 2) {
                for (int o = 0; o < Chess.TILE_COUNT; ++o) {
                    switch(o) {
                        case 0: pieces[i][o] = new Rook(WHITE, 7, o);
                        break;
                        case 1: pieces[i][o] = new Knight(WHITE, 7, o);
                        break;
                        case 2: pieces[i][o] = new Bishop(WHITE, 7, o);
                        break;
                        case 3: pieces[i][o] = new Queen(WHITE, 7, o);
                        break;
                        case 4: pieces[i][o] = new King(WHITE, 7, o);
                        break;
                        case 5: pieces[i][o] = new Bishop(WHITE, 7, o);
                        break;
                        case 6: pieces[i][o] = new Knight(WHITE, 7, o);
                        break;
                        case 7: pieces[i][o] = new Rook(WHITE, 7, o);
                    }
                }
            } else {
                for (int j = 0; j < Chess.TILE_COUNT; ++j) {
                    pieces[i][j] = new Pawn(WHITE, 6, j);
                }
            }
        }
    }
    
    private void clearBoard() {
        for (int i = 0; i < Chess.TILE_COUNT; i++) {
            for (int j = 0; j < Chess.TILE_COUNT; j++) {
                tiles[i][j].removePiece();
            }
        }
    }
    
    private void resetPieces() {
        int placedRows = 0;
        
        for (int i = 0; i < Chess.TILE_COUNT; i++) {
            for (int j = 0; j < Chess.TILE_COUNT; j++) {
                if (i == 0 || i == 7) {
                    tiles[i][j].PlacePiece(pieces[placedRows][j]);
                    pieces[placedRows][j].setRow(i);
                    pieces[placedRows][j].setColumn(j);
                } else if (i == 1 || i == 6) {
                    tiles[i][j].PlacePiece(pieces[placedRows][j]);
                    pieces[placedRows][j].setRow(i);
                    pieces[placedRows][j].setColumn(j);
                }
            }
            if (i == 0) {
                placedRows++;
            } else if (i == 5) {
                placedRows += 2;
            } else if (i == 6) {
                placedRows--;
            }
        }
        
    }
    
    private void placePieces() {
        clearBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j].getColumn() < 0) {
                    /* Do Nothing */
                } else {
                    tiles[pieces[i][j].getRow()][pieces[i][j].getColumn()].PlacePiece(pieces[i][j]);
                }
            }
        }
    }
    
    private class Save implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evnt) {
            try {
                FileOutputStream fileOut = new FileOutputStream("Chess.gam");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 8; j++) {
                        out.writeObject(pieces[i][j]);
                    }
                }
                out.writeInt(MoveListener.turn);
                out.close();
                System.out.println("File saved.");
            } catch (IOException err) {
                System.out.println("Error: " + err.getMessage());
            }
        }

    }
    
    private class Load implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent evnt) {
            String filePath = openFileDialog();
            if (filePath.equals("")) {
                System.out.println("No file found.");
                return;
            } else {
                System.out.println("The file path is: " + filePath);
            }
            
            try {
                FileInputStream fileIn = new FileInputStream(filePath);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 8; j++) {
                        pieces[i][j] = (ChessPiece) in.readObject();
                    }
                }
                moveListener.setTurn(in.readInt());
                in.close();
                fileIn.close();
                placePieces();
                System.out.println("Game loaded.");
            } catch (IOException i) {
                System.out.println(i.toString());
            } catch (ClassNotFoundException c) {
                System.out.println(c.toString());
            }
        }
        
        private String openFileDialog() {
            String filePath = "";
            FileDialog fd = new FileDialog(Chess.gameBoard, "Choose a file: ", FileDialog.LOAD);
            fd.setFile("*.gam");
            fd.setVisible(true);
            if (fd.getFile() != null) {
                filePath = fd.getDirectory() + File.separator + fd.getFile();
                System.out.println("Choice cancelled.");
            }
            return filePath;
        }
    }
    
    private class Reset implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evnt) {
            clearBoard();
            resetPieces();
            resetPawn();
            ChessBoard.moveListener.resetTurn();
            System.out.println("Board reset.");
        }
    }
    
    private void resetPawn() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < Chess.TILE_COUNT; j++) {
                if (pieces[i][j] instanceof Pawn) {
                    ((Pawn)pieces[i][j]).setFirst(0);
                }
            }
        }
    }
}
