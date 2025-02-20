/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author kamus
 */
import javax.swing.*;
import java.awt.*;

public class ExercisesView extends JFrame {
    private JLabel messageLabel;
    private boolean isLoading;
    public ExercisesView() {
        messageLabel=new JLabel("Selecciona un ejercicio para comenzar.");
        setTitle("Chess Exercises");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLayout(new BorderLayout(10, 10));

        // Panel del tablero de ajedrez
        ChessBoard chessBoard =new ChessBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",this);
        add(chessBoard, BorderLayout.EAST);
        

        // Panel para elegir ejercicios
        JPanel exercisesPanel = new JPanel();
        exercisesPanel.setLayout(new GridLayout(3, 1, 5, 5));
        exercisesPanel.setBorder(BorderFactory.createTitledBorder("Ejercicios"));
        exercisesPanel.add(new JButton("Tácticas"));
        exercisesPanel.add(new JButton("Finales"));
        exercisesPanel.add(new JButton("Problemas de mate"));
        add(exercisesPanel, BorderLayout.WEST);

        // Panel de salida de mensajes
        JPanel outputPanel = new JPanel();
        outputPanel.setBorder(BorderFactory.createTitledBorder("Mensajes"));
        outputPanel.setPreferredSize(new Dimension(0, 50));
        outputPanel.add(messageLabel);
        add(outputPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // Centra la ventana
        setVisible(true);
    }
    public void showMessage(String outPut){
        messageLabel.setText(outPut);
    }
    public void showLoading(){
        isLoading=true;
        new Thread(() -> startLoadingEffect()).start();
    
    }
    public void endLoading(){
        isLoading=false;
    }
    private void startLoadingEffect() {
        String baseText = "Cargando";
        int dots = 0;

        while (isLoading) {
            try {
                dots = (dots + 1) % 4; // Alterna entre 0, 1, 2, 3 puntos
                String text = baseText + ".".repeat(dots);
                messageLabel.setText(text);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
