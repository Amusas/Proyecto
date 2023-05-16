package Controller;

import Model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Vista_Principal_Usuario implements Initializable {

    Usuario userSesion;

    @FXML
    private Button boton_atras;

    @FXML
    private Label label_bienvenida = new Label();

    @FXML
    private Button boton_play;

    @FXML
    private Button boton_anterior;

    @FXML
    private Button boton_siguiente;

    @FXML
    private TextField campo_buscar;

    @FXML
    private Button boton_buscar;

    @FXML
    private Button boton_agregar;

    @FXML
    private ListView<?> lista_canciones;

    @FXML
    void agregar_canciones(ActionEvent event) {

    }

    @FXML
    void buscar(ActionEvent event) {

    }

    @FXML
    void obtener_Siguiente(ActionEvent event) {

    }

    @FXML
    void obtener_anterior(ActionEvent event) {

    }

    @FXML
    void reproducir(ActionEvent event) {

    }

    public void set_user (Usuario u){
        this.userSesion = u;
    }

    /**
     * este metodo inicializa la ventana
     * @param path
     * @throws IOException
     */
    public void init (String path) throws IOException {
        // Load the new FXML file
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource(path));
        Parent root = loader.load();

        // Create the scene and set it as the root of the primary stage
        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);

        // Show the primary stage
        primaryStage.show();

    }

    /**
     * con este evento, se cierra la ventana actual y cierra la sesion del usuario, devolviendose a la ventana del login
     * @param event
     * @throws IOException
     */
    @FXML
    void atras(ActionEvent event) throws IOException {
        UserSesion.getInstance(null, null).logout();
        Stage stage = (Stage) boton_atras.getScene().getWindow();
        stage.close();

        // Load the new FXML file
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/View/Login.fxml"));
        Parent root = loader.load();

        // Create the scene and set it as the root of the primary stage
        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        // Show the primary stage
        primaryStage.show();
    }

    /**
     * metodo para dar el mensaje de bienvenida al usuario
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label_bienvenida.setText("Bienvenido: " + UserSesion.getInstance(null, null).getUsername());
    }
}

