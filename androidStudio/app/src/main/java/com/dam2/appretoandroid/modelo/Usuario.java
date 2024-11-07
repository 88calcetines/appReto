package com.dam2.appretoandroid.modelo;

public class Usuario
{
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String email;
    private String contrasena;
    private String imagen;
    private String localizacion;

    public Usuario(String apellido1, String apellido2, String contrasena, String email, int id, String imagen, String localizacion, String nombre) {
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.contrasena = contrasena;
        this.email = email;
        this.id = id;
        this.imagen = imagen;
        this.localizacion = localizacion;
        this.nombre = nombre;
    }

    public Usuario()
    {
        this.apellido1 = "";
        this.apellido2 = "";
        this.contrasena = "";
        this.email = "";
        this.id = -1;
        this.imagen = "";
        this.localizacion = "";
        this.nombre = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
