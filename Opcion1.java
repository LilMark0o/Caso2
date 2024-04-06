import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Opcion1 {
    public static HashMap<String, int[]> hash = new HashMap<String, int[]>();
    public static int paginas;
    public static int tamanoPagina;
    public static int largo;
    public static int alto;
    public static Queue<String> respuestas = new LinkedList<>();

    public static void opcion1() {
        try {
            System.out.println();
            tamanoPagina = Integer.parseInt(Main.input("Ingrese el tamaño de página:"));
            largo = Integer.parseInt(Main.input("Ingrese el largo (x) de la matriz de datos:"));
            alto = Integer.parseInt(Main.input("Ingrese el alto (x) de la matriz de datos:"));
            generarSlotsMemoria(tamanoPagina, largo, alto);
            generarReferencias(tamanoPagina, largo, alto);
            escribirArchivo();
        } catch (InputMismatchException e) {
            System.out.println("El valor ingresado no es un número válido.");
        }
    }

    private static void generarSlotsMemoria(int tamanoPagina, int largo, int alto) {
        int slots = (largo * alto * 2) + 9;
        int bytes = slots * 4;
        paginas = (int) Math.ceil((double) bytes / tamanoPagina);
        int interosPorPagina = tamanoPagina / 4;
        String MatrizFiltro = "F";
        String MatrizDatos = "M";
        String MatrizResultado = "R";
        int conteo = 0;
        int nf = alto;
        int nc = largo;
        hash = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int slotEnQueVa = conteo / interosPorPagina;
                int direccion = conteo % interosPorPagina;
                String key = MatrizFiltro + i + j;
                hash.put(key, new int[] { slotEnQueVa, direccion * 4 });
                conteo++;
            }
        }
        for (int i = 0; i < nf; i++) {
            for (int j = 0; j < nc; j++) {
                int slotEnQueVa = conteo / interosPorPagina;
                int direccion = conteo % interosPorPagina;
                String key = MatrizDatos + i + j;
                hash.put(key, new int[] { slotEnQueVa, direccion * 4 });
                conteo++;
            }
        }
        for (int i = 0; i < nf; i++) {
            for (int j = 0; j < nc; j++) {
                int slotEnQueVa = conteo / interosPorPagina;
                int direccion = conteo % interosPorPagina;
                String key = MatrizResultado + i + j;
                hash.put(key, new int[] { slotEnQueVa, direccion * 4 });
                conteo++;
            }
        }
    }

    private static void generarReferencias(int tamanoPagina, int largo, int alto) {
        int nf = alto;
        int nc = largo;
        int[][] mat1 = new int[nf][nc];
        int[][] mat2 = new int[3][3];
        int[][] mat3 = new int[nf][nc];
        for (int i = 1; i < nf - 1; i++) {
            for (int j = 1; j < nc - 1; j++) {
                // Recorrer los vecinos y aplicar el filtro
                // mat1: matriz de datos
                // mat2: matriz con el filtro (usaremos un filtro de 3x3 para resaltar bordes)
                // mat3: matriz resultante
                int acum = 0;
                for (int a = -1; a <= 1; a++) {
                    for (int b = -1; b <= 1; b++) {
                        int i2 = i + a;
                        int j2 = j + b;
                        int i3 = 1 + a;
                        int j3 = 1 + b;
                        acum += (mat2[i3][j3] * mat1[i2][j2]);
                        accederDato(i2, j2, "M", "R");
                        accederDato(i3, j3, "F", "R");
                    }
                }
                if (acum >= 0 && acum <= 255) {
                    mat3[i][j] = acum;
                } else if (acum < 0) {
                    mat3[i][j] = 0;
                } else {
                    mat3[i][j] = 255;
                }
                accederDato(i, j, "R", "W");
            }
        }
        // se asigna un valor predefinido a los bordes.
        for (int i = 0; i < nc; i++) {

            mat3[0][i] = 0;

            mat3[nf - 1][i] = 255;
            accederDato(0, i, "R", "W");
            accederDato(nf - 1, i, "R", "W");
        }
        for (int i = 1; i < nf - 1; i++) {

            mat3[i][0] = 0;

            mat3[i][nc - 1] = 255;
            accederDato(i, 0, "R", "W");
            accederDato(i, nc - 1, "R", "W");
        }
    }

    private static void accederDato(int i, int j, String matriz, String operacion) {
        int[] slot = hash.get(matriz + i + j);
        String lineaAEscribir = matriz + "[" + i + "][" + j + "]," + slot[0] + "," + slot[1] + "," + operacion;
        respuestas.add(lineaAEscribir);
    }

    private static void escribirArchivo() {
        String rutaArchivo = "referencias.txt";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo));

            // Escribir tres líneas en el archivo
            writer.write("TP=" + tamanoPagina);
            writer.newLine(); // Nueva línea
            writer.write("NF=" + alto);
            writer.newLine(); // Nueva línea
            writer.write("NC=" + largo);
            writer.newLine(); // Nueva línea
            writer.write("NF_NC_Filtro=3");
            writer.newLine(); // Nueva línea
            writer.write("NR=" + respuestas.size());
            writer.newLine(); // Nueva línea
            writer.write("NP=" + paginas);
            for (String respuesta : respuestas) {
                writer.newLine();
                writer.write(respuesta);
            }
            // Cerrar el BufferedWriter
            writer.close();

            System.out.println("Se han escrito las líneas en el archivo correctamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}
