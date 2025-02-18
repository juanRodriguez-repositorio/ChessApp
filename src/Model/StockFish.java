/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Model;

/**
 *
 * @author kamus
 */
import java.io.*;

import java.io.*;

public class StockFish {
    private Process process;
    private BufferedWriter writer;
    private BufferedReader reader;

    public StockFish() throws IOException {
        // Inicia Stockfish como proceso
        ProcessBuilder processBuilder = new ProcessBuilder("C:\\Users\\kamus\\Documents\\stockfish\\stockfish-windows-x86-64.exe");
        processBuilder.redirectErrorStream(true);
        process = processBuilder.start();

        // Inicializa los flujos de entrada/salida
        writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        // Envía el comando "uci" para activar el modo Universal Chess Interface (UCI)
        writer.write("uci\n");
        writer.flush();

        // Espera la confirmación de Stockfish
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains("uciok")) break;
        }
    }

    public String getEvaluation(String moves) throws IOException {
        // Enviar la posición con los movimientos dados
        writer.write("position startpos moves " + moves + "\n");
        writer.flush();

        // Pedir evaluación de Stockfish
        writer.write("go depth 15\n");
        writer.flush();

        String bestMove = "N/A";
        String evaluation = "N/A";

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("info depth")) {
                // Busca la evaluación numérica
                if (line.contains("score cp")) {
                    int evalIndex = line.indexOf("score cp") + 9;
                    evaluation = line.substring(evalIndex).split(" ")[0];
                    evaluation = String.format("%.2f", Double.parseDouble(evaluation) / 100.0); // Convierte centipawns a peones
                } else if (line.contains("score mate")) {
                    // Si hay una ventaja de mate inminente
                    int evalIndex = line.indexOf("score mate") + 11;
                    evaluation = "Mate in " + line.substring(evalIndex).split(" ")[0];
                }
            }
            if (line.startsWith("bestmove")) {
                bestMove = line.split(" ")[1]; // Extrae el mejor movimiento
                break;
            }
        }

        return "Mejor jugada: " + bestMove + " | Evaluación: " + evaluation;
    }

    public void close() throws IOException {
        writer.write("quit\n");
        writer.flush();
        writer.close();
        reader.close();
    }

    public static void main(String[] args) {
        try {
            String stockfishPath = "C:\\Users\\kamus\\Documents\\stockfish\\stockfish-windows-x86-64.exe";
            StockFish stockfish = new StockFish();

            // Ejemplo de jugadas
            String moves = "e2e4 e7e5 g1f3 b8c6";
            String result = stockfish.getEvaluation(moves);
            System.out.println(result);

            stockfish.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}