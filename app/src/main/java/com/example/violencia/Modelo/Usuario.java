package com.example.violencia.Modelo;

public class Usuario {
    private String idUsuario;
    private String nombres;
    private String primerApellido;
    private String numeroCI ;
    private String foto;
    private String correo;


    public Usuario(){

    }

    public Usuario(String idUsuario, String nombres, String primerApellido, String numeroCI, String foto, String correo) {
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.primerApellido = primerApellido;
        this.numeroCI = numeroCI;
        this.foto = foto;
        this.correo = correo;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getNumeroCI() {
        return numeroCI;
    }

    public void setNumeroCI(String numeroCI) {
        this.numeroCI = numeroCI;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}

