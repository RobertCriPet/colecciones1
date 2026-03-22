package Excepciones;

public class RazaInvalidaException extends GatoException {
    public RazaInvalidaException() {
        super("Error: La raza del gato no puede estar vacío.");
    }
}
