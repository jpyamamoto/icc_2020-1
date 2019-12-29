package mx.unam.ciencias.icc.test;

import java.util.Random;
import mx.unam.ciencias.icc.CampoMuseo;
import mx.unam.ciencias.icc.Museo;
import mx.unam.ciencias.icc.ExcepcionLineaInvalida;
import mx.unam.ciencias.icc.Registro;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Clase para pruebas unitarias de la clase {@link Museo}.
 */
public class TestMuseo {

    @Rule public Timeout expiracion = Timeout.seconds(5);

    /* Prefijos. */
    private static final String[] PREFIJOS = {
        "Museo Nacional", "Museo", "Museo Casa", "Museo Arqueológico",
        "Museo Archivo", "Museo Antiguo", "Galería", "Centro Cultural"
    };

    /* Nombres. */
    private static final String[] NOMBRES = {
        "Soumaya", "Los Pinos", "Colegio de San Ildefonso", "de la Fotografía",
        "Moderno", "Popular", "Geología", "Historia Natural", "de la Caricatura",
        "de la Ciencia", "de las Culturas Populares", "de San Carlos"
    };

    private static Random random;

    public static String nombreAleatorio() {
        int n = random.nextInt(PREFIJOS.length);
        int ap = random.nextInt(NOMBRES.length);
        return PREFIJOS[n] + " " + NOMBRES[ap];
    }

    public static int salasAleatorio() {
        return 10 + random.nextInt(500);
    }

    public static double costoGeneralAleatorio() {
        return 1 + random.nextInt(1000) / 10.0;
    }

    public static double costoEstudiantesAleatorio() {
        return 1 + random.nextInt(500) / 10.0;
    }

    public static int visitantesAleatorio() {
        return 100 + random.nextInt(1000000);
    }

    public static boolean estacionamientoAleatorio() {
        return random.nextBoolean();
    }

    /**
     * Genera un museo aleatorio.
     * @return un museo aleatorio.
     */
    public static Museo museoAleatorio() {
        return new Museo(nombreAleatorio(),
                         salasAleatorio(),
                         costoGeneralAleatorio(),
                         costoEstudiantesAleatorio(),
                         visitantesAleatorio(),
                         estacionamientoAleatorio());
    }

    public static Museo museoAleatorio(int salas) {
        return new Museo(nombreAleatorio(),
                         salas,
                         costoGeneralAleatorio(),
                         costoEstudiantesAleatorio(),
                         visitantesAleatorio(),
                         estacionamientoAleatorio());
    }

    private Museo museo;

    private enum X {
        A;
    }

    /**
     * Prueba unitaria para {@link
     * Museo#Museo(String,int,double,double,int,boolean)}.
     */
    @Test public void testConstructor() {
        String nombre = nombreAleatorio();
        int salas = salasAleatorio();
        double costoGeneral = costoGeneralAleatorio();
        double costoEstudiantes = costoEstudiantesAleatorio();
        int visitantes = visitantesAleatorio();
        boolean estacionamiento = estacionamientoAleatorio();
        museo = new Museo(nombre, salas, costoGeneral, costoEstudiantes, visitantes, estacionamiento);
        Assert.assertTrue(museo.getNombre().equals(nombre));
        Assert.assertTrue(museo.getSalas() == salas);
        Assert.assertTrue(museo.getCostoGeneral() == costoGeneral);
        Assert.assertTrue(museo.getCostoEstudiantes() == costoEstudiantes);
        Assert.assertTrue(museo.getVisitantes() == visitantes);
        Assert.assertTrue(museo.getEstacionamiento() == estacionamiento);
    }

