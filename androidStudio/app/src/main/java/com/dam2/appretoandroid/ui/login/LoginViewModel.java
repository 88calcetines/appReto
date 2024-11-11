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


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<Boolean> loginCorrecto;

    public LoginViewModel() {
        this.loginCorrecto = new MutableLiveData<Boolean>();
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

                    sessionManager.saveAuthToken(loginResponse.getAuthToken());
                    if(!loginResponse.getAuthToken().isEmpty()){
                        loginCorrecto.postValue(true);
                    }



                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable throwable) {
                Log.d("login", "incorrecto");
                loginCorrecto.postValue(false);
            }
        });

    }
}