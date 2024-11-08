package com.dam2.appretoandroid.ui.servicios;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dam2.appretoandroid.api.MyApiAdapter;
import com.dam2.appretoandroid.modelo.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideojuegosViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<List<Producto>> mProductos;

    public VideojuegosViewModel(){
        mProductos=new MutableLiveData<List<Producto>>();
    }

    public void setmProductos(MutableLiveData<List<Producto>> mProductos) {
        this.mProductos = mProductos;
    }
    public MutableLiveData<List<Producto>> getmProductos(){
        return mProductos;
    }

    public void cargarProductos()
    {
        Call<List<Producto>> call= MyApiAdapter.getApiService().getProductosNombre("Videojuegos");
        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {

                mProductos.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable throwable) {
                Log.d("respuesta", "llamada incorrecta");
            }
        });
    }
}