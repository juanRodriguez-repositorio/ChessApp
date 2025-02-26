/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chessapp;

import View.AuthView;
import javax.swing.SwingUtilities;

/**
 *
 * @author kamus
 */
public class ChessApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AuthView authView = new AuthView();
            authView.setVisible(true);
        });
        //GamesController.instanceGames("sebas123");
        //GamesController.convertPgnToUCI("e2e4");
        //System.out.println(result+"here");
    }
    
}
