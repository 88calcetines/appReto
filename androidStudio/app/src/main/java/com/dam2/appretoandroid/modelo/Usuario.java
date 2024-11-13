package com.dam2.appretoandroid.modelo;

public class Usuario
{
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String email;
    private String password;
    private String imagen;


    public Usuario(String apellido1, String apellido2, String contrasena, String email, int id, String imagen, String nombre) {
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.password = contrasena;
        this.email = email;
        this.id = id;
        this.imagen = imagen;
        this.nombre = nombre;
    }

    public Usuario()
    {
        this.apellido1 = "";
        this.apellido2 = "";
        this.password = "";
        this.email = "";
        this.id = -1;
        this.imagen = "";
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
        return password;
    }

    public void setContrasena(String contrasena) {
        this.password = contrasena;
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



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
