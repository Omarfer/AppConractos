package com.example.omarf.miscontactos;

//TODO: Este es el Modelo

public class Contacto {

    private String nombre;
    private String telefono;
    private String email;

    //Contructor que indica que para que exista el registro, como minimo deben ser estos 2 campos
    public Contacto(String nombre, String telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
