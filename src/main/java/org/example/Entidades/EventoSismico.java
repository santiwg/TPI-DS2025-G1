package org.example.Entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

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
    private ArrayList<SerieTemporal> seriesTemporales;

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
        this.seriesTemporales = serieTemporal;
    }

    //analistaSUpervisor??
    public boolean esAutoDetectado(){
        return this.estadoActual.esAutoDetectado();
    }

    public boolean esPendienteDeRevision() { return this.estadoActual.esPendienteDeRevision(); }

    public Map<String, Object> getDatosPrincipales() {
        LocalDateTime fechaHoraOcurrencia = this.getFechaHoraOcurrencia();
        float latitudEpicentro = this.getLatitudEpicentro();
        float latitudHipocentro = this.getLatitudHipocentro();
        float longitudEpicentro = this.getLongitudEpicentro();
        float longitudHipocentro = this.getLongitudHipocentro();
        float valorMagnitud = this.getValorMagnitud();

        Map<String, Object> datos = new HashMap<>();
        datos.put("fechaHoraOcurrencia", fechaHoraOcurrencia);
        datos.put("latitudEpicentro", latitudEpicentro);
        datos.put("latitudHipocentro", latitudHipocentro);
        datos.put("longitudEpicentro", longitudEpicentro);
        datos.put("longitudHipocentro", longitudHipocentro);
        datos.put("valorMagnitud", valorMagnitud);

        return datos;
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

    public void revisar(LocalDateTime fechaHoraInicio, Estado estado, Empleado empleadoLogueado) {
        this.buscarUltimoEstado(fechaHoraInicio);
        this.crearCambioEstado(fechaHoraInicio, estado, empleadoLogueado);
        this.setEstadoActual(estado);
    }

    public void rechazar(LocalDateTime fechaHoraInicio, Estado estado, Empleado empleadoLogueado) {
        this.buscarUltimoEstado(fechaHoraInicio);
        this.crearCambioEstado(fechaHoraInicio, estado, empleadoLogueado);
        this.setEstadoActual(estado);
    }
    public ArrayList<String> buscarDatosSeriesTemporales(ArrayList<Sismografo> sismografos){
        ArrayList<String> datosSeries=new ArrayList<>();
        for (SerieTemporal serie:seriesTemporales){
            datosSeries.add(serie.getDatos(sismografos));
        }
        this.clasificarDatosPorEstacionSismologica(datosSeries);
        return datosSeries;
    }
    public void crearCambioEstado(LocalDateTime fechaHoraInicio, Estado estado, Empleado empleadoLogueado) {
        CambioEstado nuevoCambio = new CambioEstado(fechaHoraInicio, estado, empleadoLogueado);
        this.cambioEstado.add(nuevoCambio);
    }

    public void buscarUltimoEstado(LocalDateTime fechaHoraDeFin) {
        for(CambioEstado cambioEstado: cambioEstado){
            if(cambioEstado.esActual()){
                cambioEstado.setFechaHoraFin(fechaHoraDeFin);
            }
        }
    }

    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
    }

    public String mostrarAlcance() {
        return this.alcanceSismo.getNombre();
    }

    public String mostrarClasificacion() {
        return this.clasificacion.getNombre();
    }

    public String mostrarOrigenGeneracion() {
        return this.origenGeneracion.getNombre();
    }

    public void clasificarDatosPorEstacionSismologica(ArrayList<String> datosSeries) {
        datosSeries.sort(Comparator.comparing(dato -> {
            // Extraer el código de estación
            /*Hacerlo de esta forma evitará la necesidad de modificar el código si la
            estación sismológica ya no se pone al frente*/
            int inicio = dato.indexOf("Estacion Sismologica:") + "Estacion Sismologica:".length();
            int fin = dato.indexOf("-", inicio);
            return dato.substring(inicio, fin);
        }));

    }

}
