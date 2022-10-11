package com.example.violencia.Modelo;

public class MisDenuncias {

    String autoIncrement,nombresde,declaracionde,estadode,idUsuarioResponsablede;

    public MisDenuncias() {
    }


    public MisDenuncias(String autoIncrement, String nombresde, String declaracionde, String estadode, String idUsuarioResponsablede) {
        this.autoIncrement = autoIncrement;
        this.nombresde = nombresde;
        this.declaracionde = declaracionde;
        this.estadode = estadode;
        this.idUsuarioResponsablede = idUsuarioResponsablede;
    }

    public String getAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(String autoIncrement) {
        this.autoIncrement = autoIncrement;
    }


    public String getNombresde() {
        return nombresde;
    }

    public void setNombresde(String nombresde) {
        this.nombresde = nombresde;
    }

    public String getDeclaracionde() {
        return declaracionde;
    }

    public void setDeclaracionde(String declaracionde) {
        this.declaracionde = declaracionde;
    }

    public String getEstadode() {
        return estadode;
    }

    public void setEstadode(String estadode) {
        this.estadode = estadode;
    }

    public String getIdUsuarioResponsablede() {
        return idUsuarioResponsablede;
    }

    public void setIdUsuarioResponsablede(String idUsuarioResponsablede) {
        this.idUsuarioResponsablede = idUsuarioResponsablede;
    }


    public String DenuncianteInfo() {
        return "MisDenuncias{" +
                "Denunciante='" + nombresde + '\'' +
                ", Declaracion='" + declaracionde + '\'' +
                ", Estado='" + estadode + '\'' +
                ", Responsable='" + idUsuarioResponsablede + '\'' +
                '}';
    }
}
