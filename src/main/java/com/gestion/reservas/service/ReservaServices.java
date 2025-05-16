package com.gestion.reservas.service;

import com.gestion.correos.service.EmailService;
import com.gestion.reservas.entidad.Reserva;
import com.gestion.reservas.repositorio.ReservaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaServices {
    @Autowired
    private ReservaRepositorio repositorio;
    @Autowired
    private EmailService serviceEmail;


    public List<Reserva> todasLasReservas(){
       return repositorio.findAll();
    }

    public Reserva guardar(Reserva reserva) {
        String asunto = "Reserva confirmada a nombre de " + reserva.getNombre();
        String mensajeHtml = "<p>Gracias por reservar con nosotros.</p>" +
                "<p>Estos son los métodos de pago disponibles:</p>" +
                "<img src='cid:imagenMediosPago' alt='Medios de Pago' />";

        String UbicacionImagen = "static/metodoPagos.png";

        if (reserva != null){
            try {
                serviceEmail.enviarCorreo(reserva.getCorreo(),asunto, UbicacionImagen, mensajeHtml);
            }catch (Exception e){
                System.out.println(e);
            }

            return repositorio.save(reserva);
        }
        return null;

    }

    public Optional<Reserva> buscarPorId(Long id) {
        return repositorio.findById(id);
    }

    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }

}
