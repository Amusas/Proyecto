package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SigUp {

    @FXML
    private TextField nombre_usuario;

    @FXML
    private Button cuenta;

    @FXML
    private TextField nombre;

    @FXML
    private PasswordField contrasenia;

    @FXML
    private CheckBox check_usuario;

    @FXML
    private CheckBox check_Artista;

    @FXML
    private Label campo_error;

    @FXML
    private Button boton_cerrar;

    /*
    metodos para que no se puedan seleccionar los dos tipos de cuenta a la vez
     */
    @FXML
    void check_Usuario(ActionEvent event) {
        if (check_usuario.isSelected()) {
            check_Artista.setDisable(true);
        } else {
            check_Artista.setDisable(false);
        }
    }

    @FXML
    void check_artista(ActionEvent event) {
        if (check_Artista.isSelected()) {
            check_usuario.setDisable(true);
        } else {
            check_usuario.setDisable(false);
        }
    }

    /**
     * este metodo cierra la ventana actual y regresa a la ventana login
     * @param event
     * @throws IOException
     */
    @FXML
    void cerrar_ventana(ActionEvent event) throws IOException {
        Stage stage = (Stage) boton_cerrar.getScene().getWindow();
        stage.close();

        // Load the new FXML file
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/Login.fxml"));
        Parent root = loader.load();

        // Create the scene and set it as the root of the primary stage
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/Styles/Login.css");
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        // Show the primary stage

        primaryStage.show();
    }

    /**
     * contiene la logica de crear un usuario y artista
     * @param event
     * @throws Exception
     */
    @FXML
    void crear_usuario(ActionEvent event) throws Exception {

        String nombre = this.nombre.getText();
        String contrasenia = this.contrasenia.getText();
        String user = this.nombre_usuario.getText();

        //si no hay ningun campo vacio procede a crear el usuario/artista
       if (!validarNulo()){

           //verifica que no exista ya el nombre de usuario
           if (Singleton.getInstance().getDominio().leerUsuario(user) != null || Singleton.getInstance().getDominio().leerArtista(user) != null){
               campo_error.setText("este nombre de usuario ya existe");
           }else {

               //dependiendo de que casilla de cuenta este seleccionada, se crea un User o artista
               if (check_usuario.isSelected()){
                   Singleton.getInstance().getDominio().crearUusario(nombre, user, contrasenia);
                   campo_error.setTextFill(Color.GREEN);
                   campo_error.setText("Usuario Creado");
                   Singleton.getInstance().guardarDominio();
                   Singleton.getInstance().registroLog("se ha creado el Usuario: " + user, 1, "Sigup");

               }else if (check_Artista.isSelected()){
                   Singleton.getInstance().getDominio().crearArtista(user, contrasenia);
                   campo_error.setTextFill(Color.GREEN);
                   campo_error.setText("Usuario Creado");
                   Singleton.getInstance().guardarDominio();
                   Singleton.getInstance().registroLog("se ha creado el Artista: " + user, 1, "SigUp");


               }else{
                   campo_error.setText("por favor, selecciona su tipo de cuenta");
               }
           }
       }else {
           campo_error.setText("por favor, rellene todos los campos");
       }

    }

    /**
     * este metodo valida si algun campo esta vacio
     * @return
     */
    private boolean validarNulo() {
        String contrasenia = this.contrasenia.getText();
        String user = this.nombre_usuario.getText();
        String nombre = this.nombre.getText();

        if (contrasenia == "" || user == "" || nombre == "") {
            return true;
        } else {
            return false;
        }
    }

}
