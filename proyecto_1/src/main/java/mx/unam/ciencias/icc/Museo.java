package mx.unam.ciencias.icc;

/**
 * Clase que representa museos. Los datos que registramos de cada museo son el
 * nombre, número de salas, costo para el público en general, costo para
 * estudiantes, el número estimado de visitantes por año, y si tiene
 * estacionamiento o no. Esta clase implementa {@link Registro} con la
 * finalidad de poder realizar las acciones que requiere para funcionar en una
 * base de datos (representación en texto, definir las propiedades a partir de
 * una entrada como texto, caza de valores, etc).
 */
public class Museo implements Registro {

    /* Nombre del museo. */
    private String nombre;
    /* Número de salas. */
    private int salas;
    /* Costo de la entrada para público en general. */
    private double costoGeneral;
    /* Costo de la entrada para estudiantes.*/
    private double costoEstudiantes;
    /* Número estimado de visitantes por año */
    private int visitantes;
    /* ¿Tiene estacionamiento o no? */
    private boolean estacionamiento;

    /**
     * Define el estado inicial de un museo.
     * @param nombre el nombre del museo.
     * @param salas el número de salas que tiene el museo.
     * @param costoGeneral el costo para el público en general.
     * @param costoEstudiantes el costo para estudiantes.
     * @param visitantes el número estimado de visitantes del museo por año.
     * @param estacionamiento si el museo cuenta con estacionamiento o no.
     */
    public Museo(String  nombre,
                 int     salas,
                 double  costoGeneral,
                 double  costoEstudiantes,
                 int     visitantes,
                 boolean estacionamiento) {
        this.nombre           = nombre;
        this.salas            = salas;
        this.costoGeneral     = costoGeneral;
        this.costoEstudiantes = costoEstudiantes;
        this.visitantes       = visitantes;
        this.estacionamiento  = estacionamiento;
    }

    /**
     * Obtiene el nombre del museo.
     * @return nombre del museo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el nombre del museo.
     * @param nombre el nombre del museo que se asigna.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el número de salas del museo.
     * @return número de salas.
     */
    public int getSalas() {
        return salas;
    }

    /**
     * Define las salas del museo.
     * @param salas el número de salas que se asigna.
     */
    public void setSalas(int salas) {
        this.salas = salas;
    }

    /**
     * Obtiene el costo de la entrada para el público en general.
     * @return el costo para el público en general.
     */
    public double getCostoGeneral() {
        return costoGeneral;
    }

    /**
     * Define el costo de la entrada para el público en general.
     * @param costoGeneral el costo para el público en general que se asigna.
     */
    public void setCostoGeneral(double costoGeneral) {
        this.costoGeneral = costoGeneral;
    }

    /**
     * Obtiene el costo de la entrada para estudiantes.
     * @return el costo para estudiantes.
     */
    public double getCostoEstudiantes() {
        return costoEstudiantes;
    }

    /**
     * Define el costo de la entrada para estudiantes.
     * @param costoEstudiantes el costo para estudiantes que se asigna.
     */
    public void setCostoEstudiantes(double costoEstudiantes) {
        this.costoEstudiantes = costoEstudiantes;
    }

    /**
     * Obtiene el número de visitantes estimado por año.
     * @return el número de visitantes por año.
     */
    public int getVisitantes() {
        return visitantes;
    }

    /**
     * Define el número de visitantes por año.
     * @param visitantes el número de visitantes que se asigna.
     */
    public void setVisitantes(int visitantes) {
        this.visitantes = visitantes;
    }

    /**
     * Obtiene si el museo cuenta con estacionamiento.
     * @return booleano que indica si el museo tiene estacionamiento.
     */
    public boolean getEstacionamiento() {
        return estacionamiento;
    }

    /**
     * Define si el museo tiene estacionamiento.
     * @param estacionamiento el booleano que indica si tiene estacionamiento.
     */
    public void setEstacionamiento(boolean estacionamiento) {
        this.estacionamiento = estacionamiento;
    }

