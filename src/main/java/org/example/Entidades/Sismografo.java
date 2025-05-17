package org.example.Entidades;

import java.time.LocalDateTime;
import java.util.Date;

public class Sismografo {
    private LocalDateTime fechaAdquisicion;
    private int idSismografo;
    private String nroSerie;
    private SerieTemporal serieTemporal;
    private EstacionSismologica estacionSismologica;
    
    public Sismografo(){}

    public Sismografo(LocalDateTime fechaAdquisicion, int idSismografo, String nroSerie) {
        this.fechaAdquisicion = fechaAdquisicion;
        this.idSismografo = idSismografo;
        this.nroSerie = nroSerie;
    }

}
