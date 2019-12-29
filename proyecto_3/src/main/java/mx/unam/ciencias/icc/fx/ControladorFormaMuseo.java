package mx.unam.ciencias.icc.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import mx.unam.ciencias.icc.Museo;

/**
 * Clase para el controlador del contenido del diálogo para editar y crear
 * museos.
 */
public class ControladorFormaMuseo extends ControladorForma {

    /* La entrada verificable para el nombre. */
    @FXML private EntradaVerificable entradaNombre;
    /* La entrada verificable para el número de salas. */
    @FXML private EntradaVerificable entradaSalas;
    /* La entrada verificable para el costo de la entrada general. */
    @FXML private EntradaVerificable entradaCostoGeneral;
    /* La entrada verificable para el costo de la entrada para estudiantes. */
    @FXML private EntradaVerificable entradaCostoEstudiantes;
    /* La entrada verificable para el número de visitantes por año. */
    @FXML private EntradaVerificable entradaVisitantes;
    /* La entrada verificable para el estacionamiento. */
    @FXML private CasillaVerificable entradaEstacionamiento;

    /* El valor del nombre. */
    private String nombre;
    /* El valor del número de salas. */
    private int salas;
    /* El valor del costo general. */
    private double costoGeneral;
    /* El valor del costo para estudiantes. */
    private double costoEstudiantes;
    /* El valor del número de visitantes. */
    private int visitantes;
    /* El valor del estacionamiento. */
    private boolean estacionamiento;

    /* El museo creado o editado. */
    private Museo museo;

    /* Inicializa el estado de la forma. */
    @FXML private void initialize() {
        entradaNombre.setVerificador(n -> verificaNombre(n));
        entradaSalas.setVerificador(c -> verificaSalas(c));
        entradaCostoGeneral.setVerificador(p -> verificaCostoGeneral(p));
        entradaCostoEstudiantes.setVerificador(e -> verificaCostoEstudiantes(e));
        entradaVisitantes.setVerificador(e -> verificaVisitantes(e));
        entradaEstacionamiento.setVerificador(e -> verificaEstacionamiento(e));

        entradaNombre.textProperty().addListener(
            (o, v, n) -> verificaMuseo());
        entradaSalas.textProperty().addListener(
            (o, v, n) -> verificaMuseo());
        entradaCostoGeneral.textProperty().addListener(
            (o, v, n) -> verificaMuseo());
        entradaCostoEstudiantes.textProperty().addListener(
            (o, v, n) -> verificaMuseo());
        entradaVisitantes.textProperty().addListener(
            (o, v, n) -> verificaMuseo());
        entradaEstacionamiento.selectedProperty().addListener(
            (o, v, n) -> verificaMuseo());
    }

    /* Manejador para cuando se activa el botón aceptar. */
    @FXML private void aceptar(ActionEvent evento) {
        actualizaMuseo();
        aceptado = true;
        escenario.close();
    }

    /**
     * Define el museo del diálogo.
     * @param estudiante el nuevo museo del diálogo.
     */
    public void setMuseo(Museo museo) {
        this.museo = museo;
        if (museo == null)
            return;
        this.museo = new Museo(null, 0, 0, 0, 0, false);
        this.museo.actualiza(museo);
        entradaNombre.setText(museo.getNombre());
        entradaSalas.setText(String.valueOf(museo.getSalas()));
        String cg = String.format("%.2f", museo.getCostoGeneral());
        entradaCostoGeneral.setText(cg);
        String ce = String.format("%.2f", museo.getCostoEstudiantes());
        entradaCostoEstudiantes.setText(ce);
        entradaVisitantes.setText(String.valueOf(museo.getVisitantes()));
        entradaEstacionamiento.setSelected(museo.getEstacionamiento());
    }

    /* Verifica que los seis campos sean válidos. */
    private void verificaMuseo() {
        boolean n  = entradaNombre.esValida();
        boolean s  = entradaSalas.esValida();
        boolean cg = entradaCostoGeneral.esValida();
        boolean ce = entradaCostoEstudiantes.esValida();
        boolean v  = entradaVisitantes.esValida();
        boolean e  = entradaEstacionamiento.esValida();
        botonAceptar.setDisable(!n || !s || !cg || !ce || !v || !e);
    }

    /* Verifica que el nombre sea válido y lo asigna a la variable de clase. */
    private boolean verificaNombre(String n) {
        if (n == null || n.isEmpty())
            return false;
        nombre = n;
        return true;
    }

    /**
     * Verifica que el número de salas sea válido y lo asigna a la variable de
     * clase.
     */
    private boolean verificaSalas(String s) {
        if (s == null || s.isEmpty())
            return false;
        try {
            salas = Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return salas >= 0;
    }

    /**
     * Verifica que el costo general sea válido y lo asigna a la variable de
     * clase.
     */
    private boolean verificaCostoGeneral(String cg) {
        if (cg == null || cg.isEmpty())
            return false;
        try {
            costoGeneral = Double.parseDouble(cg);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return costoGeneral >= 0.0;
    }

    /**
     * Verifica que el costo para estudiantes sea válido y lo asigna a la
     * variable de clase.
     */
    private boolean verificaCostoEstudiantes(String ce) {
        if (ce == null || ce.isEmpty())
            return false;
        try {
            costoEstudiantes = Double.parseDouble(ce);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return costoEstudiantes >= 0.0;
    }

    /**
     * Verifica que el número de visitantes sea válido y lo asigna a la
     * variable de clase.
     */
    private boolean verificaVisitantes(String v) {
        if (v == null || v.isEmpty())
            return false;
        try {
            visitantes = Integer.parseInt(v);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return visitantes >= 0;
    }

    /**
     * Verifica que el valor de estacionamiento sea válido y lo asigna a la
     * variable de clase.
     */
    private boolean verificaEstacionamiento(Boolean e) {
        estacionamiento = e;

        return true;
    }

    /* Actualiza al museo, o lo crea si no existe. */
    private void actualizaMuseo() {
        if (museo != null) {
            museo.setNombre(nombre);
            museo.setCostoGeneral(costoGeneral);
            museo.setCostoEstudiantes(costoEstudiantes);
            museo.setVisitantes(visitantes);
            museo.setEstacionamiento(estacionamiento);
        } else {
            museo = new Museo(nombre, salas, costoGeneral, costoEstudiantes,
                              visitantes, estacionamiento);
        }
    }

    /**
     * Regresa el museo del diálogo.
     * @return el museo del diálogo.
     */
    public Museo getMuseo() {
        return museo;
    }

    /**
     * Define el verbo del botón de aceptar.
     * @param verbo el nuevo verbo del botón de aceptar.
     */
    public void setVerbo(String verbo) {
        botonAceptar.setText(verbo);
    }

    /**
     * Define el foco incial del diálogo.
     */
    @Override public void defineFoco() {
        entradaNombre.requestFocus();
    }
}
