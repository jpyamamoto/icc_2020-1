package mx.unam.ciencias.icc.fx;

/**
 * Interfaz funcional para objetos que pueden verificar texto.
 */
@FunctionalInterface
public interface VerificadorCasilla {

    /**
     * Verifica el booleano.
     * @param valor el booleano a verificar.
     * @return <code>true</code> si el valor es válido,
     *         <code>false</code> en otro caso.
     */
    public boolean verifica(Boolean valor);
}
