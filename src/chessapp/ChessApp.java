/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chessapp;

import javax.swing.SwingUtilities;
import View.ExercisesView;
/**
 *
 * @author kamus
 */
public class ChessApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> new ExercisesView());
        //GamesController.instanceGames("sebas123");
        //GamesController.convertPgnToUCI("e2e4");
        //System.out.println(result+"here");
    }
    
}
