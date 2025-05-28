package com.gestion.reservas.controlador;

import com.gestion.reservas.entidad.Cancha;
import com.gestion.reservas.service.CanchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cancha")
@CrossOrigin(origins = "http://localhost:3000")
public class CanchaControlador {

    @Autowired
    private CanchaService service;


    @GetMapping
    public List<Cancha> todasLasCanchas() {
        return service.todasLasCanchas();
    }

    @PutMapping("/{id}")
    public void actualizarCancha(@PathVariable Long id, @RequestBody Cancha cancha) {
        try {
            boolean result = service.actualizarCancha(id, cancha);
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    @PostMapping("/crear")
    public String crearCancha(@RequestBody Cancha cancha) {

        if (cancha != null) {
            try {
                service.crearCancha(cancha);
                return "funciono";
            } catch (Exception e) {
                return "no";
            }


        }
        return "Sin id";
    }


    @DeleteMapping("/delete/{id}")
    public String eliminarCancha(@PathVariable Long id) {
        if (id != null) {
            service.eliminarCancha(id);
            return "Eliminacion exitoso";
        } else {
            return "Error consultar con el administrador";
        }
    }
    @GetMapping("/{id}")
    public Cancha obtenerCanchaPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .orElse(null); // O lanza excepción si prefieres manejar errores
    }

}
