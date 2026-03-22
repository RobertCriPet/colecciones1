package Excepciones;

public class NombreInvalidoException extends RuntimeException {
    public NombreInvalidoException() {
        super("Error: El nombre del gato no puede estar vacío.");
    }
}
