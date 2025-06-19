package com.gestion.reservas.controlador;

import com.gestion.reservas.entidad.Reserva;
import com.gestion.reservas.repositorio.ReservaRepositorio;
import com.gestion.reservas.service.ReservaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/reserva")
public class ReservaControlador {
    @Autowired
    private ReservaServices services;

    @Autowired
    private ReservaRepositorio repositorio;

    @GetMapping
    public List<Reserva> todosLasReservas() {
        try {
            return services.todasLasReservas();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @PutMapping
    public void guardarUnaReserva(@RequestBody Reserva reserva){
        try {
        services.guardar(reserva);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @DeleteMapping("/{id}")
    public void eliminarReserva(@PathVariable Long id){
        services.eliminar(id);
    }

    @GetMapping("/usuario")
    public List<Reserva> obtenerReservas(@RequestParam(required = false) String correo) {
        if (correo != null && !correo.isEmpty()) {
            return repositorio.findByCorreo(correo);
        }
        return repositorio.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> actualizarReserva(@PathVariable Long id, @RequestBody Reserva reservaActualizada) {
        return repositorio.findById(id)
                .map(reserva -> {
                    reserva.setNombre(reservaActualizada.getNombre());
                    reserva.setCorreo(reservaActualizada.getCorreo());
                    reserva.setHorarioReserva(reservaActualizada.getHorarioReserva());
                    reserva.setCantidadPersonas(reservaActualizada.getCantidadPersonas());
                    return ResponseEntity.ok(repositorio.save(reserva));
                }).orElse(ResponseEntity.notFound().build());
    }



}
