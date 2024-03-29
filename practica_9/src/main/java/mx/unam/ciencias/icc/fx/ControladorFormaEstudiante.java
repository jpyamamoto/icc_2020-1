package mx.unam.ciencias.icc.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import mx.unam.ciencias.icc.Estudiante;

/**
 * Clase para el controlador del contenido del diálogo para editar y crear
 * estudiantes.
 */
public class ControladorFormaEstudiante extends ControladorForma {

    /* La entrada verificable para el nombre. */
    @FXML private EntradaVerificable entradaNombre;
    /* La entrada verificable para el número de cuenta. */
    @FXML private EntradaVerificable entradaCuenta;
    /* La entrada verificable para el promedio. */
    @FXML private EntradaVerificable entradaPromedio;
    /* La entrada verificable para la edad. */
    @FXML private EntradaVerificable entradaEdad;

    /* El valor del nombre. */
    private String nombre;
    /* El valor del número de cuenta. */
    private int cuenta;
    /* El valor del promedio. */
    private double promedio;
    /* El valor de la edad. */
    private int edad;

    /* El estudiante creado o editado. */
    private Estudiante estudiante;

    /* Inicializa el estado de la forma. */
    @FXML private void initialize() {
        entradaNombre.setVerificador(n -> verificaNombre(n));
        entradaCuenta.setVerificador(c -> verificaCuenta(c));
        entradaPromedio.setVerificador(p -> verificaPromedio(p));
        entradaEdad.setVerificador(e -> verificaEdad(e));

        entradaNombre.textProperty().addListener(
            (o, v, n) -> verificaEstudiante());
        entradaCuenta.textProperty().addListener(
            (o, v, n) -> verificaEstudiante());
        entradaPromedio.textProperty().addListener(
            (o, v, n) -> verificaEstudiante());
        entradaEdad.textProperty().addListener(
            (o, v, n) -> verificaEstudiante());
    }

    /* Manejador para cuando se activa el botón aceptar. */
    @FXML private void aceptar(ActionEvent evento) {
        actualizaEstudiante();
        aceptado = true;
        escenario.close();
    }

    /**
     * Define el estudiante del diálogo.
     * @param estudiante el nuevo estudiante del diálogo.
     */
    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
        if (estudiante == null)
            return;
        this.estudiante = new Estudiante(null, 0, 0, 0);
        this.estudiante.actualiza(estudiante);
        entradaNombre.setText(estudiante.getNombre());
        String c = String.format("%09d", estudiante.getCuenta());
        entradaCuenta.setText(c);
        String p = String.format("%2.2f", estudiante.getPromedio());
        entradaPromedio.setText(p);
        entradaEdad.setText(String.valueOf(estudiante.getEdad()));
    }

    /* Verifica que los cuatro campos sean válidos. */
    private void verificaEstudiante() {
        boolean n = entradaNombre.esValida();
        boolean c = entradaCuenta.esValida();
        boolean p = entradaPromedio.esValida();
        boolean e = entradaEdad.esValida();
        botonAceptar.setDisable(!n || !c || !p || !e);
    }

    /* Verifica que el nombre sea válido. */
    private boolean verificaNombre(String n) {
        String temp = n.trim();
        if (temp.length() > 0) {
            nombre = temp;
            return true;
        }
        return false;
    }

    /* Verifica que el número de cuenta sea válido. */
    private boolean verificaCuenta(String c) {
        try {
            int temp = Integer.valueOf(c);
            if (temp >= 1000000 && temp <= 99999999) {
                cuenta = temp;
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /* Verifica que el promedio sea válido. */
    private boolean verificaPromedio(String p) {
        try {
            double temp = Double.valueOf(p);
            if (temp >= 0.0 && temp <= 10.0) {
                promedio = temp;
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /* Verifica que la edad sea válida. */
    private boolean verificaEdad(String e) {
        try {
            int temp = Integer.valueOf(e);
            if (temp >= 13 && temp <= 99) {
                edad = temp;
                return true;
            }
            return false;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /* Actualiza al estudiante, o lo crea si no existe. */
    private void actualizaEstudiante() {
        if (estudiante != null) {
            estudiante.setNombre(nombre);
            estudiante.setCuenta(cuenta);
            estudiante.setPromedio(promedio);
            estudiante.setEdad(edad);
        } else {
            estudiante = new Estudiante(nombre, cuenta, promedio, edad);
        }
    }

    /**
     * Regresa el estudiante del diálogo.
     * @return el estudiante del diálogo.
     */
    public Estudiante getEstudiante() {
        return estudiante;
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
