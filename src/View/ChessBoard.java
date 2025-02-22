/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package View;

/**
 *
 * @author kamus
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.move.MoveException;
import ModelView.ChessExercisesController;
import java.text.ParseException;
import java.util.ArrayList;

public class ChessBoard extends JPanel implements MouseListener, MouseMotionListener {
    private Board board;
    private Map<Piece, Image> pieceImages;
    private int tileSize;
    private Square selectedSquare = null;  // Casilla de la pieza seleccionada
    private Image draggedPieceImage = null;
    private Point draggedPiecePosition = new Point(0, 0); // Posición de la imagen arrastrada
    private ExercisesView containerView;
    private boolean isProcessing;
    private boolean isGettingBackInPosition=false;
    private ArrayList<String> fenHistoral;
    private int fenHistoralIndex=0;
    private String fenBefore;
    private String fenAfter;
    
    
    public ChessBoard(String fen,ExercisesView container) {
        containerView=container;
        board = new Board();
        board.loadFromFen(fen);
        loadPieceImages();
        fenHistoral=new ArrayList<>();
        fenHistoral.add(fen);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    private void loadPieceImages() {
        pieceImages = new HashMap<>();
        String[] pieceNames = {"white_Pawn", "white_Knight", "white_Bishop", "white_Rook", "white_Queen", "white_King",
                               "black_Pawn", "black_Knight", "black_Bishop", "black_Rook", "black_Queen", "black_King"};
        for (String name : pieceNames) {
            try {
                Image img = new ImageIcon(getClass().getResource("/resources/pieces/" + name + ".png")).getImage();
                Piece piece = Piece.valueOf(name.toUpperCase().replace("WHITE", "WHITE").replace("BLACK", "BLACK"));
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
        tileSize = getWidth() / 8;

        // Dibujar tablero
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                boolean isWhite = (rank + file) % 2 == 0;
                g.setColor(isWhite ? Color.WHITE : Color.GRAY);
                g.fillRect(file * tileSize, rank * tileSize, tileSize, tileSize);

                Square square = Square.values()[8 * (7 - rank) + file]; // Perspectiva estándar
                Piece piece = board.getPiece(square);

                // Dibujar piezas (excepto la arrastrada)
                if (!piece.equals(Piece.NONE) && pieceImages.containsKey(piece)) {
                    if (selectedSquare == null || !selectedSquare.equals(square)) {
                        g.drawImage(pieceImages.get(piece), file * tileSize, rank * tileSize, tileSize, tileSize, this);
                    }
                }
            }
        }

        // Dibujar pieza arrastrada
        if (draggedPieceImage != null) {
            g.drawImage(draggedPieceImage, draggedPiecePosition.x - tileSize / 2, draggedPiecePosition.y - tileSize / 2, tileSize, tileSize, this);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int file = e.getX() / tileSize;
        int rank = e.getY() / tileSize;
        selectedSquare = Square.values()[8 * (7 - rank) + file];

        Piece piece = board.getPiece(selectedSquare);
        if (!piece.equals(Piece.NONE)) {
            draggedPieceImage = pieceImages.get(piece);
            draggedPiecePosition.setLocation(e.getX(), e.getY());
            repaint();
        } else {
            selectedSquare = null;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (draggedPieceImage != null) {
            draggedPiecePosition.setLocation(e.getX(), e.getY());
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        String whoMove=board.getSideToMove().toString();
        if (selectedSquare != null && draggedPieceImage != null) {
            int file = e.getX() / tileSize;
            int rank = e.getY() / tileSize;
            Square targetSquare = Square.values()[8 * (7 - rank) + file];

            Move move = new Move(selectedSquare, targetSquare);
            System.out.println(selectedSquare);
            System.out.println(targetSquare);
            System.out.println(board.legalMoves());
            System.out.println(selectedSquare.toString().toLowerCase());
            doMove(move,selectedSquare.toString(),targetSquare.toString(),whoMove);
        }

        selectedSquare = null;
        draggedPieceImage = null;
        repaint();
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400); // Tamaño recomendado
    }
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}
    
    public boolean verifyMove(String fromSquare, String toSquare) {
    String moveString = fromSquare + toSquare; // Crear el movimiento en formato "e2e4"
    moveString=moveString.toLowerCase().trim();
    for (Move move : board.legalMoves()) { // Recorrer la lista de movimientos legales
        if (move.toString().equals(moveString)) { // Comparar con el movimiento dado
            return true; // Movimiento válido
        }
    }
    return false; // Movimiento ilegal
    }
    public void updateBoard(String fen,boolean reset){
        board.loadFromFen(fen);
        if(reset){
            fenHistoral.clear();
            fenHistoral.add(fen);
            fenHistoralIndex=0;
        }
        repaint();
    }
    public void setGettingBackInPosition(boolean isIt){
        isGettingBackInPosition=isIt;
    }
    public void goBack(){
        System.out.println("aquii");
        System.out.println(fenHistoralIndex);
        if(fenHistoralIndex!=0 && isGettingBackInPosition==false && isProcessing==false){
            System.out.println("tambien aqui");
            System.out.println(fenHistoralIndex);
            fenHistoralIndex--;
            SwingUtilities.invokeLater(() -> {
                updateBoard(fenHistoral.get(fenHistoralIndex),false); // Primera acción
                deleteLastInHistoral();// Segunda acción
                System.out.println("Movimiento revertido"); // Tercera acción
});
            
        }
         
    }
    private void doMove(Move move,String fromSquare,String toSquare,String whoMove){
        fenBefore=board.getFen().trim();
        if (board.isMoveLegal(move, true) && verifyMove(fromSquare,toSquare) && isProcessing==false && isGettingBackInPosition==false) {
                
                try {
                    board.doMove(move);
                    fenAfter=board.getFen().trim();
                    fenHistoral.add(fenAfter);
                    fenHistoralIndex++;
                    System.out.println("suma  uno "+fenHistoralIndex);
                    System.out.println(board.getSideToMove().toString());
                     // Ejecutar Stockfish en segundo plano
                    isProcessing=true;
                    new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            containerView.showLoading();
                            ChessExercisesController.EvaluatePosition(fenBefore, fenAfter,whoMove,containerView);
                            return null;
                        }

                        @Override
                        protected void done() {
                            // Redibujar para limpiar la imagen colgada
                            isProcessing=false;
                            repaint();
                        }
                    }.execute();
                } catch (MoveException ex) {
                    System.err.println("Movimiento inválido: " + ex.getMessage());
                }
            }
    
        
    
    }
    public void doEngineMove(String move){
        System.out.println(move);
        Square fromSquare=Square.valueOf(move.substring(0,2).toUpperCase());
        Square toSquare=Square.valueOf(move.substring(2,4).toUpperCase());
        Move moveEngine=new Move(fromSquare,toSquare);
        String whoMove=board.getSideToMove().toString();
        doMove(moveEngine,fromSquare.toString(),toSquare.toString(),whoMove);
        SwingUtilities.invokeLater(()-> repaint());
        
    }
    public void deleteLastInHistoral(){
        if (!fenHistoral.isEmpty()) {
            System.out.println("no esta vacio");
            fenHistoral.remove(fenHistoral.size() - 1);// Elimina el último elemento
            fenHistoralIndex--;
        }
    }


}