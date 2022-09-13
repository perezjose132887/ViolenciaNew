package com.example.violencia.Modelo;

public class ModelModuloUsuarioActivity {
    private short idUsuario;
    private String nombres;
    private String primerApellido;
    private String segundoApellido;
    private String telefono;
    private String ci;
    private char sexo;
    private String nombreUsuario;
    private String contrasena;
    private String foto;
    private String correo;


    public ModelModuloUsuarioActivity(){

    }

    public ModelModuloUsuarioActivity(short idUsuario, String nombres, String primerApellido, String segundoApellido, String telefono, String ci, char sexo, String nombreUsuario, String contrasena, String foto, String correo) {
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.telefono = telefono;
        this.ci = ci;
        this.sexo = sexo;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.foto = foto;
        this.correo = correo;
    }

    public short getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(short idUsuario) {
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

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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


    @Override
    public String toString() {
        return "ModelModuloUsuarioActivity{" +
                "nombres='" + nombres + '\'' +
                ", primerApellido='" + primerApellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                '}';
    }
}
