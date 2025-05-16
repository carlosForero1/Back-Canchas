package com.gestion.reservas.entidad;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correo;
    private String numeroContacto;
    private Date horarioReserva;
    private int cantidadPersonas;

    @OneToOne
    @JoinColumn(name = "cancha_id", nullable = true)
    private Cancha cancha;




    public Reserva() {
    }

    public Reserva(Long id, String nombre, String correo,
                   String numeroContacto, Date horarioReserva,
                   int cantidadPersonas, Cancha cancha) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.numeroContacto = numeroContacto;
        this.horarioReserva = horarioReserva;
        this.cantidadPersonas = cantidadPersonas;
        this.cancha = cancha;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    public Date getHorarioReserva() {
        return horarioReserva;
    }

    public void setHorarioReserva(Date horarioReserva) {
        this.horarioReserva = horarioReserva;
    }

    public Cancha getCancha() {
        return cancha;
    }

    public void setCancha(Cancha cancha) {
        this.cancha = cancha;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    @Override
    public String toString() {
        return "reserva{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", numeroContacto='" + numeroContacto + '\'' +
                ", horarioReserva=" + horarioReserva +
                '}';
    }
}
