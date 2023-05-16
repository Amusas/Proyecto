package Controller;

import Librerias.ArbolBinario;
import Model.Artista;
import Model.Cancion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Vista_Principal_Artista implements Initializable {

    @FXML
    private Button boton_agregar;

    UserSesion artist = UserSesion.getInstance(null, null);

    Artista a = Singleton.getInstance().buscarArtistaValor(artist.getUsername());

    ArbolBinario<String , Cancion> canciones = a.getListaCanciones();


    @FXML
    private ListView<?> lista_canciones = new ListView<>();


    private void agregar_songs() {
        ObservableList songs = FXCollections.observableArrayList();;
        for (int i = 0; i < this.canciones.length(); i++){
            songs.add(a.getNombreArtista() + " -- " + canciones.buscarPorIndice(i));
        }
        this.lista_canciones.setItems(songs);
    }

    @FXML
    void agregar_canciones(ActionEvent event) throws IOException {
        Stage stage = (Stage) boton_agregar.getScene().getWindow();
        stage.close();
        Crud_canciones vista = new Crud_canciones();
        vista.init();
    }

    /**
     * con este evento, se cierra la ventana actual y cierra la sesion del usuario, devolviendose a la ventana del login
     * @throws IOException
     */
    @FXML
    void init(String path) throws IOException {
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

    @FXML
    void logOut(ActionEvent event) throws IOException {
        UserSesion.getInstance(null, null).logout();
        Singleton.getInstance().guardarDominio();
        Stage stage = (Stage) boton_agregar.getScene().getWindow();
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        agregar_songs();
    }
}
