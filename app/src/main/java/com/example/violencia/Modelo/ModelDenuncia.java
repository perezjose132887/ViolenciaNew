package com.example.violencia.Modelo;

public class ModelDenuncia {


    short idDenuncia;
    short idUsuario;
    short idCategoria;
    String declaracion;
    String foto;


    public ModelDenuncia() {

    }

    public ModelDenuncia(short idDenuncia, short idUsuario, short idCategoria, String declraracion, String foto) {
        this.idDenuncia = idDenuncia;
        this.idUsuario = idUsuario;
        this.idCategoria = idCategoria;
        this.declaracion = declraracion;
        this.foto = foto;
    }

    public short getIdDenuncia() {
        return idDenuncia;
    }

    public void setIdDenuncia(short idDenuncia) {
        this.idDenuncia = idDenuncia;
    }

    public short getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(short idUsuario) {
        this.idUsuario = idUsuario;
    }

    public short getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(short idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDeclraracion() {
        return declaracion;
    }

    public void setDeclraracion(String declraracion) {
        this.declaracion = declraracion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    @Override
    public String toString() {
        return "ModelDenuncia{" +
                "idDenuncia=" + idDenuncia +
                ", declraracion='" + declaracion + '\'' +
                '}';
    }




}
