package com.dam2.appretoandroid.modelo;

import java.util.ArrayList;

public class Cesta
{

    private ArrayList<Producto> productos;

    public Cesta(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public Cesta() {
        this.productos=new ArrayList<>();
    }
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
