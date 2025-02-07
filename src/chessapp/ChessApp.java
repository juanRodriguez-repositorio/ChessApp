/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chessapp;

import Model.User;
import Model.FirebaseServices;
/**
 *
 * @author kamus
 */
public class ChessApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Crea un objeto User para hacer el push
        User newUser = new User("Juan Pérez", 1200, "Oscuro");
        
        // Push del usuario a Firebase
        System.out.println("Intentando guardar un nuevo usuario...");
        FirebaseServices.pushUser(newUser);

        // Prueba de obtener un usuario por su ID
        //String userId="-OHemmAt7WXLoIZkmARv";  // El ID del usuario que queremos obtener
        //System.out.println("Buscando el usuario con ID " + userId + "...");
        //User user = FirebaseServices.getUserById(userId);

        // Si el usuario fue encontrado, mostrarlo
        
    }
    
}
