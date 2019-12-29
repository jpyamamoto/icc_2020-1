package mx.unam.ciencias.icc;

/**
 * Enumaración que contiene los campos de {@link Museo}.
 */
public enum CampoMuseo {

    /** Nombre del museo. */
    NOMBRE,
    /** Número de salas. */
    SALAS,
    /** Costo para público en general. */
    COSTOGENERAL,
    /** Costo para estudiantes.*/
    COSTOESTUDIANTES,
    /** Número estimado de visitantes por año */
    VISITANTES,
    /** ¿Tiene estacionamiento o no? */
    ESTACIONAMIENTO;

    /**
     * Convierte a cadena el nombre del campo.
     * @return una cadena de texto referente al campo.
     */
    @Override public String toString() {
        switch (this) {
            case NOMBRE:           return "Nombre";
            case SALAS:            return "Salas";
            case COSTOGENERAL:     return "Costo General";
            case COSTOESTUDIANTES: return "Costo Estudiantes";
            case VISITANTES:       return "Visitantes/año";
            case ESTACIONAMIENTO:  return "Estacionamiento";
            default:               return null;
            // El caso default solo sirve para que Java compile.
            // El switch nunca alcanzará este caso.
        }
    }
}
