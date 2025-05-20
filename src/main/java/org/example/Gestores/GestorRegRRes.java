package org.example.Gestores;

import org.example.Entidades.Empleado;
import org.example.Entidades.Estado;
import org.example.Entidades.EventoSismico;
import org.example.Entidades.Sesion;

import java.util.ArrayList;

public class GestorRegRRes {

    private ArrayList<EventoSismico> listaEventosSismicosNoRevisados;
    private EventoSismico eventoSismicoSeleccionado;
    private Estado estadoBloqueado;
    private Estado estadoRechazado;
    private ArrayList<> listaDatosEventos;
    private Sesion sesion;
    private Empleado empleadoLogueado;

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
    public ArrayList<> ordenarEventosSismicosPorFechaYHora(){ // ¿Debería ser un procedimiento o devolver los eventos ordenados?

    }
    public void tomarSeleccionES(){

    }

    public void buscarEmpleadoLogueado() {
        this.empleadoLogueado = this.sesion.getEmpleadoLogueado();
    }
}
