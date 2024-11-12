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

public class DevicesViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<List<Producto>> mProductos;
    private MutableLiveData<String> message;
    public LiveData<String> toastMessage;
    private MutableLiveData<Boolean> llamadaCorrecta;

    public DevicesViewModel(){
        mProductos=new MutableLiveData<List<Producto>>();
        message=new MutableLiveData<String>();
        llamadaCorrecta=new MutableLiveData<Boolean>();
        toastMessage=message;
    }

    public void setmProductos(MutableLiveData<List<Producto>> mProductos) {
        this.mProductos = mProductos;
    }
    public MutableLiveData<List<Producto>> getmProductos(){
        return mProductos;
    }

    public void cargarProductos()
    {
        Call<List<Producto>> call= MyApiAdapter.getApiService().getProductosNombre("Telefonia");
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
    }
    public MutableLiveData<Boolean> getLlamadaCorrecta() {
        return llamadaCorrecta;
    }
}