package org.example.Entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Sismografo {
    private LocalDateTime fechaAdquisicion;
    private int identificadorSismografo;
    private String nroSerie;
    private ArrayList<SerieTemporal> serieTemporal;
    private EstacionSismologica estacionSismologica;
    private Estado estadoActual;
    private ArrayList<CambioEstado> cambiosDeEstado;

    public Sismografo(){}

    public Sismografo(LocalDateTime fechaAdquisicion, int idSismografo, String nroSerie, ArrayList<SerieTemporal> serieTemporal, EstacionSismologica estacionSismologica, Estado estadoActual, ArrayList<CambioEstado> cambiosDeEstado) {
        this.fechaAdquisicion = fechaAdquisicion;
        this.identificadorSismografo = idSismografo;
        this.nroSerie = nroSerie;
        this.serieTemporal = serieTemporal;
        this.estacionSismologica = estacionSismologica;
        this.estadoActual = estadoActual;
        this.cambiosDeEstado = cambiosDeEstado;
    }

    public int getIdentificadorSismografo() {
        return identificadorSismografo;
    }

    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
    }

    public void solicitarCertificacion(){}
}
