package org.example.Entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SerieTemporal {
    private boolean condicionAlarma;
    private LocalDateTime fechaHoraInicioRegistroMuestras;
    private LocalDateTime fechaHoraRegistro;
    private String frecuenciaMuestreo; // Ver el tipo de dato
    private Estado estado;
    private ArrayList<MuestraSismica> muestraSismica;

    public SerieTemporal(){}

    public SerieTemporal(boolean condicionAlarma, LocalDateTime fechaHoraInicioRegistroMuestras, LocalDateTime fechaHoraRegistro, String frecuenciaMuestreo, Estado estado, ArrayList<MuestraSismica> muestrasSismicas) {
        this.condicionAlarma = condicionAlarma;
        this.fechaHoraInicioRegistroMuestras = fechaHoraInicioRegistroMuestras;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.frecuenciaMuestreo = frecuenciaMuestreo;
        this.estado = estado;
        this.muestraSismica = muestrasSismicas;
    }
    public String getDatos(ArrayList<Sismografo> sismografos){
        String datos="Fecha/Hora inicio: "+fechaHoraInicioRegistroMuestras;
        for (MuestraSismica muestra: muestraSismica){
            datos=datos+" -- ["+muestra.getDatos()+"]";
        }
        String codigoEstacion="Estacion Sismologica:"+this.obtenerEstacionSismologica(sismografos);
        return codigoEstacion+","+datos;
    }
    public String obtenerEstacionSismologica(ArrayList<Sismografo> sismografos){
        for(Sismografo sismografo:sismografos){
            if (sismografo.esSismografoDeSerieTemporal(this)){
                return sismografo.getCodigoEstacion();
            }
        }
        return null;
    }

    public ArrayList<MuestraSismica> getMuestraSismica() {
        return muestraSismica;
    }
}
