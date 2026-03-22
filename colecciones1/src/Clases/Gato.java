package Clases;

import Excepciones.ColorInvalidoException;
import Excepciones.NombreInvalidoException;
import Excepciones.RazaInvalidaException;

import java.util.Objects;
import Excepciones.*;

class Gato {
    private String nombre;
    private String color;
    private String raza;
    private String fechaNac;

    public Gato(String nombre, String color, String raza, String fechaNac) throws NombreInvalidoException, ColorInvalidoException, RazaInvalidaException {
        if (nombre == null || nombre.trim().isEmpty()) throw new NombreInvalidoException();
        if (color == null || color.trim().isEmpty()) throw new ColorInvalidoException();
        if (raza == null || raza.trim().isEmpty()) throw new RazaInvalidaException();
        this.nombre = nombre;
        this.color = color;
        this.raza = raza;
        this.fechaNac = fechaNac;
    }

    public String getNombre() { return nombre; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gato gato = (Gato) o;
        return Objects.equals(nombre.toLowerCase(), gato.nombre.toLowerCase());
    }

    @Override
    public String toString() {
        return "Clases.Gato { Nombre: '" + nombre + "', Color: '" + color + "', Raza: '" + raza + "', Nacimiento: '" + fechaNac + "' }";
    }
}