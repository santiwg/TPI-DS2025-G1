package org.example.Entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SerieTemporal {
    private String condicionAlarma;
    private LocalDateTime fechaHoraInicioRegistroMuestras;
    private LocalDateTime fechaHoraRegistro;
    private String frecuenciaMuestreo; // Ver el tipo de dato
    private Estado estado;
    private ArrayList<MuestraSismica> muestrasSismicas;

    public SerieTemporal(){}

    public SerieTemporal(String condicionAlarma, LocalDateTime fechaHoraInicioRegistroMuestras, LocalDateTime fechaHoraRegistro, String frecuenciaMuestreo, Estado estado, ArrayList<MuestraSismica> muestrasSismicas) {
        this.condicionAlarma = condicionAlarma;
        this.fechaHoraInicioRegistroMuestras = fechaHoraInicioRegistroMuestras;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.frecuenciaMuestreo = frecuenciaMuestreo;
        this.estado = estado;
        this.muestrasSismicas = muestrasSismicas;
    }
}
