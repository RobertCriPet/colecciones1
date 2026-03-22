package Clases;

import Excepciones.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class GestionGatosFicheros {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Gato> listaGatos = new ArrayList<>();

        // 1. LECTURA DEL ARCHIVO CON VALIDACIÓN DE RUTA
        boolean archivoCargado = false;
        while (!archivoCargado) {
            System.out.println("\nDirectorio actual de trabajo: " + System.getProperty("user.dir"));
            System.out.print("Introduce el nombre del archivo o ruta completa (ej: gatos.csv): ");
            String ruta = sc.nextLine();
            File archivo = new File(ruta);

            if (archivo.exists() && archivo.isFile()) {
                try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                    String linea;
                    int contador = 0;
                    while ((linea = br.readLine()) != null) {
                        if (linea.isBlank()) continue;
                        String[] p = linea.split(",");
                        if (p.length == 4) {
                            try {
                                listaGatos.add(new Gato(p[0], p[1], p[2], p[3]));
                                contador++;
                            } catch (GatoException e) {
                                System.err.println("Línea omitida: " + e.getMessage());
                            }
                        }
                    }
                    System.out.println(">> Se han cargado " + contador + " gatos con éxito.");
                    archivoCargado = true;
                } catch (IOException e) {
                    System.err.println("Error al leer el archivo: " + e.getMessage());
                }
            } else {
                System.out.println("No se encuentra el archivo en: " + archivo.getAbsolutePath());
                System.out.println("Asegúrate de que el nombre sea correcto o pega la ruta completa.");
            }
        }

        if (listaGatos.isEmpty()) {
            System.out.println("No hay datos para procesar. Fin.");
            return;
        }

        // 2. CONFIGURAR ORDENACIÓN
        int campo = 0;
        do {
            try {
                System.out.println("¿Por qué campo quieres ordenar?");
                System.out.print("1. Nombre | 2. Color | 3. Raza | 4. Fecha: ");
                campo = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) { campo = 0; }
        } while (campo < 1 || campo > 4);

        System.out.print("¿Sentido? 1. Ascendente | 2. Descendente: ");
        int sentido = Integer.parseInt(sc.nextLine());

        // 3. COMPARADOR DINÁMICO
        Comparator<Gato> comp = switch (campo) {
            case 1 -> Comparator.comparing(Gato::getNombre, String.CASE_INSENSITIVE_ORDER);
            case 2 -> Comparator.comparing(Gato::getColor, String.CASE_INSENSITIVE_ORDER);
            case 3 -> Comparator.comparing(Gato::getRaza, String.CASE_INSENSITIVE_ORDER);
            case 4 -> Comparator.comparing(Gato::getFechaNac);
            default -> null;
        };

        if (sentido == 2) comp = comp.reversed();
        listaGatos.sort(comp);

        // 4. GUARDAR RESULTADO
        String[] nombres = {"", "nombre", "color", "raza", "fecha"};
        String archivoSalida = "gatos_por_" + nombres[campo] + ".csv";

        try (PrintWriter pw = new PrintWriter(new FileWriter(archivoSalida))) {
            for (Gato g : listaGatos) pw.println(g);
            System.out.println("LISTA ORDENADA:");
            listaGatos.forEach(g -> System.out.println(" - " + g));
            System.out.println("Guardado en: " + archivoSalida);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo.");
        }

        sc.close();
    }
}