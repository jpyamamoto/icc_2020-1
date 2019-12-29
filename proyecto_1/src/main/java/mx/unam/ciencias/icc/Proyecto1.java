package mx.unam.ciencias.icc;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Proyecto 1: Base de datos de Museos.
 */
public class Proyecto1 {

    /**
     * Pide valores para realizar la búsqueda de los registros de la base de
     * datos cuyas propiedades coinciden con los valores ingresados.
     * La búsqueda es por los campos:
     * - Nombre: museos cuyos nombres contengan el valor ingresado.
     * - Costo General: museos donde el costo de entrada general sea igual o
     *   menor al ingresado.
     */
    private static void busquedas(BaseDeDatosMuseos baseDeDatos) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");

        // Búsqueda por nombre.
        System.out.printf("Ingresa el nombre del museo que quieres buscar: ");
        String nombre = sc.next();

        Lista registros = baseDeDatos.buscaRegistros(CampoMuseo.NOMBRE, nombre);

        if (registros.esVacia()) {
            System.out.printf("%nNo hay coincidencias de museos con el " +
                              "nombre \"%s\".%n", nombre);
        } else {
            System.out.printf("%nSe hallaron los siguientes museos con el " +
                              "nombre \"%s\":%n%n", nombre);

            Lista.Nodo nodo = registros.getCabeza();
            while (nodo != null) {
                System.out.println(nodo.get().toString() + "\n");
                nodo = nodo.getSiguiente();
            }
        }

        // Búsqueda por costo general.
        System.out.printf("Ingresa el costo máximo para el público en general " +
                          "con el que realizar la búsqueda: $");
        double costoGeneralMax = 100.00;

        try {
            costoGeneralMax = sc.nextDouble();
        } catch (InputMismatchException e) {
            System.out.printf("Costo inválido. Se interpretará como $100.00.%n");
        }

        registros = baseDeDatos.buscaRegistros(CampoMuseo.COSTOGENERAL,
                                               Double.valueOf(costoGeneralMax));

