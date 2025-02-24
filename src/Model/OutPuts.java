/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author kamus
 */
import java.util.Random;

public class OutPuts {
    private static final String[] goodMoves = {
        "¡Perfecto! Vas por buen camino.",
        "¡Muy bien! La pista tenía razón.",
        "¡Excelente elección! Sigue así.",
        "¡Acertaste! Esa era la jugada correcta.",
        "¡Bien hecho! El camino se aclara.",
        "¡Gran jugada! Cada vez más cerca.",
        "¡Perfecto! La solución está más cerca.",
        "¡Esa es la actitud! Buen movimiento.",
        "¡Justo lo que necesitábamos!",
        "¡Sigues avanzando con precisión!"
    };

    private static final String[] badMoves = {
        "¡Cuidado! Esa no era la mejor opción.",
        "Hmm... ese movimiento complica las cosas.",
        "¡Error! Piensa bien tu próxima jugada.",
        "No es por ahí, intenta de nuevo.",
        "Esa jugada no ayuda, revisa la pista.",
        "Algo no cuadra, revisa tu estrategia.",
        "¡Ups! Esa elección te aleja del objetivo.",
        "Vuelve a intentarlo, tú puedes hacerlo.",
        "Esa no era la jugada correcta, reinténtalo.",
        "Parece que te desviaste, concéntrate."
    };
    private static final String[] backMoves = {
        "¿Atrás en serio?, bueno, !Inténtalo de nuevo!", 
        "¡Vamos, sigue adelante!", 
        "¿Estás seguro de retroceder?", 
        "¡Ánimo, sigue intentando!", 
        "No te rindas, sigue adelante." 
    };
    private static final String[] CompletedExerciseMessage= {
        "¡Genial! Terminaste el ejercicio, ve al siguiente en la lista!",
        "¡Lo lograste! Has completado este desafío!",
        "¡Excelente! Pasemos al siguiente reto!",
        "¡Gran trabajo! Este ejercicio está completo!",
        "¡Perfecto! Avanza al próximo desafío!"
    };

    private static final Random random = new Random();

    public static String getGoodMoveMessage() {
        return goodMoves[random.nextInt(goodMoves.length)];
    }

    public static String getBadMoveMessage() {
        return badMoves[random.nextInt(badMoves.length)];
    }
    
    public static String getBackMoveMessage() {
        return backMoves[random.nextInt(backMoves.length)];
    }
    public static String getCompletedExerciseMessage() {
        return CompletedExerciseMessage[random.nextInt(backMoves.length)];
    }
}
