package Excepciones;

public class FechaInvalidaException extends RuntimeException {
    public FechaInvalidaException(String detalle) {
        super("Error en la fecha: " + detalle);
    }
}
