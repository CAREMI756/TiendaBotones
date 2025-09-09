package Controllers;

// Clases importadas
import Model.Cliente;
import Model.ClienteDAO;
import Model.Logica;
import Utils.Paths;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Clase principal
public class EliminarController {

    // Eventos, botones y cajas
    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnEliminarTodo;

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

    @FXML
    private TextField txtBuscar;

    // Variables globales
    private ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();

    // Método initialize
    @FXML
    public void initialize() {
        // Columnas (usando getters de Cliente)
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        tbCliente.setItems(listaClientes);
        tbCliente.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); // seleccionar varios
    }

    // Evento de buscar por nombre
    @FXML
    void eventBuscar(ActionEvent event) {
        // Variable donde almacenamos el nombre buscado
        String nombre = txtBuscar.getText().trim();
        // Alerta si no existe el nombre
        if (nombre.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campo vacío", "Ingrese un nombre para buscar.");
            return;
        }

        // Lista de cliente encontrado
        List<Cliente> encontrados = Logica.buscarClientes(nombre); // <- plural
        // Alerta si no se encuentra el cliente
        if (encontrados.isEmpty()) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sin resultados", "No se encontraron clientes.");
            return;
        }

        // Evitar duplicados en la tabla
        for (Cliente c : encontrados) {
            // Sí existe el cliente no lo agrega
            boolean yaEsta = listaClientes.stream().anyMatch(x -> x.getId() == c.getId());
            // Agregarlo si no esta
            if (!yaEsta) listaClientes.add(c);
        }
    }

    // Evento de eliminar
    @FXML
    void eventEliminar(ActionEvent event) {
        // Seleccionamos los clientes en la tabla
        var seleccionados = tbCliente.getSelectionModel().getSelectedItems();
        // Si no seleccionamos nada se muestra una alerta
        if (seleccionados == null || seleccionados.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección vacía", "Seleccione uno o más clientes para eliminar.");
            return;
        }

        // Copia para evitar ConcurrentModification
        List<Cliente> copia = new ArrayList<>(seleccionados);

        // Variable para eliminar o no
        int ok = 0, fail = 0;
        // Bucle que recorre los clientes
        for (Cliente c : copia) {
            boolean eliminado = Logica.eliminarClientePorId(c.getId());
            if (eliminado) {
                listaClientes.remove(c);
                ok++;
            } else {
                fail++;
            }
        }

        if (fail == 0) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Eliminados", "Se eliminaron " + ok + " cliente(s).");
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Parcial", "Eliminados: " + ok + ", fallidos: " + fail + ".");
        }
    }

    // Método para eliminar todo
    @FXML
    void eventEliminarTodo(ActionEvent event) {
        // Mostramos un recuadro para ingresar la contraseña
        TextInputDialog dialog = new TextInputDialog();
        // Mensaje
        dialog.setTitle("Confirmación requerida");
        dialog.setHeaderText("Eliminar todos los registros");
        dialog.setContentText("Ingrese la contraseña:");

        // Mostrar la ventana y esperar la contraseña
        Optional<String> result = dialog.showAndWait();
        if (result.isEmpty()) return;

        // Obtenemos la contraseña
        String password = result.get();
        // Contraseña que se debe introducir
        if (!"admin123".equals(password)) {
            mostrarAlerta(Alert.AlertType.ERROR, "Acceso denegado", "Contraseña incorrecta.");
            return;
        }

        // Si se introdujo la contraseña correcta ok es 'si'
        boolean ok = Logica.eliminarTodos();
        // Evaluación de 'ok'
        if (ok) {
            listaClientes.clear();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Hecho", "Todos los clientes fueron eliminados.");
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudieron eliminar los clientes.");
        }
    }

    // Evento de volver
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

    // Evento que muestra la alerta
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
