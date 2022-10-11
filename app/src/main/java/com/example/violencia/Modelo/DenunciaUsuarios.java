package com.example.violencia.Modelo;

public class DenunciaUsuarios {
    String idDenuncia;
    String descripcionCategoria;
    String nombres;
    String declaracion;
    String estado;
    String idUsuario;
    String fechaRegistro;

    public DenunciaUsuarios() {
    }

    public DenunciaUsuarios(String idDenuncia, String nombres, String declaracion, String estado) {
        this.idDenuncia = idDenuncia;
        this.nombres = nombres;
        this.declaracion = declaracion;
        this.estado = estado;
    }

    public DenunciaUsuarios(String idDenuncia, String nombres, String declaracion, String estado, String idUsuario) {
        this.idDenuncia = idDenuncia;
        this.nombres = nombres;
        this.declaracion = declaracion;
        this.estado = estado;
        this.idUsuario = idUsuario;
    }

    public DenunciaUsuarios(String idDenuncia, String nombres, String descripcionCategoria, String declaracion, String estado, String idUsuario, String fechaRegistro) {
        this.idDenuncia = idDenuncia;
        this.nombres = nombres;
        this.descripcionCategoria = descripcionCategoria;
        this.declaracion = declaracion;
        this.estado = estado;
        this.idUsuario = idUsuario;
        this.fechaRegistro = fechaRegistro;
    }

    public String getIdDenuncia() {
        return idDenuncia;
    }

    public void setIdDenuncia(String idDenuncia) {
        this.idDenuncia = idDenuncia;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }

    public String getDeclaracion() {
        return declaracion;
    }

    public void setDeclaracion(String declaracion) {
        this.declaracion = declaracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
