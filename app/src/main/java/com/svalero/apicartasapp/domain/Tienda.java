package com.svalero.apicartasapp.domain;

import java.io.Serializable;

public class Tienda implements Serializable {

    private long id;
    private String nombre;
    private String direccion;
    private String codigoPostal;
    private String email;
    private String telefono;
    // Para mapbox
    private double latitude;
    private double longitud;

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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitud) {
        this.latitude = latitude;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }



    public Tienda(String nombre, String direccion, String codigoPostal, String email, String telefono, double latitude, double longitud) {

        this.nombre = nombre;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.email = email;
        this.telefono = telefono;
        this.latitude = latitude;
        this.longitud = longitud;
    }
    public Tienda(String nombre, String direccion, String codigoPostal, String email, String telefono) {

        this.nombre = nombre;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.email = email;
        this.telefono = telefono;

    }

}
