package Excepciones;

public class ColorInvalidoException extends Throwable {
    public ColorInvalidoException() {
        super("Error: El color no puede estar vacío.");
    }

}
