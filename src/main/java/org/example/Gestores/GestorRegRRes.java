package org.example.Gestores;

import org.example.Entidades.Empleado;
import org.example.Entidades.Estado;
import org.example.Entidades.EventoSismico;
import org.example.Entidades.Sesion;
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
    private ArrayList<Object> listaDatosEventos=new ArrayList<>(); //revisar el tipo
    private Sesion sesion;
    private Empleado empleadoLogueado;
    private ArrayList<Estado> listaEstados;
    private Estado estadoBloqueadoEnRevision;
    private LocalDateTime fechaHoraActual;
    private PantRegRRes pantalla;
    private String nombreAlcance;
    private String nombreOrigenGeneracion;
    private String nombreClasificacion;

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
        for (EventoSismico evento : listaEventosSismicos) {
            if (evento.esAutoDetectado() || evento.esPendienteDeRevision()){
                Map<String, Object> diccionarioEvento = new HashMap<>();
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
        //implementar
        //habria que comparar los datos de cada evento con lo que pasa el parametro, formatenadolo
    }

    public void buscarEstadoBloqueadoEnRev(){
        for(Estado estado: listaEstados){
            if(estado.esAmbitoEventoSismico() & estado.esBloqueadoEnRevision()){
                this.estadoBloqueadoEnRevision = estado;
                break;
            }
        }
    }

    public void bloquearEventoSismico(){
        this.eventoSismicoSeleccionado.revisar(fechaHoraActual, estadoBloqueadoEnRevision, empleadoLogueado);
    }

    public void tomarFechaHoraActual(){
        this.fechaHoraActual = LocalDateTime.now();
    }

    public void buscarEmpleadoLogueado() {
        this.empleadoLogueado = this.sesion.getEmpleadoLogueado();
    }

    public void bloquearEventoSismico(){
        this.eventoSismicoSeleccionado.revisar();
    }


    public void buscarDatosEventoSismico(){
        this.nombreAlcance = this.eventoSismicoSeleccionado.conocerAlcance();
        this.nombreOrigenGeneracion = this.eventoSismicoSeleccionado.conocerClasificacion();
        this.nombreClasificacion= this.eventoSismicoSeleccionado.conocerOrigenGeneracion();
        pantalla.mostrarDatosEventoSismico(nombreAlcance,nombreOrigenGeneracion,nombreClasificacion);

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
        if (validarDatosMinimos()){
            this.buscarEstadoRechazado();
            this.tomarFechaHoraActual();
            this.rechazarEventoSismico();
        }

    }

    public void rechazarEventoSismico(){
        this.eventoSismicoSeleccionado.rechazar(fechaHoraActual, estadoRechazado, empleadoLogueado);
    }

    public void cancelarCU(){
        //implementar
    }
    public boolean validarDatosMinimos(){
        //implementar
        return true;
    }
}
