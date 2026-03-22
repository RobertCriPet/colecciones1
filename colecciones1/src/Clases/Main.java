package Clases;

import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> colores = new ArrayList<>();
        String colorIntroducido;
        System.out.println("Introduce colores (escribe 'salir' para terminar):");

        do {
            colorIntroducido = sc.nextLine();

            // Solo lo añadimos si no es la palabra de control "salir"
            if (!colorIntroducido.equalsIgnoreCase("salir")) {
                colores.add(colorIntroducido);
            }

        } while (!colorIntroducido.equalsIgnoreCase("salir"));

        // 2. Introducir 'negro' en la tercera posición (índice 2) y mostrar
        colores.add(2, "negro");
        System.out.println("\nLista tras añadir negro: " + colores);

        // 3. Borrar el primer blanco que aparezca
        colores.remove("blanco");

        // 4. Introducir otro blanco en la 4ª posición
        colores.add(3, "blanco");

        // 5. Comprobar si hay blancos, encontrarlo y borrarlo por índice
        if (colores.contains("blanco")) {
            int indiceBlanco = colores.indexOf("blanco");
            colores.remove(indiceBlanco);
        }

        // 6. Volver a comprobar si quedan blancos e indicar posición
        if (colores.contains("blanco")) {
            int pos = colores.indexOf("blanco");
            System.out.println("Queda un blanco en la posición: " + pos);

            // 7. Borrar el blanco de nuevo
            colores.remove(pos);
        }

        // 8. Mostrar contenido tras borrar la última ocurrencia de blanco
        System.out.println("Lista tras limpiar blancos: " + colores);

        // 9. Borra el elemento de la tercera posición (índice 2)
        if (colores.size() > 2) {
            colores.remove(2);
        }

        // 10. Mostrar contenido mediante forEach de Iterable
        System.out.println("\nContenido (usando forEach de Iterable):");
        colores.forEach(c -> System.out.println("- " + c));

        // 11. Recorrer con stream, lambda y contar vocales
        System.out.println("\nConteo de vocales por color:");
        colores.stream().forEach(c -> {
            long numVocales = c.toLowerCase().chars()
                    .filter(ch -> "aeiouáéíóú".indexOf(ch) != -1)
                    .count();
            System.out.println(c + " tiene " + numVocales + " vocales.");
        });

        sc.close();
    }
}