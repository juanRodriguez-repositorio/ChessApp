/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author kamus
 */
public class ChessExercisesProvider {
    private static ChessExercises[] tactics = {
        new ChessExercises("r3nr1k/p5pp/2np4/q1p1p1B1/4P3/1QP2P1N/P5PP/R4RK1 w - - 0 1", "Fácil", "Doble ataque con dama",3,"Juegan blancas y obtienen ventaja decisiva"),
        new ChessExercises("rnbqkb1r/ppp1pppp/8/4P3/1nPP4/8/PP4PP/RNBQKBNR b - - 0 1", "Fácil", "tenedor con caballo",6,"Juegan negras y ganan material"),
        new ChessExercises("6Rb/pk5r/1p2R2P/1P6/2K1p3/4B3/5P2/3r4 w - - 0 1", "Fácil", "torres al ataque",9,"Jugan blancas y ganan material"),
        new ChessExercises("r5k1/ppbbrppp/2p2n2/2P2P1q/3P4/2N1NQP1/P3R2P/2B2RK1 w - - 0 1", "Intermedio", "Sacrificio de pieza para ganar material",6,"Juegan blancas y consiguen gran ventaja"),
        new ChessExercises("5k2/6p1/p2q3p/b7/2QB4/7P/P5P1/6K1 w - - 0 1", "Intermedio", "Piezas clavadas",5,"Juegan blancas y ganan pieza"),
        new ChessExercises("4r1k1/8/3R1Qpp/2p5/2P1p1q1/P3P3/1P2PK2/8 b - - 0 1", "Intermedio", "Clavada a la dama",5,"Juegan negras y ganan pieza"),
        new ChessExercises("r1bqk2r/ppp2ppp/2p2n2/2b3B1/4P3/3P4/PPP2PPP/RN1QKB1R b - - 0 1", "Intermedio", "Sacrificio de dama",5,"Juegan negras y ganan material"),
        new ChessExercises("7k/1b1r2p1/p6p/1p2qN2/3bP3/3Q4/P5PP/1B1R3K b - - 0 1", "Intermedio", "Ataque a la descubierta",3,"Juegan negras y obtienen ventaja decisiva"),
        new ChessExercises("3r2k1/p4pbp/6p1/1pq5/P2N4/4P1P1/1Q1R1P1P/6K1 w - - 0 1", "Difícil", "Ataque multiple",4,"Juegan blancas y ganan"),
        new ChessExercises("3rr1k1/pp3ppp/8/1q1Pn3/1b1BQ3/3p2P1/PP3PBP/R2R2K1 b - - 0 1", "Difícil", "Sometimiento a las piezas blancas",6,"Juegan negras y obtienen ligera ventaja")
    };
    private static ChessExercises[] finals={
        new ChessExercises("8/1p6/p1p2kp1/2P1p2p/1P2PP1P/P4K2/8/8 w - - 0 1","Dificil","Final de peones",15,"Juegan blancas y ganan"),
        new ChessExercises("8/2k2P1p/1p1bK3/p7/3BB2n/P7/1P4p1/8 w - - 0 1","intermedio","Final con alfiles",5,"Juegan blancas y ganan"),
        new ChessExercises("4r2k/pR5p/6p1/4b3/3B4/2P2K2/1P6/8 w - - 0 1","intermedio","Final con torres",5,"Juegan blancas y ganan pieza"),
        new ChessExercises("8/5k2/8/6R1/8/1p5r/5K2/8 b - - 0 1","intermedio","Final para pensar",4,"Juegan negras y ganan"),
        new ChessExercises("r7/3kb3/6R1/2P2KB1/8/8/8/8 w - - 0 1","Dificil","Temas tacticos en el final",6,"Juegan blancas y ganan"),
        new ChessExercises("6k1/5p1p/6p1/2b5/1q1Q4/1N4n1/P3BKP1/8 w - - 1 2","Facil","Clavadas en el final",4,"Juegan negras y ganan"),
        new ChessExercises("8/3b4/3k1q1p/1ppPNp2/7P/8/P1Q3P1/6K1 w - - 0 1","Dificil","Sacrificio para ganar material",6,"Juegan blancas y ganan"),
        new ChessExercises("k4q2/n7/B1b5/2N5/8/6Q1/8/6K1 w - - 0 1","Dificil","Sacrificio ganador",6,"Juegan blancas y ganan"),
        new ChessExercises("8/6pk/7p/Rrrn4/2N5/2P3P1/R5KP/8 b - - 0 1","Dificil","Tenedor para conseguir ventaja",6,"Juegan negras y consiguen ligera ventaja"),
        new ChessExercises("2r5/5kb1/4R3/p2p2p1/1ppP4/2P3P1/PP4BP/6K1 b - - 0 1","Dificil","Intermedia ganadora",7,"Juegan negras y ganan")

    };
    private static ChessExercises[] mates={
        new ChessExercises("1b3r2/1p6/p6R/3p1rk1/8/1P1N2P1/P5K1/7R w - - 0 1","Intermedio","Red de mate",7,"Juegan blancas y dan mate"),
        new ChessExercises("rkn5/pp1R4/2pn4/2N5/8/8/8/K7 w - - 0 1","Facil","Combinacion de mate",3,"Mate en dos con blancas!"),
        new ChessExercises("2kr4/pppb3p/5r2/8/1qP2p2/3Q1N2/PP3PPP/2KRR3 w - - 0 1","Facil","Sacrificio ganador",5,"Mate en 3 con blancas!"),
        new ChessExercises("1q3rbk/Q5pp/8/3P4/1r3n2/R3N3/2B2PPP/5RK1 b - - 0 1","Intermedio","Jugada intermedia",5,"Mate en 3 con negras!"),
        new ChessExercises("5k2/4q1p1/4p3/3pP2Q/3P2P1/8/2B3K1/8 w - - 0 1","Facil","Mate inevitable",5,"Mate en 3 con blancas!"),
        new ChessExercises("2r3k1/Q1R2p1p/4p1pb/4P3/3P4/5N2/Pr2qPPP/5RK1 b - - 0 1","Intermedio","Ultima fila",9,"Mate en 5 con negras!"),
        new ChessExercises("r1bn1r1k/1p2b1p1/p4nQ1/3qN3/3P3N/2P5/P1B2PPP/R4RK1 w - - 0 1","Intermedio","Baile de caballos",7,"Mate de blancas con los caballos,!"),
        new ChessExercises("r3q2k/pp2Nppp/n7/8/2R4Q/8/PP4PP/6K1 w - - 0 1","Facil","Mate clasico",3,"Juegan blancas y dan mate!"),
        new ChessExercises("1kr5/ppp3QR/qr6/1N6/P7/1P6/2P5/1K3R2 w - - 0 1","Intermedio","Sobrecarga",9,"Juegan blancas y dan mate en 3"),
        new ChessExercises("1kbr3r/ppp2n2/4N3/1N6/8/P3B3/1PP3B1/1K6 w - - 0 1","Facil","Tema tipico",3,"Juegan blancas y dan mate!")
        
    };
    
    public static ChessExercises[] getTacticsExercises(){
        return tactics;
    
    }
    public static ChessExercises[] getFinalsExercises(){
        return finals;
    }
    public static ChessExercises[] getMatesExercises(){
        return mates;
    }
}
