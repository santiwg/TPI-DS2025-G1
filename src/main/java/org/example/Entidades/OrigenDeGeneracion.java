package org.example.Entidades;

public class OrigenDeGeneracion {
    private String nombre;
    private String descripcion;
    public OrigenDeGeneracion(String nombre, String descripcion){
        this.nombre=nombre;
        this.descripcion=nombre;
    }
    public String getNombre(){
        return this.nombre;
    }
}
