package mx.unam.ciencias.icc;

/**
 * Interfaz con los métodos que debe implementar un registro.
 * Comportamiento:
 * - Representación como cadena de texto.
 * - Actualizar las propiedades de un registro a partir de una cadena de texto.
 * - Definir si un campo y valor caza con el registro actual.
 * - Actualizar las propiedades de un registro a partir de otro registro.
 */
public interface Registro {

    /**
     * Regresa la representación como cadena de texto (en una línea para poder
     * guardarse en un archivo) del registro actual. El String que regresa este
     * método debe ser un parámetro válido para el método {@link
     * Registro#leeLinea}.
     * @return representación como cadena de texto del museo.
     */
    public String escribeLinea();

    /**
     * Modifica las variables de clase de la instancia del registro que lo manda
     * a llamar, a partir de los valores que obtiene de una línea de texto. Lo
     * que regresa el método {@link Registro#escribeLinea} debe ser un
     * parámetro válido para este método.
     * @param linea la representación como cadena de texto que contiene las
     *        propiedades del registro.
     * @throws ExcepcionLineaInvalida si el parámetro linea es nulo, vacío o no
     *         contiene los campos requeridos para el registro.
     */
    public void leeLinea(String linea);

    /**
     * Actualiza las variables de clase del registro actual a partir de las
     * propiedades de otro registro.
     * @param registro el registro cuyas propiedades adoptará el registro
     * actual.
     * @throws ClassCastException si el registro no es de la instancia correcta.
     */
    public void actualiza(Registro registro);

    /**
     * Indica si el registro caza con los valores recibidos.
     * @param campo el campo con el que se compara.
     * @param valor el valor con el que se compara un campo del registro.
     * @return <code>true</code> si el registro caza con el campo y valor
     *         recibido, <code>false</code> si el registro no caza.
     */
    public boolean caza(Enum campo, Object valor);
}
