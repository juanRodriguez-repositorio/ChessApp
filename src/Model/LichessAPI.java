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
/**
 *
 * @author kamus
 */
public class LichessAPI {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {
        String token = "lip_k8M5bhW8TVcpv23sPjSp";  // Reemplaza con tu token de Lichess
        String username = "MagnusCarlsen"; // Cambia por el nombre del jugador

        // Descargar partidas del usuario
        //downloadUserGames(token, username);

        // Analizar una partida específica
        //String gameId = "19o9Pq0s"; // Cambia por el ID de la partida que deseas analizar
        //analyzeGame(token, gameId);
        String pgn = "e4 e5 Nf3 Nc6 Bc4 Bc5 Bxf7+";  // Los primeros movimientos de la partida

        // Analizar la posición con el PGN
       
        
        
        
     }
      public static void downloadUserGames(String token, String username) {
        String url = "https://lichess.org/api/games/user/" + username;

        try {
            // Crear cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Crear solicitud GET
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url + "?max=10&analysed=true&pgnInJson=true"))
                    .header("Authorization", "Bearer " + token)
                    .header("Accept", "application/x-ndjson")
                    .build();

            // Enviar solicitud
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("Partidas descargadas exitosamente.");

                // Procesar cada línea de NDJSON
                String[] games = response.body().split("\n");
                for (String game : games) {
                    JsonElement gameJson = JsonParser.parseString(game);
                    System.out.println("Partida: " + game);
                    System.out.println("Apertura: " + gameJson.getAsJsonObject().get("opening"));
                }
            } else {
                System.out.println("Error al descargar partidas: " + response.statusCode() + " - " + response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    };
    public static void analyzeGame(String token, String gameId) {
        String url = "https://lichess.org/game/export/" + gameId;

        try {
            // Crear cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Crear solicitud GET
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url + "?analysed=true&tags=true&clocks=true"))
                    .header("Authorization", "Bearer " + token)
                    .header("Accept", "application/json")
                    .build();

            // Enviar solicitud
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("Partida analizada exitosamente.");

                // Procesar respuesta JSON
                JsonElement gameJson = JsonParser.parseString(response.body());
                System.out.println("Detalles de la partida: " + gameJson);

                // Información relevante
                System.out.println("Jugadores: " + gameJson.getAsJsonObject().get("players"));
                System.out.println("Movimientos: " + gameJson.getAsJsonObject().get("moves"));
                System.out.println("Resultado: " + gameJson.getAsJsonObject().get("status"));
            } else {
                System.out.println("Error al analizar la partida: " + response.statusCode() + " - " + response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    };
    

}
