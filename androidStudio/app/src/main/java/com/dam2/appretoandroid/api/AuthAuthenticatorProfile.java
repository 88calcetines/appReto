package com.dam2.appretoandroid.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class AuthAuthenticatorProfile implements Authenticator
{
    private SessionManager sessionManager;

    public AuthAuthenticatorProfile(SessionManager sessionManager) {
        this.sessionManager=sessionManager;
    }
    @Nullable
    @Override
    public Request authenticate(@Nullable Route route, @NonNull Response response) throws IOException {
        if(response.request().header("Authorization") == null)
        {
            String authToken = "";
            if(!getAuthToken().isEmpty())
            {
                authToken=getAuthToken();
                return response.request().newBuilder()
                        .header("Authorization", "Bearer " + authToken)
                        .build();
            }



        }
        return response.request();
    }

    private String getAuthToken()
    {
        String token="";

        if(!sessionManager.fetchAuthToken().isEmpty()){
            token=sessionManager.fetchAuthToken();

        }
        return  token;

    }
}
