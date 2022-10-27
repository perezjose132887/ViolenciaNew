package com.example.violencia.Modelo;

import java.io.Serializable;

public class ListaInstituciones implements Serializable {

    String idDepartamentoIns,nombreInstitucionIns,direccionIns,telefonoIns;

    public ListaInstituciones() {
    }

    public ListaInstituciones(String idDepartamentoIns, String nombreInstitucionIns, String direccionIns, String telefonoIns) {
        this.idDepartamentoIns = idDepartamentoIns;
        this.nombreInstitucionIns = nombreInstitucionIns;
        this.direccionIns = direccionIns;
        this.telefonoIns = telefonoIns;
    }


    public String getIdDepartamentoIns() {
        return idDepartamentoIns;
    }

    public void setIdDepartamentoIns(String idDepartamentoIns) {
        this.idDepartamentoIns = idDepartamentoIns;
    }

    public String getNombreInstitucionIns() {
        return nombreInstitucionIns;
    }

    public void setNombreInstitucionIns(String nombreInstitucionIns) {
        this.nombreInstitucionIns = nombreInstitucionIns;
    }

    public String getDireccionIns() {
        return direccionIns;
    }

    public void setDireccionIns(String direccionIns) {
        this.direccionIns = direccionIns;
    }

    public String getTelefonoIns() {
        return telefonoIns;
    }

    public void setTelefonoIns(String telefonoIns) {
        this.telefonoIns = telefonoIns;
    }
}
