package com.example.violencia.Modelo;

public class ListaComentarios {

    String idComentarioco,idUsuarioco,idPublicacionco,correoco,comentarioco,fechaRegistro;

    public ListaComentarios() {
    }

    public ListaComentarios(String idComentarioco, String idUsuarioco, String idPublicacionco, String correoco, String comentarioco, String fechaRegistro) {
        this.idComentarioco = idComentarioco;
        this.idUsuarioco = idUsuarioco;
        this.idPublicacionco = idPublicacionco;
        this.correoco = correoco;
        this.comentarioco = comentarioco;
        this.fechaRegistro = fechaRegistro;
    }


    public String getIdComentarioco() {
        return idComentarioco;
    }

    public void setIdComentarioco(String idComentarioco) {
        this.idComentarioco = idComentarioco;
    }

    public String getIdUsuarioco() {
        return idUsuarioco;
    }

    public void setIdUsuarioco(String idUsuarioco) {
        this.idUsuarioco = idUsuarioco;
    }

    public String getIdPublicacionco() {
        return idPublicacionco;
    }

    public void setIdPublicacionco(String idPublicacionco) {
        this.idPublicacionco = idPublicacionco;
    }

    public String getCorreoco() {
        return correoco;
    }

    public void setCorreoco(String correoco) {
        this.correoco = correoco;
    }

    public String getComentarioco() {
        return comentarioco;
    }

    public void setComentarioco(String comentarioco) {
        this.comentarioco = comentarioco;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
