package Excepciones;

public class FechaInvalidaException extends GatoException {
    public FechaInvalidaException(String detalle) {
        super("Error en la fecha: " + detalle);
    }
}
