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
    private Estado estadoActual;
    private ArrayList<CambioEstado> cambioEstado;
    private ClasificacionSismo clasificacion;
    private MagnitudRitcher magnitud;
    private OrigenDeGeneracion origenGeneracion;
    private AlcanceSismo alcanceSismo;
    private ArrayList<SerieTemporal> serieTemporal;

    public EventoSismico() {
    }

    public EventoSismico(LocalDateTime fechaHoraFin, LocalDateTime fechaHoraOcurrencia, float latitudEpicentro, float latitudHipocentro, float longitudEpicentro, float longitudHipocentro, float valorMagnitud, Estado estadoActual, ArrayList<CambioEstado> cambioEstado, ClasificacionSismo clasificacion, MagnitudRitcher magnitud, OrigenDeGeneracion origenGeneracion, AlcanceSismo alcanceSismo, ArrayList<SerieTemporal> serieTemporal) {
        this.fechaHoraFin = fechaHoraFin;
        this.fechaHoraOcurrencia = fechaHoraOcurrencia;
        this.latitudEpicentro = latitudEpicentro;
        this.latitudHipocentro = latitudHipocentro;
        this.longitudEpicentro = longitudEpicentro;
        this.longitudHipocentro = longitudHipocentro;
        this.valorMagnitud = valorMagnitud;
        this.estadoActual = estadoActual;
        this.cambioEstado = cambioEstado;
        this.clasificacion = clasificacion;
        this.magnitud = magnitud;
        this.origenGeneracion = origenGeneracion;
        this.alcanceSismo = alcanceSismo;
        this.serieTemporal = serieTemporal;
    }

    //analistaSUpervisor??
    public boolean esAutoDetectado(){
        return this.estadoActual.esAutoDetectado();
    }

    public boolean esPendienteDeRevision() { return this.estadoActual.esPendienteDeRevision(); }

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

    public void revisar() {
        this.estadoActual.setNombreEstado("Bloquear"); //verificar el nombre del estado
    }

    public void rechazar(LocalDateTime fechaHoraInicio, Estado estado, Empleado empleadoLogueado) {
        buscarUltimoEstado();
        crearCambioEstado(fechaHoraInicio, estado, empleadoLogueado);
        setEstadoActual(estado);
    }

    public void crearCambioEstado(LocalDateTime fechaHoraInicio, Estado estado, Empleado empleadoLogueado) {
        CambioEstado nuevoCambio = new CambioEstado(fechaHoraInicio, estado, empleadoLogueado);
    }

    public void buscarUltimoEstado() {
        for(CambioEstado cambioEstado: cambioEstado){
            if(cambioEstado.esActual()){
                cambioEstado.setFechaHoraFin(fechaHoraFin);
            }
        }
    }

    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
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
