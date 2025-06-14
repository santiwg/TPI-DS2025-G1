package org.example.Entidades;

import java.time.LocalDateTime;

public class Sesion {
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraCierre;
    private Usuario usuario;

    public Sesion(LocalDateTime fechaHoraInicio, Usuario usuario) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.usuario = usuario;
    }

    public Empleado getEmpleadoLogueado() {
        return this.usuario.getEmpleado();
    }
}
