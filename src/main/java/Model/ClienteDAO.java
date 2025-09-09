package Model;

// Importar librerias
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Clase principal
public class ClienteDAO {
    // Clase para insertar cliente utilizada en AgregarController
    public static boolean insertarCliente(Cliente cliente) {
        // Query de sql
        String sql = "INSERT INTO clientes (nombre, telefono, correo, direccion, edad, sexo) VALUES (?, ?, ?, ?, ?, ?)";

        // Try-catch para capturar cualquier error en la conexión e insertar la query
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getTelefono());
            stmt.setString(3, cliente.getCorreo());
            stmt.setString(4, cliente.getDireccion());
            stmt.setInt(5, cliente.getEdad());
            stmt.setString(6, cliente.getSexo());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar cliente: " + e.getMessage());
            return false;
        }
    }

    // Método para buscar cliente utilizada Actualizar y buscar
    public static Cliente buscarClientePorNombre(String nombre) {
        // Query de sql para eliminar
        String sql = "SELECT * FROM clientes WHERE nombre = ?";

        // Try-catch para capturar exceptions en caso de fallo con conexión de datos
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setEdad(rs.getInt("edad"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setSexo(rs.getString("sexo"));
                return cliente;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Buscar clientes almacenados en la lista para eliminar
    public static List<Cliente> buscarClientesPorNombre(String nombre) {
        // Query para buscar
        String sql = "SELECT * FROM clientes WHERE nombre LIKE ?";
        // Arraylist para almacenar al cliente
        List<Cliente> lista = new ArrayList<>();

        // Conexión con base de datos y capturar exepción en caso de ser necesario
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setCorreo(rs.getString("correo"));
                c.setTelefono(rs.getString("telefono"));
                c.setEdad(rs.getInt("edad"));
                c.setDireccion(rs.getString("direccion"));
                c.setSexo(rs.getString("sexo"));
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Eliminar cliente por Id, utilizada en eliminar
    public static boolean eliminarClientePorId(int id) {
        // Query para eliminar
        String sql = "DELETE FROM clientes WHERE id = ?";

        // Captura de exceptions en la conexión con la base de datos
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método que elimina todo
    public static boolean eliminarTodos() {
        // Query que elimina todos los registros
        String sql = "DELETE FROM clientes"; // o TRUNCATE TABLE clientes;

        // Captura de exeptions mediante el Try-catch
        try (Connection conn = ConexionBD.getConnection();
             Statement st = conn.createStatement()) {

            st.executeUpdate(sql);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para mostrar todos los clientes utilizada en MostrarController
    public static List<Cliente> obtenerTodos() {
        // Query para seleccionarlos
        String sql = "SELECT * FROM clientes";
        // Lista que almacenará los clientes
        List<Cliente> lista = new ArrayList<>();

        // Try-catch para capturar las exceptions en la conexión e inserción de la BD
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setCorreo(rs.getString("correo"));
                c.setTelefono(rs.getString("telefono"));
                c.setEdad(rs.getInt("edad"));
                c.setDireccion(rs.getString("direccion"));
                c.setSexo(rs.getString("sexo"));
                lista.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
