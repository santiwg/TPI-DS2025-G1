package org.example.Gestores;

import org.example.Entidades.*;
import org.example.Pantallas.PantRegRRes;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GestorRegRRes {

    private ArrayList<EventoSismico> listaEventosSismicos;
    private ArrayList<Map<String, Object>> listaESNoRevisados=new ArrayList<>();
    //la lista de eventos será un array de diccionarios, cada uno de los cuales tendrá evento y datos
    private EventoSismico eventoSismicoSeleccionado;
    private Estado estadoRechazado;
    private ArrayList<String> listaDatosSeriesTemporales=new ArrayList<>(); //revisar el tipo
    private Sesion sesion;
    private Empleado empleadoLogueado;
    private ArrayList<Estado> listaEstados;
    private Estado estadoBloqueadoEnRevision;
    private LocalDateTime fechaHoraActual;
    private PantRegRRes pantalla;
    private String nombreAlcance;
    private String nombreOrigenGeneracion;
    private String nombreClasificacion;
    private CambioEstado ultimoCambioDeEstado;
    private ArrayList<Sismografo> sismografos;

    public void nuevaRevisionES(){
        this.buscarESNoRevisados();
        this.ordenarEventosSismicosPorFechaYHora();

        //armo un array con los datos de los eventos formateados para pasarlo a la pantalla
        ArrayList<String> datosEventos = listaESNoRevisados.stream()
                .map(diccEvento -> {
                    //obtengo el diccionario de datos del evento y le hago un toString
                    Map<String, Object> datos = (Map<String, Object>) diccEvento.get("datos");
                    String datosStr = String.format(
                            "fechaHoraOcurrencia=%s, latitudEpicentro=%.2f, latitudHipocentro=%.2f, longitudEpicentro=%.2f, longitudHipocentro=%.2f, valorMagnitud=%.1f",
                            datos.get("fechaHoraOcurrencia"),
                            datos.get("latitudEpicentro"),
                            datos.get("latitudHipocentro"),
                            datos.get("longitudEpicentro"),
                            datos.get("longitudHipocentro"),
                            datos.get("valorMagnitud")
                    );
                    return datosStr;
                })
                .collect(Collectors.toCollection(ArrayList::new));

        pantalla.mostrarESParaSeleccion(datosEventos);
    }
    public void buscarESNoRevisados(){
        for (EventoSismico evento : listaEventosSismicos) { // Recorro todos los eventos sísmicos registrados
            if (evento.esAutoDetectado() || evento.esPendienteDeRevision()){ // Chequeo el estado del evento
                Map<String, Object> diccionarioEvento = new HashMap<>(); // Nuevo diccionario que va a tener los eventos y sus datos
                diccionarioEvento.put("evento", evento);
                diccionarioEvento.put("datos", evento.getDatosPrincipales()); // se espera que esto devuelva un Map
                listaESNoRevisados.add(diccionarioEvento);
            }
        }
    }
    public void ordenarEventosSismicosPorFechaYHora() {
        this.listaESNoRevisados.sort((diccEvento1, diccEvento2) -> {
            //obtengo los datos del evento, que son un diccionario
            //debo hacer casteo porque los definí con tipo Object
            Map<String, Object> datos1 = (Map<String, Object>) diccEvento1.get("datos");
            Map<String, Object> datos2 = (Map<String, Object>) diccEvento2.get("datos");

            //obtengo la fecha de cada evento
            LocalDateTime fecha1 = (LocalDateTime) datos1.get("fechaHoraOcurrencia");
            LocalDateTime fecha2 = (LocalDateTime) datos2.get("fechaHoraOcurrencia");

            // Orden descendente (más reciente primero)
            return fecha2.compareTo(fecha1);
        });
    }




    public void tomarSeleccionES(String eventoSelecc){
        for (Map<String, Object> diccEvento : listaESNoRevisados) {// Busco el evento correspondiente en la lista de eventos no revisados
            Map<String, Object> datos = (Map<String, Object>) diccEvento.get("datos");
            String datosStr = String.format(// Armo el texto del evento tal como se mostró en pantalla
                    "fechaHoraOcurrencia=%s, latitudEpicentro=%.2f, latitudHipocentro=%.2f, longitudEpicentro=%.2f, longitudHipocentro=%.2f, valorMagnitud=%.1f",
                    datos.get("fechaHoraOcurrencia"),
                    datos.get("latitudEpicentro"),
                    datos.get("latitudHipocentro"),
                    datos.get("longitudEpicentro"),
                    datos.get("longitudHipocentro"),
                    datos.get("valorMagnitud")
            );
            if (datosStr.equals(eventoSelecc)) {// Comparo el texto armado con el que me pasó la pantalla
                // Si coinciden, guardo este evento como el seleccionado para revisar
                this.eventoSismicoSeleccionado = (EventoSismico) diccEvento.get("evento");
                break;
            }
        }
            this.buscarEstadoBloqueadoEnRev();// Busco el estado correspondiente a "bloqueadoEnRevisión"

            this.tomarFechaHoraActual();// Guardo la fecha y hora actual para registrar el cambio de estado

            this.buscarEmpleadoLogueado();// Obtengo el empleado que está usando el sistema en este momento

            this.bloquearEventoSismico();// Marco el evento como bloqueado para que nadie más lo modifique mientras se revisa

            this.buscarDatosEventoSismico();// Extraigo los datos del evento que necesito mostrar en pantalla

            // Muestro en la pantalla los datos principales del evento
            pantalla.mostrarDatosEventoSismico(this.nombreAlcance, this.nombreOrigenGeneracion, this.nombreClasificacion);

            this.buscarDatosSeriesTemporales();// Busco los datos necesarios para generar la gráfica del sismograma

            this.llamarCUGenerarSismograma();// Llamo al caso de uso que genera el sismograma\

            pantalla.habilitarOpcVerMapa();
            pantalla.habilitarOpcModificarDatosES();
            pantalla.pedirSeleccionResultadoEvento();
    }

    public void buscarEstadoBloqueadoEnRev(){
        for(Estado estado: listaEstados){//Recorro todos los estados de la listaEstados
            // Verifico que el estado pertenezca al ámbito de eventos sísmicos y que su tipo sea "bloqueadoEnRevision"
            if(estado.esAmbitoEventoSismico() & estado.esBloqueadoEnRevision()){
                this.estadoBloqueadoEnRevision = estado;
                break;
            }
        }
    }

    public void bloquearEventoSismico(){
        //Invoca el método para cambiar el estado del evento y almacena el cambio de estado que se crea
        this.ultimoCambioDeEstado=this.eventoSismicoSeleccionado.revisar(fechaHoraActual, estadoBloqueadoEnRevision, empleadoLogueado);
    }

    public void tomarFechaHoraActual(){
        this.fechaHoraActual = LocalDateTime.now();
    }

    public void buscarEmpleadoLogueado() {
        this.empleadoLogueado = this.sesion.getEmpleadoLogueado();
    }

    public void buscarDatosEventoSismico(){
        this.nombreAlcance = this.eventoSismicoSeleccionado.mostrarAlcance();
        this.nombreOrigenGeneracion = this.eventoSismicoSeleccionado.mostrarClasificacion();
        this.nombreClasificacion= this.eventoSismicoSeleccionado.mostrarOrigenGeneracion();
    }

    public void buscarEstadoRechazado(){
        for(Estado estado: listaEstados){
            if(estado.esAmbitoEventoSismico() & estado.esRechazado()){
                this.estadoRechazado = estado;
                break;
            }
        }
    }

    public void tomarSeleccionRechazo(){
        if (this.validarDatosMinimos()){
            this.buscarEstadoRechazado();
            this.tomarFechaHoraActual();
            this.rechazarEventoSismico();
            this.finCU();
        }
        //podriamos hacer la alternativa en que no se validan los datos mínimos.

    }

    public void rechazarEventoSismico(){
        this.eventoSismicoSeleccionado.rechazar(fechaHoraActual, estadoRechazado, empleadoLogueado,ultimoCambioDeEstado);
    }

    public void cancelarCU(){
        //chequear si está bien lo siguiente
        this.listaEventosSismicos=null;
        this.listaESNoRevisados=null;
        this.eventoSismicoSeleccionado=null;
        this.estadoRechazado=null;
        this.listaDatosSeriesTemporales=null;
        this.sesion=null;
        this.empleadoLogueado=null;
        this.listaEstados=null;
        this.estadoBloqueadoEnRevision=null;
        this.fechaHoraActual=null;
        this.pantalla=null;
        this.nombreAlcance=null;
        this.nombreOrigenGeneracion=null;
        this.nombreClasificacion=null;
        this.ultimoCambioDeEstado=null;
        this.sismografos=null;
    }
    public boolean validarDatosMinimos(){
        float magnitud = 0;
        for (Map<String, Object> diccEvento : listaESNoRevisados) {
            EventoSismico evento = (EventoSismico) diccEvento.get("evento");
            if (evento.equals(eventoSismicoSeleccionado)) {
                Map<String, Object> datos = (Map<String, Object>) diccEvento.get("datos");
                // Suponiendo que el valor es un float, lo casteamos
                magnitud = (float) datos.get("valorMagnitud");
                break;
            }
        }
        return (magnitud != 0 & this.nombreAlcance != null & this.nombreOrigenGeneracion != null);
    }
    public void buscarDatosSeriesTemporales(){
        this.listaDatosSeriesTemporales=eventoSismicoSeleccionado.buscarDatosSeriesTemporales(sismografos);
    }
    public void llamarCUGenerarSismograma(){
        //no se implementa
    }
    public void finCU(){
        this.listaEventosSismicos=null;
        this.listaESNoRevisados=null;
        this.eventoSismicoSeleccionado=null;
        this.estadoRechazado=null;
        this.listaDatosSeriesTemporales=null;
        this.sesion=null;
        this.empleadoLogueado=null;
        this.listaEstados=null;
        this.estadoBloqueadoEnRevision=null;
        this.fechaHoraActual=null;
        this.pantalla=null;
        this.nombreAlcance=null;
        this.nombreOrigenGeneracion=null;
        this.nombreClasificacion=null;
        this.ultimoCambioDeEstado=null;
        this.sismografos=null;
    }
}
