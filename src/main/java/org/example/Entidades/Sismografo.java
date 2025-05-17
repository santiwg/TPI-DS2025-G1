package org.example.Entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Sismografo {
    private LocalDateTime fechaAdquisicion;
    private int idSismografo;
    private String nroSerie;
    private ArrayList<SerieTemporal> serieTemporal;
    private EstacionSismologica estacionSismologica;
    private Estado estado;

    public Sismografo(){}

    public Sismografo(LocalDateTime fechaAdquisicion, int idSismografo, String nroSerie, ArrayList<SerieTemporal> serieTemporal, EstacionSismologica estacionSismologica, Estado estado) {
        this.fechaAdquisicion = fechaAdquisicion;
        this.idSismografo = idSismografo;
        this.nroSerie = nroSerie;
        this.serieTemporal = serieTemporal;
        this.estacionSismologica = estacionSismologica;
        this.estado = estado;
    }
}
