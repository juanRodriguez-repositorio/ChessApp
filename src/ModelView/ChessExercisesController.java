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
    private static String bestMoveInActualPosition;
    
    
    static {
        try {
            stockFish = new StockFish();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al iniciar StockFish");
        }
    }
    public static void EvaluatePosition(String fenBefore, String fenAfter,String whoMove,ExercisesView window)throws ParseException{
       
        try {
            
            String[] evaluationBefore=stockFish.getEvaluation(fenBefore);
            String[] evaluationAfter=stockFish.getEvaluation(fenAfter);
            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
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
            bestMoveInActualPosition=evaluationAfter[0];
            if(Math.abs(evaluationNumberBefore-evaluationNumberAfter)>1.5){
                System.out.println("opps... error");
                System.out.println("mejor jugada: "+evaluationAfter[0]);
                window.showMessage("opps... error"+"La mejor jugada en la posicion era: "+evaluationBefore[0]+" Evaluacion de la posicion: "+evaluationNumberAfter);
                window.getBack(fenBefore);
            }else{
                System.out.println("gran jugada");
                System.out.println("mejor jugada: "+evaluationAfter[0]);
                window.showMessage("gran jugada! "+"La mejor jugada en la posicion era: "+evaluationBefore[0]+ " Evaluacion de la posicion: "+evaluationNumberAfter);
                
            }
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
        board.doEngineMove(bestMoveInActualPosition);
        
    }
    
    
}
