package org.example.Gestores;

import org.example.Entidades.Empleado;
import org.example.Entidades.Estado;
import org.example.Entidades.EventoSismico;
import org.example.Entidades.Sesion;
import org.example.Pantallas.PantRegRRes;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Comparator;

public class GestorRegRRes {

    private ArrayList<EventoSismico> listaEventosSismicos;
    private ArrayList<EventoSismico> listaEventosSismicosNoRevisados;
    private EventoSismico eventoSismicoSeleccionado;
    private Estado estadoRechazado;
    private ArrayList<Object> listaDatosEventos; //revisar el tipo
    private Sesion sesion;
    private Empleado empleadoLogueado;
    private ArrayList<Estado> listaEstados;
    private Estado estadoBloqueadoEnRevision;
    private LocalDateTime fechaHoraActual;
    private PantRegRRes pantalla;

    public void nuevaRevisionES(){
        this.buscarESNoRevisados();
        this.ordenarEventosSismicosPorFechaYHora(listaEventosSismicosNoRevisados);
        //this.pantalla.mostrarESParaSeleccion(listaEventosSismicosNoRevisados);
    }
    public void buscarESNoRevisados(){
        for (EventoSismico evento : listaEventosSismicos) {
            if (evento.esAutoDetectado() || evento.esPendienteDeRevision()){
                listaEventosSismicosNoRevisados.add(evento);
                //listaDatosEventos.add(evento.getDatosPrincipales()); // Habría que unificar ambos en un diccionario.
            }
        }
    }
    public void ordenarEventosSismicosPorFechaYHora(ArrayList<EventoSismico> listaEventosSismicosNoRevisados){ // ¿Debería ser un procedimiento o devolver los eventos ordenados?
        listaEventosSismicosNoRevisados.sort(Comparator.comparing(EventoSismico::getFechaHoraOcurrencia));
        //corregir
    }

    public void tomarSeleccionES(){

    }

    public void buscarEstadoBloqueadoEnRev(){
        for(Estado estado: listaEstados){
            if(estado.esAmbitoEventoSismico() & estado.esBloqueadoEnRevision()){
                this.estadoBloqueadoEnRevision = estado;
                break;
            }
        }
        //Se supone que si o si el estado va a estar (entra al if) o se puede dar el caso que no exista el estado?
    }

    public void tomarFechaHoraActual(){
        this.fechaHoraActual = LocalDateTime.now();
    }

    public void buscarEmpleadoLogueado() {
        this.empleadoLogueado = this.sesion.getEmpleadoLogueado();
    }

    public void bloquearEventoSismico(){
        this.eventoSismicoSeleccionado.bloquear();
    }

    public String buscarDatosEventoSismico(){
        String alcance = this.eventoSismicoSeleccionado.conocerAlcance().getNombre();
        String clasificacion = this.eventoSismicoSeleccionado.conocerClasificacion().getNombre();
        String origenGeneracion = this.eventoSismicoSeleccionado.conocerOrigenGeneracion().getNombre();
        String datos = alcance + clasificacion + origenGeneracion; //Ver la forma en que se retornaran los datos
        return datos;
    }
    public void cancelarCU(){
        //implementar
    }
}
