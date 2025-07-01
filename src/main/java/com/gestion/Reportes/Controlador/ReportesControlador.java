package com.gestion.Reportes.Controlador;

import com.gestion.Reportes.Entidad.Alerta;
import com.gestion.Reportes.Repositorio.ReporteRepositorio;
import com.gestion.Reportes.servicio.ReporteServicio;
import com.gestion.login.entidad.Usuario;
import com.gestion.reservas.entidad.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "http://localhost:3000")
public class ReportesControlador {
    @Autowired
    private ReporteServicio reporteService;

    @Autowired
    private ReporteRepositorio alertaRepositorio;

    @GetMapping("/usuarios")
    public List<Usuario> usuariosPorTipo() {
        return reporteService.obtenerUsuarios();
    }

    @GetMapping("/reservas")
    public List<Reserva> todasReservas() {
        return reporteService.obtenerReservas();
    }

    @GetMapping("/uso-canchas")
    public List<Map<String, Object>> usoDeCanchas() {
        return reporteService.usoDeCanchas();
    }

    @GetMapping("/alertas")
    public List<Alerta> getTodasAlertas() {
        return alertaRepositorio.findAll();
    }

    @PutMapping("/alerta/{id}/estado")
    public Alerta cambiarEstado(@PathVariable Long id, @RequestBody Map<String, Boolean> body) {
        Alerta alerta = alertaRepositorio.findById(id).orElseThrow();
        alerta.setActiva(body.get("activa"));
        return alertaRepositorio.save(alerta);
    }

    @PostMapping("/alerta")
    public Alerta crearAlerta(@RequestBody Alerta alerta) {
        alerta.setActiva(true);
        return alertaRepositorio.save(alerta);
    }

    @GetMapping("/alerta")
    public Alerta getAlertaActiva() {
        return alertaRepositorio.findAll()
                .stream()
                .filter(Alerta::isActiva)
                .findFirst()
                .orElse(null);
    }

}
