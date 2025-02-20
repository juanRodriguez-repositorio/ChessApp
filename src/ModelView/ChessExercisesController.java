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

/**
 *
 * @author kamus
 */
public class ChessExercisesController {
    private static StockFish stockFish;
    
    static {
        try {
            stockFish = new StockFish();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al iniciar StockFish");
        }
    }
    public static void EvaluatePosition(String fenBefore, String fenAfter,ExercisesView window)throws ParseException{
       
        try {
            
            String[] evaluationBefore=stockFish.getEvaluation(fenBefore);
            String[] evaluationAfter=stockFish.getEvaluation(fenAfter);
            System.out.println(evaluationBefore[1]);
            System.out.println(evaluationAfter[1]);
            window.endLoading();
            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
            if(Math.abs(format.parse(evaluationBefore[1]).doubleValue()-format.parse(evaluationAfter[1]).doubleValue())>1.5){
                System.out.println("opps... error");
                System.out.println("mejor jugada: "+evaluationAfter[0]);
                window.showMessage("opps... error"+"La mejor jugada en la posicion era: "+evaluationBefore[0]+" Evaluacion de la posicion: "+evaluationAfter[1]);
            }else{
                System.out.println("gran jugada");
                System.out.println("mejor jugada: "+evaluationAfter[0]);
                window.showMessage("gran jugada! "+"La mejor jugada en la posicion era: "+evaluationBefore[0]+ " Evaluacion de la posicion: "+evaluationAfter[1]);
                
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al iniciar StockFish");
        }
       
    
      
    
    }
    
    
}
