package org.example.Gestores;

import org.example.Entidades.Empleado;
import org.example.Entidades.Estado;
import org.example.Entidades.EventoSismico;
import org.example.Entidades.Sesion;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Comparator;

public class GestorRegRRes {

    private ArrayList<EventoSismico> listaEventosSismicosNoRevisados;
    private EventoSismico eventoSismicoSeleccionado;
    private Estado estadoBloqueado;
    private Estado estadoRechazado;
    private ArrayList<> listaDatosEventos;
    private Sesion sesion;
    private Empleado empleadoLogueado;
    private ArrayList<Estado> listaEstados;
    private int punteroEstadoBloqueadoEnRevision;
    private LocalDateTime fechaHoraActual;

    public void nuevaRevisionES(){
        this.buscarESNoRevisados();
        this.ordenarEventosSismicosPorFechaYHora();
        //pantalla.mostrarESParaSeleccion
    }
    public void buscarESNoRevisados(){
        for (EventoSismico evento : eventos) { // ¿Cómo consigue todos los eventos sísmicos?¿Debería tener un atributo de seteo en el main (listaEventosSismicos)?
            if (evento.esAutoDetectado() || evento.esPendienteDeRevision()){
                listaEventosSismicosNoRevisados.add(evento);
                listaDatosEventos.add(evento.getDatosPrincipales()); // Habría que unificar ambos en un diccionario.
            }
        }
    }
    public ArrayList<EventoSismico> ordenarEventosSismicosPorFechaYHora(ArrayList<EventoSismico> listaEventosSismicosNoRevisados){ // ¿Debería ser un procedimiento o devolver los eventos ordenados?
        listaEventosSismicosNoRevisados.sort(Comparator.comparing(EventoSismico::getFechaHoraOcurrencia));
        return listaEventosSismicosNoRevisados;
        //Verificar funcionamiento
    }

    public void tomarSeleccionES(){

    }

    public int buscarEstadoBloquearEnRev(){
        int puntero = 0;
        for(Estado estado: listaEstados){
            puntero = puntero + 1;
            if(estado.esAmbitoEventoSismico() & estado.esBloqueadoEnRevision()){
                break;
            }
        }
        return this.punteroEstadoBloqueadoEnRevision = puntero;
        //Se supone que si o si el estado va a estar (entra al if) o se puede dar el caso que no exista el estado?
    }

    public void setFechaHoraActual(){
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
}
