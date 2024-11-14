package com.dam2.appretoandroid.ui.servicios;

import android.util.Log;

import androidx.lifecycle.LiveData;
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
    private MutableLiveData<List<Producto>> mProductosMejorValorados;
    private MutableLiveData<List<Producto>> mProductosRecientes;
    private MutableLiveData<List<Producto>> mProductosBaratos;
    private MutableLiveData<List<Producto>> mProductos;
    private MutableLiveData<String> message;
    private MutableLiveData<Boolean> llamadaCorrecta;
    public LiveData<String> toastMessage;
    private String categoria;

    public VideojuegosViewModel(){
        mProductosMejorValorados=new MutableLiveData<List<Producto>>();
        mProductosRecientes=new MutableLiveData<List<Producto>>();
        mProductosBaratos=new MutableLiveData<List<Producto>>();
        mProductos=new MutableLiveData<List<Producto>>();
        message=new MutableLiveData<String>();
        llamadaCorrecta=new MutableLiveData<Boolean>();
        toastMessage=message;

    }

    public void setmProductos(MutableLiveData<List<Producto>> mProductos) {
        this.mProductosMejorValorados = mProductos;
    }
    public MutableLiveData<List<Producto>> getmProductos(){
        return mProductos;
    }
    public MutableLiveData<List<Producto>> getmProductosMejorValorados(){
        return mProductosMejorValorados;
    }

    public MutableLiveData<List<Producto>> getmProductosRecientes() {
        return mProductosRecientes;
    }

    public MutableLiveData<List<Producto>> getmProductosBaratos() {
        return mProductosBaratos;
    }

    public void cargarProductos()
    {
        Call<List<Producto>> call= MyApiAdapter.getApiService().getProductosNombre(categoria);
        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {



                mProductos.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable throwable) {

                message.postValue("No se han podido recuperar los datos");
                llamadaCorrecta.postValue(false);
            }
        });
        Call<List<Producto>> callMejorValorados= MyApiAdapter.getApiService().getProductosMejorValorados();
        callMejorValorados.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {


                mProductosMejorValorados.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable throwable) {

                message.postValue("No se han podido recuperar los datos");
                llamadaCorrecta.postValue(false);
            }
        });
        Call<List<Producto>> callBaratos= MyApiAdapter.getApiService().getProductosMasBaratos(categoria);
        callBaratos.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                mProductosBaratos.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable throwable) {
                message.postValue("No se han podido recuperar los datos");
                llamadaCorrecta.postValue(false);
            }
        });

        Call<List<Producto>> callRecientes= MyApiAdapter.getApiService().getProductosRecientes();
        callRecientes.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                mProductosRecientes.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable throwable) {
                message.postValue("No se han podido recuperar los datos");
                llamadaCorrecta.postValue(false);
            }
        });


    }

    public MutableLiveData<Boolean> getLlamadaCorrecta() {
        return llamadaCorrecta;
    }
    public void setCategoria(String categoria)
    {
        this.categoria=categoria;
    }
}