    /**
     * Prueba unitaria para {@link Museo#getNombre}.
     */
    @Test public void testGetNombre() {
        String nombre = nombreAleatorio();
        int salas = salasAleatorio();
        double costoGeneral = costoGeneralAleatorio();
        double costoEstudiantes = costoEstudiantesAleatorio();
        int visitantes = visitantesAleatorio();
        boolean estacionamiento = estacionamientoAleatorio();
        museo = new Museo(nombre, salas, costoGeneral, costoEstudiantes, visitantes, estacionamiento);
        Assert.assertTrue(museo.getNombre().equals(nombre));
        Assert.assertFalse(museo.getNombre().equals(nombre + " X"));
    }

    /**
     * Prueba unitaria para {@link Museo#setNombre}.
     */
    @Test public void testSetNombre() {
        String nombre = nombreAleatorio();
        String nuevoNombre = nombre + " X";
        int salas = salasAleatorio();
        double costoGeneral = costoGeneralAleatorio();
        double costoEstudiantes = costoEstudiantesAleatorio();
        int visitantes = visitantesAleatorio();
        boolean estacionamiento = estacionamientoAleatorio();
        museo = new Museo(nombre, salas, costoGeneral, costoEstudiantes, visitantes, estacionamiento);
        Assert.assertTrue(museo.getNombre().equals(nombre));
        Assert.assertFalse(museo.getNombre().equals(nuevoNombre));
        museo.setNombre(nuevoNombre);
        Assert.assertFalse(museo.getNombre().equals(nombre));
        Assert.assertTrue(museo.getNombre().equals(nuevoNombre));
    }

    /**
     * Prueba unitaria para {@link Museo#getSalas}.
     */
    @Test public void testGetSalas() {
        String nombre = nombreAleatorio();
        int salas = salasAleatorio();
        double costoGeneral = costoGeneralAleatorio();
        double costoEstudiantes = costoEstudiantesAleatorio();
        int visitantes = visitantesAleatorio();
        boolean estacionamiento = estacionamientoAleatorio();
        museo = new Museo(nombre, salas, costoGeneral, costoEstudiantes, visitantes, estacionamiento);
        Assert.assertTrue(museo.getSalas() == salas);
        Assert.assertFalse(museo.getSalas() == salas + 100);
    }

    /**
     * Prueba unitaria para {@link Museo#setSalas}.
     */
    @Test public void testSetSalas() {
        String nombre = nombreAleatorio();
        int salas = salasAleatorio();
        int nuevaSalas = salas + 100;
        double costoGeneral = costoGeneralAleatorio();
        double costoEstudiantes = costoEstudiantesAleatorio();
        int visitantes = visitantesAleatorio();
        boolean estacionamiento = estacionamientoAleatorio();
        museo = new Museo(nombre, salas, costoGeneral, costoEstudiantes, visitantes, estacionamiento);
        Assert.assertTrue(museo.getSalas() == salas);
        Assert.assertFalse(museo.getSalas() == salas + 100);
        museo.setSalas(nuevaSalas);
        Assert.assertFalse(museo.getSalas() == salas);
        Assert.assertTrue(museo.getSalas() == nuevaSalas);
    }

    /**
     * Prueba unitaria para {@link Museo#getCostoGeneral}.
     */
    @Test public void testGetCostoGeneral() {
        String nombre = nombreAleatorio();
        int salas = salasAleatorio();
        double costoGeneral = costoGeneralAleatorio();
        double costoEstudiantes = costoEstudiantesAleatorio();
        int visitantes = visitantesAleatorio();
        boolean estacionamiento = estacionamientoAleatorio();
        museo = new Museo(nombre, salas, costoGeneral, costoEstudiantes, visitantes, estacionamiento);
        Assert.assertTrue(museo.getCostoGeneral() == costoGeneral);
        Assert.assertFalse(museo.getCostoGeneral() == costoGeneral + 1.0);
    }

