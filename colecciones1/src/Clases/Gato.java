package Clases;

import Excepciones.ColorInvalidoException;
import Excepciones.NombreInvalidoException;
import Excepciones.RazaInvalidaException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import Excepciones.*;

class Gato {
    private String nombre;
    private String color;
    private String raza;
    private LocalDate fechaNac;

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Gato(String nombre, String color, String raza, String fechaStr) throws GatoException{
        if (nombre == null || nombre.trim().isEmpty()) throw new NombreInvalidoException();
        if (color == null || color.trim().isEmpty()) throw new ColorInvalidoException();
        if (raza == null || raza.trim().isEmpty()) throw new RazaInvalidaException();
        try {
            this.fechaNac = LocalDate.parse(fechaStr.trim(), FORMATO);
        } catch (Exception e) {
            throw new FechaInvalidaException("Formato dd/MM/yyyy inválido en: " + fechaStr);
        }
        this.nombre = nombre.trim();
        this.color = color.trim();
        this.raza = raza.trim();
    }

    public String getNombre() { return nombre; }
    public String getColor() { return color; }
    public String getRaza() { return raza; }
    public LocalDate getFechaNac() { return fechaNac; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gato gato)) return false;
        return nombre.equalsIgnoreCase(gato.nombre);
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s", nombre, color, raza, fechaNac.format(FORMATO));
    }

}