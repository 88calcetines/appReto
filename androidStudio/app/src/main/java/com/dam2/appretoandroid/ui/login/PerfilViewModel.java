package com.dam2.appretoandroid.ui.login;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dam2.appretoandroid.api.MyApiAdapter;
import com.dam2.appretoandroid.api.MyApiAdapterProfile;
import com.dam2.appretoandroid.modelo.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<Usuario> usuario;
    private Context mContext;


    public PerfilViewModel(Application application) {
        super(application);
        this.usuario=new MutableLiveData<Usuario>();
        mContext=getApplication().getApplicationContext();
    }



    public void getPerfil()
    {
        Call<Usuario> callUsuario= MyApiAdapterProfile.getApiService(mContext).getMiPerfil();

        callUsuario.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful())
                {
                    usuario.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable throwable) {

            }
        });
    }

    public MutableLiveData<Usuario> getUsuario() {
        return usuario;
    }
}