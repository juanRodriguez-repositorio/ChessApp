/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 *
 * @author kamus
 */
public class Game {
    private Player whitePlayer;
    private Player blackPlayer;
    private String moves;
    private String speed;
    private String id;
    private String winner;

    public Game(String id,Player whitePlayer, Player blackPlayer, String moves, String speed, String winner) {
        this.id=id;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.moves = moves;
        this.speed = speed;
        this.winner = winner;
    }
    //parsear info.
    
    public static Game fromJson(String gameJson) {
        JsonObject jsonObject = JsonParser.parseString(gameJson).getAsJsonObject();
        
        String id=jsonObject.get("id").getAsString();
        String winner = jsonObject.has("winner") ? jsonObject.get("winner").getAsString() : "draw";
        String speed = jsonObject.get("speed").getAsString();
        String moves = jsonObject.get("moves").getAsString();
        JsonObject players = jsonObject.getAsJsonObject("players");
        Player whitePlayer = Player.fromJson(players.getAsJsonObject("white"), "white");
        Player blackPlayer = Player.fromJson(players.getAsJsonObject("black"), "black");

        return new Game(id,whitePlayer, blackPlayer,moves,speed,winner);
    }
     @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\''+
                ", winner='" + winner + '\'' +
                ", speed='" + speed + '\'' +
                ", moves='" + (moves.length() > 20 ? moves.substring(0, 20) + "..." : moves) + '\'' +
                ", whitePlayer=" + whitePlayer +
                ", blackPlayer=" + blackPlayer +
                '}';
    }

    // Getters y setters
    public Player getWhite() {
        return whitePlayer;
    }
    public Player getBlack() {
        return blackPlayer;
    }
    public String getMoves() {
        return moves;
    }
    public String getSpeed() {
        return speed;
    }
    public String getWinner() {
        return winner;
    }
    public String getId(){
        return id;
    }
    
    
}
