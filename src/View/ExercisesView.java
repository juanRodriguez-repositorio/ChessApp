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
import javax.swing.border.EmptyBorder;


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
    private JButton backButton, machineMoveButton,resetButton,nextButton,freeGameButton; 
    private ChessExercises exerciseSelected;
    private int index;
    private int gameMode=-1;
    
    
    

    public ExercisesView() {
        getContentPane().setBackground(new Color(213, 249, 222));
        messageLabel = new JLabel("<html><div style='text-align:center; font-size:14pt;'>Selecciona una partida para comenzar.</div></html>");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setVerticalAlignment(SwingConstants.CENTER);
        setTitle("Chess Exercises");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 740);
        setLayout(new BorderLayout(10, 10));

        // Panel del tablero de ajedrez
        chessBoard = new ChessBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", this);
        
        // Menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Navegación");
        menu.setForeground(new Color(10, 10, 10));
        JMenuItem exercisesItem = new JMenuItem("Ejercicios");
        exercisesItem.addActionListener(e -> {
   
        });

        JMenuItem resourcesItem = new JMenuItem("Recursos");
        resourcesItem.addActionListener(e -> {
           dispose();
           SwingUtilities.invokeLater(()->new ChessResourcesView());
        });

        menu.add(exercisesItem);
        menu.add(resourcesItem);
        menuBar.add(menu);
        menuBar.setBackground(new Color(162, 210, 187));
        menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 20, 0, new Color(213, 249, 222))); // Solo línea inferior negra de 2px
        setJMenuBar(menuBar);

        
        //botones de ejercicios
        tacticsButton=new JButton("Tacticas");
        finalsButton=new JButton("Finales");
        matesButton=new JButton("Mates");
        freeGameButton=new JButton("Juego libre!");
        backButton=new JButton("Atras");
        machineMoveButton=new JButton("Jugada de la maquina");
        resetButton=new JButton("Reiniciar");
        nextButton=new JButton("Siguiente");
        
        styleButtonGreen(backButton);
        styleButtonGreen(machineMoveButton);
        styleButtonWhite(resetButton);
        styleButtonWhite(nextButton);
        styleButtonLightGreen(tacticsButton);
        styleButtonLightGreen(finalsButton);
        styleButtonLightGreen(matesButton);
        styleButtonLightGreen(freeGameButton);
        
        setResetButtonDisabled();
        setNextButtonDisabled();
        
        
        //eventos a los botones
        
        tacticsButton.addActionListener(e -> loadExercises(0));
        finalsButton.addActionListener(e -> loadExercises(1));
        matesButton.addActionListener(e -> loadExercises(2));
        freeGameButton.addActionListener(e -> setFreeMode());
        backButton.addActionListener(e -> goBackInHistoral());
        machineMoveButton.addActionListener(e -> doEngineMove());
        resetButton.addActionListener(e -> resetExercise());
        nextButton.addActionListener(e -> nextExercise());
        setDisabledMoveMachine();
        
        // Modelo de lista y lista con scroll
        listModel = new DefaultListModel<>();
        gamesList = new JList<>(listModel);
        gamesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        
        listModel.addElement("Elige un modo para empezar");
        

        // Evento de clic en la lista
        gamesList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(chessBoard.getIsProcessing() || isLoading || getGameMode()==-1){
                    return;
                }
                if (e.getClickCount() == 2) { // Doble clic
                    index = gamesList.getSelectedIndex();
                    if(index >= 0 && index < exercises.length){
                        exerciseSelected=exercises[index];
                        chessBoard.updateBoard(exerciseSelected.getFen(),true);
                        ChessExercisesController.setStepsInExercise(exerciseSelected.getMovesForward(),chessBoard.getSideOfUser());
                        setDisabledMoveMachine();
                        setResetButtonEnabled();
                        setNextButtonDisabled();
                        setBackButtonEnabled();
                        chessBoard.setIsExerciseFinish(false);
                        showMessage("Vamos! "+exerciseSelected.getAim()+" Dificultad: "+showDificulty(exerciseSelected.getLevel()));
                    }
                    
                }
            }
        });

        // Panel izquierdo con lista y botones
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(gamesList);
        scrollPane.setBackground(new Color(213, 249, 222));
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Partidas"));
        leftPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel de botones debajo de la lista
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Apilados verticalmente
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT); 
        JPanel topButtonsPanel = new JPanel();
        topButtonsPanel.setBackground(new Color(213, 249, 222));
        topButtonsPanel.setLayout(new GridLayout(1, 3, 5, 5));
        topButtonsPanel.add(tacticsButton);
        topButtonsPanel.add(finalsButton);
        topButtonsPanel.add(matesButton);
        topButtonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        freeGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(topButtonsPanel);
        buttonPanel.add(Box.createVerticalStrut(10)); // Espaciado opcional
        buttonPanel.add(freeGameButton);
        buttonPanel.setBackground(new Color(213, 249, 222));
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);
        leftPanel.setPreferredSize(new Dimension(300,350));
        
        
        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        container.add(leftPanel);
        container.setBackground(new Color(213, 249, 222));
        container.setBorder(new EmptyBorder(0, 20, 0, 0)); // Solo margen izquierdo
        add(container, BorderLayout.WEST);
        
        
        //panel del chessBoard y los botones de atras y jugada de la maquina
        
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        controlPanel.add(backButton);
        controlPanel.add(machineMoveButton);
        controlPanel.add(resetButton);
        controlPanel.add(nextButton);
        controlPanel.setBackground(new Color(213, 249, 222));
        
        JPanel chessPanel = new JPanel(new BorderLayout());
        JPanel boardContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        boardContainer.add(chessBoard);
        boardContainer.setBackground(new Color(213, 249, 222));
        chessPanel.add(boardContainer, BorderLayout.CENTER);
        chessPanel.add(controlPanel, BorderLayout.SOUTH);
        chessPanel.setBorder(new EmptyBorder(0,0,0,20));
        chessPanel.setBackground(new Color(213, 249, 222));
        chessPanel.setBorder(new EmptyBorder(0,0,40,0));
        add(chessPanel, BorderLayout.EAST);
        // Panel de salida de mensajes
        JPanel outputPanel = new JPanel(new GridBagLayout());
        outputPanel.setBorder(BorderFactory.createTitledBorder("Mensajes"));
        outputPanel.setPreferredSize(new Dimension(0, 70));
        outputPanel.add(messageLabel);
        outputPanel.setBackground(new Color(213, 249, 222));
        add(outputPanel, BorderLayout.NORTH);

        setLocationRelativeTo(null); // Centra la ventana
        setVisible(true);
        ChessExercisesController.init();
        
    }

    public void showMessage(String outPut) {
        
        SwingUtilities.invokeLater(()-> messageLabel.setText("<html>" +"<span style='font-size:14pt'>"+outPut+"</span></html>"));
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
                showMessage("<span style='color:rgb(120,120,120);'>"+text+"</span>");
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
        if(chessBoard.getIsProcessing() || isLoading){
            return;
        }
        isLoading=true;
        setGameMode(type);
        ArrayList<String> titleOfExercises=new ArrayList<>();
        new Thread(this::startLoadingExercises).start();
        new Thread(() -> {
            setExercises(ChessExercisesController.getExercises(type));

            SwingUtilities.invokeLater(() -> {
                for (ChessExercises exercise : exercises) {
                    titleOfExercises.add(exercise.getTitle());
                }
                showMessage("Selecciona una partida para comenzar");
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
        if(gameMode==-1){
            return;
        }
        machineMoveButton.setEnabled(false);
        if(gameMode==3){
            backButton.setEnabled(false);
            System.out.println("hola");
            SwingUtilities.invokeLater(()->ChessExercisesController.doEngineMove(chessBoard,gameMode));
            return;
        }
        ChessExercisesController.doEngineMove(chessBoard,gameMode);
    }
    public void setEnabledMoveMachine(){
        machineMoveButton.setEnabled(true);
        styleEnabledGreenButton(machineMoveButton);
    }
    public void setDisabledMoveMachine(){
        machineMoveButton.setEnabled(false);
        styleDisabledGreenButton(machineMoveButton);
    }
    public void doEngineMoveInboard(String move){
        chessBoard.doEngineMoveInBoard(move,this);
    }
    private void resetExercise(){
        if(isLoading || chessBoard.getIsProcessing()){
            return;
        }
        chessBoard.updateBoard(exerciseSelected.getFen(),true);
        ChessExercisesController.setStepsInExercise(exerciseSelected.getMovesForward(),chessBoard.getSideOfUser());
        setNextButtonDisabled();
        setBackButtonEnabled();
        setDisabledMoveMachine();
        showMessage("Vamos! "+exerciseSelected.getAim());
        chessBoard.setIsExerciseFinish(false);
        
    }
    private void nextExercise(){
        if(isLoading || chessBoard.getIsProcessing()){
            return;
        }
        if ((index < listModel.getSize() - 1)==false){
            index=-1;
        } 
        index++;
        gamesList.setSelectedIndex(index);
        gamesList.ensureIndexIsVisible(index);
        exerciseSelected=exercises[index];
        chessBoard.updateBoard(exerciseSelected.getFen(),true);
        ChessExercisesController.setStepsInExercise(exerciseSelected.getMovesForward(),chessBoard.getSideOfUser());
        setNextButtonDisabled();
        setResetButtonEnabled();
        setBackButtonEnabled();
        setDisabledMoveMachine();
        chessBoard.setIsExerciseFinish(false);
        showMessage("Vamos! "+exerciseSelected.getAim()+" Dificultad: "+showDificulty(exerciseSelected.getLevel()));
        
    }
    private void styleButtonLightGreen(JButton button){
        button.setBackground(new Color(219, 244, 167));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 12)); // Fuente
        button.setFocusPainted(false); // Eliminar el borde de enfoque
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Agregar padding
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(button.isEnabled()==false){
                    return;
                }
                button.setBackground(new Color(200, 233, 143)); // Color de fondo más claro al pasar el ratón
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(button.isEnabled()==false){
                    return;
                }
                button.setBackground(new Color(219, 244, 167)); // Restaurar el color original
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(button.isEnabled()==false){
                    return;
                }
                UIManager.put("Button.select",new Color(176, 217, 119));
                button.revalidate(); // Cambiar el color cuando se hace clic
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(button.isEnabled()==false){
                    return;
                }
                if (button.getBackground() != new Color(219, 244, 167)) {
                     //button.setBackground(hoverColor);
                     UIManager.put("Button.select",new Color(200, 233, 143));
                     } else {
                       //button.setBackground(backgroundColor);
                       UIManager.put("Button.select",new Color(219, 244, 167));
                     }
            
                } // Restaurar el color original al soltar el clic
            
            });
        
    }
    private void styleButtonGreen(JButton button) {
        button.setBackground(new Color(0, 148, 76)); 
        button.setForeground(new Color(255,255,255)); // Color de texto (blanco)
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente
        button.setFocusPainted(false); // Eliminar el borde de enfoque
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Agregar padding
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(button.isEnabled()==false){
                    return;
                }
                button.setBackground(new Color(0, 127, 65)); // Color de fondo más claro al pasar el ratón
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(button.isEnabled()==false){
                    return;
                }
                button.setBackground(new Color(0, 148, 76)); // Restaurar el color original
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(button.isEnabled()==false){
                    return;
                }
                UIManager.put("Button.select",new Color(0, 102, 55));
                button.revalidate(); // Cambiar el color cuando se hace clic
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(button.isEnabled()==false){
                    return;
                }
                if (button.getBackground() != new Color(0, 148, 76)) {
                     //button.setBackground(hoverColor);
                     UIManager.put("Button.select",new Color(0, 127, 65));
                     } else {
                       //button.setBackground(backgroundColor);
                       UIManager.put("Button.select",new Color(0, 148, 76));
                     }
            
                } // Restaurar el color original al soltar el clic
            
            });
    }
    private void styleButtonWhite(JButton button) {
        button.setBackground(new Color(255,255,255)); 
        button.setForeground(Color.BLACK); 
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente
        button.setFocusPainted(false); // Eliminar el borde de enfoque
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Agregar padding
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(button.isEnabled()==false){
                    return;
                }
                button.setBackground(new Color(230,230,230)); // Color de fondo más claro al pasar el ratón
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(button.isEnabled()==false){
                    return;
                }
                button.setBackground(new Color(255,255,255)); // Restaurar el color original
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(button.isEnabled()==false){
                    return;
                }
                UIManager.put("Button.select",new Color(220,220,220));
                button.revalidate(); // Cambiar el color cuando se hace clic
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(button.isEnabled()==false){
                    return;
                }
                if (button.getBackground() != new Color(255,255,255)) {
                     //button.setBackground(hoverColor);
                     UIManager.put("Button.select",new Color(230,230,230));
                     } else {
                       //button.setBackground(backgroundColor);
                       UIManager.put("Button.select",new Color(255,255,255));
                     }
            
                } // Restaurar el color original al soltar el clic
            
            });
    }
    public void setResetButtonEnabled(){
        resetButton.setEnabled(true);
        styleEnabledWhiteButton(resetButton);
    }
    public void setResetButtonDisabled(){
        resetButton.setEnabled(false);
        styleDisabledWhiteButton(resetButton);
    }
    public void setNextButtonEnabled(){
        nextButton.setEnabled(true);
        styleEnabledWhiteButton(nextButton);
    }
    public void setNextButtonDisabled(){
        nextButton.setEnabled(false);
        styleDisabledWhiteButton(nextButton);
    }
    public void setBackButtonDisabled(){
        backButton.setEnabled(false);
        styleDisabledGreenButton(backButton);
    }
    public void setBackButtonEnabled(){
        backButton.setEnabled(true);
        styleEnabledGreenButton(backButton);
    }
    public void styleDisabledGreenButton(JButton button){
        button.setBackground(new Color(0,121,64));
        button.setForeground(new Color(220,220,220));
        
    }
    public void styleEnabledGreenButton(JButton button){
        button.setBackground(new Color(0, 148, 76));
        button.setForeground(new Color(255,255,255));
    }
    public void styleDisabledWhiteButton(JButton button){
        
        button.setBackground(new Color(210,210,210));
        button.setForeground(new Color(20,20,20));
    }
    public void styleEnabledWhiteButton(JButton button){
        button.setBackground(new Color(255,255,255));
        button.setForeground(Color.BLACK);
    }
    public int getGameMode(){
        return gameMode;
    }
    private void setGameMode(int mode){
        gameMode=mode;
    }
    public boolean getIsLoadingList(){
        return isLoading;
    }
    private void setFreeMode(){
        if(isLoading || chessBoard.getIsProcessing()){
            return;
        }
        gameMode=3;
        listModel.clear();
        listModel.addElement("Análisis libre!");
        chessBoard.updateBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", true);
        setNextButtonDisabled();
        setEnabledMoveMachine();
        setResetButtonDisabled();
        setBackButtonEnabled();
        showMessage("Análisis libre!");
    }
    private String showDificulty(String dificulty){
        if(dificulty.equalsIgnoreCase("fácil")){
            return "<span style='color: #4CAF50;'>Fácil</span>";
        }else if(dificulty.equalsIgnoreCase("difícil")){
            return "<span style='color: #FFC107;'>Intermedio</span>";
        }else{
            return "<span style='color: #F44336;'>Difícil</span>";
        }
    }
    
}