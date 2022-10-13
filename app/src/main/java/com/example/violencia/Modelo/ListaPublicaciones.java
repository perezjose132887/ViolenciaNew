package com.example.violencia.Modelo;

public class ListaPublicaciones {

    String idPublicacion,titulo,contenido,tipo,fotoPublicacion;

    public ListaPublicaciones() {
    }


    public ListaPublicaciones(String idPublicacion, String titulo, String contenido, String tipo, String fotoPublicacion) {
        this.idPublicacion = idPublicacion;
        this.titulo = titulo;
        this.contenido = contenido;
        this.tipo = tipo;
        this.fotoPublicacion = fotoPublicacion;
    }

    public String getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(String idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFotoPublicacion() {
        return fotoPublicacion;
    }

    public void setFotoPublicacion(String fotoPublicacion) {
        this.fotoPublicacion = fotoPublicacion;
    }
}
