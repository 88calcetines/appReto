package com.dam2.appretoandroid.api;

import com.dam2.appretoandroid.modelo.LoginRequest;
import com.dam2.appretoandroid.modelo.LoginResponse;
import com.dam2.appretoandroid.modelo.Producto;
import com.dam2.appretoandroid.modelo.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyApiService
{
    @GET("productos")
    Call<List<Producto>> getProductos();

    @GET("productoCategoria/{nombre}")
    Call<List<Producto>> getProductosNombre(@Path("nombre")String nombre);

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @GET("productosMejoresValorados")
    Call<List<Producto>> getProductosMejorValorados();

    @GET("productosMasBaratosporCategoria/{nombre}")
    Call<List<Producto>> getProductosMasBaratos(@Path("nombre")String nombre);

    @GET("productosRecientes")
    Call<List<Producto>> getProductosRecientes();

    @GET("miperfil")
    Call<Usuario> getMiPerfil();

    @POST("register")
    Call<Void> registro(@Body Usuario usuario);



    //register
    //
    // @POST("register")

}
