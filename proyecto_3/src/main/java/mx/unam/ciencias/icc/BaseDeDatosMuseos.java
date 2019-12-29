package mx.unam.ciencias.icc;

/**
 * Clase que extiende a {@link BaseDeDatos} para crear una base de datos de
 * Museos.
 */
public class BaseDeDatosMuseos
    extends BaseDeDatos<Museo, CampoMuseo> {

    /**
     * Regresa un museo nuevo sin información.
     * @return un museo en blanco.
     */
    @Override public Museo creaRegistro() {
        return new Museo(null, 0, 0.0, 0.0, 0, false);
    }
}

