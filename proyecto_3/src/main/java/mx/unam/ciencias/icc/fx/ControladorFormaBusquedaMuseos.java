package mx.unam.ciencias.icc.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import mx.unam.ciencias.icc.CampoMuseo;

/**
 * Clase para el controlador del contenido del diálogo para buscar museos.
 */
public class ControladorFormaBusquedaMuseos extends ControladorForma {

    /* El combo del campo. */
    @FXML private ComboBox<CampoMuseo> opcionesCampo;
    /* El campo de texto para el valor. */
    @FXML private EntradaVerificable entradaValor;
    /* La casilla para buscar por un booleano. */
    @FXML private CasillaVerificable casillaValor;

    /* Inicializa el estado de la forma. */
    @FXML private void initialize() {
        entradaValor.setVerificador(s -> verificaValor(s));
        entradaValor.textProperty().addListener(
            (o, v, n) -> revisaValor(null));
    }

    /* Revisa el valor después de un cambio. */
    @FXML private void revisaValor(ActionEvent evento) {
        Tooltip.install(entradaValor, getTooltip());
        String s = entradaValor.getText();
        botonAceptar.setDisable(!entradaValor.esValida());
        entradaValor.setVisible(opcionesCampo.getValue() != CampoMuseo.ESTACIONAMIENTO);
        casillaValor.setVisible(opcionesCampo.getValue() == CampoMuseo.ESTACIONAMIENTO);
    }

    /* Manejador para cuando se activa el botón aceptar. */
    @FXML private void aceptar(ActionEvent evento) {
        aceptado = true;
        escenario.close();
    }

    /* Obtiene la pista. */
    private Tooltip getTooltip() {
        String m = "";
        switch (opcionesCampo.getValue()) {
        case NOMBRE:
            m = "Ingresa al menos un caracter para buscar.";
            break;
        case SALAS:
            m = "Ingresa el número de salas a buscar.";
            break;
        case COSTOGENERAL:
            m = "Ingresa un número para buscar por costo.";
            break;
        case COSTOESTUDIANTES:
            m = "Ingresa un número para buscar por costo.";
            break;
        case VISITANTES:
            m = "Ingresa el número de visitantes/año a buscar.";
            break;
        case ESTACIONAMIENTO:
            m = "¿El museo tiene estacionamiento o no?";
            break;
        }
        return new Tooltip(m);
    }

    /* Verifica el valor. */
    private boolean verificaValor(String s) {
        switch (opcionesCampo.getValue()) {
        case NOMBRE:           return verificaNombre(s);
        case SALAS:            return verificaSalas(s);
        case COSTOGENERAL:
        case COSTOESTUDIANTES: return verificaCostos(s);
        case VISITANTES:       return verificaVisitantes(s);
        case ESTACIONAMIENTO:  return true; // La casilla siempre toma un valor booleano, por defecto false.
        default:               return false;
        }
    }

    /* Verifica que el nombre a buscar sea válido. */
    private boolean verificaNombre(String n) {
        return n != null && !n.isEmpty();
    }

    /* Verifica que el número de salas a buscar sea válido. */
    private boolean verificaSalas(String s) {
        if (s == null || s.isEmpty())
            return false;
        int salas = -1;
        try {
            salas = Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return salas >= 0;
    }

    /* Verifica que la entrada sea un costo válido. */
    private boolean verificaCostos(String c) {
        if (c == null || c.isEmpty())
            return false;
        double costo = -1.0;
        try {
            costo = Double.parseDouble(c);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return costo >= 0.0;
    }

    /* Verifica que el número de visitantes a buscar sea válido. */
    private boolean verificaVisitantes(String v) {
        if (v == null || v.isEmpty())
            return false;
        int visitantes = -1;
        try {
            visitantes = Integer.parseInt(v);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return visitantes >= 0;
    }

    /**
     * Regresa el campo seleccionado.
     * @return el campo seleccionado.
     */
    public CampoMuseo getCampo() {
        return opcionesCampo.getValue();
    }

    /**
     * Regresa el valor ingresado.
     * @return el valor ingresado.
     */
    public Object getValor() {
        switch (opcionesCampo.getValue()) {
        case NOMBRE:           return entradaValor.getText();
        case SALAS:            return Integer.parseInt(entradaValor.getText());
        case COSTOGENERAL:     return Double.parseDouble(entradaValor.getText());
        case COSTOESTUDIANTES: return Double.parseDouble(entradaValor.getText());
        case VISITANTES:       return Integer.parseInt(entradaValor.getText());
        case ESTACIONAMIENTO:  return casillaValor.isSelected();
        default:               return entradaValor.getText(); // No debería ocurrir.
        }
    }

    /**
     * Define el foco incial del diálogo.
     */
    @Override public void defineFoco() {
        entradaValor.requestFocus();
    }
}
