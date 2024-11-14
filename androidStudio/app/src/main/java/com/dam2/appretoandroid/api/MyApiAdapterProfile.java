package com.dam2.appretoandroid.api;

import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApiAdapterProfile
{
    private static MyApiService API_SERVICE;
    private static final String BASE_URL = "https://calcetines88.duckdns.org/";



    public static MyApiService getApiService(Context context){
        // Creamos un interceptor y le indicamos el log level a usar
        final HttpLoggingInterceptor logging= new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);


        // Asociamos el interceptor a las peticiones
        final OkHttpClient.Builder httpClient= new OkHttpClient.Builder()
                .authenticator(new AuthAuthenticatorProfile(new SessionManager(context)));
        httpClient.addInterceptor(logging);
        if(API_SERVICE == null)
        {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())//setLogLevel
                    .build();
            API_SERVICE = retrofit.create(MyApiService.class);
        }
        return API_SERVICE;
    }
}
