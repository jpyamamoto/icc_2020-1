package mx.unam.ciencias.icc;

/**
 * Clase para representar estudiantes. Un estudiante tiene nombre, número de
 * cuenta, promedio y edad.
 */
public class Estudiante {

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
    public String toString() {
        return String.format(
            "Nombre   : %s\n" +
            "Cuenta   : %09d\n" +
            "Promedio : %2.2f\n" +
            "Edad     : %d",
            nombre, cuenta, promedio, edad);
    }

    /**
     * Nos dice si el estudiante recibido es igual al que manda llamar el
     * método.
     * @param estudiante el estudiante con el cual comparar.
     * @return <code>true</code> si el estudiante recibido tiene las mismas
     *         propiedades que el estudiante que manda llamar al método,
     *         <code>false</code> en otro caso.
     */
    public boolean equals(Estudiante estudiante) {
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
}

