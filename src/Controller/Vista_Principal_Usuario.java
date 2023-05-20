package Controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import Librerias.ListaCircular;
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
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

import java.net.URL;
import java.util.ResourceBundle;

public class Vista_Principal_Usuario implements Initializable {

    AdvancedPlayer player;

    private int framePos = 0;

    private boolean pausado = false;
    @FXML
    private Slider time_slider;

    Usuario u = Singleton.getInstance().getDominio().leerUsuario(UserSesion.getInstance().getUsername());

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

    private void agregar_songs() {
        ListaCircular <Cancion> playList = u.getPlayList();
        ObservableList songs = FXCollections.observableArrayList();;
        for (int i = 0; i < playList.size(); i++){
            songs.add(playList.buscar(i).getNombre_artista() + " -- " + playList.buscar(i).getTitulo());
        }
        this.lista_canciones.setItems(songs);
    }

    @FXML
    private ListView<?> lista_canciones = new ListView<>();


    @FXML
    void agregar_canciones(ActionEvent event) throws IOException {
        Stage stage = (Stage) boton_atras.getScene().getWindow();
        stage.close();

        agregar_canciones vista = new agregar_canciones();
        vista.init();

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
    void reproducir() {
            if (!pausado) {
                new Thread(() -> {
                    try {
                        BasicPlayer player1 = new BasicPlayer();
                        player1.open(new File("C:\\Users\\HP 245 RYZEN 3\\IdeaProjects\\Umusic\\src\\Recursos\\one.wav"));
                    } catch (BasicPlayerException e) {
                        throw new RuntimeException(e);
                    }
                }).start();

            } else {
                player.stop();
                pausado = false;
            }
        }


    /*
    private void configureTimeSlider(AdvancedPlayer mediaPlayer) {
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (player != null) {
                    player.setPosition(newValue.intValue());
                }
            }
        });
    }
     */


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
        agregar_songs();
    }

}

