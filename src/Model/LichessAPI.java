/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author kamus
 */
public class LichessAPI {
    private static final String token="lip_SKw4BXz3JhSe3YgN5Ij0";
    
    public static List<String> downloadUserGames(String username) {
        String url = "https://lichess.org/api/games/user/" + username;
        List<String> gamesList = new ArrayList<>();

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url + "?max=5&analysed=true&pgnInJson=true"))
                    .header("Authorization", "Bearer " + token)
                    .header("Accept", "application/x-ndjson")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("Partidas descargadas exitosamente.");

                // Guardar cada partida en la lista sin procesarlas
                String[] games = response.body().split("\n");
                for (String game : games) {
                    gamesList.add(game);
                }
            } else {
                System.out.println("Error al descargar partidas: " + response.statusCode() + " - " + response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return gamesList;
    }
}
