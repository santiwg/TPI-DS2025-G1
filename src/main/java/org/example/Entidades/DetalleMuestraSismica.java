package org.example.Entidades;

import java.util.Timer;

public class DetalleMuestraSismica {
    private double valor;
    private TipoDeDato tipoDeDato;

    public DetalleMuestraSismica(){}

    public DetalleMuestraSismica(double valor, TipoDeDato tipoDeDato) {
        this.valor = valor;
        this.tipoDeDato = tipoDeDato;
    }

    public double getValor() {
        return valor;
    }

    public String getDatos() {
        return this.tipoDeDato.getDenominacion()+":"+valor; // Ejemplo de salida 'Longitud:20
    }
}
