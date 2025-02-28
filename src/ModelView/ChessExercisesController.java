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
import Model.OutPuts;

/**
 *
 * @author kamus
 */
public class ChessExercisesController {
    private static StockFish stockFish;
    private static int actualStepInExercise=-1;
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
    public static void EvaluateFreePosition(String fenBefore,String fenAfter,String whoMove,ExercisesView window,boolean isEngine)throws ParseException{
        try{
            if(isEngine && fenBefore==null){
                String[] evaluationAfter=stockFish.getEvaluation(fenAfter);
                NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
                window.endLoading();
                if(evaluationAfter[2].equals("")!=true){
                    int mateStatusAfter=Integer.parseInt(evaluationAfter[2]);
                    mateStatusAfter=mateStatusAfter*-1;
                    if(mateStatusAfter==0){
                        bestMoveInActualPosition=evaluationAfter[0];
                        window.doEngineMoveInboard(bestMoveInActualPosition);
                        window.showMessage("Jaque mate!");
                        return;
                    }
                    bestMoveInActualPosition=evaluationAfter[0];
                    window.doEngineMoveInboard(bestMoveInActualPosition);
                    window.showMessage("hay mate en "+"<span style=\"color:green; font-size:16pt;\">"+mateStatusAfter+"</span>");
                    return;   
                }
                double evaluationNumberAfter=format.parse(evaluationAfter[1]).doubleValue();
                bestMoveInActualPosition=evaluationAfter[0];
                window.doEngineMoveInboard(bestMoveInActualPosition);
                window.showMessage("Jugada de la maquina: "+"<span style='color:blue; font-size:16pt;'>" +evaluationAfter[0]+"</span> "+", evaluación de la posición: "+"<span style=\"color:green; font-size:16pt;\">"+evaluationNumberAfter+"</span>");
                return;
                
            }
            String[] evaluationBefore=stockFish.getEvaluation(fenBefore);
            String[] evaluationAfter=stockFish.getEvaluation(fenAfter);
            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
            window.endLoading();
            if(evaluationAfter[2].equals("")!=true){
                int mateStatusAfter=Integer.parseInt(evaluationAfter[2]);
                mateStatusAfter=mateStatusAfter*-1;
                if(mateStatusAfter==0){
                    window.showMessage("Jaque mate!");
                    return;
                }
                window.showMessage("hay mate en "+"<span style=\"color:green; font-size:16pt;\">"+mateStatusAfter+"</span>");
                return;   
            }
            
            double evaluationNumberBefore=format.parse(evaluationBefore[1]).doubleValue();
            double evaluationNumberAfter=format.parse(evaluationAfter[1]).doubleValue();
            double absoluteEvaluation=Math.abs(evaluationNumberBefore-evaluationNumberAfter);
            if(whoMove.equalsIgnoreCase("WHITE")){
                System.out.println("here");
                evaluationNumberAfter=evaluationNumberAfter*-1;
            }else{
                evaluationNumberBefore=evaluationNumberBefore*-1;
            }
            if(absoluteEvaluation>0.3){
                window.showMessage(buildMessage("Execelente movimiento! "," sigue asi! <br>"+OutPuts.getGoodMoveMessage(),evaluationNumberAfter,evaluationBefore[0]));
            }else if(absoluteEvaluation>=0.3 && absoluteEvaluation<1){
                window.showMessage(buildMessage("No es un movimiento perdedor, pero no es lo mejor "," vamos! <br>"+OutPuts.getNotBadMoveMessage(),evaluationNumberAfter,evaluationBefore[0]));
            }else if(absoluteEvaluation>=1 && absoluteEvaluation<2){
                window.showMessage(buildMessage("mmm... mal movimiento "," puedes mejorar!<br>"+OutPuts.getSlightlyBadMoveMessage(),evaluationNumberAfter,evaluationBefore[0]));
            }else if(absoluteEvaluation>=2 && absoluteEvaluation<4){
                window.showMessage(buildMessage("Grave error! "," sigue intentando<br>"+OutPuts.badFreeMoveMessage(),evaluationNumberAfter,evaluationBefore[0]));
            }else{
                window.showMessage(buildMessage("!Oh NO, muuuy mal movimiento !"," tienes que mejorar!<br>"+OutPuts.verybadMoveMessage(),evaluationNumberAfter,evaluationBefore[0]));
            }
            window.setEnabledMoveMachine();
        }catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al iniciar StockFish");
        }
        
       
        
    }
    public static void EvaluatePosition(String fenBefore, String fenAfter,String whoMove,ExercisesView window,boolean isEngine,ChessBoard board,String move)throws ParseException{
        
        
        try {
            if(sideOfMachine.equalsIgnoreCase(whoMove)){
                window.setDisabledMoveMachine();
            }
            String[] evaluationBefore=stockFish.getEvaluation(fenBefore);
            String[] evaluationAfter=stockFish.getEvaluation(fenAfter);
            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
            window.endLoading();
            if(evaluationBefore[2].equals("")!=true){
          
                if(evaluationAfter[2].equals("")!=true){
                    int mateStatusBefore=Integer.parseInt(evaluationBefore[2]);
                    int mateStatusAfter=Integer.parseInt(evaluationAfter[2]);
                    System.out.println("esto va a ser: "+mateStatusBefore);
                    System.out.println("esto va a ser: "+mateStatusAfter);
                    mateStatusAfter=mateStatusAfter*-1;
                    System.out.println(mateStatusAfter);
                    if(whoMove.equalsIgnoreCase(sideOfUser)){
                        if(mateStatusAfter==0){
                            System.out.println("jaque mate");
                            window.showMessage("Genial, lo completaste, jaque mate!!");
                            window.setNextButtonEnabled();
                            window.setBackButtonDisabled();
                            board.setIsExerciseFinish(true);      
                            return;
                        }
                        if((mateStatusBefore>0 && mateStatusAfter>0)||(mateStatusBefore>0 && mateStatusAfter>0)){
                            window.showMessage("Gran movimiento, tienes mate en "+"<span style=\"color:green; font-size:16pt;\">"+mateStatusAfter+"</span>");
                            window.setEnabledMoveMachine();
                            return;
                        }else{
                            window.showMessage("opps...error, mala jugada, dejaste mate en "+"<span style=\"color:green; font-size:16pt;\">"+mateStatusAfter+"</span>");
                            window.getBack(fenBefore);
                            return;
                        }
                    }else{
                        if(isEngine){
                            bestMoveInActualPosition=evaluationAfter[0];
                            window.doEngineMoveInboard(bestMoveInActualPosition);
                            showMessageOpposideSide(mateStatusBefore,mateStatusAfter,window,fenBefore,evaluationBefore[0],isEngine,true);
                            return;
                        }
                        System.out.println("deberia estar aqui");
                        System.out.println(mateStatusAfter);
                        showMessageOpposideSide(mateStatusBefore*-1,mateStatusAfter*-1,window,fenBefore,move,isEngine,true);
                    }
                    System.out.println("pasa algo con whoMove");
                    return;
                }
                double evaluationNumberAfter=format.parse(evaluationAfter[1]).doubleValue();
                evaluationNumberAfter=evaluationNumberAfter*-1;
                if(whoMove.equalsIgnoreCase("WHITE")){
                    if(evaluationNumberAfter>8){
                        window.showMessage("Buen movimiento, aunque desaprovechaste una oportunidad de mate");
                        window.getBack(fenBefore);
                        window.setEnabledMoveMachine();
                        return;
                    }else{
                        window.showMessage(buildMessage("opps... Error! "," inténtalo de nuevo!",evaluationNumberAfter,evaluationBefore[0]));
                        window.getBack(fenBefore);
                        return;
                    }
                }else{
                    if(evaluationNumberAfter<-8){
                        window.showMessage("Buen movimiento, aunque desaprovechaste una oportunidad de mate en "+"<span style=\"color:green; font-size:16pt;\">"+Integer.parseInt(evaluationBefore[2])*-1+"</span>");
                        window.getBack(fenBefore);
                        window.setEnabledMoveMachine();
                        return;
                    }else{
                        window.showMessage(buildMessage("opps... Error! "," inténtalo de nuevo!<br>"+OutPuts.getBadMoveMessage(),evaluationNumberAfter,evaluationBefore[0]));
                        window.getBack(fenBefore);
                        return;
                    }
                }
                
            
            }
            if(evaluationAfter[2].equals("")!=true){
                double evaluationNumberBefore=format.parse(evaluationBefore[1]).doubleValue();
                int mateStatusAfter=Integer.parseInt(evaluationAfter[2]);
                mateStatusAfter=mateStatusAfter*-1;
                evaluationNumberBefore=evaluationNumberBefore*-1;
                if(whoMove.equalsIgnoreCase("WHITE")){
                    if(evaluationNumberBefore<0){
                        window.showMessage("opps...error, mala jugada, dejaste mate en "+"<span style=\"color:green; font-size:16pt;\">"+mateStatusAfter+"</span>");
                        window.getBack(fenBefore);
                        return;
                    }else{
                        if(isEngine){
                            bestMoveInActualPosition=evaluationAfter[0];
                            window.doEngineMoveInboard(bestMoveInActualPosition);
                            showMessageOpposideSide(-1,mateStatusAfter,window,fenBefore,evaluationAfter[0],isEngine,true);
                            return;
                        }
                        goAheadInSteps();
                        if(totalStepsInExercise==actualStepInExercise){
                            window.showMessage(OutPuts.getCompletedExerciseMessage());
                            window.setNextButtonEnabled();
                            window.setBackButtonDisabled();
                            board.setIsExerciseFinish(true); 
                            return;
                        }
                        window.showMessage("Brillante!!, mate en "+"<span style=\"color:green; font-size:16pt;\">"+mateStatusAfter+"</span>");
                        window.setEnabledMoveMachine();
                        return;
                    }
                    
                }else{
                    if(evaluationNumberBefore>0){
                        window.showMessage("opps...error, mala jugada, dejaste mate en "+"<span style=\"color:green; font-size:16pt;\">"+mateStatusAfter+"</span>");
                        window.getBack(fenBefore);
                        return;
                    }else{
                        if(isEngine){
                            bestMoveInActualPosition=evaluationAfter[0];
                            window.doEngineMoveInboard(bestMoveInActualPosition);
                            showMessageOpposideSide(-1,mateStatusAfter,window,fenBefore,evaluationAfter[0],isEngine,true);
                            return;
                            
                        }
                        goAheadInSteps();
                        if(totalStepsInExercise==actualStepInExercise){
                            window.showMessage(OutPuts.getCompletedExerciseMessage());
                            window.setNextButtonEnabled();
                            window.setBackButtonDisabled();
                            board.setIsExerciseFinish(true); 
                            return;
                        }
                        window.showMessage("Brillante!!, mate en "+"<span style=\"color:green; font-size:16pt;\">"+mateStatusAfter+"</span>");
                        window.setEnabledMoveMachine();
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
            if(isEngine){
                bestMoveInActualPosition=evaluationAfter[0];
                window.doEngineMoveInboard(bestMoveInActualPosition);
                showMessageOpposideSide(evaluationNumberBefore,evaluationNumberAfter,window,fenBefore,evaluationAfter[0],isEngine,false);
                return;
            }
            if(whoMove.equalsIgnoreCase(sideOfMachine)){
                showMessageOpposideSide(evaluationNumberBefore,evaluationNumberAfter,window,fenBefore,move,isEngine,false);
                return;
            }
            if(Math.abs(evaluationNumberBefore-evaluationNumberAfter)>0.7){
                System.out.println("opps... error");
                System.out.println("mejor jugada: "+evaluationAfter[0]);
                window.showMessage(buildMessage("opps... Error! "," inténtalo de nuevo!<br>"+OutPuts.getBadMoveMessage(),evaluationNumberAfter,evaluationBefore[0]));
                window.getBack(fenBefore);
            }else{
                System.out.println("gran jugada");
                System.out.println("mejor jugada: "+evaluationAfter[0]);
                goAheadInSteps();
                if(totalStepsInExercise==actualStepInExercise){
                    window.showMessage(OutPuts.getCompletedExerciseMessage());
                    window.setNextButtonEnabled();
                    window.setBackButtonDisabled();
                    board.setIsExerciseFinish(true); 
                    return;
                }
                window.showMessage(buildMessage("Gran jugada! "," Siguiente paso!<br>"+OutPuts.getGoodMoveMessage(),evaluationNumberAfter,evaluationBefore[0]));
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
    public static void doEngineMove(ChessBoard board,int type){
        System.out.println("se ejecuta rapido o lento?");
        board.doEngineMove(type);
        
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
        System.out.println("se ejucuta de marravilla");
    }
    private static void showMessageOpposideSide(double evaluationNumberBefore,double evaluationNumberAfter,ExercisesView window,String fenBefore,String opponentMove,boolean isEngine,boolean isCheck){
        if(isCheck && isEngine){
            goAheadInSteps();
            if(totalStepsInExercise==actualStepInExercise){
              window.showMessage(OutPuts.getCompletedExerciseMessage());
              window.setNextButtonEnabled();
              window.setBackButtonDisabled();
              return;
            }
            window.showMessage("Jugada elegida para tu oponente: "+"<span style='color:blue; font-size:16pt;'>" +opponentMove+"</span> "+", Mate en: "+"<span style='color:green; font-size:16pt;'>"+evaluationNumberAfter+"</span> ");
            return;
            
        }else if(isCheck){
            if((evaluationNumberBefore<0 && evaluationNumberAfter<0) || (evaluationNumberBefore>0 && evaluationNumberAfter>0)){
                goAheadInSteps();
                if(totalStepsInExercise==actualStepInExercise){
                    window.showMessage(OutPuts.getCompletedExerciseMessage());
                    window.setNextButtonEnabled();
                    window.setBackButtonDisabled();
                    return;
                }
                System.out.println("si algo pasa aqui tan raaaro");
                System.out.println(evaluationNumberBefore);
                System.out.println(evaluationNumberAfter);
                window.showMessage("Jugada elegida para tu oponente: "+"<span style='color:blue; font-size:16pt;'>" +opponentMove+"</span> "+", Mate en: "+"<span style='color:green; font-size:16pt;'>"+evaluationNumberAfter+"</span> ");
                return;
            }
            window.showMessage("La jugada que elegiste para tu oponente es demasiado mala,<br> intenta con otra, o pidele a la maquina una jugada");
            window.getBack(fenBefore);
            return;
            
        }
        if(isEngine){
            evaluationNumberBefore=evaluationNumberBefore*-1;
            evaluationNumberAfter=evaluationNumberAfter*-1;
        }
        System.out.println("esta es la que quiero "+evaluationNumberBefore);
        System.out.println("esta tambien "+evaluationNumberAfter);
        double evaluationNumber=Math.abs(evaluationNumberBefore-evaluationNumberAfter);
        if(evaluationNumber>1){
           window.showMessage("La jugada que elegiste para tu oponente es demasiado mala,<br> intenta con otra, o pidele a la maquina una jugada");
           window.getBack(fenBefore);
           errorTolerance=errorTolerance-lastEvaluationNumber;
            
        }else if(errorTolerance>5){
            window.showMessage("Acumulaste una serie de jugadas malas para tu oponente,<br> debes elegir una mejor jugada o pedirle a la maquina una jugada");
            window.getBack(fenBefore);
            errorTolerance=errorTolerance-lastEvaluationNumber;
        }else{
            errorTolerance=errorTolerance+evaluationNumber;
            goAheadInSteps();
            if(totalStepsInExercise==actualStepInExercise){
              window.showMessage(OutPuts.getCompletedExerciseMessage());
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
    public static void resetSteps(){
        actualStepInExercise=0;
    }
    public static void init(){
        
    }
    
    
}
