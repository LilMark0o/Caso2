import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Opcion2 {
    public static Queue<Instruccion> ordenAccesos = new LinkedList<>();
    private static int marcosPagina;
    public static ElementoTabla[] memoriaReal;
    public static int fallosDePagina = 0;
    public static int aciertosDePagina = 0;

    public static void opcion2() {
        // Verificamos que se haya pasado el nombre del archivo como argumento
        getDatos();
        memoriaReal = new ElementoTabla[marcosPagina];
        iniciarMemoriaReal();
        EjecutadorDeAccesos ejecutadorDeAccesos = new EjecutadorDeAccesos();
        ejecutadorDeAccesos.start();
        EjecutadorDeLimpieza ejecutadorDeLimpieza = new EjecutadorDeLimpieza();
        ejecutadorDeLimpieza.start();
        try {
            ejecutadorDeAccesos.join();
            ejecutadorDeLimpieza.join();
        } catch (InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
            System.out.println("Error");
        }
        System.out.println("Aciertos de página: " + aciertosDePagina);
        System.out.println("Fallos de página: " + fallosDePagina);
    }

    public static void opcion2(String nombreArchivo, int marcosPagina) {
        // Verificamos que se haya pasado el nombre del archivo como argumento
        getDatos(nombreArchivo, marcosPagina);
        memoriaReal = new ElementoTabla[marcosPagina];
        iniciarMemoriaReal();
        EjecutadorDeAccesos ejecutadorDeAccesos = new EjecutadorDeAccesos();
        ejecutadorDeAccesos.start();
        EjecutadorDeLimpieza ejecutadorDeLimpieza = new EjecutadorDeLimpieza();
        ejecutadorDeLimpieza.start();
        try {
            ejecutadorDeAccesos.join();
            ejecutadorDeLimpieza.join();
        } catch (InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
            System.out.println("Error");
        }
        System.out.println("Aciertos de página: " + aciertosDePagina);
        System.out.println("Fallos de página: " + fallosDePagina);
        ordenAccesos = new LinkedList<>();
        fallosDePagina = 0;
        aciertosDePagina = 0;

    }

    private static void iniciarMemoriaReal() {
        for (int i = 0; i < memoriaReal.length; i++) {
            memoriaReal[i] = null;
        }
    }

    public static int buscarEnMemoriaReal(int pagina) {
        for (int i = 0; i < memoriaReal.length; i++) {
            if (memoriaReal[i] != null) {
                if (memoriaReal[i].getpaginaApuntada() == pagina) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void reemplazarEnMemoriaReal(int pagina, String operation) {
        Integer firstClass0 = null;
        Integer firstClass1 = null;
        Integer firstClass2 = null;
        Integer firstClass3 = null;
        // System.out.println("\n\nReemplazando página " + pagina);
        // comoVaTabla();
        for (int i = 0; i < memoriaReal.length; i++) {
            if (memoriaReal[i] == null) {
                memoriaReal[i] = new ElementoTabla(pagina);
                memoriaReal[i].reset();
                memoriaReal[i].reference();
                if (operation.equals("W")) {
                    memoriaReal[i].modify();
                }
                return;
            }
            if (memoriaReal[i].getClassPage() == 0) {
                // if (firstClass0 == null && memoriaReal[i].getClassPage() == 0) {
                firstClass0 = i;
            }
            if (memoriaReal[i].getClassPage() == 1) {
                // if (firstClass1 == null && memoriaReal[i].getClassPage() == 1) {
                firstClass1 = i;
            }
            if (memoriaReal[i].getClassPage() == 2) {
                // if (firstClass2 == null && memoriaReal[i].getClassPage() == 2) {
                firstClass2 = i;
            }
            if (memoriaReal[i].getClassPage() == 3) {
                // if (firstClass3 == null && memoriaReal[i].getClassPage() == 3) {
                firstClass3 = i;
            }
        }
        if (firstClass0 != null) {
            memoriaReal[firstClass0].setPaginaApuntada(pagina);
            memoriaReal[firstClass0].reset();
            memoriaReal[firstClass0].reference();
            if (operation.equals("W")) {
                memoriaReal[firstClass0].modify();
            }
        } else if (firstClass1 != null) {
            memoriaReal[firstClass1].setPaginaApuntada(pagina);
            memoriaReal[firstClass1].reset();
            memoriaReal[firstClass1].reference();
            if (operation.equals("W")) {
                memoriaReal[firstClass1].modify();
            }

        } else if (firstClass2 != null) {
            memoriaReal[firstClass2].setPaginaApuntada(pagina);
            memoriaReal[firstClass2].reset();
            memoriaReal[firstClass2].reference();
            if (operation.equals("W")) {
                memoriaReal[firstClass2].modify();
            }
        } else if (firstClass3 != null) {
            memoriaReal[firstClass3].setPaginaApuntada(pagina);
            memoriaReal[firstClass3].reset();
            memoriaReal[firstClass3].reference();
            if (operation.equals("W")) {
                memoriaReal[firstClass3].modify();
            }
        }

    }

    public static void comoVaTabla() {
        for (int i = 0; i < memoriaReal.length; i++) {
            if (memoriaReal[i] != null) {
                System.out.println("Marco " + i + ": " + memoriaReal[i].getpaginaApuntada() + " R: "
                        + memoriaReal[i].getR() + " M: " + memoriaReal[i].getM() + " Clase: "
                        + memoriaReal[i].getClassPage());
            } else {
                System.out.println("Marco " + i + ": Vacío");
            }
        }
    }

    public static void limpiarReferencias() {
        for (int i = 0; i < memoriaReal.length; i++) {
            if (memoriaReal[i] != null) {
                memoriaReal[i].deReference();
            }
        }
    }

    private static void getDatos() {
        try {
            String nombreArchivo = Main.input("¿Qué archivo quieres leer de referencias?");
            marcosPagina = Integer.parseInt(Main.input("¿Cual es el número de marcos de página?"));
            File archivo = new File(nombreArchivo);
            Scanner scannerArchivo = new Scanner(archivo);
            scannerArchivo.nextLine();
            scannerArchivo.nextLine();
            scannerArchivo.nextLine();
            scannerArchivo.nextLine();
            scannerArchivo.nextLine();
            scannerArchivo.nextLine();
            while (scannerArchivo.hasNextLine()) {
                String data = scannerArchivo.nextLine();
                Instruccion instruccion = new Instruccion(data);
                ordenAccesos.add(instruccion);
            }
            scannerArchivo.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        }
    }

    public static void avisarGetSynchrinized() {
        // System.out.println("Cojo a la memoria virtual");
    }

    public static void avisarReleaseSynchrinized() {
        // System.out.println("Suelto a la memoria virtual");
    }

    private static void getDatos(String nombreArchivo, int marcosPaginaPrueba) {
        try {
            marcosPagina = marcosPaginaPrueba;
            File archivo = new File(nombreArchivo);
            Scanner scannerArchivo = new Scanner(archivo);
            scannerArchivo.nextLine();
            scannerArchivo.nextLine();
            scannerArchivo.nextLine();
            scannerArchivo.nextLine();
            scannerArchivo.nextLine();
            scannerArchivo.nextLine();
            while (scannerArchivo.hasNextLine()) {
                String data = scannerArchivo.nextLine();
                Instruccion instruccion = new Instruccion(data);
                ordenAccesos.add(instruccion);
            }
            scannerArchivo.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        }
    }

}
