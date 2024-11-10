package com.dam2.appretoandroid.api;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class JwtInterceptor implements Interceptor {
    private String token;

    public JwtInterceptor(String token)
    {
        this.token=token;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        return chain.proceed(chain.request().newBuilder()
                .addHeader("Authorization", "Bearer" + token)
                .build());
    }
}
