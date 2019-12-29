package mx.unam.ciencias.icc;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.BooleanProperty;

/**
 * Clase que representa museos. Los datos que registramos de cada museo son el
 * nombre, número de salas, costo para el público en general, costo para
 * estudiantes, el número estimado de visitantes por año, y si tiene
 * estacionamiento o no. Esta clase implementa {@link Registro} con la
 * finalidad de poder realizar las acciones que requiere para funcionar en una
 * base de datos (representación en texto, definir las propiedades a partir de
 * una entrada como texto, caza de valores, etc).
 */
public class Museo implements Registro<Museo, CampoMuseo> {

    /* Nombre del museo. */
    private StringProperty nombre;
    /* Número de salas. */
    private IntegerProperty salas;
    /* Costo de la entrada para público en general. */
    private DoubleProperty costoGeneral;
    /* Costo de la entrada para estudiantes.*/
    private DoubleProperty costoEstudiantes;
    /* Número estimado de visitantes por año */
    private IntegerProperty visitantes;
    /* ¿Tiene estacionamiento o no? */
    private BooleanProperty estacionamiento;

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
        this.nombre           = new SimpleStringProperty(nombre);
        this.salas            = new SimpleIntegerProperty(salas);
        this.costoGeneral     = new SimpleDoubleProperty(costoGeneral);
        this.costoEstudiantes = new SimpleDoubleProperty(costoEstudiantes);
        this.visitantes       = new SimpleIntegerProperty(visitantes);
        this.estacionamiento  = new SimpleBooleanProperty(estacionamiento);
    }

    /**
     * Regresa el nombre del museo.
     * @return el nombre del museo.
     */
    public String getNombre() {
        return nombre.get();
    }

    /**
     * Define el nombre del museo.
     * @param nombre el nuevo nombre del museo.
     */
    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    /**
     * Regresa la propiedad del nombre.
     * @return la propiedad del nombre.
     */
    public StringProperty nombreProperty() {
        return this.nombre;
    }

    /**
     * Regresa el número de salas del museo.
     * @return el número de salas del museo.
     */
    public int getSalas() {
        return salas.get();
    }

    /**
     * Define el número de salas del museo.
     * @param salas el nuevo número de salas del estudiante.
     */
    public void setSalas(int salas) {
        this.salas.set(salas);
    }

    /**
     * Regresa la propiedad del número de salas.
     * @return la propiedad del número de salas.
     */
    public IntegerProperty salasProperty() {
        return this.salas;
    }

    /**
     * Regresa el costo general del museo.
     * @return el costo general del museo.
     */
    public double getCostoGeneral() {
        return costoGeneral.get();
    }

    /**
     * Define el costo general del museo.
     * @param costoGeneral el nuevo costo general del museo.
     */
    public void setCostoGeneral(double costoGeneral) {
        this.costoGeneral.set(costoGeneral);
    }

    /**
     * Regresa la propiedad del costo general.
     * @return la propiedad del costo general.
     */
    public DoubleProperty costoGeneralProperty() {
        return this.costoGeneral;
    }

    /**
     * Regresa el costo para estudiantes.
     * @return el costo para estudiantes.
     */
    public double getCostoEstudiantes() {
        return costoEstudiantes.get();
    }

    /**
     * Define el costo para estudiantes.
     * @param costoEstudiantes el nuevo costo para estudiantes.
     */
    public void setCostoEstudiantes(double costoEstudiantes) {
        this.costoEstudiantes.set(costoEstudiantes);
    }

    /**
     * Regresa la propiedad del costo para estudiantes.
     * @return la propiedad del costo para estudiantes.
     */
    public DoubleProperty costoEstudiantesProperty() {
        return this.costoEstudiantes;
    }

    /**
     * Regresa el número de visitantes.
     * @return el número de visitantes del museo.
     */
    public int getVisitantes() {
        return visitantes.get();
    }

    /**
     * Define el número de visitantes.
     * @param visitantes el nuevo número de visitantes.
     */
    public void setVisitantes(int visitantes) {
        this.visitantes.set(visitantes);
    }

    /**
     * Regresa la propiedad de los visitantes.
     * @return la propiedad de los visitantes.
     */
    public IntegerProperty visitantesProperty() {
        return this.visitantes;
    }

    /**
     * Regresa el booleano que representa si el museo cuenta con
     * estacionamiento.
     * @return el booleano del estacionamiento.
     */
    public boolean getEstacionamiento() {
        return estacionamiento.get();
    }

    /**
     * Define si el estacionamiento cuenta con estacionamiento.
     * @param estacionamiento si el museo cuenta con estacionamiento.
     */
    public void setEstacionamiento(boolean estacionamiento) {
        this.estacionamiento.set(estacionamiento);
    }

    /**
     * Regresa la propiedad del estacionamiento.
     * @return la propiedad del estacionamiento.
     */
    public BooleanProperty estacionamientoProperty() {
        return this.estacionamiento;
    }

    /**
     * Regresa una representación en cadena del museo.
     * @return una representación en cadena del museo.
     */
    @Override public String toString() {
        return String.format(
            "Nombre            : %s\n" +
            "Salas             : %d\n" +
            "Costo General     : $%.2f\n" +
            "Costo Estudiantes : $%.2f\n" +
            "Visitantes/año    : %,d\n" +
            "Estacionamiento   : %b\n",
            nombre.get(), salas.get(), costoGeneral.get(),
            costoEstudiantes.get(), visitantes.get(), estacionamiento.get());
    }

    /**
     * Nos dice si el objeto recibido es un museo igual al que manda llamar el
     * método.
     * @param objeto el objeto con el que el museo se comparará.
     * @return <code>true</code> si el objeto o es un museo con las mismas
     *         propiedades que el objeto que manda llamar al método,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (!(objeto instanceof Museo))
            return false;
        Museo museo = (Museo)objeto;

        return museo != null &&
            nombre.get().equals(museo.nombre.get()) &&
            salas.get()            == museo.salas.get() &&
            costoGeneral.get()     == museo.costoGeneral.get() &&
            costoEstudiantes.get() == museo.costoEstudiantes.get() &&
            visitantes.get()       == museo.visitantes.get() &&
            estacionamiento.get()  == museo.estacionamiento.get();

    }

    /**
     * Regresa una representación del museo en una línea de texto. La línea de
     * texto que este método regresa debe ser aceptada por el método {@link
     * Museo#deLinea}.
     * @return una representación del museo en una línea de texto.
     */
    @Override public String aLinea() {
        return String.format("%s\t%d\t%.2f\t%.2f\t%d\t%b\n",
                             nombre.get(), salas.get(), costoGeneral.get(),
                             costoEstudiantes.get(), visitantes.get(),
                             estacionamiento.get());
    }

    /**
     * Define las propiedades del museo a partir de una línea de texto. Las
     * líneas producidas por el método {@link Museo#aLinea} deben ser aceptadas
     * por éste método.
     * @param linea la línea con las nuevas propiedades del museo.
     * @throws ExcepcionLineaInvalida si la línea recibida es nula, vacía o no
     *         contiene los campos de un museo.
     */
    @Override public void deLinea(String linea) {
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

        nombre.set(partes[0]);
        salas.set(salasNuevo);
        costoGeneral.set(costoGeneralNuevo);
        costoEstudiantes.set(costoEstudiantesNuevo);
        visitantes.set(visitantesNuevo);

        // Boolean.valueOf() no lanza excepción.
        estacionamiento.set(Boolean.valueOf(partes[5]));
    }

    /**
     * Actualiza los valores del museo con los del museo recibido.
     * @param museo el museo con el cual actualizar los valores.
     * @throws IllegalArgumentException si el museo es <code>null</code>.
     */
    public void actualiza(Museo museo) {
        if (museo == null)
            throw new IllegalArgumentException("El museo es inválido.");

        nombre.set(museo.nombre.get());
        salas.set(museo.salas.get());
        costoGeneral.set(museo.costoGeneral.get());
        costoEstudiantes.set(museo.costoEstudiantes.get());
        visitantes.set(museo.visitantes.get());
        estacionamiento.set(museo.estacionamiento.get());
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
    @Override public boolean caza(CampoMuseo campo, Object valor) {
        if (campo == null)
            throw new IllegalArgumentException("El campo es inválido.");

        switch (campo) {
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
        return nombreValor.length() != 0 && nombre.get().contains(nombreValor);
    }

    private boolean cazaSalas(Object valor) {
        if (!(valor instanceof Integer))
            return false;

        int salasValor = (int)valor;
        return salasValor == salas.get();
    }

    private boolean cazaCostoGeneral(Object valor) {
        if (!(valor instanceof Double))
            return false;

        double costoGeneralValor = (double)valor;
        return costoGeneralValor >= costoGeneral.get();
    }

    private boolean cazaCostoEstudiantes(Object valor) {
        if (!(valor instanceof Double))
            return false;

        double costoEstudiantesValor = (double)valor;
        return costoEstudiantesValor >= costoEstudiantes.get();
    }

    private boolean cazaVisitantes(Object valor) {
        if (!(valor instanceof Integer))
            return false;

        int visitantesValor = (int)valor;
        return visitantesValor <= visitantes.get();
    }

    private boolean cazaEstacionamiento(Object valor) {
        if (!(valor instanceof Boolean))
            return false;

        boolean estacionamientoValor = (boolean)valor;
        return estacionamientoValor == estacionamiento.get();
    }
}
