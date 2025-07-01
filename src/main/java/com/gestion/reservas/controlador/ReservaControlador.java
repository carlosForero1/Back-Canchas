package com.gestion.reservas.controlador;

import com.gestion.reservas.entidad.CancelarRequest;
import com.gestion.reservas.entidad.Reserva;
import com.gestion.reservas.repositorio.ReservaRepositorio;
import com.gestion.reservas.service.ReservaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public void guardarUnaReserva(@RequestBody Reserva reserva) {
        try {
            services.guardar(reserva);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @GetMapping("/usuario")
    public List<Reserva> obtenerReservas(@RequestParam(required = false) String correo) {
        List<Reserva>  ListaResultado = new ArrayList<>();
        if (correo != null && !correo.isEmpty()) {
            List<Reserva> listaDeReservas = repositorio.findAll();
            if (!listaDeReservas.isEmpty()) {
                for (Reserva reserva : listaDeReservas) {
                    if (reserva.getUsuario().getCorreo().equals(correo)) {
                        listaDeReservas.add(reserva);
                    }
                }
            }
        }
        return ListaResultado;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> actualizarReserva(@PathVariable Long id, @RequestBody Reserva reservaActualizada) {
        services.editar(reservaActualizada);
        return repositorio.findById(id)
                .map(reserva -> {
                    reserva.setNombre(reservaActualizada.getNombre());
                    reserva.getUsuario().setCorreo(reservaActualizada.getUsuario().getCorreo());
                    reserva.setHorarioReserva(reservaActualizada.getHorarioReserva());
                    reserva.setCantidadPersonas(reservaActualizada.getCantidadPersonas());
                    return ResponseEntity.ok(repositorio.save(reserva));
                }).orElse(ResponseEntity.notFound().build());

    }


    @PostMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelarReservaConMotivo(@PathVariable Long id, @RequestBody CancelarRequest request) {
        Reserva reserva = services.buscarPorId(id);
        services.cancela(reserva, request.getMotivo());
        services.eliminar(id);
        return ResponseEntity.ok().build();
    }

}

