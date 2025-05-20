package org.example.Entidades;

import java.time.LocalDateTime;

public class Sesion {
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraCierre;
    private Usuario usuario;

    public Empleado getEmpleadoLogueado() {
        return this.usuario.getEmpleado();
    }
}
