package org.example.Entidades;

public class Empleado {
    private String apellido;
    private String mail;
    private String nombre;
    private String telefono;
    //le ponemos el rol?

    public Empleado() {}

    public Empleado(String apellido, String mail, String nombre, String telefono) {
        this.apellido = apellido;
        this.mail = mail;
        this.nombre = nombre;
        this.telefono = telefono;
    }

}
