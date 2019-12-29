package mx.unam.ciencias.icc;

/**
 * Interfaz para registros. Los registros deben de poder representarse como una
 * línea de texto, y definir sus propiedades a partir de una línea de
 * texto. También deben poder determinar si sus campos cazan valores
 * arbitrarios y actualizarse con los valores de otro registro.
 */
public interface Registro {

    /**
     * Regresa una representación del registro en una línea de texto. La línea
     * de texto que este método regresa debe ser aceptada por el método {@link
     * Registro#leeLinea}.
     * @return una representación del registro en una línea de texto.
     */
    public String escribeLinea();

    /**
     * Define las propiedades del registro a partir de una línea de texto. Las
     * líneas producidas por el método {@link Registro#escribeLinea} deben ser
     * aceptadas por éste método.
     * @param linea la línea con las nuevas propiedades del registro.
     * @throws ExcepcionLineaInvalida si la línea recibida es nula, vacía o no
     *         contiene los campos necesarios del registro.
     */
    public void leeLinea(String linea);

    /**
     * Actualiza los valores del registro con los del registro recibido.
     * @param registro el registro con el cual actualizar los valores.
     * @throws ClassCastException si el registro no es de la instancia correcta.
     */
    public void actualiza(Registro registro);

    /**
     * Nos dice si el registro caza el valor dado en el campo especificado.
     * @param campo el campo que hay que cazar.
     * @param valor el valor con el que debe cazar el campo del registro.
     * @return <code>true</code> si el campo especificado del registro caza el
     *         valor dado, <code>false</code> en otro caso.
     */
    public boolean caza(Enum campo, Object valor);
}
