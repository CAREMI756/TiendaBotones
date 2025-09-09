package Controllers;

// Agregar clases
import Model.Cliente;
import Model.ClienteDAO;
import Utils.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

// Clase principal
public class AgregarController {

    // Botones, eventos y cajas
    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnVolver;

    @FXML
    private ComboBox<Integer> cbEdad;

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

    @FXML
    private RadioButton rbFemenino;

    @FXML
    private RadioButton rbMasculino;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNumero;

    @FXML
    void cbEdad(ActionEvent event) {}

    @FXML
    void eventCorreo(ActionEvent event) {}

    @FXML
    void eventDireccion(ActionEvent event) {}

    @FXML
    void eventNombre(ActionEvent event) {}

    @FXML
    void eventNumero(ActionEvent event) {}

    // Método de inicialización
    @FXML
    public void initialize() {
        // Agregamos la información a la tabla
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));

        cbEdad.setVisibleRowCount(5); // Mostrar 5 filas visibles
        // Llenar el ComboBox de 18 a 70
        for (int i = 18; i <= 70; i++) {
            cbEdad.getItems().add(i);
        }

        // Agrupar los RadioButton
        ToggleGroup group = new ToggleGroup();
        rbMasculino.setToggleGroup(group);
        rbFemenino.setToggleGroup(group);
    }

    // Evento de agregar
    @FXML
    void eventAgregar(ActionEvent event) {
        // Creamos objeto cliente
        Cliente cliente = new Cliente();

        // Obtenemos los textos y los añadimos a los getter and setters
        cliente.setNombre(txtNombre.getText());
        cliente.setCorreo(txtCorreo.getText());
        cliente.setTelefono(txtNumero.getText());
        cliente.setEdad(cbEdad.getValue());
        cliente.setDireccion(txtDireccion.getText());
        if (rbMasculino.isSelected()) {
            cliente.setSexo("Masculino");
        } else if (rbFemenino.isSelected()) {
            cliente.setSexo("Femenino");
        } else {
            cliente.setSexo("No definido");
        }

        // Guardar en la base de datos
        if (ClienteDAO.insertarCliente(cliente)) {
            // Agrega a la tabla
            tbCliente.getItems().add(cliente);

            // Limpiar campos
            txtNombre.clear();
            txtCorreo.clear();
            txtNumero.clear();
            txtDireccion.clear();
            cbEdad.setValue(null);
            rbMasculino.setSelected(false);
            rbFemenino.setSelected(false);
        }
    }

    // Evento de volver a la ventana principal
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
