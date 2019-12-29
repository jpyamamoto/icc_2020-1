package mx.unam.ciencias.icc.fx;

import javafx.scene.control.CheckBox;

/**
 * Clase para casillas verificables.
 */
public class CasillaVerificable extends CheckBox {

    /* El verificador de la casilla. */
    private VerificadorCasilla verificador;

    /**
     * Define el estado inicial de una casilla verificable.
     */
    public CasillaVerificable() {
        verificador = e -> false;
    }

    /**
     * Define el verificador de la casilla.
     * @param verificador el nuevo verificador de la casilla.
     */
    public void setVerificador(VerificadorCasilla verificador) {
        this.verificador = verificador;
    }

    /**
     * Nos dice si la casilla es válida.
     * @return <code>true</code> si la casilla es válida, <code>false</code> en
     *         otro caso.
     */
    public boolean esValida() {
        return verificador.verifica(isSelected());
    }
}
