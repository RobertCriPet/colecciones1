package Excepciones;

public class NombreInvalidoException extends GatoException {
    public NombreInvalidoException() {
        super("Error: El nombre del gato no puede estar vacío.");
    }
}
