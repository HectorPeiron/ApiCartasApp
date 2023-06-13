package com.svalero.apicartasapp.domain;

import java.io.Serializable;

public class Carta implements Serializable {


    private long id;
    private String nombre;
    private String tipo;
    private String coleccion;
    private int anioSalida;


    public Carta(long id, String nombre, String tipo, String coleccion, int anioSalida) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.coleccion = coleccion;
        this.anioSalida = anioSalida;
    }

    public Carta(String nombre, String tipo, String coleccion, int anioSalida) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.coleccion = coleccion;
        this.anioSalida = anioSalida;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getColeccion() {
        return coleccion;
    }

    public void setColeccion(String coleccion) {
        this.coleccion = coleccion;
    }

    public int getAnioSalida() {
        return anioSalida;
    }

    public String getAnioSalidaString() {
        String anioSalidaString = String.valueOf(anioSalida);
        return anioSalidaString;
    }

    public void setAnioSalida(int anioSalida) {
        this.anioSalida = anioSalida;
    }


}


