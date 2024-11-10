package com.dam2.appretoandroid.modelo;

public class LoginResponse {
    private int statusCode;
    private String authToken;




    public LoginResponse() {
        this.statusCode=-1;
        this.authToken="";
        ;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }


}
