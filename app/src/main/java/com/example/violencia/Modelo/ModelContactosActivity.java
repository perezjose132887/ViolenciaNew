package com.example.violencia.Modelo;

public class ModelContactosActivity {
    private Integer id;
    private String contacto;
    private String telefono;
    private Integer usuariobd;

    public ModelContactosActivity(){

    }

    public ModelContactosActivity(Integer id, String contacto, String telefono,Integer usuariobd) {
        this.id = id;
        this.contacto = contacto;
        this.telefono = telefono;
        this.usuariobd=usuariobd;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getUsuariobd() {
        return usuariobd;
    }

    public void setUsuariobd(Integer usuariobd) {
        this.usuariobd = usuariobd;
    }

    @Override
    public String toString() {
        return " "+contacto+" - " + telefono;
    }
}
