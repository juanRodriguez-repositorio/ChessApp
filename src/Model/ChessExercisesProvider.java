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
        new ChessExercises("r3nr1k/p5pp/2np4/q1p1p1B1/4P3/1QP2P1N/P5PP/R4RK1 w - - 0 1", "Fácil", "Doble ataque con dama",3),
        new ChessExercises("rnbqkb1r/ppp1pppp/8/4P3/1nPP4/8/PP4PP/RNBQKBNR b - - 0 1", "Fácil", "tenedor con caballo",6),
        new ChessExercises("6Rb/pk5r/1p2R2P/1P6/2K1p3/4B3/5P2/3r4 w - - 0 1", "Fácil", "torres al ataque",9),
        new ChessExercises("r5k1/ppbbrppp/2p2n2/2P2P1q/3P4/2N1NQP1/P3R2P/2B2RK1 w - - 0 1", "Intermedio", "Sacrificio de pieza para ganar material",6),
        new ChessExercises("5k2/6p1/p2q3p/b7/2QB4/7P/P5P1/6K1 w - - 0 1", "Intermedio", "Piezas clavadas",5),
        new ChessExercises("4r1k1/8/3R1Qpp/2p5/2P1p1q1/P3P3/1P2PK2/8 b - - 0 1", "Intermedio", "Clavada a la dama",5),
        new ChessExercises("r1bqk2r/ppp2ppp/2p2n2/2b3B1/4P3/3P4/PPP2PPP/RN1QKB1R b - - 0 1", "Intermedio", "Sacrificio de dama",5),
        new ChessExercises("7k/1b1r2p1/p6p/1p2qN2/3bP3/3Q4/P5PP/1B1R3K b - - 0 1", "Intermedio", "Ataque a la descubierta",3),
        new ChessExercises("3r2k1/p4pbp/6p1/1pq5/P2N4/4P1P1/1Q1R1P1P/6K1 w - - 0 1", "Difícil", "Ataque multiple",5),
        new ChessExercises("3rr1k1/pp3ppp/8/1q1Pn3/1b1BQ3/3p2P1/PP3PBP/R2R2K1 b - - 0 1", "Difícil", "Sometimiento a las piezas blancas",6)
    };
    private static ChessExercises[] finals;
    private static ChessExercises[] mates;
    
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
