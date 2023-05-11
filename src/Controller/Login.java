package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Login {

    @FXML
    private Button boton_cerrar;

    @FXML
    private TextField nombre_usuario;

    @FXML
    private PasswordField contrasenia;

    @FXML
    void crear_cuenta(ActionEvent event) throws IOException {
        Stage stage = (Stage) boton_cerrar.getScene().getWindow();
        stage.close();

        // Load the new FXML file
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/SigUp.fxml"));
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
    void logIn(ActionEvent event) {

    }

    @FXML
    void cerrar(ActionEvent event){
        Stage stage = (Stage) boton_cerrar.getScene().getWindow();
        stage.close();
    }

}

