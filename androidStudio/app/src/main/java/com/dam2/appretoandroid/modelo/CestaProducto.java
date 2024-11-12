package com.dam2.appretoandroid.modelo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Cesta")
public class CestaProducto
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nombre;
    private float precio;
    private int stock;
    private String descripcion;
    private String imagen;
    private int tipo_producto;
    private double valoracion;

    public CestaProducto(String nombre, float precio, int stock, String descripcion, String imagen, int tipo_producto, double valoracion) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.tipo_producto = tipo_producto;
        this.valoracion = valoracion;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getTipo_producto() {
        return tipo_producto;
    }

    public void setTipo_producto(int tipo_producto) {
        this.tipo_producto = tipo_producto;
    }

    public double getValoracion() {
        return valoracion;
    }

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }
}