    /**
     * Regresa una representación como cadena de texto de la instancia museo.
     * @return representación como cadena del museo.
     */
    @Override public String toString() {
        return String.format(
            "Nombre            : %s\n" +
            "Salas             : %d\n" +
            "Costo General     : $%.2f\n" +
            "Costo Estudiantes : $%.2f\n" +
            "Visitantes/año    : %,d\n" +
            "Estacionamiento   : %b\n",
            nombre, salas, costoGeneral,
            costoEstudiantes, visitantes, estacionamiento);
    }

    /**
     * Indica si el objeto que se pasa como parámetro es igual que la instancia
     * de museo que manda a llamar al método.
     * @param objeto el objeto con las propiedades con que se compara el objeto
     *               actual.
     * @return <code>true</code> si objeto es un museo y todas sus propiedades
     *         son iguales que las variables de clase de la instancia actual.
     *         <code>false</code> si el objeto no es museo o tiene al menos una
     *         variable de clase distinta.
     */
    @Override public boolean equals(Object objeto) {
        if (!(objeto instanceof Museo))
            return false;
        Museo museo = (Museo)objeto;

        return museo != null &&
            nombre.equals(museo.nombre) &&
            salas            == museo.salas &&
            costoGeneral     == museo.costoGeneral &&
            costoEstudiantes == museo.costoEstudiantes &&
            visitantes       == museo.visitantes &&
            estacionamiento  == museo.estacionamiento;

    }

    /**
     * Regresa la representación como cadena de texto (en una línea para poder
     * guardarse en un archivo) del museo actual. El String que regresa este
     * método debe ser un parámetro válido para el método {@link Museo#leeLinea}.
     * @return representación como cadena de texto del museo.
     */
    @Override public String escribeLinea() {
        return String.format("%s\t%d\t%.2f\t%.2f\t%d\t%b\n",
                             nombre, salas, costoGeneral,
                             costoEstudiantes, visitantes, estacionamiento);
    }

    /**
     * Modifica las variables de clase de la instancia del museo que lo manda a
     * llamar, a partir de los valores que obtiene de una línea de texto. Lo
     * que regresa el método {@link Museo#escribeLinea} debe ser un parámetro
     * válido para este método.
     * @param linea la representación como cadena de texto que contiene las
     *        propiedades del museo.
     * @throws ExcepcionLineaInvalida si el parámetro linea es nulo, vacío o no
     *         contiene los campos requeridos para el museo.
     */
    @Override public void leeLinea(String linea) {
        if (linea == null || linea.equals(""))
            throw new ExcepcionLineaInvalida("La línea es vacía.");

        String[] partes = linea.trim().split("\t");

        if (partes.length != 6)
            throw new ExcepcionLineaInvalida("La línea no contiene todos los campos requeridos.");

        int salasNuevo, visitantesNuevo;
        double costoGeneralNuevo, costoEstudiantesNuevo;

        try {
            salasNuevo = Integer.valueOf(partes[1]);
            costoGeneralNuevo = Double.valueOf(partes[2]);
            costoEstudiantesNuevo = Double.valueOf(partes[3]);
            visitantesNuevo = Integer.valueOf(partes[4]);
        } catch (NumberFormatException e) {
            throw new ExcepcionLineaInvalida("Los valores de la línea no son válidos.");
        }

        // Es necesario verificar que tengamos registradas las cadenas
        // "true" o "false" explicitamente. De otra manera, cualquier
        // cadena regresa false al hacer la audición a Boolean.
        if (!(partes[5].equals("true") || partes[5].equals("false")))
            throw new ExcepcionLineaInvalida("El valor de estacionamiento es inválido.");

        nombre           = partes[0];
        salas            = salasNuevo;
        costoGeneral     = costoGeneralNuevo;
        costoEstudiantes = costoEstudiantesNuevo;
        visitantes       = visitantesNuevo;

        // Boolean.valueOf() no lanza excepción.
        estacionamiento  = Boolean.valueOf(partes[5]);
    }