    /**
     * Prueba unitaria para {@link Museo#setCostoGeneral}.
     */
    @Test public void testSetCostoGeneral() {
        String nombre = nombreAleatorio();
        int salas = salasAleatorio();
        double costoGeneral = costoGeneralAleatorio();
        double nuevoCostoGeneral = costoGeneral + 1.0;
        double costoEstudiantes = costoEstudiantesAleatorio();
        int visitantes = visitantesAleatorio();
        boolean estacionamiento = estacionamientoAleatorio();
        museo = new Museo(nombre, salas, costoGeneral, costoEstudiantes, visitantes, estacionamiento);
        Assert.assertTrue(museo.getCostoGeneral() == costoGeneral);
        Assert.assertFalse(museo.getCostoGeneral() == nuevoCostoGeneral);
        museo.setCostoGeneral(nuevoCostoGeneral);
        Assert.assertFalse(museo.getCostoGeneral() == costoGeneral);
        Assert.assertTrue(museo.getCostoGeneral() == nuevoCostoGeneral);
    }

    /**
     * Prueba unitaria para {@link Museo#getCostoEstudiantes}.
     */
    @Test public void testGetCostoEstudiantes() {
        String nombre = nombreAleatorio();
        int salas = salasAleatorio();
        double costoGeneral = costoGeneralAleatorio();
        double costoEstudiantes = costoEstudiantesAleatorio();
        int visitantes = visitantesAleatorio();
        boolean estacionamiento = estacionamientoAleatorio();
        museo = new Museo(nombre, salas, costoGeneral, costoEstudiantes, visitantes, estacionamiento);
        Assert.assertTrue(museo.getCostoEstudiantes() == costoEstudiantes);
        Assert.assertFalse(museo.getCostoEstudiantes() == costoEstudiantes + 1.0);
    }

    /**
     * Prueba unitaria para {@link Museo#setCostoEstudiantes}.
     */
    @Test public void testSetCostoEstudiantes() {
        String nombre = nombreAleatorio();
        int salas = salasAleatorio();
        double costoGeneral = costoGeneralAleatorio();
        double costoEstudiantes = costoEstudiantesAleatorio();
        double nuevoCostoEstudiantes = costoEstudiantes + 1.0;
        int visitantes = visitantesAleatorio();
        boolean estacionamiento = estacionamientoAleatorio();
        museo = new Museo(nombre, salas, costoGeneral, costoEstudiantes, visitantes, estacionamiento);
        Assert.assertTrue(museo.getCostoEstudiantes() == costoEstudiantes);
        Assert.assertFalse(museo.getCostoEstudiantes() == nuevoCostoEstudiantes);
        museo.setCostoEstudiantes(nuevoCostoEstudiantes);
        Assert.assertFalse(museo.getCostoEstudiantes() == costoEstudiantes);
        Assert.assertTrue(museo.getCostoEstudiantes() == nuevoCostoEstudiantes);
    }

    /**
     * Prueba unitaria para {@link Museo#getVisitantes}.
     */
    @Test public void testVisitantes() {
        String nombre = nombreAleatorio();
        int salas = salasAleatorio();
        double costoGeneral = costoGeneralAleatorio();
        double costoEstudiantes = costoEstudiantesAleatorio();
        int visitantes = visitantesAleatorio();
        boolean estacionamiento = estacionamientoAleatorio();
        museo = new Museo(nombre, salas, costoGeneral, costoEstudiantes, visitantes, estacionamiento);
        Assert.assertTrue(museo.getVisitantes() == visitantes);
        Assert.assertFalse(museo.getVisitantes() == visitantes + 100);
    }

    /**
     * Prueba unitaria para {@link Museo#setVisitantes}.
     */
    @Test public void testSetVisitantes() {
        String nombre = nombreAleatorio();
        int salas = salasAleatorio();
        double costoGeneral = costoGeneralAleatorio();
        double costoEstudiantes = costoEstudiantesAleatorio();
        int visitantes = visitantesAleatorio();
        int nuevaVisitantes = visitantes + 100;
        boolean estacionamiento = estacionamientoAleatorio();
        museo = new Museo(nombre, salas, costoGeneral, costoEstudiantes, visitantes, estacionamiento);
        Assert.assertTrue(museo.getVisitantes() == visitantes);
        Assert.assertFalse(museo.getVisitantes() == visitantes + 100);
        museo.setVisitantes(nuevaVisitantes);
        Assert.assertFalse(museo.getVisitantes() == visitantes);
        Assert.assertTrue(museo.getVisitantes() == nuevaVisitantes);
    }

