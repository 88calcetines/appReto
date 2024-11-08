package com.dam2.appretoandroid.api;

import com.dam2.appretoandroid.modelo.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyApiService
{
    @GET("productos")
    Call<List<Producto>> getProductos();

    @GET("productoCategoria/{nombre}")
    Call<List<Producto>> getProductosNombre(@Path("nombre")String nombre);



}
