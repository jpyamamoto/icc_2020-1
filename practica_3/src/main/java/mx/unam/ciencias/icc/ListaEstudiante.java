package mx.unam.ciencias.icc;

/**
 * <p>Clase para listas de estudiantes doblemente ligadas.</p>
 *
 * <p>Las listas de estudiantes nos permiten agregar elementos al inicio o final
 * de la lista, eliminar elementos de la lista, comprobar si un elemento está o
 * no en la lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas de estudiantes son iterables utilizando sus nodos. Las listas
 * no aceptan a <code>null</code> como elemento.</p>
 *
 * <p>Los elementos en una lista de estudiantes siempre son instancias de la
 * clase {@link Estudiante}.</p>
 */
public class ListaEstudiante {

    /**
     * Clase interna para nodos.
     */
    public class Nodo {

        /* El elemento del nodo. */
        private Estudiante elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nodo con un elemento. */
        private Nodo(Estudiante elemento) {
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
        public Estudiante get() {
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
     * @param elemento el elemento a agregar. El elemento se agrega únicamente
     *                 si es distinto de <code>null</code>.
     */
    public void agregaFinal(Estudiante elemento) {
        this.inserta(longitud, elemento);
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar. El elemento se agrega únicamente
     *                 si es distinto de <code>null</code>.
     */
    public void agregaInicio(Estudiante elemento) {
        this.inserta(0, elemento);
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al final de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar. El elemento se inserta únicamente
     *                 si es distinto de <code>null</code>.
     */
    public void inserta(int i, Estudiante elemento) {
        if (elemento == null)
            return;

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
    public void elimina(Estudiante elemento) {
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
     * @return el primer elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista es vacía.
     */
    public Estudiante eliminaPrimero() {
        if (longitud == 0)
            return null;

        Estudiante estudiante = cabeza.elemento;
        cabeza = cabeza.siguiente;

        if (cabeza != null)
            cabeza.anterior = null;
        else
            rabo = null;

        longitud--;
        return estudiante;
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista es vacía.
     */
    public Estudiante eliminaUltimo() {
        if (longitud == 0)
            return null;

        Estudiante estudiante = rabo.elemento;
        rabo = rabo.anterior;

        if (rabo != null)
            rabo.siguiente = null;
        else
            cabeza = null;

        longitud--;
        return estudiante;
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <code>true</code> si <code>elemento</code> está en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean contiene(Estudiante elemento) {
        return indiceDe(elemento) != -1;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public ListaEstudiante reversa() {
        return reversa(cabeza, new ListaEstudiante());
    }
    private ListaEstudiante reversa(Nodo nodo, ListaEstudiante lista) {
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
    public ListaEstudiante copia() {
        return copia(cabeza, new ListaEstudiante());
    }
    private ListaEstudiante copia(Nodo nodo, ListaEstudiante lista) {
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
     * @return el primer elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public Estudiante getPrimero() {
        if (longitud == 0)
            return null;

        return cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el último elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public Estudiante getUltimo() {
        if (longitud == 0)
            return null;

        return rabo.elemento;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista, o <code>null</code> si
     *         <em>i</em> es menor que cero o mayor o igual que el número de
     *         elementos en la lista.
     */
    public Estudiante get(int i) {
        if (i < 0 || i >= longitud)
            return null;

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
    public int indiceDe(Estudiante elemento) {
        return indiceDe(elemento, cabeza, 0);
    }
    private int indiceDe(Estudiante elemento, Nodo nodo, int posicion) {
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
    public String toString() {
        String texto = "[";

        if (longitud != 0)
            texto += toString(cabeza, "");

        texto += "]";
        return texto;
    }
    private String toString(Nodo nodo, String texto) {
        if (nodo.siguiente == null)
            return texto + String.format("%s", nodo.elemento);

        return toString(nodo.siguiente, texto + String.format("%s, ", nodo.elemento));
    }

    /**
     * Nos dice si la lista es igual a la lista recibida.
     * @param lista la lista con la que hay que comparar.
     * @return <code>true</code> si la lista es igual a la recibida;
     *         <code>false</code> en otro caso.
     */
    public boolean equals(ListaEstudiante lista) {
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

