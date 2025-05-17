package org.example.Entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Sismografo {
    private LocalDateTime fechaAdquisicion;
    private int identificadorSismografo;
    private String nroSerie;
    private ArrayList<SerieTemporal> serieTemporal;
    private EstacionSismologica estacionSismologica;
    private Estado estado;
    private ArrayList<CambioEstado> cambiosDeEstado;

    public Sismografo(){}

    public Sismografo(LocalDateTime fechaAdquisicion, int idSismografo, String nroSerie, ArrayList<SerieTemporal> serieTemporal, EstacionSismologica estacionSismologica, Estado estado, ArrayList<CambioEstado> cambiosDeEstado) {
        this.fechaAdquisicion = fechaAdquisicion;
        this.identificadorSismografo = idSismografo;
        this.nroSerie = nroSerie;
        this.serieTemporal = serieTemporal;
        this.estacionSismologica = estacionSismologica;
        this.estado = estado;
        this.cambiosDeEstado = cambiosDeEstado;
    }

    public int getIdSismografo() {
        return identificadorSismografo;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void solicitarCertificacion(){}
}
