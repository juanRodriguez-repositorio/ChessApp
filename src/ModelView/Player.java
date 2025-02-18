/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelView;

import com.google.gson.JsonObject;
/**
 *
 * @author kamus
 */
public class Player {
    
    private String name;
    private int elo;
    private String color; // "white" o "black"

    public Player(String name, int elo, String color) {
        this.name = name;
        this.elo = elo;
        this.color = color;
    }
    
    public static Player fromJson(JsonObject playerJson, String color) {
        String username = playerJson.getAsJsonObject("user").get("name").getAsString();
        int elo = playerJson.get("rating").getAsInt();
        return new Player(username, elo, color);
    }
    
    @Override
    public String toString() {
        return "Player{" +
                "username='" + this.name + '\'' +
                ", rating=" + this.elo +
                ", color='" + this.color + '\'' +
                '}';
    }


    // Getters 
    public String getName() {
        return name;
    }
    public int getElo() {
        return elo;
    }
    
    public String getColor() {
        return color;
    }
    
    
    
}
