package mx.unam.ciencias.icc;

/**
 * Excepción que se utiliza para indicar que se intentó acceder a un índice
 * no válido de una lista.
 */
public class ExcepcionIndiceInvalido extends IndexOutOfBoundsException {

    /**
     * El constructor sin parámetros no realiza nada.
     */
    public ExcepcionIndiceInvalido() {}

    /**
     * El constructor con una cadena de texto como único parámetro para
     * notificar al usuario del error.
     * @param mensaje el mensaje que despliega la excepción.
     */
    public ExcepcionIndiceInvalido(String mensaje) {
        super(mensaje);
    }
}
