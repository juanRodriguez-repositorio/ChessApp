/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelView;
import Model.StockFish;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import View.ExercisesView;
import Model.ChessExercises;
import Model.ChessExercisesProvider;
import View.ChessBoard;

/**
 *
 * @author kamus
 */
public class ChessExercisesController {
    private static StockFish stockFish;
    private static int actualStepInExercise;
    private static int totalStepsInExercise=0;
    private static String sideOfUser;
    private static String sideOfMachine;
    private static double errorTolerance=0;
    private static double lastEvaluationNumber=0.5;
    private static String bestMoveInActualPosition;
    
    
    static {
        try {
            stockFish = new StockFish();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al iniciar StockFish");
        }
    }
    public static void EvaluatePosition(String fenBefore, String fenAfter,String whoMove,ExercisesView window,boolean isEngine,ChessBoard board)throws ParseException{
       
        try {
            if(sideOfMachine.equalsIgnoreCase(whoMove)){
                window.setDisabledMoveMachine();
            }
            String[] evaluationBefore=stockFish.getEvaluation(fenBefore);
            String[] evaluationAfter=stockFish.getEvaluation(fenAfter);
            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
            if(evaluationBefore[2].equals("")!=true){
                double evaluationNumberAfter=format.parse(evaluationAfter[1]).doubleValue();
                if(evaluationAfter[2].equals("")!=true){
                    goAheadInSteps();
                    if(totalStepsInExercise==actualStepInExercise){
                        window.showMessage("Genial!, terminaste el ejercicio, ve al siguiente en la lista!");
                        window.setNextButtonEnabled();
                        window.setBackButtonDisabled();
                        board.setIsExerciseFinish(true); 
                        return;
                    }
                    if(whoMove.equalsIgnoreCase(sideOfUser)){
                        if(Integer.parseInt(evaluationAfter[2].trim().split(" ")[2])==0){
                            window.showMessage("Genial, lo completaste, jaque mate!!");
                            window.setNextButtonEnabled();
                            window.setBackButtonDisabled();
                            board.setIsExerciseFinish(true);      
                            return;
                        }
                        window.showMessage("Gran movimiento, tienes "+evaluationAfter[2]);
                    }else{
                        window.showMessage("Movimiento de tu oponente: "+"<span style='color:blue; font-size:16pt;'>" +evaluationAfter[1]+"</span>"+evaluationAfter[2]);
                    }
                    return;
                }
                if(whoMove.equalsIgnoreCase("WHITE")){
                    System.out.println("here");
                    evaluationNumberAfter=evaluationNumberAfter*-1;
                    if(evaluationNumberAfter>8){
                    window.showMessage("Buen movimiento, aunque desaprovechaste una oportunidad de mate");
                    }else{
                        window.showMessage(buildMessage("opps... Error! "," inténtalo de nuevo!",evaluationNumberAfter,evaluationBefore[0]));
                        window.getBack(fenBefore);
                        return;
                    }
                }else{
                    if(evaluationNumberAfter<-8){
                        window.showMessage("Buen movimiento, aunque desaprovechaste una oportunidad de"+evaluationAfter[2]);
                    }else{
                        window.showMessage(buildMessage("opps... Error! "," inténtalo de nuevo!",evaluationNumberAfter,evaluationBefore[0]));
                        window.getBack(fenBefore);
                        return;
                    }
                }
                
            
            }
            double evaluationNumberBefore=format.parse(evaluationBefore[1]).doubleValue();
            double evaluationNumberAfter=format.parse(evaluationAfter[1]).doubleValue();
            System.out.println(whoMove);
            if(whoMove.equalsIgnoreCase("WHITE")){
                System.out.println("here");
                evaluationNumberAfter=evaluationNumberAfter*-1;
            }else{
                evaluationNumberBefore=evaluationNumberBefore*-1;
            }
            window.endLoading();
            System.out.println("antes: "+evaluationNumberBefore);
            System.out.println("despues: "+evaluationNumberAfter);
            if(isEngine){
                bestMoveInActualPosition=evaluationAfter[0];
                window.doEngineMoveInboard(bestMoveInActualPosition);
                showMessageOpposideSide(evaluationNumberBefore,evaluationNumberAfter,window,fenBefore,evaluationAfter[0],isEngine);
                return;
            }
            if(whoMove.equalsIgnoreCase(sideOfMachine)){
                showMessageOpposideSide(evaluationNumberBefore,evaluationNumberAfter,window,fenBefore,evaluationAfter[0],isEngine);
                return;
            }
            if(Math.abs(evaluationNumberBefore-evaluationNumberAfter)>0.7){
                System.out.println("opps... error");
                System.out.println("mejor jugada: "+evaluationAfter[0]);
                window.showMessage(buildMessage("opps... Error! "," inténtalo de nuevo!",evaluationNumberAfter,evaluationBefore[0]));
                window.getBack(fenBefore);
            }else{
                System.out.println("gran jugada");
                System.out.println("mejor jugada: "+evaluationAfter[0]);
                goAheadInSteps();
                if(totalStepsInExercise==actualStepInExercise){
                    window.showMessage("Genial!, terminaste el ejercicio, ve al siguiente en la lista!");
                    window.setNextButtonEnabled();
                    window.setBackButtonDisabled();
                    board.setIsExerciseFinish(true); 
                    return;
                }
                window.showMessage(buildMessage("Gran jugada! "," Siguente paso!",evaluationNumberAfter,evaluationBefore[0]));
                window.setEnabledMoveMachine();
                
            }
            System.out.println("here: "+isEngine+" esto es lo que pasa");
            
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al iniciar StockFish");
        }
       
    
      
    
    }
    public static ChessExercises[] getExercises(int type){
        ChessExercises[] exercises;
        switch(type){
            case 0:
                exercises=ChessExercisesProvider.getTacticsExercises();
                break;
            case 1:
                exercises=ChessExercisesProvider.getFinalsExercises();
                break;
            case 2:
                exercises=ChessExercisesProvider.getMatesExercises();
                break;
            default:
                exercises=null;
            
        }
        return exercises;
        
    }
    public static void doEngineMove(ChessBoard board){
        board.doEngineMove();
        
    }
    public static void setStepsInExercise(int steps,String sideOfUser){
        System.out.println("el usuario juega con: "+sideOfUser);
        ChessExercisesController.sideOfUser=sideOfUser.trim().toLowerCase();
        if(ChessExercisesController.sideOfUser.equals("white")){
            sideOfMachine="black";
        }else{
            sideOfMachine="white";
        }
        totalStepsInExercise=steps;
        actualStepInExercise=0;
        errorTolerance=0;
    }
    public static void goBackInSteps(){
        if(actualStepInExercise!=0){
            actualStepInExercise--;
        }
    }
    private static void goAheadInSteps(){
        actualStepInExercise++;
    }
    private static void showMessageOpposideSide(double evaluationNumberBefore,double evaluationNumberAfter,ExercisesView window,String fenBefore,String opponentMove,boolean isEngine){
        if(isEngine){
            evaluationNumberBefore=evaluationNumberBefore*-1;
            evaluationNumberAfter=evaluationNumberAfter*-1;
        }
        System.out.println("esta es la que quiero "+evaluationNumberBefore);
        System.out.println("esta tambien "+evaluationNumberAfter);
        double evaluationNumber=Math.abs(evaluationNumberBefore-evaluationNumberAfter);
        if(Math.abs(evaluationNumberBefore-evaluationNumberAfter)>1){
           window.showMessage("La jugada que elegiste para tu oponente es demasiado mala, intenta con otra, o pidele a la maquina una jugada");
           window.getBack(fenBefore);
           errorTolerance=errorTolerance-lastEvaluationNumber;
            
        }else if(errorTolerance>5){
            window.showMessage("Acumulaste una serie de jugadas malas para tu oponente,debes elegir una mejor jugada o pedirle a la maquina una jugada");
            window.getBack(fenBefore);
            errorTolerance=errorTolerance-lastEvaluationNumber;
        }else{
            errorTolerance=errorTolerance+evaluationNumber;
            goAheadInSteps();
            if(totalStepsInExercise==actualStepInExercise){
              window.showMessage("Genial!, terminaste el ejercicio, ve al siguiente en la lista!");
              window.setNextButtonEnabled();
              window.setBackButtonDisabled();
              return;
            }
            window.showMessage("Jugada elegida para tu oponente: "+"<span style='color:blue; font-size:16pt;'>" +opponentMove+"</span> "+", Evaluacion de la posicion: "+"<span style='color:green; font-size:16pt;'>"+evaluationNumberAfter+"</span> ");
        }
        
    }
    public static boolean canDoMove(){
         if(totalStepsInExercise==actualStepInExercise){
             return false;
         }
         return true;
    }
    public static void resetMachineButton(String whoMove,ExercisesView window){
        if(sideOfUser!=null && sideOfUser.equalsIgnoreCase(whoMove)){
            window.setDisabledMoveMachine();
            
        }else if(sideOfUser!=null){
            window.setEnabledMoveMachine();
        }
        
    }
    private static String buildMessage(String initialString,String finalString,double evaluationNumber,String evaluationMove){
        return initialString +
                 "<b>Mejor jugada en la posición: </b>"+"<span style='color:blue; font-size:16pt;'>" + evaluationMove  +"</span> "+
                 "<b>Evaluación de la posición: </b>" +"<span style='color:green; font-size:16pt;'>"+ evaluationNumber  +"</span> "+
                finalString;
    }
    
    
}
