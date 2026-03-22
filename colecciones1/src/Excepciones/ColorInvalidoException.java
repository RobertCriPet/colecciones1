package Excepciones;

public class ColorInvalidoException extends GatoException {
    public ColorInvalidoException() {
        super("Error: El color no puede estar vacío.");
    }

}
