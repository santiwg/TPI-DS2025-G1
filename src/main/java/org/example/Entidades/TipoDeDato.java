package org.example.Entidades;

public class TipoDeDato {
    private String denominacion;
    private String nombreUnidadMedida;
    private float valorUmbral;

    public TipoDeDato(){}

    public TipoDeDato(String denominacion, String nombreUnidadMedida, float valorUmbral) {
        this.denominacion = denominacion;
        this.nombreUnidadMedida = nombreUnidadMedida;
        this.valorUmbral = valorUmbral;
    }


    public String getDenominacion() {
        return denominacion;
    }
}