    /**
     * Prueba unitaria para {@link Museo#getEstacionamiento}.
     */
    @Test public void testEstacionamiento() {
        String nombre = nombreAleatorio();
        int salas = salasAleatorio();
        double costoGeneral = costoGeneralAleatorio();
        double costoEstudiantes = costoEstudiantesAleatorio();
        int visitantes = visitantesAleatorio();
        boolean estacionamiento = estacionamientoAleatorio();
        museo = new Museo(nombre, salas, costoGeneral, costoEstudiantes, visitantes, estacionamiento);
        Assert.assertTrue(museo.getEstacionamiento() == estacionamiento);
        Assert.assertFalse(museo.getEstacionamiento() == !estacionamiento);
    }

    /**
     * Prueba unitaria para {@link Museo#setEstacionamiento}.
     */
    @Test public void testSetEstacionamiento() {
        String nombre = nombreAleatorio();
        int salas = salasAleatorio();
        double costoGeneral = costoGeneralAleatorio();
        double costoEstudiantes = costoEstudiantesAleatorio();
        int visitantes = visitantesAleatorio();
        boolean estacionamiento = estacionamientoAleatorio();
        boolean nuevaEstacionamiento = !estacionamiento;
        museo = new Museo(nombre, salas, costoGeneral, costoEstudiantes, visitantes, estacionamiento);
        Assert.assertTrue(museo.getEstacionamiento() == estacionamiento);
        Assert.assertFalse(museo.getEstacionamiento() == !estacionamiento);
        museo.setEstacionamiento(nuevaEstacionamiento);
        Assert.assertFalse(museo.getEstacionamiento() == estacionamiento);
        Assert.assertTrue(museo.getEstacionamiento() == nuevaEstacionamiento);
    }

    /**
     * Prueba unitaria para {@link Museo#toString}.
     */
    @Test public void testToString() {
        String nombre = nombreAleatorio();
        int salas = salasAleatorio();
        double costoGeneral = costoGeneralAleatorio();
        double costoEstudiantes = costoEstudiantesAleatorio();
        int visitantes = visitantesAleatorio();
        boolean estacionamiento = estacionamientoAleatorio();
        museo = new Museo(nombre, salas, costoGeneral, costoEstudiantes, visitantes, estacionamiento);
        String cadena = String.format("Nombre            : %s\n" +
                                      "Salas             : %d\n" +
                                      "Costo General     : $%.2f\n" +
                                      "Costo Estudiantes : $%.2f\n" +
                                      "Visitantes/año    : %,d\n" +
                                      "Estacionamiento   : %b\n",
                                      nombre, salas, costoGeneral,
                                      costoEstudiantes, visitantes,
                                      estacionamiento);
        Assert.assertTrue(museo.toString().equals(cadena));
        salas = 110;
        costoGeneral = 100.99;
        museo.setSalas(salas);
        museo.setCostoGeneral(costoGeneral);
        cadena = String.format("Nombre            : %s\n" +
                               "Salas             : %d\n" +
                               "Costo General     : $%.2f\n" +
                               "Costo Estudiantes : $%.2f\n" +
                               "Visitantes/año    : %,d\n" +
                               "Estacionamiento   : %b\n",
                               nombre, salas, costoGeneral, costoEstudiantes,
                               visitantes, estacionamiento);
        Assert.assertTrue(museo.toString().equals(cadena));
    }

