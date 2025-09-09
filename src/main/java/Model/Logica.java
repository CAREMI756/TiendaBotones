package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Logica {
    // Buscar cliente por nombre
    public static Cliente buscarClientePorNombre(String nombre) {
        String sql = "SELECT * FROM clientes WHERE nombre = ?";
        Cliente cliente = null;

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setEdad(rs.getInt("edad"));
                cliente.setSexo(rs.getString("sexo"));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar cliente: " + e.getMessage());
        }

        return cliente;
    }

    // Actualizar cliente
    public static boolean actualizarCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET telefono=?, correo=?, direccion=?, edad=?, sexo=? WHERE nombre=?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getTelefono());
            stmt.setString(2, cliente.getCorreo());
            stmt.setString(3, cliente.getDireccion());
            stmt.setInt(4, cliente.getEdad());
            stmt.setString(5, cliente.getSexo());
            stmt.setString(6, cliente.getNombre()); // criterio de búsqueda

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    public static Cliente buscarCliente(String nombre) {
        return ClienteDAO.buscarClientePorNombre(nombre);
    }

    // Buscar varios por nombre (LIKE)
    public static List<Cliente> buscarClientes(String nombre) {
        return ClienteDAO.buscarClientesPorNombre(nombre);
    }

    public static boolean eliminarClientePorId(int id) {
        return ClienteDAO.eliminarClientePorId(id);
    }

    public static boolean eliminarTodos() {
        return ClienteDAO.eliminarTodos();
    }
}
