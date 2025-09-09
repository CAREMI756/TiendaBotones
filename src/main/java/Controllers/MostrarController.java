package Controllers;

// Importar clases
import Model.Cliente;
import Model.ClienteDAO;
import Utils.Paths;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

// Clase principal
public class MostrarController {

    // Botones y cajas
    @FXML
    private Button btnVolver;

    @FXML
    private TableColumn<Cliente, String> colCorreo;

    @FXML
    private TableColumn<Cliente, String> colDireccion;

    @FXML
    private TableColumn<Cliente, Integer> colEdad;

    @FXML
    private TableColumn<Cliente, String> colNombre;

    @FXML
    private TableColumn<Cliente, String> colSexo;

    @FXML
    private TableColumn<Cliente, Integer> colTelefono;

    @FXML
    private TableView<Cliente> tbCliente;

    // Variables globales
    private final ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();

    // Método initialize
    @FXML
    public void initialize() {
        // Configurar columnas
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        tbCliente.setItems(listaClientes);

        // Cargar todos los clientes al iniciar
        cargarClientes();
    }

    // Método para cargar clientes
    private void cargarClientes() {
        // Creación de lista clientes, para almacenar los clientes
        List<Cliente> clientes = ClienteDAO.obtenerTodos();
        // Limpiar espacios
        listaClientes.clear();
        // Añadir todos los datos
        listaClientes.addAll(clientes);
    }

    // Regresamos a la ventana principal
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
