package com.dam2.appretoandroid.modelo;

import java.util.ArrayList;

public class Cesta
{

    private ArrayList<Producto> productos;

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }
    public void addProducto(Producto producto)
    {
        productos.add(producto);
    }

}
