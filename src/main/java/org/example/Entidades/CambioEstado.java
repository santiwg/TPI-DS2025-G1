package org.example.Entidades;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CambioEstado {
    private LocalDateTime fechaHoraFin;
    private LocalDateTime fechaHoraInicio;
    private Estado estado;
    private Empleado empleadoResponsable;

    public CambioEstado() {}

    public CambioEstado(LocalDateTime fechaHoraFin, LocalDateTime fechaHoraInicio, Estado estado, Empleado empleadoResponsable) {
        this.fechaHoraFin = fechaHoraFin;
        this.fechaHoraInicio = fechaHoraInicio;
        this.estado = estado;
        this.empleadoResponsable = empleadoResponsable;
    }
}
