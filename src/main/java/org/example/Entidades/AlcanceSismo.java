package org.example.Entidades;

public class AlcanceSismo {
    private String descripcion;
    private String nombre;

    public AlcanceSismo(){}
    public AlcanceSismo(String descripcion, String nombre) {
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
