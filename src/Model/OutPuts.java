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
    private static final String[] completedExerciseMessage= {
        "¡Genial! Terminaste el ejercicio, ve al siguiente en la lista!",
        "¡Lo lograste! Has completado este desafío!",
        "¡Excelente! Pasemos al siguiente reto!",
        "¡Gran trabajo! Este ejercicio está completo!",
        "¡Perfecto! Avanza al próximo desafío!"
    };
    private static final String[] notBadMoveMessage = {
    "Un movimiento sólido, pero hay mejores opciones.",
    "Aceptable, pero podrías presionar más.",
    "Vas bien, pero aún puedes mejorar.",
    "Nada mal, pero hay jugadas más fuertes.",
    "Estás en camino, sigue pensando.",
    "Un paso estable, pero sin sorpresas.",
    "Defendiste bien, pero falta iniciativa.",
    "Buena idea, pero no la más precisa.",
    "Correcto, pero podías ser más agresivo.",
    "Seguro, pero deja pasar una oportunidad."
    };
    private static final String[] slightlyBadMoveMessage = {
    "Ese movimiento es malo, pero no es para llorar",
    "Cuidado, esa jugada pierde algo de ventaja.",
    "No es catastrófico, pero es impreciso.",
    "Esa jugada te complica la posición",
    "Pierdes algo de terreno, piensa mejor la próxima.",
    "No es lo peor, pero hay consecuencias.",
    "Tu posición empeora a lo que estaba considerablemente",
    "Has dejado escapar una mejor jugada., o muchas mejores jugadas",
    "Podrías haber jugado con más precisión." 
    };
    private static final String[] badMoveMessage = {
    "Ese movimiento es malo, nada que hacer",
    "Cuidado, concentrate, fue un mal movimiento.",
    "Una decisión arriesgada que sale mal.",
    "Esa jugada no es lo que stockfish quiere ver.",
    "Ese movimiento fue bastante malo",
    "Has cometido un error significativo.",
    "Pierdes control sobre la partida.¿o que?",
    "Esa decisión abre debilidades para tu rival.",
    "Un error que tu rival puede aprovechar.",
    
    };
    private static final String[] veryBadMoveMessage = {
        "¡Graaave error!",
        "Esa jugada es casi decisiva, por lo mala que es.",
        "Has dejado una gran oportunidad al rival.",
        "Un descuido en la posición",
        "Ese movimiento es un desastre.",
        "¿Has tenido un mal dia?.",
        "Un error crítico.",
        "Es una jugada muuy mala.",
        "WOW es demasiado malo que parece mentira.",
        "Un fallo bastante grave." 
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
        return completedExerciseMessage[random.nextInt(completedExerciseMessage.length)];
    }
    public static String getNotBadMoveMessage() {
        return notBadMoveMessage[random.nextInt(notBadMoveMessage.length)];
    }
    public static String getSlightlyBadMoveMessage() {
        return slightlyBadMoveMessage[random.nextInt(slightlyBadMoveMessage.length)];
    }
    public static String badFreeMoveMessage() {
        return badMoveMessage[random.nextInt(badMoveMessage.length)];
    }
    public static String verybadMoveMessage() {
        return veryBadMoveMessage[random.nextInt(veryBadMoveMessage.length)];
    }
}
