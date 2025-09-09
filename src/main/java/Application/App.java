package Application;

import Utils.Paths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class App extends Application {
    //  Variables globales
    public static App app;
    private Stage stageWindow;

    public static void main(String[] args) {
        // Llama al método de inicio de la aplicación
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Inicializa la aplicación y la ventana centrada
        app = this;
        // Carga la vista inicial
        stageWindow = stage;
        // Establece la escena inicial
        setScene(Paths.GESTIONAR_VISTAPRINCIPAL_VIEW);
        // Hace que no se pueda redimensionar la ventana
        stage.setResizable(false);
    }

    // Método para cambiar la escena de la aplicación
    public void setScene(String path) {
        // Carga la vista desde el archivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        // Carga la vista y establece la escena
        try {
            // Carga el archivo FXML y establece la escena
            AnchorPane pane = loader.load();
            // crea una nueva escena con el pane cargado
            Scene scene = new Scene(pane);
            // Establece la escena en la ventana
            stageWindow.setScene(scene);
            // Hace que no se pueda redimensionar la ventana
            stageWindow.setResizable(false);
            // Establece un título para la ventana
            stageWindow.setTitle("Vista Principal");
            // Muestra la ventana
            stageWindow.show();
        } catch (Exception e) {
            // Maneja la excepción si no se puede cargar el archivo FXML
            e.printStackTrace();
        }
    }
}
