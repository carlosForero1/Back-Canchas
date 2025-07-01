package com.gestion.reservas.entidad;

import com.gestion.login.entidad.Usuario; // Importa tu entidad Usuario
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String numeroContacto;
    private Date horarioReserva;
    private int cantidadPersonas;

    @OneToOne
    @JoinColumn(name = "cancha_id", nullable = true)
    private Cancha cancha;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = true)
    private Usuario usuario;

    public Reserva() {
    }

    public Reserva(Long id, String nombre, String numeroContacto, Date horarioReserva,
                   int cantidadPersonas, Cancha cancha, Usuario usuario) {
        this.id = id;
        this.nombre = nombre;
        this.numeroContacto = numeroContacto;
        this.horarioReserva = horarioReserva;
        this.cantidadPersonas = cantidadPersonas;
        this.cancha = cancha;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", numeroContacto='" + numeroContacto + '\'' +
                ", horarioReserva=" + horarioReserva +
                ", cantidadPersonas=" + cantidadPersonas +
                ", cancha=" + (cancha != null ? cancha.getNombre() : null) +
                ", usuario=" + (usuario != null ? usuario.getNombre() : null) +
                '}';
    }
}
