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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
