package org.example.Entidades;

public class Empleado {
    private String apellido;
    private String mail;
    private String nombre;
    private String telefono;

    public Empleado() {}

    public Empleado(String apellido, String mail, String nombre, String telefono) {
        this.apellido = apellido;
        this.mail = mail;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    } // Muestra el valor de los atributos del objeto Empleado
}
