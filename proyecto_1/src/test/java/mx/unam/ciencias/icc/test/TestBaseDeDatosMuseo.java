package mx.unam.ciencias.icc.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Random;
import mx.unam.ciencias.icc.BaseDeDatos;
import mx.unam.ciencias.icc.BaseDeDatosMuseos;
import mx.unam.ciencias.icc.CampoMuseo;
import mx.unam.ciencias.icc.Museo;
import mx.unam.ciencias.icc.Lista;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Clase para pruebas unitarias de la clase {@link BaseDeDatosMuseos}.
 */
public class TestBaseDeDatosMuseo {

    @Rule public Timeout expiracion = Timeout.seconds(5);

    private Random random;

    private BaseDeDatosMuseos bdd;

    private int total;

    private enum X {
        A;
    }

    /**
     * Crea un generador de números aleatorios para cada prueba y una base de
     * datos de museos.
     */
    public TestBaseDeDatosMuseo() {
        random = new Random();
        bdd = new BaseDeDatosMuseos();
        total = 1 + random.nextInt(100);
    }

    /**
     * Prueba unitaria para {@link
     * BaseDeDatosMuseos#BaseDeDatosMuseos}.
     */
    @Test public void testConstructor() {
        Lista museos = bdd.getRegistros();
        Assert.assertFalse(museos == null);
        Assert.assertTrue(museos.getLongitud() == 0);
        Assert.assertTrue(bdd.getNumRegistros() == 0);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#getNumRegistros}.
     */
    @Test public void testGetNumRegistros() {
        Assert.assertTrue(bdd.getNumRegistros() == 0);
        for (int i = 0; i < total; i++) {
            Museo e = TestMuseo.museoAleatorio();
            bdd.agregaRegistro(e);
            Assert.assertTrue(bdd.getNumRegistros() == i+1);
        }
        Assert.assertTrue(bdd.getNumRegistros() == total);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#getRegistros}.
     */
    @Test public void testGetRegistros() {
        Lista l = bdd.getRegistros();
        Lista r = bdd.getRegistros();
        Assert.assertTrue(l.equals(r));
        Assert.assertFalse(l == r);
        Museo[] museos = new Museo[total];
        for (int i = 0; i < total; i++) {
            museos[i] = TestMuseo.museoAleatorio();
            bdd.agregaRegistro(museos[i]);
        }
        l = bdd.getRegistros();
        int c = 0;
        Lista.Nodo nodo = l.getCabeza();
        while (nodo != null) {
            Assert.assertTrue(museos[c++].equals(nodo.get()));
            nodo = nodo.getSiguiente();
        }
        l.elimina(museos[0]);
        Assert.assertFalse(l.equals(bdd.getRegistros()));
        Assert.assertFalse(l.getLongitud() == bdd.getNumRegistros());
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#agregaRegistro}.
     */
    @Test public void testAgregaRegistro() {
        for (int i = 0; i < total; i++) {
            Museo e = TestMuseo.museoAleatorio();
            Assert.assertFalse(bdd.getRegistros().contiene(e));
            bdd.agregaRegistro(e);
            Assert.assertTrue(bdd.getRegistros().contiene(e));
            Lista l = bdd.getRegistros();
            Assert.assertTrue(l.get(l.getLongitud() - 1).equals(e));
        }
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#eliminaRegistro}.
     */
    @Test public void testEliminaRegistro() {
        int ini = random.nextInt(1000000);
        for (int i = 0; i < total; i++) {
            Museo e = TestMuseo.museoAleatorio(ini + i);
            bdd.agregaRegistro(e);
        }
        while (bdd.getNumRegistros() > 0) {
            int i = random.nextInt(bdd.getNumRegistros());
            Museo e = (Museo)bdd.getRegistros().get(i);
            Assert.assertTrue(bdd.getRegistros().contiene(e));
            bdd.eliminaRegistro(e);
            Assert.assertFalse(bdd.getRegistros().contiene(e));
        }
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#limpia}.
     */
    @Test public void testLimpia() {
        for (int i = 0; i < total; i++) {
            Museo e = TestMuseo.museoAleatorio();
            bdd.agregaRegistro(e);
        }
        Lista registros = bdd.getRegistros();
        Assert.assertFalse(registros.esVacia());
        Assert.assertFalse(registros.getLongitud() == 0);
        bdd.limpia();
        registros = bdd.getRegistros();
        Assert.assertTrue(registros.esVacia());
        Assert.assertTrue(registros.getLongitud() == 0);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#guarda}.
     */
    @Test public void testGuarda() {
        for (int i = 0; i < total; i++) {
            Museo e = TestMuseo.museoAleatorio();
            bdd.agregaRegistro(e);
        }
        String guardado = "";
        try {
            StringWriter swOut = new StringWriter();
            BufferedWriter out = new BufferedWriter(swOut);
            bdd.guarda(out);
            out.close();
            guardado = swOut.toString();
        } catch (IOException ioe) {
            Assert.fail();
        }
        String[] lineas = guardado.split("\n");
        Assert.assertTrue(lineas.length == total);
        Lista l = bdd.getRegistros();
        int c = 0;
        Lista.Nodo nodo = l.getCabeza();
        while (nodo != null) {
            Museo e = (Museo)nodo.get();
            String el = String.format("%s\t%d\t%.2f\t%.2f\t%d\t%b",
                                      e.getNombre(),
                                      e.getSalas(),
                                      e.getCostoGeneral(),
                                      e.getCostoEstudiantes(),
                                      e.getVisitantes(),
                                      e.getEstacionamiento());
            Assert.assertTrue(lineas[c++].equals(el));
            nodo = nodo.getSiguiente();
        }
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#carga}.
     */
    @Test public void testCarga() {
        int ini = random.nextInt(1000000);
        String entrada = "";
        Museo[] museos = new Museo[total];
        for (int i = 0; i < total; i++) {
            museos[i] = TestMuseo.museoAleatorio(ini + i);
            entrada += String.format("%s\t%d\t%.2f\t%.2f\t%d\t%b\n",
                                     museos[i].getNombre(),
                                     museos[i].getSalas(),
                                     museos[i].getCostoGeneral(),
                                     museos[i].getCostoEstudiantes(),
                                     museos[i].getVisitantes(),
                                     museos[i].getEstacionamiento());
            bdd.agregaRegistro(museos[i]);
        }
        try {
            StringReader srInt = new StringReader(entrada);
            BufferedReader in = new BufferedReader(srInt, 8192);
            bdd.carga(in);
            in.close();
        } catch (IOException ioe) {
            Assert.fail();
        }
        Assert.assertTrue(bdd.getNumRegistros() == total);
        int c = 0;
        Lista l = bdd.getRegistros();
        Lista.Nodo nodo = l.getCabeza();
        while (nodo != null) {
            Assert.assertTrue(museos[c++].equals(nodo.get()));
            nodo = nodo.getSiguiente();
        }
        entrada = "";
        try {
            StringReader srInt = new StringReader(entrada);
            BufferedReader in = new BufferedReader(srInt, 8192);
            bdd.carga(in);
            in.close();
        } catch (IOException ioe) {
            Assert.fail();
        }
        Assert.assertTrue(bdd.getNumRegistros() == 0);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatosMuseos#creaRegistro}.
     */
    @Test public void testCreaRegistro() {
        Museo e = (Museo)bdd.creaRegistro();
        Assert.assertTrue(e.getNombre() == null);
        Assert.assertTrue(e.getSalas() == 0);
        Assert.assertTrue(e.getCostoGeneral() == 0.0);
        Assert.assertTrue(e.getCostoEstudiantes() == 0.0);
        Assert.assertTrue(e.getVisitantes() == 0);
        Assert.assertTrue(e.getEstacionamiento() == false);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatosMuseos#buscaRegistros}.
     */
    @Test public void testBuscaRegistros() {
        Museo[] museos = new Museo[total];
        int ini = 1000000 + random.nextInt(999999);
        for (int i = 0; i < total; i++) {
            Museo e =  new Museo(String.valueOf(ini+i), ini+i,
                                 1 + (i * 10.0) / total, 1 + (i * 15.0) / total, i,
                                 random.nextBoolean());
            museos[i] = e;
            bdd.agregaRegistro(e);
        }

        Museo museo;
        Lista l;
        Lista.Nodo nodo;
        int i;

        for (int k = 0; k < total/10 + 3; k++) {
            i = random.nextInt(total);
            museo = museos[i];

            String nombre = museo.getNombre();
            l = bdd.buscaRegistros(CampoMuseo.NOMBRE, nombre);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(museo));
            nodo = l.getCabeza();
            while (nodo != null) {
                Museo e = (Museo)nodo.get();
                Assert.assertTrue(e.getNombre().indexOf(nombre) > -1);
                nodo = nodo.getSiguiente();
            }
            int n = nombre.length();
            String bn = nombre.substring(random.nextInt(2),
                                         2 + random.nextInt(n-2));
            l = bdd.buscaRegistros(CampoMuseo.NOMBRE, bn);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(museo));
            nodo = l.getCabeza();
            while (nodo != null) {
                Museo e = (Museo)nodo.get();
                Assert.assertTrue(e.getNombre().indexOf(bn) > -1);
                nodo = nodo.getSiguiente();
            }

            Integer salas = Integer.valueOf(museo.getSalas());
            l = bdd.buscaRegistros(CampoMuseo.SALAS, salas);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(museo));
            nodo = l.getCabeza();
            while (nodo != null) {
                Museo e = (Museo)nodo.get();
                Assert.assertTrue(e.getSalas() >= salas.intValue());
                nodo = nodo.getSiguiente();
            }
            Integer bc = Integer.valueOf(salas.intValue());
            l = bdd.buscaRegistros(CampoMuseo.SALAS, bc);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(museo));
            nodo = l.getCabeza();
            while (nodo != null) {
                Museo e = (Museo)nodo.get();
                Assert.assertTrue(e.getSalas() >= bc.intValue());
                nodo = nodo.getSiguiente();
            }

            Double costoGeneral = Double.valueOf(museo.getCostoGeneral());
            l = bdd.buscaRegistros(CampoMuseo.COSTOGENERAL, costoGeneral);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(museo));
            nodo = l.getCabeza();
            while (nodo != null) {
                Museo e = (Museo)nodo.get();
                Assert.assertTrue(e.getCostoGeneral() <= costoGeneral.doubleValue());
                nodo = nodo.getSiguiente();
            }
            Double bp = Double.valueOf(costoGeneral.doubleValue() + 5.0);
            l = bdd.buscaRegistros(CampoMuseo.COSTOGENERAL, bp);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(museo));
            nodo = l.getCabeza();
            while (nodo != null) {
                Museo e = (Museo)nodo.get();
                Assert.assertTrue(e.getCostoGeneral() <= bp.doubleValue());
                nodo = nodo.getSiguiente();
            }

            Double costoEstudiantes = Double.valueOf(museo.getCostoEstudiantes());
            l = bdd.buscaRegistros(CampoMuseo.COSTOESTUDIANTES, costoEstudiantes);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(museo));
            nodo = l.getCabeza();
            while (nodo != null) {
                Museo e = (Museo)nodo.get();
                Assert.assertTrue(e.getCostoEstudiantes() <= costoEstudiantes.doubleValue());
                nodo = nodo.getSiguiente();
            }
            Double be = Double.valueOf(costoEstudiantes.doubleValue() + 5.0);
            l = bdd.buscaRegistros(CampoMuseo.COSTOESTUDIANTES, be);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(museo));
            nodo = l.getCabeza();
            while (nodo != null) {
                Museo e = (Museo)nodo.get();
                Assert.assertTrue(e.getCostoEstudiantes() <= be.doubleValue());
                nodo = nodo.getSiguiente();
            }

            Integer visitantes = Integer.valueOf(museo.getVisitantes());
            l = bdd.buscaRegistros(CampoMuseo.VISITANTES, visitantes);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(museo));
            nodo = l.getCabeza();
            while (nodo != null) {
                Museo e = (Museo)nodo.get();
                Assert.assertTrue(e.getVisitantes() >= visitantes.intValue());
                nodo = nodo.getSiguiente();
            }
            Integer bv = Integer.valueOf(visitantes.intValue() - 10);
            l = bdd.buscaRegistros(CampoMuseo.VISITANTES, bv);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(museo));
            nodo = l.getCabeza();
            while (nodo != null) {
                Museo e = (Museo)nodo.get();
                Assert.assertTrue(e.getVisitantes() >= bv.intValue());
                nodo = nodo.getSiguiente();
            }
        }

