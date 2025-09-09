package Controllers;

import Utils.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class VistaPrincipalController {
    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnMostrar;

    @FXML
    void escActualizar(ActionEvent event) {
        // Cargar la vista de actualizar
        try {
            // Cargar la vista de agregar
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.GESTIONAR_ACTUALIZAR_VIEW));
            // Cargar el archivo FXML
            Parent root = loader.load();

            // Obtener el controlador de la vista de agregar
            ActualizarController controller = loader.getController();
            // Mostrar las opciones de agregar

            // Crear una nueva escena con la vista de agregar
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            // Establecer el título de la ventana
            stage.setTitle("Actualizar Cliente");
            // Impedir la redimensión de la ventana
            stage.setResizable(false);
            // Mostrar la ventana
            stage.show();

            ((Stage) btnActualizar.getScene().getWindow()).close(); // Cierra la ventana actual si lo deseas
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void escAgregar(ActionEvent event) {
        // Cargar la vista de agregar
        try {
            // Cargar la vista de agregar
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.GESTIONAR_AGREGAR_VIEW));
            // Cargar el archivo FXML
            Parent root = loader.load();

            // Obtener el controlador de la vista de agregar
            AgregarController controller = loader.getController();
            // Mostrar las opciones de agregar

            // Crear una nueva escena con la vista de agregar
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            // Establecer el título de la ventana
            stage.setTitle("Agregar Nuevo Cliente");
            // Impedir la redimensión de la ventana
            stage.setResizable(false);
            // Mostrar la ventana
            stage.show();

            ((Stage) btnAgregar.getScene().getWindow()).close(); // Cierra la ventana actual si lo deseas
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void escBuscar(ActionEvent event) {
        // Cargar la vista de eliminar
        try {
            // Cargar la vista de buscar
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.GESTIONAR_BUSCAR_VIEW));
            // Cargar el archivo FXML
            Parent root = loader.load();

            // Obtener el controlador de la vista de agregar
            BuscarController controller = loader.getController();
            // Mostrar las opciones de agregar

            // Crear una nueva escena con la vista de agregar
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            // Establecer el título de la ventana
            stage.setTitle("Buscar Cliente");
            // Impedir la redimensión de la ventana
            stage.setResizable(false);
            // Mostrar la ventana
            stage.show();

            ((Stage) btnBuscar.getScene().getWindow()).close(); // Cierra la ventana actual si lo deseas
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void escEliminar(ActionEvent event) {
        // Cargar la vista de eliminar
        try {
            // Cargar la vista de buscar
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.GESTIONAR_ELIMINAR_VIEW));
            // Cargar el archivo FXML
            Parent root = loader.load();

            // Obtener el controlador de la vista de agregar
            EliminarController controller = loader.getController();
            // Mostrar las opciones de agregar

            // Crear una nueva escena con la vista de agregar
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            // Establecer el título de la ventana
            stage.setTitle("Eliminar Cliente");
            // Impedir la redimensión de la ventana
            stage.setResizable(false);
            // Mostrar la ventana
            stage.show();

            ((Stage) btnEliminar.getScene().getWindow()).close(); // Cierra la ventana actual si lo deseas
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void escMostrar(ActionEvent event) {
        // Cargar la vista de mostrar
        try {
            // Cargar la vista de buscar
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.GESTIONAR_MOSTRAR_VIEW));
            // Cargar el archivo FXML
            Parent root = loader.load();

            // Obtener el controlador de la vista de agregar
            MostrarController controller = loader.getController();
            // Mostrar las opciones de agregar

            // Crear una nueva escena con la vista de agregar
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            // Establecer el título de la ventana
            stage.setTitle("Eliminar Cliente");
            // Impedir la redimensión de la ventana
            stage.setResizable(false);
            // Mostrar la ventana
            stage.show();

            ((Stage) btnMostrar.getScene().getWindow()).close(); // Cierra la ventana actual si lo deseas
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
