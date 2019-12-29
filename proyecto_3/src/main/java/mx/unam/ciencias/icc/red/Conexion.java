package mx.unam.ciencias.icc.red;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import mx.unam.ciencias.icc.EventoBaseDeDatos;
import mx.unam.ciencias.icc.ExcepcionLineaInvalida;
import mx.unam.ciencias.icc.BaseDeDatos;
import mx.unam.ciencias.icc.Lista;
import mx.unam.ciencias.icc.Registro;

/**
 * Clase para conexiones de la base de datos.
 */
public class Conexion<R extends Registro<R, ?>> {

    /* Contador de números seriales. */
    private static int contadorSerial;

    /* La entrada de la conexión. */
    private BufferedReader in;
    /* La salida de la conexión. */
    private BufferedWriter out;
    /* La base de datos. */
    private BaseDeDatos<R, ?> bdd;
    /* Lista de escuchas de conexión. */
    private Lista<EscuchaConexion<R>> escuchas;
    /* El enchufe. */
    private Socket enchufe;
    /* Si la conexión está activa. */
    private boolean activa;
    /* El número serial único de la conexión. */
    private int serial;

    /**
     * Define el estado inicial de una nueva conexión.
     * @param bdd la base de datos.
     * @param enchufe el enchufe de la conexión ya establecida.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    public Conexion(BaseDeDatos<R, ?> bdd, Socket enchufe) throws IOException {
        this.in = new BufferedReader(new InputStreamReader(enchufe.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(enchufe.getOutputStream()));
        this.bdd = bdd;
        this.escuchas = new Lista<EscuchaConexion<R>>();
        this.enchufe = enchufe;
        this.activa = true;
        this.serial = ++contadorSerial;
    }

    /**
     * Recibe los mensajes de la conexión. El método no termina hasta que la
     * conexión sea cerrada. Al ir leyendo su entrada, la conexión convertirá lo
     * que lea en mensajes y reportará cada uno a los escuchas.
     */
    public void recibeMensajes() {
        try {
            String linea;
            while((linea = in.readLine()) != null) {
                Mensaje mensaje = Mensaje.getMensaje(linea);
                notificaEscuchas(mensaje);
            }
            activa = false;
        } catch (IOException ioe) {
            if (activa)
                notificaEscuchas(Mensaje.INVALIDO);
        }
        notificaEscuchas(Mensaje.DESCONECTAR);
    }

    /**
     * Recibe la base de datos del otro lado de la conexión.
     * @throws IOException si la base de datos no puede recibirse.
     */
    public void recibeBaseDeDatos() throws IOException {
        bdd.carga(in);
    }

    /**
     * Envía la base de datos al otro lado de la conexión.
     * @throws IOException si la base de datos no puede enviarse.
     */
    public void enviaBaseDeDatos() throws IOException {
        bdd.guarda(out);
        out.newLine();
        out.flush();
    }

    /**
     * Recibe un registro del otro lado de la conexión.
     * @return un registro del otro lado de la conexión.
     * @throws IOException si el registro no puede recibirse.
     */
    public R recibeRegistro() throws IOException {
        String linea = in.readLine();
        R registro = bdd.creaRegistro();
        try {
            registro.deLinea(linea);
        } catch (ExcepcionLineaInvalida e) {
            throw new IOException("Se recibió línea inválida.");
        }
        return registro;
    }

    /**
     * Envía un registro al otro lado de la conexión.
     * @param registro el registro a enviar.
     * @throws IOException si el registro no puede enviarse.
     */
    public void enviaRegistro(R registro) throws IOException {
        out.write(registro.aLinea());
        //out.newLine();
        out.flush();
    }

    /**
     * Envía mensaje registro al otro lado de la conexión.
     * @param mensaje el mensaje a enviar.
     * @throws IOException si el mensaje no puede enviarse.
     */
    public void enviaMensaje(Mensaje mensaje) throws IOException {
        out.write(mensaje.toString());
        out.newLine();
        out.flush();
    }

    /**
     * Regresa un número serial para cada conexión.
     * @return un número serial para cada conexión.
     */
    public int getSerial() {
        return serial;
    }

    /**
     * Cierra la conexión.
     */
    public void desconecta() {
        activa = false;
        try {
            enchufe.close();
        } catch (IOException ioe) {
            // Ignoramos errores al cerrar enchufe.
        }
    }

    /**
     * Nos dice si la conexión es activa.
     * @return <code>true</code> si la conexión es activa; <code>false</code> en
     *         otro caso.
     */
    public boolean isActiva() {
        return activa;
    }

    /**
     * Agrega un escucha de conexión.
     * @param escucha el escucha a agregar.
     */
    public void agregaEscucha(EscuchaConexion<R> escucha) {
        escuchas.agregaFinal(escucha);
    }

    private void notificaEscuchas(Mensaje mensaje) {
        for (EscuchaConexion<R> escucha : escuchas)
            escucha.mensajeRecibido(this, mensaje);
    }
}
