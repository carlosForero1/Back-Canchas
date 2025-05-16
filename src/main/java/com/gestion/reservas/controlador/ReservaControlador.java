package com.gestion.reservas.controlador;

import com.gestion.reservas.entidad.Reserva;
import com.gestion.reservas.service.ReservaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/reserva")
public class ReservaControlador {
    @Autowired
    private ReservaServices services;

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

}
