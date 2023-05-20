package Controller;

import Model.Artista;
import Model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class Login {

    @FXML
    private TextField nombre_usuario;

    @FXML
    private Hyperlink cuenta_nueva;

    @FXML
    private Button log;

    @FXML
    private PasswordField contrasenia;

    @FXML
    private Label campo_errores;

    @FXML
    private Button boton_cerrar;


    /**
     * este metodo cambia de ventana a la de crear una cuenta, al hacer click en el texto crear cuenta
     * @param event
     * @throws IOException
     */
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
        scene.getStylesheets().add("/Styles/SigUp.css");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        // Show the primary stage
        primaryStage.show();

    }

    /**
     * este metodo contiene la logica de un login
     * @param event
     * @throws IOException
     */
    @FXML
    private void logIn(ActionEvent event) throws IOException {
        String contrasenia = this.contrasenia.getText();
        String user = nombre_usuario.getText();

        //se busca si existe el Usuario o Artista en la base de datos
        Usuario u = Singleton.getInstance().getDominio().leerUsuario(user);
        Artista a = Singleton.getInstance().getDominio().leerArtista(user);

        //si existen, ya que al hacer la peticion devuelven el Usuario/Artista, entonces es diferente de nulo
        if (u != null || a != null) {
            Usuario usuario = (u != null) ? u : a;
            //se valida que la constraseña sea la correcta
            if (usuario.getContrasenia().equals(contrasenia)) {
                if (a != null){
                    //si la Cuenta es artista, se cambia a la ventana del artista
                    Singleton.getInstance().registroLog("el artista: " + usuario.getNombreUsuario() + " ha iniciado sesion", 1, "Login");
                    cambiar_Ventana_Artista("/View/Vista_Principal_Artista.fxml", (Artista) usuario);
                }else if (u != null){
                    //si la Cuenta es Usuario, se cambia a la Ventana Usuario
                    Singleton.getInstance().registroLog("el usuario: " + usuario.getNombreUsuario() + " ha iniciado sesion", 1, "Login");
                    cambiar_Ventana_User("/View/Vista_Principal_Usuario.fxml", usuario);
                }
            } else {
                campo_errores.setText("Contraseña Incorrecta");
            }
        } else {
            //si algun campo esta vacio, muestra ese mensaje
            if (validarNulo()){
                campo_errores.setText("por favor, rellene todos los campos");
            }else{
                campo_errores.setText("Usuario no Valido");
                this.contrasenia.setText("");
                this.nombre_usuario.setText("");
            }
        }
    }

    private void cambiar_Ventana_Artista(String path, Artista user) throws IOException {
        UserSesion.getInstance(user.getContrasenia(), user.getNombreUsuario());
        Stage stage = (Stage) boton_cerrar.getScene().getWindow();
        stage.close();
        Singleton.getInstance().registroLog("ha iniciado sesion el artista: " + user.getNombreUsuario(), 1, "login" );
        Vista_Principal_Artista vista = new Vista_Principal_Artista();
        vista.init(path);
    }

    /**
     * este metodo valida si algun campo esta vacio
     * @return
     */
    private boolean validarNulo() {
        String contrasenia = this.contrasenia.getText();
        String user = this.nombre_usuario.getText();

        if (contrasenia == "" || user == ""){
            return true;
        }else {
            return false;
        }

    }


    /**
     * metodo para cambiar de ventana al hacer login
     * @param path
     * @throws IOException
     */
    private void cambiar_Ventana_User(String path, Usuario u) throws IOException {
        UserSesion.getInstance(u.getContrasenia(), u.getNombreUsuario());
        Stage stage = (Stage) boton_cerrar.getScene().getWindow();
        stage.close();
        Vista_Principal_Usuario vista = new Vista_Principal_Usuario();
        vista.init(path);
    }

    /**
     * este metodo cierra la ventana
     */
    @FXML
    void cerrar(ActionEvent event){
        Stage stage = (Stage) boton_cerrar.getScene().getWindow();
        stage.close();
    }

}

