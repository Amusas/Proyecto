package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Principal extends Application{

    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }


    /**
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set. The primary stage will be embedded in
     * the browser if the application was launched as an applet.
     * Applications may create other stages, if needed, but they will not be
     * primary stages and will not be embedded in the browser.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Universidad");
        this.primaryStage.setResizable(false);
        this.primaryStage.initStyle(StageStyle.UNDECORATED);
        mostrarVentanaPrincipal();

    }

    /**
     * Este metodo muestra la vista principal de la aplicacion, cargando un archivo fxml
     */
    public void mostrarVentanaPrincipal(){
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/Login.fxml")); //especificacion de la ruta del recurso

            AnchorPane root = (AnchorPane) loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
