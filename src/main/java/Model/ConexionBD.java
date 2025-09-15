package Model;

// Clases importadas
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

// Clase principal
public class ConexionBD {
    // Cargar variables del archivo .env
    static Dotenv dotenv = Dotenv.load();
    private static final String URL = dotenv.get("URL"); // Url de BD
    private static final String USER = dotenv.get("USER"); // User
    private static final String PASSWORD = dotenv.get("PASSWORD"); // Password

    // Variables globales
    private static Connection connection;

    // Entablar conexión con la BD
    public static Connection getConnection() {
        // Try-catch que nos muestra el mensaje si la conexión fue exitosa o no
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Conexión exitosa a la base de datos.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar: " + e.getMessage());
        }
        return connection;
    }
}
