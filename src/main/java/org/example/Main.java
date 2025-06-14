package org.example;

import org.example.Entidades.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Creo tipos de dato
        TipoDeDato velocidadDeOnda=new TipoDeDato("Velocidad de onda","Km/seg", 1f);
        TipoDeDato frecuenciaDeOnda=new TipoDeDato("Frecuencia De Onda","Hz",15f);
        TipoDeDato longitud=new TipoDeDato("Longitud","Km/ciclo",1f);

        //Creo detalles de muestras sismicas
        DetalleMuestraSismica velocidad=new DetalleMuestraSismica(0.7,velocidadDeOnda);
        DetalleMuestraSismica frecuencia=new DetalleMuestraSismica(10,frecuenciaDeOnda);
        DetalleMuestraSismica medidaLongitud=new DetalleMuestraSismica(0.7, longitud);
        ArrayList<DetalleMuestraSismica> detallesMuestra=new ArrayList<DetalleMuestraSismica>();
        detallesMuestra.add(velocidad);
        detallesMuestra.add(frecuencia);
        detallesMuestra.add(medidaLongitud);

        //Creo muestras sismicas
        MuestraSismica muestra1=new MuestraSismica(LocalDateTime.of(2025, 6,14,13,48,34),detallesMuestra);
        MuestraSismica muestra2=new MuestraSismica(LocalDateTime.of(2025, 6,14,13,28,54),detallesMuestra);
        MuestraSismica muestra3=new MuestraSismica(LocalDateTime.of(2025, 6,14,12,28,54),detallesMuestra);
        ArrayList<MuestraSismica> muestrasSismicas=new ArrayList<>();
        muestrasSismicas.add(muestra1);
        muestrasSismicas.add(muestra2);
        muestrasSismicas.add(muestra3);

        //creo el estado de la serie temporal
        Estado completada=new Estado("SerieTemporal","Completada");

        //creo series temporales
        SerieTemporal serie1=new SerieTemporal(
                false,
                LocalDateTime.of(2025, 6,14,12,28,54),
                LocalDateTime.of(2025, 6,14,12,28,54),
                "50 hz",
                completada,
                muestrasSismicas);
        SerieTemporal serie2=new SerieTemporal(
                false,
                LocalDateTime.of(2025, 6,14,12,21,54),
                LocalDateTime.of(2025, 6,14,12,21,54),
                "50 hz",
                completada,
                muestrasSismicas);
        SerieTemporal serie3=new SerieTemporal(
                false,
                LocalDateTime.of(2025, 6,14,12,23,54),
                LocalDateTime.of(2025, 6,14,12,23,54),
                "50 hz",
                completada,
                muestrasSismicas);
        SerieTemporal serie4=new SerieTemporal(
                false,
                LocalDateTime.of(2025, 6,14,12,29,54),
                LocalDateTime.of(2025, 6,14,12,29,54),
                "50 hz",
                completada,
                muestrasSismicas);
        ArrayList<SerieTemporal> seriesTemporales= new ArrayList<>();
        seriesTemporales.add(serie1);
        seriesTemporales.add(serie2);
        seriesTemporales.add(serie3);
        seriesTemporales.add(serie4);
        ArrayList<SerieTemporal> seriesSismografo1=new ArrayList<>();
        seriesSismografo1.add(serie2);
        seriesSismografo1.add(serie4);
        ArrayList<SerieTemporal> seriesSismografo2=new ArrayList<>();
        seriesSismografo2.add(serie1);
        seriesSismografo2.add(serie3);

        //Creo estaciones sismologicas
        EstacionSismologica estacion1=new EstacionSismologica("AAA",-32.56f,-94.56f,"Estacion Uno");
        EstacionSismologica estacion2=new EstacionSismologica("AAB",-32.56f,-94.56f,"Estacion Dos");

        //Creo sismografos
        Sismografo sismografo1=new Sismografo(1,seriesSismografo1,estacion1);
        Sismografo sismografo2=new Sismografo(2,seriesSismografo2,estacion2);
        ArrayList<Sismografo> sismografos=new ArrayList<>();
        sismografos.add(sismografo1);
        sismografos.add(sismografo2);

        



    }
}