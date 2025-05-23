package org.example.Entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventoSismico {
    private LocalDateTime fechaHoraFin;
    private LocalDateTime fechaHoraOcurrencia;
    private float latitudEpicentro;
    private float latitudHipocentro;
    private float longitudEpicentro;
    private float longitudHipocentro;
    private float valorMagnitud; //chequear
    private Estado estado;
    private ArrayList<CambioEstado> cambiosDeEstado;
    private ClasificacionSismo clasificacion;
    private MagnitudRitcher magnitud;
    private OrigenDeGeneracion origenGeneracion;
    private AlcanceSismo alcanceSismo;
    private ArrayList<SerieTemporal> serieTemporal;

    public EventoSismico() {
    }

    public EventoSismico(LocalDateTime fechaHoraFin, LocalDateTime fechaHoraOcurrencia, float latitudEpicentro, float latitudHipocentro, float longitudEpicentro, float longitudHipocentro, float valorMagnitud, Estado estado, ArrayList<CambioEstado> cambiosDeEstado, ClasificacionSismo clasificacion, MagnitudRitcher magnitud, OrigenDeGeneracion origenGeneracion, AlcanceSismo alcanceSismo, ArrayList<SerieTemporal> serieTemporal) {
        this.fechaHoraFin = fechaHoraFin;
        this.fechaHoraOcurrencia = fechaHoraOcurrencia;
        this.latitudEpicentro = latitudEpicentro;
        this.latitudHipocentro = latitudHipocentro;
        this.longitudEpicentro = longitudEpicentro;
        this.longitudHipocentro = longitudHipocentro;
        this.valorMagnitud = valorMagnitud;
        this.estado = estado;
        this.cambiosDeEstado = cambiosDeEstado;
        this.clasificacion = clasificacion;
        this.magnitud = magnitud;
        this.origenGeneracion = origenGeneracion;
        this.alcanceSismo = alcanceSismo;
        this.serieTemporal = serieTemporal;
    }

    //analistaSUpervisor??
    public boolean esAutoDetectado(){
        return this.estado.esAutoDetectado();
    }
    public boolean esPendienteDeRevision() { return this.estado.esPendienteDeRevision(); }
    public void getDatosPrincipales(){
        //que debería devolver?
        LocalDateTime fechaHoraOcurrencia=this.getFechaHoraOcurrencia();
        float latitudEpicentro=this.getLatitudEpicentro();
        float latitudHipocentro=this.getLatitudHipocentro();
        float longitudEpicentro=this.getLongitudEpicentro();
        float longitudHipocentro=this.getLongitudHipocentro();
        float valorMagnitud=this.getValorMagnitud();


    }

    public LocalDateTime getFechaHoraOcurrencia() {
        return fechaHoraOcurrencia;
    }

    public float getLatitudEpicentro() {
        return latitudEpicentro;
    }

    public float getLatitudHipocentro() {
        return latitudHipocentro;
    }

    public float getLongitudEpicentro() {
        return longitudEpicentro;
    }

    public float getLongitudHipocentro() {
        return longitudHipocentro;
    }

    public float getValorMagnitud() {
        return valorMagnitud;
    }

    public void bloquear() {
        this.estado.setNombreEstado("Bloquear"); //verificar el nombre del estado
    }

    public void buscarUltimoEstado() {}

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public AlcanceSismo conocerAlcance() {
        return this.alcanceSismo;
    }

    public ClasificacionSismo conocerClasificacion() {
        return this.clasificacion;
    }

    public OrigenDeGeneracion conocerOrigenGeneracion() {
        return this.origenGeneracion;
    }

    public void clasificarDatosPorEstacionSismologica() {}


}
