package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

public class Crud_canciones {

    @FXML
    private Button boton_subir;

    @FXML
    private Button boton_volver;

    @FXML
    private TextField campo_artista;

    @FXML
    private TextField campo_cancion;

    @FXML
    void subir_camcion(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open MP3 File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("FLAC Files", "*.flac"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            // Do something with the selected file
        }
    }

    @FXML
    void volver(ActionEvent event) {

    }

}
