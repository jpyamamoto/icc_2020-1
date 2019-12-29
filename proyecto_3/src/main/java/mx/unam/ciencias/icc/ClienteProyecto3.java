package mx.unam.ciencias.icc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mx.unam.ciencias.icc.fx.ControladorInterfazMuseos;
import mx.unam.ciencias.icc.fx.ControladorTablaMuseos;

/**
 * ClienteProyecto3: Parte del cliente para proyecto 3: Base de datos en red e
 * interfaz gráfica.
 */
public class ClienteProyecto3 extends Application {

    /* Vista de la interfaz museos. */
    private static final String INTERFAZ_MUSEOS_FXML =
        "fxml/interfaz-museos.fxml";
    /* Vista de la tabla de museos. */
    private static final String TABLA_MUSEOS_FXML =
        "fxml/tabla-museos.fxml";
    /* Ícono de la Facultad de Ciencias. */
    private static final String ICONO_CIENCIAS =
        "icons/ciencias.png";

    /**
     * Inicia la aplicación.
     * @param escenario la ventana principal de la aplicación.
     * @throws Exception si algo sale mal. Literalmente así está definido.
     */
    @Override public void start(Stage escenario) throws Exception {
        ClassLoader cl = getClass().getClassLoader();
        String url = cl.getResource(ICONO_CIENCIAS).toString();
        escenario.getIcons().add(new Image(url));
        escenario.setTitle("Administrador de Museos");

        FXMLLoader cargador =
            new FXMLLoader(cl.getResource(TABLA_MUSEOS_FXML));
        GridPane tablaMuseo = (GridPane)cargador.load();
        ControladorTablaMuseos controladorTablaMuseos =
            cargador.getController();

        cargador = new FXMLLoader(cl.getResource(INTERFAZ_MUSEOS_FXML));
        BorderPane interfazMuseos = (BorderPane)cargador.load();
        interfazMuseos.setCenter(tablaMuseo);
        ControladorInterfazMuseos controladorInterfazMuseos =
            cargador.getController();
        controladorInterfazMuseos.setEscenario(escenario);
        controladorInterfazMuseos.setControladorTablaMuseos(
            controladorTablaMuseos);

        Scene escena = new Scene(interfazMuseos);
        escenario.setScene(escena);
        escenario.setOnCloseRequest(e -> controladorInterfazMuseos.salir(null));
        escenario.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
