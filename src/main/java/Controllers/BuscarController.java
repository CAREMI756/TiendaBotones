package Controllers;

// Importar clases
import Model.Cliente;
import Model.Logica;
import Utils.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

// Clases principales
public class BuscarController {

    // Eventos, botones y cajas
    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnVolver;

    @FXML
    private TextField txtBuscar;

    @FXML
    private TextArea txtaDatos;

    // Método de inicialización
    @FXML
    public void initialize() {
        // Inicialización si es necesario
        txtaDatos.setEditable(false);
    }

    // Evento de buscar cliente
    @FXML
    void eventBuscar(ActionEvent event) {
        // Almacenamos el nombre del textfield
        String nombre = txtBuscar.getText().trim();

        // Validación para ver si existe o no el nombre, emitiendo alerta
        if (nombre.isEmpty()) {
            mostrarAlerta("Error", "Debe ingresar un nombre para buscar.");
            return;
        }

        // Objeto cliente, enviándole de parámetro el nombre para buscarlo
        Cliente clienteEncontrado = Logica.buscarCliente(nombre);

        // Si el cliente existe
        if (clienteEncontrado != null) {
            // Mostramos los datos en el TextArea
            StringBuilder sb = new StringBuilder();
            sb.append("ID: ").append(clienteEncontrado.getId()).append("\n");
            sb.append("Nombre: ").append(clienteEncontrado.getNombre()).append("\n");
            sb.append("Edad: ").append(clienteEncontrado.getEdad()).append("\n");
            sb.append("Correo: ").append(clienteEncontrado.getCorreo()).append("\n");
            sb.append("Teléfono: ").append(clienteEncontrado.getTelefono()).append("\n");
            sb.append("Sexo: ").append(clienteEncontrado.getSexo()).append("\n");

            // Los mostramos en el textarea
            txtaDatos.setText(sb.toString());
        } else { // Si no existe
            txtaDatos.clear();
            mostrarAlerta("No encontrado", "No existe un cliente con el nombre: " + nombre);
        }
    }

    // Mostrar alerta
    private void mostrarAlerta(String titulo, String mensaje) {
        // Objeto de alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        // Añadimos titulo a la ventana
        alert.setTitle(titulo);
        // Ponemos en vacío el header de la alerta
        alert.setHeaderText(null);
        // Añadimos el mensaje
        alert.setContentText(mensaje);
        // Mostrar la alerta
        alert.showAndWait();
    }

    // Evento de regresar
    @FXML
    void eventVolver(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.GESTIONAR_VISTAPRINCIPAL_VIEW));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Vista Principal");
            stage.show();

            ((Stage) btnVolver.getScene().getWindow()).close(); // Cierra la ventana actual si lo deseas
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
