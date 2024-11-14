package com.dam2.appretoandroid;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.dam2.appretoandroid.bbdd.AppDatabase;
import com.dam2.appretoandroid.bbdd.AppExecutors;
import com.dam2.appretoandroid.bbdd.CestaProductoDao;
import com.dam2.appretoandroid.modelo.CestaProducto;

import java.util.List;

public class CestaItemRepository
{
    private CestaProductoDao cestaProductoDao;
    private LiveData<List<CestaProducto>> allItems;
    private LiveData<Double> precioTotal;
    private Context context;

    public CestaItemRepository(Context context) {
        this.context=context;
        AppDatabase db = AppDatabase.getInstance(context);
        cestaProductoDao = db.cestaProductoDao();
        allItems =  cestaProductoDao.getAllProductos();
        precioTotal = cestaProductoDao.getTotalPrice();
    }

    public LiveData<List<CestaProducto>> getAllItems() {
        return allItems;
    }



    public LiveData<Double> getPrecioTotal() {
        return precioTotal;
    }

    public void insertItem(CestaProducto cestaProducto) {
        AppDatabase db = AppDatabase.getInstance(context);
        AppExecutors.getInstance().getDiskIp().execute(new Runnable() {
            @Override
            public void run() {
                db.cestaProductoDao().insertProducto(cestaProducto);
            }
        });
    }

    public void deleteItem(CestaProducto cestaProducto) {
        AppDatabase db = AppDatabase.getInstance(context);
        AppExecutors.getInstance().getDiskIp().execute(new Runnable() {
            @Override
            public void run() {
                db.cestaProductoDao().deleteCestaProducto(cestaProducto);
            }
        });
    }




}
