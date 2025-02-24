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

    public String[] getEvaluation(String fen) throws IOException {
        // Enviar la posición con los movimientos dados
        writer.write("position fen " + fen + "\n");
        writer.flush();

        // Pedir evaluación de Stockfish
        writer.write("go movetime 3000\n");
        writer.flush();

        String bestMove = "N/A";
        String evaluation = "N/A";
        String evaluationMate="";
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("info depth")) {
                // Busca la evaluación numérica
                if (line.contains("score cp")) {
                    int evalIndex = line.indexOf("score cp") + 9;
                    evaluation = line.substring(evalIndex).split(" ")[0];
                    evaluation = String.format("%.2f", Double.parseDouble(evaluation) / 100.0); // Convierte centipawns a peones
                    System.out.println("here"+evaluation);
                } else if (line.contains("score mate")) {
                    // Si hay una ventaja de mate inminente
                    int scoreMate = line.indexOf("score mate") + 11;
                    evaluationMate = " mate en " + "<span style='color:green; font-size:16pt;'>"+line.substring(scoreMate).split(" ")[0]+"</span>";
                }
            }
            if (line.startsWith("bestmove")) {
                bestMove = line.split(" ")[1]; // Extrae el mejor movimiento
                break;
            }
        }

        return new String[]{bestMove,evaluation,evaluationMate};
    }

    public void close() throws IOException {
        writer.write("quit\n");
        writer.flush();
        writer.close();
        reader.close();
    }

    
}