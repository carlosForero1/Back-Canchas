package com.gestion.reservas.entidad;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cancha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int capacidad;
    private String precio;
    private String imagen;
    private String descripcion;
    private int reservadaCuenta;

    public Cancha() {
    }

    public Cancha(Long id, String nombre, int capacidad, String precio, String imagen, String descripcion,int reservadaCuenta) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.precio = precio;
        this.imagen = imagen;
        this.descripcion =descripcion;
        this.reservadaCuenta = reservadaCuenta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getReservadaCuenta() {
        return reservadaCuenta;
    }

    public void setReservadaCuenta(int reservadaCuenta) {
        this.reservadaCuenta = reservadaCuenta;
    }

    @Override
    public String toString() {
        return "TipoCancha{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", capacidad='" + capacidad + '\'' +
                ", precio='" + precio + '\'' +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
