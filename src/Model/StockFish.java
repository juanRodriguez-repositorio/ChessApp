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

public class StockFish {
    public static void main(String[] args) {
        // Ruta al ejecutable de Stockfish
        String stockfishPath = "C:\\Users\\kamus\\Documents\\stockfish\\stockfish-windows-x86-64.exe";  // Cambia esto a tu ruta real

        try {
            // Inicia el proceso de Stockfish
            ProcessBuilder processBuilder = new ProcessBuilder(stockfishPath);
            processBuilder.redirectErrorStream(true);  // Redirige errores a la misma salida
            Process process = processBuilder.start();

            // Flujo para escribir comandos a Stockfish
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            // Flujo para leer las respuestas de Stockfish
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // **1. Inicializa Stockfish en modo UCI**
            writer.write("uci\n");
            writer.flush();

            // Lee y muestra las respuestas iniciales de Stockfish
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);  // Muestra la salida de Stockfish
                if (line.contains("uciok")) {  // Espera hasta que Stockfish esté listo
                    break;
                }
            }

            // **2. Configura la posición inicial o una específica**
            // Ejemplo: posición inicial con movimientos e4, e5, Nf3, Nc6
            String positionCommand = "position startpos moves e2e4 e7e5 g1f3 b8c6\n";
            writer.write(positionCommand);
            writer.flush();

            // **3. Pide a Stockfish que evalúe la posición**
            writer.write("go depth 15\n");  // Evalúa con profundidad 20
            writer.flush();

            // Lee y procesa la salida de evaluación
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if (line.startsWith("bestmove")) {  // Salida final con la mejor jugada
                    System.out.println("Evaluación completada: " + line);
                    break;
                }
            }

            // **4. Cierra Stockfish**
            writer.write("quit\n");
            writer.flush();

            // Cierra los flujos y el proceso
            writer.close();
            reader.close();
            process.waitFor();
            System.out.println("Stockfish cerrado correctamente.");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}