    /**
     * Prueba unitaria para {@link Museo#equals}.
     */
    @Test public void testEquals() {
        String nombre = nombreAleatorio();
        int salas = salasAleatorio();
        double costoGeneral = costoGeneralAleatorio();
        double costoEstudiantes = costoEstudiantesAleatorio();
        int visitantes = visitantesAleatorio();
        boolean estacionamiento = estacionamientoAleatorio();
        museo = new Museo(nombre, salas, costoGeneral, costoEstudiantes, visitantes, estacionamiento);
        Museo igual = new Museo(new String(nombre), salas, costoGeneral,
                                costoEstudiantes, visitantes, estacionamiento);
        Assert.assertTrue(museo.equals(igual));
        String otroNombre = nombre + " (Fundado en 1990)";
        int otroSalas = salas + 1;
        double otroCostoGeneral = (costoGeneral != 0.0) ? costoGeneral / 10.0 : 10.0;
        double otroCostoEstudiantes = (costoGeneral != 0.0) ? costoEstudiantes / 10.0 : 10.0;
        int otroVisitantes = visitantes + 1;
        boolean otroEstacionamiento = !estacionamiento;
        Museo distinto = new Museo(otroNombre, salas, costoGeneral, costoEstudiantes, visitantes, estacionamiento);
        Assert.assertFalse(museo.equals(distinto));
        distinto = new Museo(nombre, otroSalas, costoGeneral, costoEstudiantes, visitantes, estacionamiento);
        Assert.assertFalse(museo.equals(distinto));
        distinto = new Museo(nombre, salas, otroCostoGeneral, costoEstudiantes, visitantes, estacionamiento);
        Assert.assertFalse(museo.equals(distinto));
        distinto = new Museo(nombre, salas, costoGeneral, otroCostoEstudiantes, visitantes, estacionamiento);
        Assert.assertFalse(museo.equals(distinto));
        distinto = new Museo(nombre, salas, costoGeneral, costoEstudiantes, otroVisitantes, estacionamiento);
        Assert.assertFalse(museo.equals(distinto));
        distinto = new Museo(nombre, salas, costoGeneral, costoEstudiantes, visitantes, otroEstacionamiento);
        Assert.assertFalse(museo.equals(distinto));
        distinto = new Museo(otroNombre, otroSalas, otroCostoGeneral,
                             otroCostoEstudiantes, otroVisitantes,
                             otroEstacionamiento);
        Assert.assertFalse(museo.equals(distinto));
        Assert.assertFalse(museo.equals("Una cadena"));
        Assert.assertFalse(museo.equals(null));
    }

    /**
     * Prueba unitaria para {@link Museo#escribeLinea}.
     */
    @Test public void testEscribeLinea() {
        String nombre = nombreAleatorio();
        int salas = salasAleatorio();
        double costoGeneral = costoGeneralAleatorio();
        double costoEstudiantes = costoEstudiantesAleatorio();
        int visitantes = visitantesAleatorio();
        boolean estacionamiento = estacionamientoAleatorio();
        museo = new Museo(nombre, salas, costoGeneral, costoEstudiantes, visitantes, estacionamiento);
        String linea = String.format("%s\t%d\t%.2f\t%.2f\t%d\t%b\n",
                                     nombre, salas, costoGeneral,
                                     costoEstudiantes, visitantes,
                                     estacionamiento);
        Assert.assertTrue(museo.escribeLinea().equals(linea));
    }

