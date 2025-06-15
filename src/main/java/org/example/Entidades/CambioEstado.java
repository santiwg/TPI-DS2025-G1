package org.example.Entidades;
import java.time.LocalDateTime;

public class CambioEstado {
    private LocalDateTime fechaHoraFin;
    private LocalDateTime fechaHoraInicio;
    private Estado estado;
    private Empleado empleadoResponsable;

    public CambioEstado() {}


    public CambioEstado(LocalDateTime fechaHoraInicio, Estado estado, Empleado empleadoResponsable) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.estado = estado;
        this.empleadoResponsable = empleadoResponsable;
    }

    public boolean esActual() { return this.fechaHoraFin == null; }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    @Override
    public String toString() {
        return "CambioEstado{" +
                "fechaHoraFin=" + fechaHoraFin +
                ", fechaHoraInicio=" + fechaHoraInicio +
                ", estado=" + estado +
                ", empleadoResponsable=" + empleadoResponsable +
                '}';
    }
}
