package mx.unam.ciencias.icc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Clase abstracta que permite implementar el comportamiento para las bases de
 * datos de distintos tipos de objetos.
 * Nos permite agregar registros, eliminarlos, guardar los datos en una salida,
 * crearse a partir de una entrada, buscar por valores, etc.
 * El único método abstracto es {@link #creaRegistro} para crear registros
 * vacíos, que posteriormente serán actualizados.
 */
public abstract class BaseDeDatos {

    /* Lista que simula una base de datos. */
    private Lista registros;

    /**
     * Constructor que asigna una lista vacía en la variable registros.
     */
    public BaseDeDatos() {
        registros = new Lista();
    }

    /**
     * Regresa el número de registros.
     * @return número de registros.
     */
    public int getNumRegistros() {
        return registros.getLongitud();
    }

    /**
     * Obtiene los registros que se encuentran en la base de datos actual, como
     * una copia con la finalidad de que no se modifique la información de la
     * base de datos al trabajar con lo que regresa este método.
     * @return una copia de los registros.
     */
    public Lista getRegistros() {
        return registros.copia();
    }

    /**
     * Agrega un registro a la base de datos.
     * @param registro el registro a agregar.
     */
    public void agregaRegistro(Registro registro) {
        registros.agregaFinal(registro);
    }

    /**
     * Elimina el registro recibido de la base de datos.
     * Elimina de la base de datos la primera instancia igual al registro
     * recibido.
     * @param registro el registro que debe eliminarse de la base de datos.
     */
    public void eliminaRegistro(Registro registro) {
        registros.elimina(registro);
    }

    /**
     * Deja en blanco la base de datos.
     */
    public void limpia() {
        registros.limpia();
    }

    /**
     * Escribe la representación en cadena de los registros guardados en la
     * base de datos, en la salida que recibo como parámetro.
     * @param out la salida en la que se escriben los registros.
     * @throws IOException cuando hay un error relacionado con la entrada o
     * salida.
     */
    public void guarda(BufferedWriter out) throws IOException {
        Lista.Nodo nodo = registros.getCabeza();

        while (nodo != null) {
            Object elemento = nodo.get();

            if (!(elemento instanceof Registro))
                return;

            Registro reg = (Registro)elemento;
            out.write(reg.escribeLinea());

            nodo = nodo.getSiguiente();
        }
    }

    /**
     * Limpia la base de datos y crea los registros que lee de la entrada y los
     * guarda en la base de datos.
     * @param in la entrada de donde leer la información para crear los
     *        registros.
     * @throws IOException cuando hay un error relacionado con la entrada o
     * salida.
     */
    public void carga(BufferedReader in) throws IOException {
        limpia();
        String linea;

        while ((linea = in.readLine()) != null) {
            Registro reg = creaRegistro();
            try {
                reg.leeLinea(linea);
            } catch (ExcepcionLineaInvalida e) {
                throw new IOException("Línea Inválida.");
            }
            agregaRegistro(reg);
        }
    }

    /**
     * Obtiene los registros que coinciden con un campo y valor recibidos.
     * @param campo el campo a partir del cuál hacer la búsqueda.
     * @param valor el valor que comparar para la búsqueda.
     * @return una lista que contiene a los registros que coinciden con el campo
     *         y valor recibidos.
     * @throws IllegalArgumentException si el campo recibido es inválido.
     */
    public Lista buscaRegistros(Enum campo, Object valor) {
        Lista lista = new Lista();

        Lista.Nodo nodo = registros.getCabeza();

        while (nodo != null) {
            Object elemento = nodo.get();

            Registro reg = (Registro)elemento;

            if (reg.caza(campo, valor))
                lista.agregaFinal(reg);

            nodo = nodo.getSiguiente();
        }

        return lista;
    }

    /**
     * Crea un nuevo registro en blanco.
     * @return un nuevo registro en blanco.
     */
    public abstract Registro creaRegistro();

}
