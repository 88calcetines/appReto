package com.dam2.appretoandroid.bbdd;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.dam2.appretoandroid.modelo.CestaProducto;
import com.dam2.appretoandroid.modelo.Producto;

import java.util.List;

@Dao
public interface CestaProductoDao
{
    @Insert
    void insertProducto(CestaProducto producto);

    @Query("SELECT * FROM Cesta")
    LiveData<List<CestaProducto>> getAllProductos();

    @Delete
    void deleteCestaProducto(CestaProducto producto);

    @Query("SELECT SUM(precio) FROM Cesta")
    LiveData<Double> getTotalPrice();
}
