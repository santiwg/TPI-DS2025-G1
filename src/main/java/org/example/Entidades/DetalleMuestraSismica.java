package org.example.Entidades;

import java.util.Timer;

public class DetalleMuestraSismica {
    private String valor;
    private TipoDeDato tipoDeDato;

    public DetalleMuestraSismica(){}

    public DetalleMuestraSismica(String valor, TipoDeDato tipoDeDato) {
        this.valor = valor;
        this.tipoDeDato = tipoDeDato;
    }

    public String getValor() {
        return valor;
    }

    public String getDatos() {
        return this.tipoDeDato.getDenominacion()+":"+valor;

    }
}
