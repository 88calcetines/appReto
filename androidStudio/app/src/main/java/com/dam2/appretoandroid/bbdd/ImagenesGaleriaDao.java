package com.dam2.appretoandroid.bbdd;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.dam2.appretoandroid.modelo.ImagenGaleria;

import java.util.List;

@Dao
public interface ImagenesGaleriaDao {

    @Query("Select * from ImagenGaleria")
    LiveData<List<ImagenGaleria>> loadImagenes();

    @Insert
    void insertImagen(ImagenGaleria imagenGaleria);

}
