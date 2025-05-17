package org.example.Entidades;

import java.time.LocalDateTime;

public class EstacionSismologica {
    private int codigoEstacion;
    private String documentoCerficacionAdq;
    private LocalDateTime fechaSolicitudCertificacion;
    private float latitud;
    private float longitud;
    private String nombre;
    private int nroCertificacionAdquisicion;

    public EstacionSismologica(){}
    public EstacionSismologica(int codigoEstacion, String documentoCerficacionAdq, LocalDateTime fechaSolicitudCertificacion, float latitud, float longitud, String nombre, int nroCertificacionAdquisicion) {
        this.codigoEstacion = codigoEstacion;
        this.documentoCerficacionAdq = documentoCerficacionAdq;
        this.fechaSolicitudCertificacion = fechaSolicitudCertificacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.nroCertificacionAdquisicion = nroCertificacionAdquisicion;
    }

}
