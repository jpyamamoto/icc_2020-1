package mx.unam.ciencias.icc;

/**
 * Interfaz para registros. Los registros deben de poder representarse como una
 * línea de texto, y definir sus propiedades a partir de una línea de
 * texto. También deben poder determinar si sus campos cazan valores
 * arbitrarios y actualizarse con los valores de otro registro.
 *
 * @param <R> El tipo de los registros, para poder actualizar registros del
 * mismo tipo.
 * @param <C> El tipo de los campos de los registros, que debe ser una
 * enumeración {@link Enum}.
 */
public interface Registro<R, C extends Enum> {

    /**
     * Regresa una representación del registro en una línea de texto. La línea
     * de texto que este método regresa debe ser aceptada por el método {@link
     * Registro#deLinea}.
     * @return una representación del registro en una línea de texto.
     */
    public String aLinea();

    /**
     * Define las propiedades del registro a partir de una línea de texto. Las
     * líneas producidas por el método {@link Registro#aLinea} deben ser
     * aceptadas por éste método.
     * @param linea la línea con las nuevas propiedades del registro.
     * @throws ExcepcionLineaInvalida si la línea recibida es nula, vacía o no
     *         contiene los campos necesarios del registro.
     */
    public void deLinea(String linea);

    /**
     * Actualiza los valores del registro con los del registro recibido.
     * @param registro el registro con el cual actualizar los valores.
     */
    public void actualiza(R registro);

    /**
     * Nos dice si el registro caza el valor dado en el campo especificado.
     * @param campo el campo que hay que cazar.
     * @param valor el valor con el que debe cazar el campo del registro.
     * @return <code>true</code> si el campo especificado del registro caza el
     *         valor dado, <code>false</code> en otro caso.
     */
    public boolean caza(C campo, Object valor);
}