        if (registros.esVacia()) {
            System.out.printf("%nNo se hallaron museos con el precio de " +
                              "entrada general igual o menor a: $%.2f.%n",
                              costoGeneralMax);
        } else {
            System.out.printf("%nSe hallaron los siguientes museos con el " +
                              "precio de $%.2f o menor:%n%n", costoGeneralMax);

            Lista.Nodo nodo = registros.getCabeza();
            while (nodo != null) {
                System.out.println(nodo.get().toString() + "\n");
                nodo = nodo.getSiguiente();
            }
        }
    }

    /**
     * Verifica si el archivo en donde se escribirá la salida ya existe. Si es
     * así, avisa al usuario para que termine el programa sin sobreescribirlo.
     */
    private static void verificaArchivo(String nombreArchivo) {
        File archivo = new File(nombreArchivo);

        if (archivo.exists()) {
            System.out.printf("El archivo \"%s\" ya existe.%n" +
                              "Presiona Ctrl-C si no quieres reescribirlo.%n",
                              nombreArchivo);
        }
    }

    /**
     * Escribe la base de datos recibida en el disco duro, en el archivo
     * indicado.
     */
    private static void escribeArchivo(String nombreArchivo, BaseDeDatosMuseos baseDeDatos) {
        try {
            FileOutputStream fileOut = new FileOutputStream(nombreArchivo);
            OutputStreamWriter osOut = new OutputStreamWriter(fileOut);
            BufferedWriter out = new BufferedWriter(osOut);
            baseDeDatos.guarda(out);
            out.close();
        } catch (IOException ioe) {
            System.out.printf("No pude guardar en el archivo \"%s\".%n",
                              nombreArchivo);
            System.exit(1);
        }

        System.out.printf("%nBase de datos guardada exitosamente en \"%s\".%n",
                          nombreArchivo);
    }

    /**
     * Crea una instancia de base de datos y agrega registros a partir de la
     * entrada del usuario. Guarda los registros en un archivo y regresa la
     * base de datos creada con los registros ingresados.
     */
    private static BaseDeDatosMuseos escritura(String nombreArchivo) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        verificaArchivo(nombreArchivo);

        System.out.println("Entra museos en la base de datos.\n" +
                           "Cuando desees terminar, deja el nombre en blanco.\n");

        BaseDeDatosMuseos bdd = new BaseDeDatosMuseos();

        while (true) {
            String nombre;
            int salas, visitantes;
            double costoGeneral, costoEstudiantes;
            boolean estacionamiento = false;

            System.out.printf("Nombre : ");
            nombre = sc.next();
            if (nombre.equals(""))
                break;

            try {
                System.out.printf("Número de salas : ");
                salas = sc.nextInt();
                System.out.printf("Costo General : $");
                costoGeneral = sc.nextDouble();
                System.out.printf("Costo Estudiantes : $");
                costoEstudiantes = sc.nextDouble();
                System.out.printf("Visitantes : ");
                visitantes = sc.nextInt();
                System.out.printf("Estacionamiento : ");
                estacionamiento = sc.nextBoolean();
            } catch (InputMismatchException ime) {
                System.out.println("\nEntrada inválida - se descartará " +
                                   "este museo.");
                sc.next();
                continue;
            }

            Museo e = new Museo(nombre, salas, costoGeneral, costoEstudiantes,
                                visitantes, estacionamiento);
            bdd.agregaRegistro(e);
            System.out.println();
        }

        int n = bdd.getNumRegistros();
        if (n == 1)
            System.out.printf("%nSe agregó 1 museo.%n");
        else
            System.out.printf("%nSe agregaron %d museos.%n", n);

        escribeArchivo(nombreArchivo, bdd);
        return bdd;
    }

    /**
     * Regresa una nueva instancia de una base de datos con los registros que
     * obtiene de un archivo en el disco duro.
     */
    private static BaseDeDatosMuseos lectura(String nombreArchivo) {
        BaseDeDatosMuseos baseDeDatos = new BaseDeDatosMuseos();

        try {
            FileInputStream fileIn = new FileInputStream(nombreArchivo);
            InputStreamReader isIn = new InputStreamReader(fileIn);
            BufferedReader in = new BufferedReader(isIn);
            baseDeDatos.carga(in);
            in.close();
        } catch (IOException ioe) {
            System.out.printf("Error al cargar el archivo \"%s\".%n",
                              nombreArchivo);
            System.exit(1);
        }

        System.out.printf("Base de datos \"%s\" cargada exitosamente.%n%n",
                          nombreArchivo);

        Lista registros = baseDeDatos.getRegistros();
        Lista.Nodo nodo = registros.getCabeza();
        while (nodo != null) {
            System.out.println(nodo.get().toString() + "\n");
            nodo = nodo.getSiguiente();
        }

        return baseDeDatos;
    }

    /**
     * Imprime en la terminal las instrucciones de cómo utilizar el programa
     * apropiadamente.
     */
    private static void instrucciones() {
        System.out.println("Uso: java -jar practica5.jar [-g|-c] <archivo>");
        System.out.println("-g para crear un archivo de la base de datos.");
        System.out.println("-c para leer un archivo de una base de datos.");
        System.exit(1); // 1 es el código de error.
    }

    /**
     * Punto de entrada del programa.
     */
    public static void main(String[] args) {
        // args son las instrucciones que recibe el programa al correrlo desde
        // la terminal.
        if (args.length != 2)
            instrucciones();

        String bandera = args[0];
        String nombreArchivo = args[1];

        if (!bandera.equals("-g") && !bandera.equals("-c"))
            instrucciones();

        BaseDeDatosMuseos baseDeDatos;

        if (bandera.equals("-g"))
            baseDeDatos = escritura(nombreArchivo);
        else
            baseDeDatos = lectura(nombreArchivo);

        busquedas(baseDeDatos);
    }
}
