package com.dam2.appretoandroid.bbdd;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.dam2.appretoandroid.modelo.CestaProducto;
import com.dam2.appretoandroid.modelo.ImagenGaleria;

@Database(entities = {ImagenGaleria.class, CestaProducto.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "JuegaAlmi";
    private static AppDatabase sInstance;


    public static AppDatabase getInstance(Context context){
        if(sInstance == null) {
            synchronized (LOCK)
            {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME).build();
            }

        }
        return sInstance;
    }

    public abstract ImagenesGaleriaDao imagenesGaleriaDao();
    public abstract CestaProductoDao cestaProductoDao();
}
