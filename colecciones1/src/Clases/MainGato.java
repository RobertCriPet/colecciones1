package Clases;

import Excepciones.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class MainGato {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Gato> listaGatos = new ArrayList<>();
        String continuar;

        System.out.println("=== REGISTRO DE GATOS ===");

        // 1. Entrada de datos con validación y excepciones
        do {
            try {
                System.out.println("\nIntroduzca los datos del gato:");
                System.out.print("Nombre: ");
                String nombre = sc.nextLine();

                System.out.print("Color: ");
                String color = sc.nextLine();

                System.out.print("Raza: ");
                String raza = sc.nextLine();

                System.out.print("Fecha Nacimiento (dd/MM/yyyy): ");
                String fecha = sc.nextLine();

                Gato nuevoGato = new Gato(nombre, color, raza, fecha);
                listaGatos.add(nuevoGato);
                System.out.println(">> Gato registrado correctamente.");

            } catch (GatoException e) {
                System.out.println(">>> " + e.getMessage());
            }

            System.out.print("¿Desea añadir otro gato? (s/n): ");
            continuar = sc.nextLine();
        } while (continuar.equalsIgnoreCase("s"));

        if (listaGatos.isEmpty()) {
            System.out.println("No hay datos para procesar. Fin del programa.");
            return;
        }

        // 2. Elección del método de ordenación
        int opcion = 0;
        do {
            try {
                System.out.println("\nElija el orden por NOMBRE:");
                System.out.println("1. Ascendente (A-Z)");
                System.out.println("2. Descendente (Z-A)");
                System.out.print("Opción: ");
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduzca un número (1 o 2).");
            }
        } while (opcion != 1 && opcion != 2);

        // 3. Ordenación mediante Comparator
        if (opcion == 1) {
            listaGatos.sort(Comparator.comparing(Gato::getNombre, String.CASE_INSENSITIVE_ORDER));
        } else {
            listaGatos.sort(Comparator.comparing(Gato::getNombre, String.CASE_INSENSITIVE_ORDER).reversed());
        }

        // 4. Mostrar resultados
        System.out.println("\n" + "=".repeat(60));
        System.out.println(String.format("| %-12s | %-10s | %-12s | %-12s |", "NOMBRE", "COLOR", "RAZA", "FECHA NAC."));
        System.out.println("-".repeat(60));
        listaGatos.forEach(System.out::println);
        System.out.println("=".repeat(60));

        sc.close();
    }
}
