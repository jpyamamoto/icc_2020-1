package mx.unam.ciencias.icc;

import java.util.NoSuchElementException;

/**
 * <p>Clase para listas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas son iterables utilizando sus nodos. Las listas no aceptan a
 * <code>null</code> como elemento.</p>
 */
public class Lista {

    /**
     * Clase interna para nodos.
     */
    public class Nodo {

        /* El elemento del nodo. */
        private Object elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nodo con un elemento. */
        private Nodo(Object elemento) {
            this.elemento = elemento;
        }

        /**
         * Regresa el nodo anterior del nodo.
         * @return el nodo anterior del nodo.
         */
        public Nodo getAnterior() {
            return anterior;
        }

        /**
         * Regresa el nodo siguiente del nodo.
         * @return el nodo siguiente del nodo.
         */
        public Nodo getSiguiente() {
            return siguiente;
        }

        /**
         * Regresa el elemento del nodo.
         * @return el elemento del nodo.
         */
        public Object get() {
            return elemento;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        return longitud;
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean esVacia() {
        return longitud == 0;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(Object elemento) throws IllegalArgumentException {
        this.inserta(longitud, elemento);
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(Object elemento) throws IllegalArgumentException {
        this.inserta(0, elemento);
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void inserta(int i, Object elemento) throws IllegalArgumentException {
        if (elemento == null)
            throw new IllegalArgumentException();

        Nodo nodo = new Nodo(elemento);
        longitud++;

        if (i <= 0) {
            nodo.siguiente = cabeza;

            if (rabo == null)
                rabo = nodo;
            else
                cabeza.anterior = nodo;

            cabeza = nodo;
            return;
        }

        if (i >= longitud - 1) {
            nodo.anterior = rabo;

            if (cabeza == null)
                cabeza = nodo;
            else
                rabo.siguiente = nodo;

            rabo = nodo;
            return;
        }

        Nodo nodoTemp = getNodo(i);

        Nodo nodoAnterior = nodoTemp.anterior;

        nodoAnterior.siguiente = nodo;
        nodoTemp.anterior = nodo;
        nodo.anterior = nodoAnterior;
        nodo.siguiente = nodoTemp;
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(Object elemento) {
        int indice = indiceDe(elemento);

        if (indice == -1)
            return;

        if (indice == 0) {
            eliminaPrimero();
            return;
        }

        if (indice == longitud - 1) {
            eliminaUltimo();
            return;
        }

        Nodo nodo = getNodo(indice);

        nodo.anterior.siguiente = nodo.siguiente;
        nodo.siguiente.anterior = nodo.anterior;
        longitud--;
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public Object eliminaPrimero() throws NoSuchElementException {
        if (longitud == 0)
            throw new NoSuchElementException();

        Object elementoObj = cabeza.elemento;
        cabeza = cabeza.siguiente;

        if (cabeza != null)
            cabeza.anterior = null;
        else
            rabo = null;

        longitud--;
        return elementoObj;
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public Object eliminaUltimo() throws NoSuchElementException {
        if (longitud == 0)
            throw new NoSuchElementException();

        Object elementoObj = rabo.elemento;
        rabo = rabo.anterior;

        if (rabo != null)
            rabo.siguiente = null;
        else
            cabeza = null;

        longitud--;
        return elementoObj;
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <code>true</code> si <code>elemento</code> está en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean contiene(Object elemento) {
        return indiceDe(elemento) != -1;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista reversa() {
        return reversa(cabeza, new Lista());
    }
    private Lista reversa(Nodo nodo, Lista lista) {
        if (nodo == null)
            return lista;

        lista.agregaInicio(nodo.elemento);
        return reversa(nodo.siguiente, lista);
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copia de la lista.
     */
    public Lista copia() {
        return copia(cabeza, new Lista());
    }
    private Lista copia(Nodo nodo, Lista lista) {
        if (nodo == null)
            return lista;

        lista.agregaFinal(nodo.elemento);
        return copia(nodo.siguiente, lista);
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    public void limpia() {
        cabeza = rabo = null;
        longitud = 0;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public Object getPrimero() throws NoSuchElementException {
        if (longitud == 0)
            throw new NoSuchElementException();

        return cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public Object getUltimo() throws NoSuchElementException {
        if (longitud == 0)
            throw new NoSuchElementException();

        return rabo.elemento;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista.
     * @throws ExcepcionIndiceInvalido si <em>i</em> es menor que cero o mayor o
     *         igual que el número de elementos en la lista.
     */
    public Object get(int i) throws ExcepcionIndiceInvalido {
        if (i < 0 || i >= longitud)
            throw new ExcepcionIndiceInvalido();

        return getNodo(i).elemento;
    }

    private Nodo getNodo(int posicion) {
        return getNodo(posicion, cabeza);
    }
    private Nodo getNodo(int posicion, Nodo nodo) {
        if (posicion == 0 || nodo == null)
            return nodo;

        return getNodo(--posicion, nodo.siguiente);
    }


    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(Object elemento) {
        return indiceDe(elemento, cabeza, 0);
    }
    private int indiceDe(Object elemento, Nodo nodo, int posicion) {
        if (nodo == null)
            return -1;

        if (nodo.elemento.equals(elemento))
            return posicion;

        return indiceDe(elemento, nodo.siguiente, ++posicion);
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
        String texto = "[";

        if (longitud != 0)
            texto += toString(cabeza);

        texto += "]";
        return texto;
    }
    private String toString(Nodo nodo) {
        if (nodo.siguiente == null)
            return String.format("%s", nodo.elemento);

        return String.format("%s, ", nodo.elemento) + toString(nodo.siguiente);
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param objeto el objeto con el que hay que comparar.
     * @return <code>true</code> si la lista es igual al objeto recibido;
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (!(objeto instanceof Lista))
            return false;
        Lista lista = (Lista)objeto;

        if (lista == null || longitud != lista.getLongitud())
            return false;

        return equals(lista.cabeza, cabeza, 0);
    }
    private boolean equals(Nodo nodoLista1, Nodo nodoLista2, int posicion) {
        if (posicion == longitud)
            return true;

        if (!nodoLista1.elemento.equals(nodoLista2.elemento))
            return false;

        return equals(nodoLista1.siguiente, nodoLista2.siguiente, ++posicion);
    }

    /**
     * Regresa el nodo cabeza de la lista.
     * @return el nodo cabeza de la lista.
     */
    public Nodo getCabeza() {
        return cabeza;
    }

    /**
     * Regresa el nodo rabo de la lista.
     * @return el nodo rabo de la lista.
     */
    public Nodo getRabo() {
        return rabo;
    }
}
