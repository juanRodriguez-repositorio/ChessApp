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
import java.awt.event.*;
import ModelView.ChessExercisesController;
import Model.ChessExercises;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ExercisesView extends JFrame {
    private JLabel messageLabel;
    private boolean isLoading;
    private JList<String> gamesList;
    private DefaultListModel<String> listModel;
    private JButton tacticsButton;
    private JButton finalsButton;
    private JButton matesButton;
    private ChessExercises[] exercises;
    private ChessBoard chessBoard;
    private boolean isGettingBack;
    private JButton backButton, machineMoveButton,resetButton,nextButton; 
    private ChessExercises exerciseSelected;
    private int index;
    
    
    

    public ExercisesView() {
        messageLabel = new JLabel("Selecciona una partida para comenzar.");
        setTitle("Chess Exercises");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLayout(new BorderLayout(10, 10));

        // Panel del tablero de ajedrez
        chessBoard = new ChessBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", this);
        
        
        //botones de ejercicios
        tacticsButton=new JButton("Tacticas");
        finalsButton=new JButton("Finales");
        matesButton=new JButton("Mates");
        backButton=new JButton("Atras");
        machineMoveButton=new JButton("Jugada de la maquina");
        resetButton=new JButton("Reiniciar");
        nextButton=new JButton("Siguente");
        
        resetButton.setEnabled(false);
        nextButton.setEnabled(false);
        //eventos a los botones
        
        tacticsButton.addActionListener(e -> loadExercises(0));
        finalsButton.addActionListener(e -> loadExercises(1));
        matesButton.addActionListener(e -> loadExercises(2));
        backButton.addActionListener(e -> goBackInHistoral());
        machineMoveButton.addActionListener(e -> doEngineMove());
        resetButton.addActionListener(e -> resetExercise());
        nextButton.addActionListener(e -> nextExercise());
        machineMoveButton.setEnabled(false);
        
        // Modelo de lista y lista con scroll
        listModel = new DefaultListModel<>();
        gamesList = new JList<>(listModel);
        gamesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Agregar juegos de ejemplo
        listModel.addElement("Partida 1 - Defensa Siciliana");
        listModel.addElement("Partida 2 - Gambito de Dama");
        listModel.addElement("Partida 3 - Defensa Francesa");
        listModel.addElement("Partida 4 - Apertura Española");
        listModel.addElement("Partida 5 - Defensa Caro-Kann");

        // Evento de clic en la lista
        gamesList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Doble clic
                    index = gamesList.getSelectedIndex();
                    exerciseSelected=exercises[index];
                    chessBoard.updateBoard(exerciseSelected.getFen(),true);
                    ChessExercisesController.setStepsInExercise(exerciseSelected.getMovesForward(),chessBoard.getSideOfUser());
                    setDisabledMoveMachine();
                    resetButton.setEnabled(true);
                    nextButton.setEnabled(false);
                    if (index != -1) {
                        showMessage("Vamos! "+exerciseSelected.getAim());
                    }
                }
            }
        });

        // Panel izquierdo con lista y botones
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(gamesList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Partidas"));
        leftPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel de botones debajo de la lista
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 5, 5));
        buttonPanel.add(tacticsButton);
        buttonPanel.add(finalsButton);
        buttonPanel.add(matesButton);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(leftPanel, BorderLayout.WEST);
        
        //panel del chessBoard y los botones de atras y jugada de la maquina
        
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        controlPanel.add(backButton);
        controlPanel.add(machineMoveButton);
        controlPanel.add(resetButton);
        controlPanel.add(nextButton);
        
        JPanel chessPanel = new JPanel(new BorderLayout());
        chessPanel.add(chessBoard, BorderLayout.CENTER);
        chessPanel.add(controlPanel, BorderLayout.SOUTH);
        add(chessPanel, BorderLayout.EAST);
        // Panel de salida de mensajes
        JPanel outputPanel = new JPanel();
        outputPanel.setBorder(BorderFactory.createTitledBorder("Mensajes"));
        outputPanel.setPreferredSize(new Dimension(0, 50));
        outputPanel.add(messageLabel);
        add(outputPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // Centra la ventana
        setVisible(true);
    }

    public void showMessage(String outPut) {
        messageLabel.setText(outPut);
    }

    public void showLoading() {
        isLoading = true;
        new Thread(this::startLoadingEffect).start();
    }

    public void endLoading() {
        isLoading = false;
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
    private void startLoadingExercises(){
        String baseText = "Cargando";
        int dots = 0;

        while (isLoading) {
            try {
                dots = (dots + 1) % 4; // Alterna entre 0, 1, 2, 3 puntos
                String text = baseText + ".".repeat(dots);
                listModel.clear();
                listModel.addElement(text);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    private void loadExercises(int type) {
        isLoading=true;
        ArrayList<String> titleOfExercises=new ArrayList<>();
        new Thread(this::startLoadingExercises).start();
        new Thread(() -> {
            setExercises(ChessExercisesController.getExercises(type));

            SwingUtilities.invokeLater(() -> {
                for (ChessExercises exercise : exercises) {
                    titleOfExercises.add(exercise.getTitle());
                }
                endLoading();
                listModel.clear();
                for (String title : titleOfExercises) {
                    listModel.addElement(title);
                }
            });
        }).start();
    }
    private void setExercises(ChessExercises[] exercises){
        this.exercises=exercises;
    }
    public void getBack(String fenBefore){
        chessBoard.setGettingBackInPosition(true);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                chessBoard.updateBoard(fenBefore,false);
                chessBoard.deleteLastInHistoral();
                chessBoard.setGettingBackInPosition(false);
            }
        }, 1000);
    
    }
    private void goBackInHistoral(){
        System.out.println("click");
        chessBoard.goBack(this);
    }
    private void doEngineMove(){
        machineMoveButton.setEnabled(false);
        ChessExercisesController.doEngineMove(chessBoard);
    }
    public void setEnabledMoveMachine(){
        machineMoveButton.setEnabled(true);
    }
    public void setDisabledMoveMachine(){
        machineMoveButton.setEnabled(false);
    }
    public void doEngineMoveInboard(String move){
        chessBoard.doEngineMoveInBoard(move,this);
    }
    private void resetExercise(){
        chessBoard.updateBoard(exerciseSelected.getFen(),true);
        ChessExercisesController.setStepsInExercise(exerciseSelected.getMovesForward(),chessBoard.getSideOfUser());
        nextButton.setEnabled(false);
        backButton.setEnabled(true);
        showMessage("Vamos! "+exerciseSelected.getAim());
        chessBoard.setIsExerciseFinish(false);
        
    }
    private void nextExercise(){
        if (index < listModel.getSize() - 1) {
            index++;
            gamesList.setSelectedIndex(index);
            gamesList.ensureIndexIsVisible(index);
            exerciseSelected=exercises[index];
            showMessage("Vamos! "+exerciseSelected.getAim());
        } else {
            JOptionPane.showMessageDialog(null, "Fin de la lista");
        }
    }
    public void setResetButtonEnabled(){
        resetButton.setEnabled(true);
    }
    public void setResetButtonDisabled(){
        resetButton.setEnabled(false);
    }
    public void setNextButtonEnabled(){
        nextButton.setEnabled(true);
    }
    public void setNextButtonDisabled(){
        nextButton.setEnabled(false);
    }
    public void setBackButtonDisabled(){
        backButton.setEnabled(false);
    }
    public void setBackButtonEnabled(){
        backButton.setEnabled(true);
    }
    
}