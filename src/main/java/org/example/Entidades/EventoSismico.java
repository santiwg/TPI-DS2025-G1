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
    //analistaSUpervisor??
    public boolean esAutoDetectado(){
        return estado.esAutoDetectado();
    }
    public boolean esPendienteDeRevision(){

    }
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
}
