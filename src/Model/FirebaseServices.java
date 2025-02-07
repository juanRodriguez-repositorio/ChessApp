/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.concurrent.TimeUnit;
import com.google.firebase.database.DataSnapshot;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 *
 * @author kamus
 */
public class FirebaseServices {
    private static FirebaseDatabase firebaseDatabase;
    private static void initFirebase() {
         // Verifica si ya existe una instancia de FirebaseApp
        if (!FirebaseApp.getApps().isEmpty()) {
            System.out.println("Firebase ya está inicializado.");
            return;  // Retorna si ya está inicializado
        }
        
        try {
            // Verificar que el archivo JSON existe
            File file = new File("C:/Users/kamus/Documents/NetBeansProjects/ChessApp/Credentials/admin-key.json");
            if (!file.exists()) {
                System.out.println("Error: El archivo de credenciales no existe en la ruta especificada.");
                return;
            }
            System.out.println("Archivo JSON encontrado en la ruta especificada.");
            
            // Configuración de Firebase
            FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new FileInputStream("C:\\Users\\kamus\\Documents\\NetBeansProjects\\ChessApp\\Credentials\\admin-key.json")))
                    .setDatabaseUrl("https://chessapp-dbab6-default-rtdb.firebaseio.com/")
                
                    .build();

            FirebaseApp.initializeApp(firebaseOptions);
            firebaseDatabase = FirebaseDatabase.getInstance();
            System.out.println("La conexión se realizó exitosamente...");
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (RuntimeException ex) {   
            ex.printStackTrace();
        }
    };
    public static void pushUser(User user) {
        if (user == null) {
            System.out.println("El usuario es null, no se puede guardar.");
            return;
        }

        // Inicializa Firebase
        System.out.println("Inicializando Firebase...");
        initFirebase();

        // Verifica que la conexión a Firebase haya sido exitosa
        if (firebaseDatabase == null) {
            System.out.println("Error: FirebaseDatabase es null, no se puede continuar.");
            return;
        }

        // Obtiene la referencia a la ruta /users
        DatabaseReference userReference = firebaseDatabase.getReference("users");

        // Crea un CountDownLatch para esperar que Firebase complete la escritura
        CountDownLatch countDownLatch = new CountDownLatch(1);

        // Intenta guardar el usuario
        System.out.println("Guardando el usuario...");
        try {
            // Push para agregar el usuario a la base de datos
            userReference.push().setValue(user, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError != null) {
                        System.out.println("Error al guardar el usuario: " + databaseError.getMessage());
                        System.out.println("Detalles: " + databaseError.getDetails());
                    } else {
                        System.out.println("Usuario guardado exitosamente con ID: " + databaseReference.getKey());
                    }
                    countDownLatch.countDown(); // Libera el CountDownLatch
                }
            });

            // Espera hasta que Firebase complete la operación o se agote el tiempo
            if (!countDownLatch.await(1000, TimeUnit.SECONDS)) {
                System.out.println("Tiempo de espera agotado: Firebase no respondió.");
            }
        } catch (Exception e) {
            System.out.println("Error inesperado durante la operación de guardado:");
            e.printStackTrace();
        }
    };
    
    public static User getUserById(String firebaseId) {
    // Verifica si el firebaseId es válido
    if (firebaseId == null || firebaseId.isEmpty()) {
        System.out.println("El ID del usuario es null o vacío, no se puede buscar.");
        return null;
    }

    // Inicializa Firebase
    System.out.println("Inicializando Firebase...");
    initFirebase();

    // Verifica que la conexión a Firebase haya sido exitosa
    if (firebaseDatabase == null) {
        System.out.println("Error: FirebaseDatabase es null, no se puede continuar.");
        return null;
    }

    // Obtiene la referencia a la ruta /users
    DatabaseReference userReference = firebaseDatabase.getReference("users");

    // Crea un CountDownLatch para esperar que Firebase complete la lectura
    CountDownLatch countDownLatch = new CountDownLatch(1);

    // Contenedor para almacenar el usuario recuperado
    final User[] retrievedUser = new User[1];

    // Intentamos obtener el usuario por su ID de Firebase
    System.out.println("Buscando el usuario con ID Firebase: " + firebaseId);
    try {
        userReference.child(firebaseId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Si el usuario existe, lo mapeamos al objeto User
                    retrievedUser[0] = dataSnapshot.getValue(User.class);
                } else {
                    System.out.println("Usuario con ID Firebase " + firebaseId + " no encontrado.");
                }
                countDownLatch.countDown(); // Liberamos el CountDownLatch
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Error al buscar el usuario: " + databaseError.getMessage());
                countDownLatch.countDown(); // Liberamos el CountDownLatch en caso de error
            }
        });

        // Esperamos hasta que Firebase complete la operación o se agote el tiempo
        if (!countDownLatch.await(1000, TimeUnit.SECONDS)) {
            System.out.println("Tiempo de espera agotado: Firebase no respondió.");
        }
    } catch (Exception e) {
        System.out.println("Error inesperado durante la operación de obtención:");
        e.printStackTrace();
    }

    // Devolvemos el usuario recuperado o null si no se encontró
    return retrievedUser[0];
    }
}
