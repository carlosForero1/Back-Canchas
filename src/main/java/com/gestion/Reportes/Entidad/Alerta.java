package com.gestion.Reportes.Entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Alerta {
    @Id
    @GeneratedValue
    private Long id;
    private String tipo;
    private String mensaje;
    private boolean activa = true;

    public Alerta() {
    }

    public Alerta(Long id, String tipo, String mensaje, boolean activa) {
        this.id = id;
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.activa = activa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    @Override
    public String toString() {
        return "Alerta{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", activa=" + activa +
                '}';
    }
}
