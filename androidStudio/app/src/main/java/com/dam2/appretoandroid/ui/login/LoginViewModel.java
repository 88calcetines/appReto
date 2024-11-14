package com.dam2.appretoandroid.ui.login;

import android.content.Context;
import android.util.Log;


import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.dam2.appretoandroid.api.MyApiAdapter;
import com.dam2.appretoandroid.api.SessionManager;
import com.dam2.appretoandroid.modelo.LoginRequest;
import com.dam2.appretoandroid.modelo.LoginResponse;
import com.dam2.appretoandroid.modelo.Usuario;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<Boolean> loginCorrecto;
    private MutableLiveData<Boolean> registroCorrecto;

    public LoginViewModel() {

        this.loginCorrecto = new MutableLiveData<Boolean>();
        this.registroCorrecto = new MutableLiveData<Boolean>();
    }

    public MutableLiveData<Boolean> getLoginCorrecto() {
        return loginCorrecto;
    }

    public void login(LifecycleOwner owner , Context context, String email, String password)
    {
        SessionManager sessionManager = new SessionManager(context);

        Call<LoginResponse> call= MyApiAdapter.getApiService().login(new LoginRequest(email, password));
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    // Get the response body
                    LoginResponse loginResponse = response.body();


                    if(!loginResponse.getAuthToken().isEmpty()){
                        sessionManager.saveAuthToken(loginResponse.getAuthToken());
                        loginCorrecto.postValue(true);
                    }



                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable throwable) {

                Log.e("login", "Login failed: " + (throwable != null ? throwable.toString() : "Unknown error"));

                // Optionally, you can add additional logging or exception handling here
                if (throwable != null) {
                    throwable.printStackTrace(); // Prints the stack trace for debugging
                }
                Log.d("login", "incorrecto");
                loginCorrecto.postValue(false);
            }
        });

    }

    public void registro(LifecycleOwner owner , Context context, Usuario usuario)
    {
        Call<Void> call= MyApiAdapter.getApiService().registro(usuario);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                registroCorrecto.postValue(true);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                registroCorrecto.postValue(false);
            }
        });
    }

    public MutableLiveData<Boolean> getRegistroCorrecto() {
        return registroCorrecto;
    }
}