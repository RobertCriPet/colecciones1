import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Clase encargada de la gestión, ordenación y persistencia de datos 
 * numéricos y de texto mediante el uso de la API Stream de Java.
 * @author Robert Cristian Petric Petric
 * @version 1.0
 */
public class OrdenadorArchivos {

    /**
     * Punto de entrada principal de la aplicación.
     * Gestiona la interacción con el usuario para determinar el tipo de ordenación
     * y coordina la llamada a los métodos de procesamiento de archivos.
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Seleccione orden: (1) Ascendente (2) Descendente");
        boolean ascendente = sc.nextInt() == 1;

        // Rutas propias, cambiar las rutas al ejecutar el codigo
        String rutaNumeros = "C:\\Users\\rober\\Documents\\GitHub\\colecciones1\\colecciones1\\Documentos\\numeros.txt";
        String rutaPersonas = "C:\\Users\\rober\\Documents\\GitHub\\colecciones1\\colecciones1\\src\\personas.txt";

        // Ejecución de los procesos de ordenación
        ordenarYGuardarEnteros(rutaNumeros, "SalidaEnteros.txt", ascendente);
        ordenarYGuardarStrings(rutaPersonas, "SalidaCadenas.txt", ascendente);

        System.out.println("Proceso finalizado. Revisa los archivos de salida.");
    }

    /**
     * Lee un archivo de números, los ordena según el criterio especificado
     * y guarda el resultado en un nuevo archivo de texto.
     * <p>
     * El método maneja internamente las excepciones de E/S y errores de formato
     * numérico si el archivo contiene datos no válidos.
     * </p>
     * @param origen  Ruta del archivo .txt que contiene los números (uno por línea).
     * @param destino Nombre del archivo de salida.
     * @param asc     Verdadero para orden ascendente, falso para descendente.
     */
    private static void ordenarYGuardarEnteros(String origen, String destino, boolean asc) {
        try (Stream<String> lineas = Files.lines(Paths.get(origen))) {

            List<String> resultado = lineas
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Integer::parseInt) // Conversión a Integer para ordenación numérica real
                    .sorted(asc ? Comparator.naturalOrder() : Comparator.reverseOrder())
                    .map(String::valueOf)
                    .collect(Collectors.toList());

            Files.write(Paths.get(destino), resultado);
            System.out.println("[OK] Enteros guardados con éxito en " + destino);

        } catch (IOException e) {
            System.err.println("[Error de Archivo] No se pudo acceder a " + origen + ": " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("[Error de Formato] 'numeros.txt' contiene elementos que no son números enteros.");
        }
    }

    /**
     * Lee un archivo de cadenas de texto (nombres de personas), las ordena 
     * alfabéticamente y guarda el resultado en un nuevo archivo.
     * <p>
     * Utiliza un bloque try-with-resources para asegurar el cierre del Stream
     * y captura cualquier excepción durante el proceso para evitar la parada del programa.
     * </p>
     * @param origen  Ruta del archivo .txt con los nombres.
     * @param destino Nombre del archivo de salida.
     * @param asc     Verdadero para orden A-Z, falso para Z-A.
     */
    private static void ordenarYGuardarStrings(String origen, String destino, boolean asc) {
        try (Stream<String> lineas = Files.lines(Paths.get(origen))) {

            // Configuramos el comparador para español ya que no ordena bien con acentos el naturalOrder
            Collator collator = Collator.getInstance(new Locale("es", "ES"));
            // Establecemos fuerza primaria para que ignore tildes si es necesario
            collator.setStrength(Collator.PRIMARY);

            Comparator<String> comparador = (s1, s2) -> collator.compare(s1, s2);

            List<String> resultado = lineas
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .sorted(asc ? comparador : comparador.reversed())
                    .toList();

            Files.write(Paths.get(destino), resultado);
            System.out.println("[OK] Cadenas guardadas correctamente en " + destino);

        } catch (IOException e) {
            System.err.println("[Error] No se pudo procesar el archivo: " + e.getMessage());
        }
    }
}