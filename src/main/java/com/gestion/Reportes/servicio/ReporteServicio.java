package com.gestion.Reportes.servicio;

import com.gestion.Reportes.Entidad.Alerta;
import com.gestion.Reportes.Repositorio.ReporteRepositorio;
import com.gestion.login.entidad.Usuario;
import com.gestion.login.repositorio.UsuarioRepositorio;
import com.gestion.reservas.entidad.Reserva;
import com.gestion.reservas.repositorio.CanchaRepositorio;
import com.gestion.reservas.repositorio.ReservaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReporteServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Autowired
    private ReservaRepositorio reservaRepo;

    @Autowired
    private CanchaRepositorio canchaRepo;

    @Autowired
    private ReporteRepositorio reporteRepo;

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepo.findAll();
    }

    public List<Reserva> obtenerReservas() {
        return reservaRepo.findAll();
    }

    public List<Map<String, Object>> usoDeCanchas() {
        List<Map<String, Object>> resultado = new ArrayList<>();
        canchaRepo.findAll().forEach(cancha -> {
            Map<String, Object> datos = new HashMap<>();
            datos.put("nombre", cancha.getNombre());
            datos.put("vecesReservada", cancha.getReservadaCuenta());
            resultado.add(datos);
        });
        return resultado;
    }




    public Alerta guardarAlerta(String mensaje) {
        Alerta nuevaAlerta = new Alerta();
        nuevaAlerta.setMensaje(mensaje);
        nuevaAlerta.setActiva(true);
        return reporteRepo.save(nuevaAlerta);
    }

}