    /**
     * Prueba unitaria para {@link Museo#leeLinea}.
     */
    @Test public void testLeeLinea() {
        museo = new Museo(null, 0, 0.0, 0.0, 0, false);

        String nombre = nombreAleatorio();
        int salas = salasAleatorio();
        double costoGeneral = costoGeneralAleatorio();
        double costoEstudiantes = costoEstudiantesAleatorio();
        int visitantes = visitantesAleatorio();
        boolean estacionamiento = estacionamientoAleatorio();

        String linea = String.format("%s\t%d\t%.2f\t%.2f\t%d\t%b\n",
                                     nombre, salas, costoGeneral,
                                     costoEstudiantes, visitantes,
                                     estacionamiento);

        try {
            museo.leeLinea(linea);
        } catch (ExcepcionLineaInvalida eli) {
            Assert.fail();
        }

        Assert.assertTrue(museo.getNombre().equals(nombre));
        Assert.assertTrue(museo.getSalas() == salas);
        Assert.assertTrue(museo.getCostoGeneral() == costoGeneral);
        Assert.assertTrue(museo.getCostoEstudiantes() == costoEstudiantes);
        Assert.assertTrue(museo.getVisitantes() == visitantes);
        Assert.assertTrue(museo.getEstacionamiento() == estacionamiento);

        String[] invalidas = {"", " ", "\t", "  ", "\t\t",
                              " \t", "\t ", "\n", "a\ta\ta",
                              "a\ta\ta\ta"};

        for (int i = 0; i < invalidas.length; i++) {
            linea = invalidas[i];
            try {
                museo.leeLinea(linea);
                Assert.fail();
            } catch (ExcepcionLineaInvalida eli) {}
        }
    }