        l = bdd.buscaRegistros(CampoMuseo.NOMBRE,
                               "xxx-nombre");
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoMuseo.SALAS,
                               Integer.valueOf(600));
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoMuseo.COSTOGENERAL,
                               Double.valueOf(0.00));
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoMuseo.COSTOESTUDIANTES,
                               Double.valueOf(0.00));
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoMuseo.VISITANTES,
                               Integer.valueOf(1000100));
        Assert.assertTrue(l.esVacia());

        l = bdd.buscaRegistros(CampoMuseo.NOMBRE, "");
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoMuseo.SALAS,
                               Integer.valueOf(Integer.MAX_VALUE));
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoMuseo.COSTOGENERAL,
                               Double.valueOf(Double.MAX_VALUE));
        Assert.assertFalse(l.esVacia());
        l = bdd.buscaRegistros(CampoMuseo.COSTOESTUDIANTES,
                               Double.valueOf(Double.MAX_VALUE));
        Assert.assertFalse(l.esVacia());
        l = bdd.buscaRegistros(CampoMuseo.VISITANTES,
                               Integer.valueOf(Integer.MAX_VALUE));
        Assert.assertTrue(l.esVacia());

        l = bdd.buscaRegistros(CampoMuseo.NOMBRE, null);
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoMuseo.SALAS, null);
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoMuseo.COSTOGENERAL, null);
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoMuseo.COSTOESTUDIANTES, null);
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoMuseo.VISITANTES, null);
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoMuseo.ESTACIONAMIENTO, null);
        Assert.assertTrue(l.esVacia());

        try {
            l = bdd.buscaRegistros(null, null);
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
        try {
            l = bdd.buscaRegistros(X.A, null);
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
    }
}
