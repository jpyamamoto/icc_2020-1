package mx.unam.ciencias.icc.test;

import mx.unam.ciencias.icc.CampoMuseo;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Clase para pruebas unitarias de la enumeración {@link CampoMuseo}.
 */
public class TestCampoMuseo {

    // Las pruebas no deben tomar más de 5 segundos.
    @Rule public Timeout expiracion = Timeout.seconds(5);

    /**
     * Prueba unitaria para {@link CampoMuseo#toString}.
     */
    @Test public void testToString() {
        String s = CampoMuseo.NOMBRE.toString();
        Assert.assertTrue(s.equals("Nombre"));

        s = CampoMuseo.SALAS.toString();
        Assert.assertTrue(s.equals("Salas"));

        s = CampoMuseo.COSTOGENERAL.toString();
        Assert.assertTrue(s.equals("Costo General"));

        s = CampoMuseo.COSTOESTUDIANTES.toString();
        Assert.assertTrue(s.equals("Costo Estudiantes"));

        s = CampoMuseo.VISITANTES.toString();
        Assert.assertTrue(s.equals("Visitantes/año"));

        s = CampoMuseo.ESTACIONAMIENTO.toString();
        Assert.assertTrue(s.equals("Estacionamiento"));
    }
}
