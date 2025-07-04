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


    public SerieTemporal(boolean condicionAlarma, LocalDateTime fechaHoraInicioRegistroMuestras, LocalDateTime fechaHoraRegistro, String frecuenciaMuestreo, Estado estado, ArrayList<MuestraSismica> muestrasSismicas) {
        this.condicionAlarma = condicionAlarma;
        this.fechaHoraInicioRegistroMuestras = fechaHoraInicioRegistroMuestras;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.frecuenciaMuestreo = frecuenciaMuestreo;
        this.estado = estado;
        this.muestraSismica = muestrasSismicas;
    }
    public String getDatos(ArrayList<Sismografo> sismografos){
        String datos = "Fecha/Hora inicio: " + fechaHoraInicioRegistroMuestras; // Inicializa un string con la fecha y hora de inicio del registro de muestras
        for (MuestraSismica muestra : muestraSismica){ // Itera las muestras sismicas
            datos = datos + " -- [" + muestra.getDatos() + "]"; // Agrega los datos de cada muestra al string
        }
        String codigoEstacion = "Estacion Sismologica: " + this.obtenerEstacionSismologica(sismografos); // Guarda el codigo de la estacion sismologica en un string
        return codigoEstacion + "," + datos; // Devuelve el string completo con los datos de las muestras y el codigo de la estacion concatenados
    }
    public String obtenerEstacionSismologica(ArrayList<Sismografo> sismografos){ // Obtiene el codigo de la estacion sismologica
        for(Sismografo sismografo : sismografos){ // Itera los sismografos
            if (sismografo.esSismografoDeSerieTemporal(this)){
                return sismografo.getCodigoEstacion(); // Cuando encuentra el sismógrafo al que pertence la serie, obtiene el código de la estación
            }
        }
        return null; // En caso de que no se encuentre una estacion a la que pertenezca el sismografo, devuelve un null
    }
}