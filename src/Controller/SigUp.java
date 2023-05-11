package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SigUp {

    @FXML
    private TextField nombre_usuario;

    @FXML
    private TextField contrasenia;

    @FXML
    private Button cuenta;

    @FXML
    private TextField nombre;

    @FXML
    private Button boton_cerrar;

    @FXML
    void cerrar_ventana(ActionEvent event) {
        Stage stage = (Stage) boton_cerrar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void crear_usuario(ActionEvent event) {

    }

}
