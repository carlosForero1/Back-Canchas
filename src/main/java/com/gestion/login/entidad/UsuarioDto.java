package com.gestion.login.entidad;


public class UsuarioDto {

    private String nombre;
    private int tipoUsuario;

    public UsuarioDto() {
    }

    public UsuarioDto(String nombre, String contrasena, int tipoUsuario) {
        this.nombre = nombre;
        this.tipoUsuario = tipoUsuario;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return "UsuarioDto{" +
                "nombre='" + nombre + '\'' +
                ", tipoUsuario=" + tipoUsuario +
                '}';
    }
}