    /**
     * Prueba unitaria para {@link Museo#actualiza}.
     */
    @Test public void testActualiza() {
        museo = new Museo("A", 1, 1, 1, 1, true);
        Museo e = new Museo("B", 2, 2, 2, 2, false);
        Assert.assertFalse(museo == e);
        Assert.assertFalse(museo.equals(e));
        museo.actualiza(e);
        Assert.assertFalse(museo == e);
        Assert.assertTrue(museo.equals(e));
        try {
            museo.actualiza(null);
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
        try {
            museo.actualiza(new Registro() {
                    @Override public String escribeLinea() { return null; }
                    @Override public void leeLinea(String linea) {}
                    @Override public void actualiza(Registro registro) {}
                    @Override public boolean caza(Enum campo, Object valor) {
                        return false;
                    }
                });
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
    }

    /**
     * Prueba unitaria para {@link Museo#caza}.
     */
    @Test public void testCaza() {
        String nombre = nombreAleatorio();
        int salas = salasAleatorio();
        double costoGeneral = costoGeneralAleatorio();
        double costoEstudiantes = costoEstudiantesAleatorio();
        int visitantes = visitantesAleatorio();
        boolean estacionamiento = estacionamientoAleatorio();
        museo = new Museo(nombre, salas, costoGeneral, costoEstudiantes, visitantes, estacionamiento);

        String n = museo.getNombre();
        int m = museo.getNombre().length();
        Assert.assertTrue(museo.caza(CampoMuseo.NOMBRE, n));
        n = museo.getNombre().substring(0, m/2);
        Assert.assertTrue(museo.caza(CampoMuseo.NOMBRE, n));
        n = museo.getNombre().substring(m/2, m);
        Assert.assertTrue(museo.caza(CampoMuseo.NOMBRE, n));
        n = museo.getNombre().substring(m/3, 2*(m/3));
        Assert.assertTrue(museo.caza(CampoMuseo.NOMBRE, n));
        Assert.assertFalse(museo.caza(CampoMuseo.NOMBRE, ""));
        Assert.assertFalse(museo.caza(CampoMuseo.NOMBRE, "XXX"));
        Assert.assertFalse(museo.caza(CampoMuseo.NOMBRE,
                                           Integer.valueOf(1000)));
        Assert.assertFalse(museo.caza(CampoMuseo.NOMBRE, null));

        Integer s = Integer.valueOf(museo.getSalas());
        Assert.assertTrue(museo.caza(CampoMuseo.SALAS, s));
        s = Integer.valueOf(museo.getSalas() - 100);
        Assert.assertFalse(museo.caza(CampoMuseo.SALAS, s));
        s = Integer.valueOf(museo.getSalas() + 100);
        Assert.assertFalse(museo.caza(CampoMuseo.SALAS, s));
        Assert.assertFalse(museo.caza(CampoMuseo.SALAS, "XXX"));
        Assert.assertFalse(museo.caza(CampoMuseo.SALAS, null));

        Double cg = Double.valueOf(museo.getCostoGeneral());
        Assert.assertTrue(museo.caza(CampoMuseo.COSTOGENERAL, cg));
        cg = Double.valueOf(museo.getCostoGeneral() + 5.0);
        Assert.assertTrue(museo.caza(CampoMuseo.COSTOGENERAL, cg));
        cg = Double.valueOf(museo.getCostoGeneral() - 5.0);
        Assert.assertFalse(museo.caza(CampoMuseo.COSTOGENERAL, cg));
        Assert.assertFalse(museo.caza(CampoMuseo.COSTOGENERAL, "XXX"));
        Assert.assertFalse(museo.caza(CampoMuseo.COSTOGENERAL, null));

        Double cp = Double.valueOf(museo.getCostoEstudiantes());
        Assert.assertTrue(museo.caza(CampoMuseo.COSTOESTUDIANTES, cp));
        cp = Double.valueOf(museo.getCostoEstudiantes() + 5.0);
        Assert.assertTrue(museo.caza(CampoMuseo.COSTOESTUDIANTES, cp));
        cp = Double.valueOf(museo.getCostoEstudiantes() - 5.0);
        Assert.assertFalse(museo.caza(CampoMuseo.COSTOESTUDIANTES, cp));
        Assert.assertFalse(museo.caza(CampoMuseo.COSTOESTUDIANTES, "XXX"));
        Assert.assertFalse(museo.caza(CampoMuseo.COSTOESTUDIANTES, null));

        Integer v = Integer.valueOf(museo.getVisitantes());
        Assert.assertTrue(museo.caza(CampoMuseo.VISITANTES, v));
        v = Integer.valueOf(museo.getVisitantes() - 10);
        Assert.assertTrue(museo.caza(CampoMuseo.VISITANTES, v));
        v = Integer.valueOf(museo.getVisitantes() + 10);
        Assert.assertFalse(museo.caza(CampoMuseo.VISITANTES, v));
        Assert.assertFalse(museo.caza(CampoMuseo.VISITANTES, "XXX"));
        Assert.assertFalse(museo.caza(CampoMuseo.VISITANTES, null));

        Boolean e = Boolean.valueOf(museo.getEstacionamiento());
        Assert.assertTrue(museo.caza(CampoMuseo.ESTACIONAMIENTO, e));
        e = Boolean.valueOf(!museo.getEstacionamiento());
        Assert.assertFalse(museo.caza(CampoMuseo.ESTACIONAMIENTO, e));
        Assert.assertFalse(museo.caza(CampoMuseo.ESTACIONAMIENTO, "XXX"));
        Assert.assertFalse(museo.caza(CampoMuseo.ESTACIONAMIENTO, null));

        try {
            museo.caza(null, null);
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
        try {
            museo.caza(X.A, museo.getNombre());
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
        try {
            Object o = Integer.valueOf(museo.getSalas());
            museo.caza(X.A, o);
        } catch (IllegalArgumentException iae) {}
        try {
            Object o = Double.valueOf(museo.getCostoGeneral());
            museo.caza(X.A, o);
        } catch (IllegalArgumentException iae) {}
        try {
            Object o = Double.valueOf(museo.getCostoEstudiantes());
            museo.caza(X.A, o);
        } catch (IllegalArgumentException iae) {}
        try {
            Object o = Integer.valueOf(museo.getVisitantes());
            museo.caza(X.A, o);
        } catch (IllegalArgumentException iae) {}
        try {
            Object o = Boolean.valueOf(museo.getEstacionamiento());
            museo.caza(X.A, o);
        } catch (IllegalArgumentException iae) {}
        try {
            Assert.assertFalse(museo.caza(X.A, null));
        } catch (IllegalArgumentException iae) {}
    }

    static {
        random = new Random();
    }
}
