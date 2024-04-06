import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        boolean prueba = true;
        if (prueba) {
            int[] marcosAsignados = { 4, 8, 12, 16 };
            String[] archivos = { "16B4x4.txt", "16B8x8.txt", "16B16x16.txt", "16B32x32.txt", "32B4x4.txt",
                    "32B8x8.txt", "32B16x16.txt", "32B32x32.txt", "64B4x4.txt", "64B8x8.txt", "64B16x16.txt",
                    "64B32x32.txt" };
            for (int i = 0; i < marcosAsignados.length; i++) {
                for (int j = 0; j < archivos.length; j++) {
                    System.out.println("Marcos asignados: " + marcosAsignados[i] + ", Archivo: " + archivos[j]);
                    Opcion2.opcion2(archivos[j], marcosAsignados[i]);
                }
            }
            return;
        }
        System.out.println("Bienvenido!");
        try {
            int numero = Integer.parseInt(input("Por favor ingresa un número entre 1 y 2:"));
            if (numero == 1) {
                Opcion1.opcion1();
            } else if (numero == 2) {
                Opcion2.opcion2();
            } else {
                System.out.println("El número ingresado no está dentro del rango especificado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("El valor ingresado no es un número.");
            return;
        }
    }

    public static String input(String mensaje) {
        try {
            System.out.print(mensaje + ": ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Error leyendo de la consola");
            e.printStackTrace();
        }
        return null;
    }
}
