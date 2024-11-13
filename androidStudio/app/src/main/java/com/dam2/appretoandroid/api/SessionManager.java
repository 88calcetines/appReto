package com.dam2.appretoandroid.api;

import android.content.Context;
import android.content.SharedPreferences;

import com.dam2.appretoandroid.R;

public class SessionManager
{
    private SharedPreferences prefs;
    private static SessionManager instance;
    public static final String USER_TOKEN = "user_token";

    public SessionManager(Context context)
    {
        prefs = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }


    public void saveAuthToken(String token) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(USER_TOKEN, token);
        editor.apply();  // Apply changes asynchronously
    }

    public String fetchAuthToken() {
        return prefs.getString(USER_TOKEN, null);  // Return null if no token exists
    }

    public static synchronized SessionManager getInstance(Context context) {
    if (instance == null) {
        if (context != null) {
            // Initialize with context if it's valid
            instance = new SessionManager(context.getApplicationContext());
        } else {
            // Throw an exception if context is null (context must not be null)
            throw new IllegalArgumentException("Context cannot be null");
        }
    }
    return instance;
}


}
