package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class agregar_canciones {

    @FXML
    private Button boton_anadir;

    @FXML
    private Button boton_atras;

    @FXML
    void anadir_cancion(ActionEvent event) {

    }

    @FXML
    void volver(ActionEvent event) {

    }

    public void init() throws IOException {
        // Load the new FXML file
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/View/Agregar_Canciones"));
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
}

