package Controller;
import Model.Cancion;
import Model.Usuario;
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
import java.util.ArrayList;
import java.util.ResourceBundle;


public class agregar_canciones implements Initializable {

    @FXML
    private Button boton_anadir;

    @FXML
    private Button boton_atras;

    @FXML
    private ListView<?> lista_canciones = new ListView<>();


    private void agregar_songs() {

        ArrayList <Cancion> cancio = Singleton.getInstance().getDominio().getCanciones();
        ObservableList songs = FXCollections.observableArrayList();
        for (int i = 0; i < cancio.size(); i++){
            songs.add(cancio.get(i).getNombre_artista() + " -- " + cancio.get(i).getTitulo());
        }
        this.lista_canciones.setItems(songs);

    }



    @FXML
    void anadir_cancion(ActionEvent event) {
        int selectedIndex = lista_canciones.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            ArrayList <Cancion> songs = Singleton.getInstance().getDominio().getCanciones();
            String nombre = UserSesion.getInstance().getUsername();
            Usuario u = Singleton.getInstance().getDominio().leerUsuario(nombre);
            u.agregar_cancion((songs.get(selectedIndex)));
        }
    }


    @FXML
    void volver(ActionEvent event) throws IOException {
        Stage stage = (Stage) boton_atras.getScene().getWindow();
        stage.close();

        // Load the new FXML file
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/View/Vista_Principal_Usuario.fxml"));
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

    public void init() throws IOException {
        // Load the new FXML file
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/View/Agregar_Canciones.fxml"));
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

