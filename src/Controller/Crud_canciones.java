package Controller;

import Exceptions.StringNuloOrVacioException;
import Model.Artista;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

public class Crud_canciones {

    @FXML
    private Label label_error;

    @FXML
    private Button boton_subir;

    @FXML
    private Button boton_volver;

    @FXML
    private TextField campo_artista;

    @FXML
    private TextField campo_cancion;

    /**
     * con este metodo, permite al artista crear una cancion para subir al servicio
     * @param event
     */
    @FXML
    void subir_camcion(ActionEvent event) throws StringNuloOrVacioException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open FLAC File");

        //escoje un archivo flac, solo se admiten audios hq
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("FLAC Files", "*.flac"));
        File song = fileChooser.showOpenDialog(null);

        if (song != null) {
                if (!validarNulo()){
                    Artista a = Singleton.getInstance().buscarArtistaValor(UserSesion.getInstance(null, null).getUsername());
                    a.crearCancion(campo_cancion.getText(), song.getPath());
                    Singleton.getInstance().registroLog("se ha agregado la cancion: " + campo_cancion.getText(), 1, "cruds");
                    label_error.setText("Cancion agregada con exito!!");
                }else {
                    label_error.setText("Por favor, rellena los campos.");
                }
        }else {
            label_error.setText("Por favor, escoje un archivo.");
        }
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        Singleton.getInstance().guardarDominio();
        Stage stage = (Stage) boton_subir.getScene().getWindow();
        stage.close();

        // Load the new FXML file
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/View/Vista_Principal_Artista.fxml"));
        Parent root = loader.load();

        // Create the scene and set it as the root of the primary stage
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/Styles/Artista.css");
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

        loader.setLocation(getClass().getResource("/View/Crud_canciones.fxml"));
        Parent root = loader.load();

        // Create the scene and set it as the root of the primary stage
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/Styles/Canciones.css");

        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        // Show the primary stage
        primaryStage.show();
    }

    /**
     * este metodo valida si algun campo esta vacio
     * @return
     */
    private boolean validarNulo() {
        String cancion = this.campo_cancion.getText();
        String artista = this.campo_artista.getText();

        if (cancion == "" || artista == ""){
            return true;
        }else {
            return false;
        }

    }
}
