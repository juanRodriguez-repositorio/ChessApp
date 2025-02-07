/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package view;

/**
 *
 * @author kamus
 */
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Square;

public class ChessBoard extends JPanel {
    private Board board;
    private Map<Piece, Image> pieceImages; // Mapa para almacenar imágenes de las piezas

    public ChessBoard(String fen) {
        board = new Board();
        board.loadFromFen(fen);
        loadPieceImages(); // Cargar las imágenes
    }

    private void loadPieceImages() {
        pieceImages = new HashMap<>();
        String[] pieceNames = {"white_Pawn", "white_Knight", "white_Bishop", "white_Rook", "white_Queen", "white_King",
                               "black_Pawn", "black_Knight", "black_Bishop", "black_Rook", "black_Queen", "black_King"};
        for (String name : pieceNames) {
            try {
                Image img = new ImageIcon(getClass().getResource("/resources/pieces/" + name + ".png")).getImage();
                 Piece piece = Piece.valueOf(name.toUpperCase().replace("white", "WHITE").replace("black", "BLACK"));
                pieceImages.put(piece, img);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("No se pudo cargar la imagen de " + name);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int tileSize = getWidth() / 8;

        // Dibujar tablero
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                boolean isWhite = (rank + file) % 2 == 0;
                g.setColor(isWhite ? Color.WHITE : Color.GRAY);
                g.fillRect(file * tileSize, rank * tileSize, tileSize, tileSize);

                // Dibujar pieza
                Square square = Square.values()[8 * (7 - rank) + file]; // Tablero en perspectiva estándar
                Piece piece = board.getPiece(square);
                if (!piece.equals(Piece.NONE) && pieceImages.containsKey(piece)) {
                    g.drawImage(pieceImages.get(piece), file * tileSize, rank * tileSize, tileSize, tileSize, this);
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ajedrez en Java con Iconos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        String startingFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        ChessBoard panel = new ChessBoard(startingFEN);
        frame.add(panel);
        frame.setVisible(true);
    }
}