    /**
     * Actualiza las variables de clase del museo actual a partir de las
     * propiedades de otro registro.
     * @param registro el registro cuyas propiedades adoptará el museo actual.
     * @throws IllegalArgumentException si el registro no es instancia de {@link
     *         Museo}
     */
    public void actualiza(Registro registro) {
        if (!(registro instanceof Museo))
            throw new IllegalArgumentException("El registro no es un Museo.");

        Museo museo = (Museo)registro;

        nombre           = museo.nombre;
        salas            = museo.salas;
        costoGeneral     = museo.costoGeneral;
        costoEstudiantes = museo.costoEstudiantes;
        visitantes       = museo.visitantes;
        estacionamiento  = museo.estacionamiento;
    }

    /**
     * Indica si el museo caza con los parámetros recibidos por el método.
     * @param campo el campo que hay que comparar.
     * @param valor el valor con el que se compara el campo recibido del museo.
     * @return <code>true</code> si:
     *         <ul>
     *           <li><code>campo</code> es {@link CampoMuseo#NOMBRE} y
     *              <code>valor</code> es instancia de {@link String} y es una
     *              subcadena del nombre del museo.</li>
     *           <li><code>campo</code> es {@link CampoMuseo#SALAS} y
     *              <code>valor</code> es instancia de {@link Integer} y el
     *              valor es igual al número de salas del museo.</li>
     *           <li><code>campo</code> es {@link CampoMuseo#COSTOGENERAL} y
     *              <code>valor</code> es instancia de {@link Double} y su
     *              valor es igual o mayor al costo general del museo.</li>
     *           <li><code>campo</code> es {@link CampoMuseo#COSTOESTUDIANTES}
     *              y <code>valor</code> es instancia de {@link Double} y su
     *              valor es igual o mayor al costo para estudiantes del
     *              museo.</li>
     *           <li><code>campo</code> es {@link CampoMuseo#VISITANTES} y
     *              <code>valor</code> es instancia de {@link Integer} y el
     *              valor es igual o menor al número de visitantes del
     *              museo.</li>
     *           <li><code>campo</code> es {@link CampoMuseo#ESTACIONAMIENTO} y
     *              <code>valor</code> es instancia de {@link Boolean} y el
     *              valor es igual que el valor de la variable
     *              estacionamiento.</li>
     *         </ul>
     *         <code>false</code> en otro caso.
     * @throws IllegalArgumentException si el campo no es instancia de {@link
     *         CampoMuseo}.
     */
    public boolean caza(Enum campo, Object valor) {
        if (!(campo instanceof CampoMuseo))
            throw new IllegalArgumentException("El campo no es campo de Museo.");

        CampoMuseo enumCampo = (CampoMuseo)campo;

        switch (enumCampo) {
            case NOMBRE:           return cazaNombre(valor);
            case SALAS:            return cazaSalas(valor);
            case COSTOGENERAL:     return cazaCostoGeneral(valor);
            case COSTOESTUDIANTES: return cazaCostoEstudiantes(valor);
            case VISITANTES:       return cazaVisitantes(valor);
            case ESTACIONAMIENTO:  return cazaEstacionamiento(valor);
            default:               return false;
        }
    }

    private boolean cazaNombre(Object valor) {
        if (!(valor instanceof String))
            return false;

        String nombreValor = (String)valor;
        return nombreValor.length() != 0 && nombre.contains(nombreValor);
    }

    private boolean cazaSalas(Object valor) {
        if (!(valor instanceof Integer))
            return false;

        int salasValor = (int)valor;
        return salasValor == salas;
    }

    private boolean cazaCostoGeneral(Object valor) {
        if (!(valor instanceof Double))
            return false;

        double costoGeneralValor = (double)valor;
        return costoGeneralValor >= costoGeneral;
    }

    private boolean cazaCostoEstudiantes(Object valor) {
        if (!(valor instanceof Double))
            return false;

        double costoEstudiantesValor = (double)valor;
        return costoEstudiantesValor >= costoEstudiantes;
    }

    private boolean cazaVisitantes(Object valor) {
        if (!(valor instanceof Integer))
            return false;

        int visitantesValor = (int)valor;
        return visitantesValor <= visitantes;
    }

    private boolean cazaEstacionamiento(Object valor) {
        if (!(valor instanceof Boolean))
            return false;

        boolean estacionamientoValor = (boolean)valor;
        return estacionamientoValor == estacionamiento;
    }
}
