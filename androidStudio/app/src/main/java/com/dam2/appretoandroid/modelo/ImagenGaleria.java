package com.dam2.appretoandroid.modelo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "ImagenGaleria")
public class ImagenGaleria implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String foto;

    public ImagenGaleria(String foto)
    {
        this.foto=foto;
    }

}
