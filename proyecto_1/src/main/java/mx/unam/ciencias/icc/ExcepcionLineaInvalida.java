package mx.unam.ciencias.icc;

/**
 * Excepción que se utiliza para indicar que se intentó trabajar con una línea
 * no válida de un registro.
 */
public class ExcepcionLineaInvalida extends IllegalArgumentException {

    /**
     * El constructor sin parámetros no realiza nada.
     */
    public ExcepcionLineaInvalida() {}

    /**
     * El constructor con una cadena de texto como único parámetro para
     * notificar al usuario del error.
     * @param mensaje el mensaje que despliega la excepción.
     */
    public ExcepcionLineaInvalida(String mensaje) {
        super(mensaje);
    }
}
