package com.dam2.appretoandroid.modelo;

import java.io.Serializable;

public class Producto implements Serializable
{
    private int id;
    private String nombre;
    private float precio;
    private int stock;
    private String descripcion;
    private String imagen;
    private int tipo_producto;

    public Producto(String descripcion, int id, String imagen, String nombre, float precio, int stock, int tipo_producto) {
        this.descripcion = descripcion;
        this.id = id;
        this.imagen = imagen;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.tipo_producto = tipo_producto;
    }

    public Producto() {
        this.descripcion="";
        this.id = -1;
        this.imagen = "";
        this.nombre = "";
        this.precio = -1;
        this.stock = -1;
        this.tipo_producto = -1;

    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getId() {
        return id;
    }

    public String getImagen() {
        return imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public int getTipo_producto() {
        return tipo_producto;
    }
}
