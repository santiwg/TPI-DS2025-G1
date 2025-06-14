package org.example.Entidades;

public class Usuario {
    private String contrasenia;
    private String nombreUsuario;
    private Empleado empleado;

    public Usuario(String contrasenia, String nombreUsuario, Empleado empleado) {
        this.contrasenia = contrasenia;
        this.nombreUsuario = nombreUsuario;
        this.empleado = empleado;
    }

    public Empleado getEmpleado() { return this.empleado; }
}
