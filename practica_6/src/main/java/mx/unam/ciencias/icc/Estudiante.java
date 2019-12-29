package mx.unam.ciencias.icc;

/**
 * Clase para representar estudiantes. Un estudiante tiene nombre, número de
 * cuenta, promedio y edad. La clase implementa {@link Registro}, por lo que
 * puede representarse con una línea de texto y definir sus propiedades con una
 * línea de texto; además de determinar si sus campos cazan valores arbitrarios.
 */
public class Estudiante implements Registro<Estudiante, CampoEstudiante> {

    /* Nombre del estudiante. */
    private String nombre;
    /* Número de cuenta. */
    private int cuenta;
    /* Pormedio del estudiante. */
    private double promedio;
    /* Edad del estudiante.*/
    private int edad;

    /**
     * Define el estado inicial de un estudiante.
     * @param nombre el nombre del estudiante.
     * @param cuenta el número de cuenta del estudiante.
     * @param promedio el promedio del estudiante.
     * @param edad la edad del estudiante.
     */
    public Estudiante(String nombre,
                      int    cuenta,
                      double promedio,
                      int    edad) {
        this.nombre   = nombre;
        this.cuenta   = cuenta;
        this.promedio = promedio;
        this.edad     = edad;
    }

    /**
     * Regresa el nombre del estudiante.
     * @return el nombre del estudiante.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el nombre del estudiante.
     * @param nombre el nuevo nombre del estudiante.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Regresa el número de cuenta del estudiante.
     * @return el número de cuenta del estudiante.
     */
    public int getCuenta() {
        return cuenta;
    }

    /**
     * Define el número cuenta del estudiante.
     * @param cuenta el nuevo número de cuenta del estudiante.
     */
    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * Regresa el promedio del estudiante.
     * @return el promedio del estudiante.
     */
    public double getPromedio() {
        return promedio;
    }

    /**
     * Define el promedio del estudiante.
     * @param promedio el nuevo promedio del estudiante.
     */
    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    /**
     * Regresa la edad del estudiante.
     * @return la edad del estudiante.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Define la edad del estudiante.
     * @param edad la nueva edad del estudiante.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Regresa una representación en cadena del estudiante.
     * @return una representación en cadena del estudiante.
     */
    @Override public String toString() {
        return String.format(
            "Nombre   : %s\n" +
            "Cuenta   : %09d\n" +
            "Promedio : %2.2f\n" +
            "Edad     : %d",
            nombre, cuenta, promedio, edad);
    }

    /**
     * Nos dice si el objeto recibido es un estudiante igual al que manda llamar
     * el método.
     * @param objeto el objeto con el que el estudiante se comparará.
     * @return <code>true</code> si el objeto recibido es un estudiante con las
     *         mismas propiedades que el objeto que manda llamar al método,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (!(objeto instanceof Estudiante))
            return false;
        Estudiante estudiante = (Estudiante)objeto;

        if (estudiante == null)
            return false;

        if (!nombre.equals(estudiante.nombre))
            return false;

        if (cuenta != estudiante.cuenta)
            return false;

        if (promedio != estudiante.promedio)
            return false;

        if (edad != estudiante.edad)
            return false;

        return true;
    }

    /**
     * Regresa una representación del estudiante en una línea de texto. La línea
     * de texto que este método regresa debe ser aceptada por el método {@link
     * Estudiante#deLinea}.
     * @return una representación del estudiante en una línea de texto.
     */
    @Override public String aLinea() {
        return String.format("%s\t%d\t%2.2f\t%d\n",
                             nombre, cuenta, promedio, edad);
    }

    /**
     * Define las propiedades del estudiante a partir de una línea de texto. Las
     * líneas producidas por el método {@link Estudiante#aLinea} deben ser
     * aceptadas por éste método.
     * @param linea la línea con las nuevas propiedades del estudiante.
     * @throws ExcepcionLineaInvalida si la línea recibida es nula, vacía o no
     *         contiene los campos de un estudiante.
     */
    @Override public void deLinea(String linea) throws ExcepcionLineaInvalida {
        if (linea == null || linea.equals(""))
            throw new ExcepcionLineaInvalida();

        String[] partes = linea.trim().split("\t");

        if (partes.length != 4)
            throw new ExcepcionLineaInvalida();

        int cuenta_nuevo, edad_nuevo;
        double promedio_nuevo;
        try {
            cuenta_nuevo = Integer.valueOf(partes[1]);
            promedio_nuevo = Double.valueOf(partes[2]);
            edad_nuevo = Integer.valueOf(partes[3]);
        } catch (NumberFormatException e) {
            throw new ExcepcionLineaInvalida();
        }

       nombre = partes[0];
       promedio = promedio_nuevo;
       cuenta = cuenta_nuevo;
       edad = edad_nuevo;
    }

    /**
     * Actualiza los valores del estudiante con los del estudiante recibido.
     * @param estudiante el estudiante con el cual actualizar los valores.
     * @throws IllegalArgumentException si el estudiante es <code>null</code>.
     */
    public void actualiza(Estudiante estudiante) throws IllegalArgumentException {
        if (estudiante == null)
            throw new IllegalArgumentException();

        nombre   = estudiante.nombre;
        cuenta   = estudiante.cuenta;
        promedio = estudiante.promedio;
        edad     = estudiante.edad;
    }

    /**
     * Nos dice si el estudiante caza el valor dado en el campo especificado.
     * @param campo el campo que hay que cazar.
     * @param valor el valor con el que debe cazar el campo del registro.
     * @return <code>true</code> si:
     *         <ul>
     *           <li><code>campo</code> es {@link CampoEstudiante#NOMBRE} y
     *              <code>valor</code> es instancia de {@link String} y es una
     *              subcadena del nombre del estudiante.</li>
     *           <li><code>campo</code> es {@link CampoEstudiante#CUENTA} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es mayor o igual a la cuenta del
     *              estudiante.</li>
     *           <li><code>campo</code> es {@link CampoEstudiante#PROMEDIO} y
     *              <code>valor</code> es instancia de {@link Double} y su
     *              valor doble es mayor o igual al promedio del
     *              estudiante.</li>
     *           <li><code>campo</code> es {@link CampoEstudiante#EDAD} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es mayor o igual a la edad del
     *              estudiante.</li>
     *         </ul>
     *         <code>false</code> en otro caso.
     * @throws IllegalArgumentException si el campo es <code>null</code>.
     */
    @Override public boolean caza(CampoEstudiante campo, Object valor) {
        if (campo == null)
            throw new IllegalArgumentException();

        switch (campo) {
            case NOMBRE:   return cazaNombre(valor);
            case CUENTA:   return cazaCuenta(valor);
            case PROMEDIO: return cazaPromedio(valor);
            case EDAD:     return cazaEdad(valor);
            default:       return false;
        }
    }

    private boolean cazaNombre(Object valor) {
        if (!(valor instanceof String))
            return false;

        String nombre_valor = (String)valor;
        return nombre_valor.length() != 0 && nombre.contains(nombre_valor);
    }

    private boolean cazaCuenta(Object valor) {
        if (!(valor instanceof Integer))
            return false;

        int cuenta_valor = (int)valor;
        return cuenta_valor <= cuenta;
    }

    private boolean cazaPromedio(Object valor) {
        if (!(valor instanceof Double))
            return false;

        double promedio_valor = (double)valor;
        return promedio_valor <= promedio;
    }

    private boolean cazaEdad(Object valor) {
        if (!(valor instanceof Integer))
            return false;

        int edad_valor = (int)valor;
        return edad_valor <= edad;
    }
}
