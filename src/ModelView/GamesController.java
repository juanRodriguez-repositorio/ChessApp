/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelView;
import Model.LichessAPI;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kamus
 */
public class GamesController {
    
    
    
    public static void instanceGames(String user) {
        List<String> gameList = LichessAPI.downloadUserGames(user);
        List<Game> games = new ArrayList<>();

        for (String gameJson : gameList) {
            try {
                Game game = Game.fromJson(gameJson); // Ahora sí existe el método
                games.add(game);
                System.out.println(game);
            } catch (Exception e) {
                System.out.println("Error procesando partida: " + gameJson);
                e.printStackTrace();
            }
        }

        
    }
    
    

    
}
