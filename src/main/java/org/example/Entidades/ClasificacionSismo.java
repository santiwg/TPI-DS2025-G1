package org.example.Entidades;

public class ClasificacionSismo {
    private float kmProfundidadDesde;
    private float kmProfundidadHasta;
    private String nombre;

    public ClasificacionSismo(){}

    public ClasificacionSismo(float kmProfundidadDesde, float kmProfundidadHasta, String nombre) {
        this.kmProfundidadDesde = kmProfundidadDesde;
        this.kmProfundidadHasta = kmProfundidadHasta;
        this.nombre = nombre;
    }

    public String getNombre(){
        return this.nombre;
    }
}
