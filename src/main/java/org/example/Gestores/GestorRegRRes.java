package org.example.Gestores;

import org.example.Entidades.Estado;
import org.example.Entidades.EventoSismico;

import java.util.ArrayList;

public class GestorRegRRes {

    private ArrayList<EventoSismico> listaEventosSismicos;
    private EventoSismico eventoSismicoSeleccionado;
    private Estado estadoBloqueado;
    private Estado estadoRechazado;
    private ArrayList<> listaDatosEventos;

    public void nuevaRevisionES(){
        this.buscarESNoRevisados();
        this.ordenarEventosSismicos();
        //pantalla.mostrarESParaSeleccion
    }
    public void buscarESNoRevisados(){
        for (EventoSismico evento : eventos) {
            if (evento.esAutoDetectado()){
                listaEventosSismicos.add(evento);
                listaDatosEventos.add(evento.getDatosPrincipales());
            }
            else if(evento.esPendienteDeRevision()){
                listaEventosSismicos.add(evento);
                listaDatosEventos.add(evento.getDatosPrincipales());
            }
        }
    }
    public ArrayList<> ordenarEventosSismicos(){

    }
    public void tomarSeleccionES(){


    }
}
