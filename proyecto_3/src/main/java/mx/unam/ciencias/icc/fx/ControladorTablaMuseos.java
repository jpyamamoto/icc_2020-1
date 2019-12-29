package mx.unam.ciencias.icc.fx;

import javafx.collections.ListChangeListener.Change;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import mx.unam.ciencias.icc.Museo;
import mx.unam.ciencias.icc.Lista;

/**
 * Clase para el controlador de la tabla para mostrar la base de datos.
 */
public class ControladorTablaMuseos {

    /* La tabla. */
    @FXML private TableView<Museo> tabla;

    /* La columna del nombre. */
    @FXML private TableColumn<Museo, String> columnaNombre;
    /* La columna del número de salas. */
    @FXML private TableColumn<Museo, Number> columnaSalas;
    /* La columna del costo general. */
    @FXML private TableColumn<Museo, Number> columnaCostoGeneral;
    /* La columna del costo para estudiantes. */
    @FXML private TableColumn<Museo, Number> columnaCostoEstudiantes;
    /* La columna del número de visitantes. */
    @FXML private TableColumn<Museo, Number> columnaVisitantes;
    /* La columna del estacionamiento. */
    @FXML private TableColumn<Museo, Boolean> columnaEstacionamiento;

    /* El modelo de la selección. */
    TableView.TableViewSelectionModel<Museo> modeloSeleccion;
    /* La selección. */
    private ObservableList<TablePosition> seleccion;
    /* Lista de escuchas de selección. */
    private Lista<EscuchaSeleccion> escuchas;
    /* Los renglones en la tabla. */
    private ObservableList<Museo> renglones;

    /* Inicializa el controlador. */
    @FXML private void initialize() {
        renglones = tabla.getItems();
        modeloSeleccion = tabla.getSelectionModel();
        modeloSeleccion.setSelectionMode(SelectionMode.MULTIPLE);
        seleccion = modeloSeleccion.getSelectedCells();
        ListChangeListener<TablePosition> lcl = c -> cambioEnSeleccion();
        seleccion.addListener(lcl);
        columnaNombre.setCellValueFactory(
            c -> c.getValue().nombreProperty());
        columnaSalas.setCellValueFactory(
            c -> c.getValue().salasProperty());
        columnaCostoGeneral.setCellValueFactory(
            c -> c.getValue().costoGeneralProperty());
        columnaCostoEstudiantes.setCellValueFactory(
            c -> c.getValue().costoEstudiantesProperty());
        columnaVisitantes.setCellValueFactory(
            c -> c.getValue().visitantesProperty());
        columnaEstacionamiento.setCellValueFactory(
            c -> c.getValue().estacionamientoProperty());
        escuchas = new Lista<EscuchaSeleccion>();
    }

    /* Notifica a los escuchas que hubo un cambio en la selección. */
    private void cambioEnSeleccion() {
        for (EscuchaSeleccion escucha : escuchas)
            escucha.renglonesSeleccionados(seleccion.size());
    }

    /**
     * Limpia la tabla.
     */
    public void limpiaTabla() {
        renglones.clear();
    }

    /**
     * Agrega un renglón a la tabla.
     * @param museo el renglón a agregar.
     */
    public void agregaRenglon(Museo museo) {
        renglones.add(museo);
        tabla.sort();
    }

    /**
     * Elimina un renglón de la tabla.
     * @param museo el renglón a eliminar.
     */
    public void eliminaRenglon(Museo museo) {
        renglones.remove(museo);
        tabla.sort();
    }

    /**
     * Selecciona renglones de la tabla.
     * @param museos los renglones a seleccionar.
     */
    public void seleccionaRenglones(Lista<Museo> museos) {
        modeloSeleccion.clearSelection();
        for (Museo museo : museos)
            modeloSeleccion.select(museo);
    }

    /**
     * Regresa una lista con los registros seleccionados en la tabla.
     * @return una lista con los registros seleccionados en la tabla.
     */
    public Lista<Museo> getSeleccion() {
        Lista<Museo> seleccionados = new Lista<Museo>();
        for (TablePosition tp : seleccion) {
            int r = tp.getRow();
            seleccionados.agregaFinal(renglones.get(r));
        }
        return seleccionados;
    }

    /**
     * Regresa el museo seleccionado en la tabla.
     * @return el museo seleccionado en la tabla.
     */
    public Museo getRenglonSeleccionado() {
        int r = seleccion.get(0).getRow();
        return renglones.get(r);
    }

    /**
     * Agrega un escucha de selección.
     * @param escucha el escucha a agregar.
     */
    public void agregaEscuchaSeleccion(EscuchaSeleccion escucha) {
        escuchas.agregaFinal(escucha);
    }

    /**
     * Fuerza un reordenamiento de la tabla.
     */
    public void reordena() {
        tabla.sort();
    }

    /**
     * Enfoca la tabla.
     */
    public void enfocaTabla() {
        tabla.requestFocus();
    }
}
