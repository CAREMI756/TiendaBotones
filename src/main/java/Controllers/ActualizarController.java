package Controllers;

// importación de clases
import Model.Cliente;
import Model.Logica;
import Utils.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

// Clase principal
public class ActualizarController {

    // Botones y cajas
    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnVolver;

    @FXML
    private ComboBox<Integer> cbEdad;

    @FXML
    private RadioButton rbFemenino;

    @FXML
    private RadioButton rbMasculino;

    @FXML
    private TextField txtBuscar;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNumero;

    private ToggleGroup grupoSexo;

    private Cliente clienteEncontrado;

    // Método initialize
    @FXML
    public void initialize() {
        // Inicializar ComboBox de edad
        for (int i = 1; i <= 100; i++) {
            // Obtener la edad segun el nombre
            cbEdad.getItems().add(i);
        }

        // Inicializar ToggleGroup para los RadioButtons
        grupoSexo = new ToggleGroup();
        rbMasculino.setToggleGroup(grupoSexo);
        rbFemenino.setToggleGroup(grupoSexo);
    }

    // Evento de actualizar
    @FXML
    void eventActualizar(ActionEvent event) {
        // Condicional para encontrar al cliente con el botón buscar
        if (clienteEncontrado != null) {
            // Si esta el cliente, se añade la información a las cajas de texto
            clienteEncontrado.setNombre(txtNombre.getText());
            clienteEncontrado.setTelefono(txtNumero.getText());
            clienteEncontrado.setCorreo(txtCorreo.getText());
            clienteEncontrado.setDireccion(txtDireccion.getText());
            clienteEncontrado.setEdad(cbEdad.getValue());

            // Condicional para evaluar los Radio Button
            if (rbMasculino.isSelected()) {
                clienteEncontrado.setSexo("Masculino");
            } else if (rbFemenino.isSelected()) {
                clienteEncontrado.setSexo("Femenino");
            }

            // Una ves encontrado se emite la alerta de encontrado o no encontrado
            if (Logica.actualizarCliente(clienteEncontrado)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Cliente actualizado correctamente");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error al actualizar cliente");
                alert.showAndWait();
            }
        }

        if (Logica.actualizarCliente(clienteEncontrado)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Actualización exitosa");
            alert.setHeaderText(null);
            alert.setContentText("Cliente actualizado correctamente ✅");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error al actualizar el cliente ❌");
            alert.showAndWait();
        }
    }

    @FXML
    void eventBuscar(ActionEvent event) {
        // Variable para almacenar el nombre ingresadoen el txtfield
        String nombre = txtBuscar.getText();

        // Almacenar cliente que fue buscado en el método buscar cliente
        clienteEncontrado = Logica.buscarClientePorNombre(nombre);

        // Sí se encuentra el cliente
        if (clienteEncontrado != null) {
            // Rellenar campos con datos del cliente
            txtNombre.setText(clienteEncontrado.getNombre());
            txtNumero.setText(clienteEncontrado.getTelefono());
            txtCorreo.setText(clienteEncontrado.getCorreo());
            txtDireccion.setText(clienteEncontrado.getDireccion());
            cbEdad.setValue(clienteEncontrado.getEdad());

            if ("Masculino".equals(clienteEncontrado.getSexo())) {
                rbMasculino.setSelected(true);
            } else if ("Femenino".equals(clienteEncontrado.getSexo())) {
                rbFemenino.setSelected(true);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Cliente no encontrado");
            alert.showAndWait();
        }
    }

    // Evento de volver
    @FXML
    void eventVolver(ActionEvent event) {
        // Try-catch para regresar a la ventana principal
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
