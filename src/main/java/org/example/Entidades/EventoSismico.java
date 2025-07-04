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
    private OrigenDeGeneracion origenGeneracion;
    private AlcanceSismo alcanceSismo;
    private ArrayList<SerieTemporal> serieTemporal;
    private Empleado analistaSuperior;

    public EventoSismico() {
    }

    public EventoSismico(LocalDateTime fechaHoraOcurrencia, float latitudEpicentro, float latitudHipocentro, float longitudEpicentro, float longitudHipocentro, float valorMagnitud, Estado estadoActual, ArrayList<CambioEstado> cambioEstado, ClasificacionSismo clasificacion, OrigenDeGeneracion origenGeneracion, AlcanceSismo alcanceSismo, ArrayList<SerieTemporal> serieTemporal) {
        this.fechaHoraOcurrencia = fechaHoraOcurrencia;
        this.latitudEpicentro = latitudEpicentro;
        this.latitudHipocentro = latitudHipocentro;
        this.longitudEpicentro = longitudEpicentro;
        this.longitudHipocentro = longitudHipocentro;
        this.valorMagnitud = valorMagnitud;
        this.estadoActual = estadoActual;
        this.cambioEstado = cambioEstado;
        this.clasificacion = clasificacion;
        this.origenGeneracion = origenGeneracion;
        this.alcanceSismo = alcanceSismo;
        this.serieTemporal = serieTemporal;
    }

    public boolean esAutoDetectado(){
        return this.estadoActual.esAutoDetectado();
    } // Metodo que nos comunica si el estado actual es AutoDetectado

    public boolean esPendienteDeRevision() { return this.estadoActual.esPendienteDeRevision(); } // Metodo que nos comunica si el estado actual es PendienteDeRevision

    public Map<String, Object> getDatosPrincipales() { // Obtiene los datos principales del evento ordenados dentro de un HashMap
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

    public CambioEstado revisar(LocalDateTime fechaHoraInicio, Estado estado, Empleado empleadoLogueado) { // Pone fin al ultimo cambio de estado y crea uno nuevo
        this.buscarUltimoEstado(fechaHoraInicio); // Busca el ultimo CambioEstado
        CambioEstado nuevoEstado = this.crearCambioEstado(fechaHoraInicio, estado, empleadoLogueado); // Crea una nueva instancia de CambioEstado
        this.setEstadoActual(estado); // Asigna el nuevo estado actual

        return nuevoEstado; // Devuelve el cambio de estado para que el gestor lo almacene y posteriormente no se tenga que buscar.
    }

    public void rechazar(LocalDateTime fechaHoraInicio, Estado estado, Empleado empleadoLogueado, CambioEstado ultimoEstado) { // Pone fin al ultimo cambio de estado y crea uno
        this.actualizarUltimoEstado(fechaHoraInicio, ultimoEstado); // Setea fechaFin al ultimo estado
        this.crearCambioEstado(fechaHoraInicio, estado, empleadoLogueado); // Crea un nuevo cambio de estado
        this.setEstadoActual(estado); // Asigna el nuevo estado acutal
    }

    public void confirmar(LocalDateTime fechaHoraInicio, Estado estado, Empleado empleadoLogueado, CambioEstado ultimoEstado) {
        this.actualizarUltimoEstado(fechaHoraInicio, ultimoEstado); // Setea fechaFin al ultimo estado
        this.crearCambioEstado(fechaHoraInicio, estado, empleadoLogueado); // Crea un nuevo cambio de estado
        this.setEstadoActual(estado); // Asigna el nuevo estado acutal
    }

    public ArrayList<String> buscarDatosSeriesTemporales(ArrayList<Sismografo> sismografos){ // Obtiene los datos de todas las series temporales del evento
        ArrayList<String> datosSeries = new ArrayList<>();
        for (SerieTemporal serie: serieTemporal){ // Itera las series temporales
            datosSeries.add(serie.getDatos(sismografos)); // Obtiene los datos de las series temporales y los guarda en el array datosSeries
        }
        this.clasificarDatosPorEstacionSismologica(datosSeries); // Clasifica los elementos del array datosSeries por estacion sismologica
        return datosSeries;
    }

    public CambioEstado crearCambioEstado(LocalDateTime fechaHoraInicio, Estado estado, Empleado empleadoLogueado) { // Crea un nuevo cambio de estado
        CambioEstado nuevoCambio = new CambioEstado(fechaHoraInicio, estado, empleadoLogueado); // Crea una instancia de CambioEstado
        this.cambioEstado.add(nuevoCambio); // Añade la nueva instancia al array cambioEstado

        return nuevoCambio; // Devuelve el cambio de estado para que el gestor lo almacene y posteriormente no se tenga que buscar.
    }

    public void buscarUltimoEstado(LocalDateTime fechaHoraDeFin) { // Busca el ultimo cambio de estado y le asigna una fechaHoraFin
        for(CambioEstado cambioEstado: cambioEstado){ // Itera los cambios de estado
            if(cambioEstado.esActual()){
                cambioEstado.setFechaHoraFin(fechaHoraDeFin); // Setea fechaHoraFin al cambio de estado actual
            }
        }
    }

    public void actualizarUltimoEstado(LocalDateTime fechaHoraDeFin,CambioEstado ultimoEstado) { // Setea fechaHoraFin al ultimo cambio de estado
        ultimoEstado.setFechaHoraFin(fechaHoraDeFin);
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

    public void clasificarDatosPorEstacionSismologica(ArrayList<String> datosSeries) { // Ordena la lista de datos de las series sismologicas por estacion sismologica
        datosSeries.sort(Comparator.comparing(dato -> {
            // Extrae el código de estación
            /* Hacerlo de esta forma evitará la necesidad de modificar el código si la
            estación sismológica ya no se pone al frente */
            int inicio = dato.indexOf("Estacion Sismologica:") + "Estacion Sismologica:".length();
            int fin = dato.indexOf("-", inicio);
            return dato.substring(inicio, fin);
        }));
    }

    @Override
    public String toString() {
        return "EventoSismico{" +
                "fechaHoraFin=" + fechaHoraFin +
                ", fechaHoraOcurrencia=" + fechaHoraOcurrencia +
                ", estadoActual=" + estadoActual +
                ", cambioEstado=" + cambioEstado +
                '}';
    } // Muestra el valor de los atributos del objeto EventoSismico
}