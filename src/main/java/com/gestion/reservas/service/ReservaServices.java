package com.gestion.reservas.service;

import com.gestion.correos.service.EmailService;
import com.gestion.login.entidad.Usuario;
import com.gestion.login.repositorio.UsuarioRepositorio;
import com.gestion.reservas.entidad.Cancha;
import com.gestion.reservas.entidad.Reserva;
import com.gestion.reservas.repositorio.CanchaRepositorio;
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
    @Autowired
    private CanchaRepositorio canchaRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;


    public List<Reserva> todasLasReservas() {
        return repositorio.findAll();
    }

    public Reserva guardar(Reserva reserva) {
        if (reserva == null) {
            return null;
        }

        Usuario usuarioCompleto = null;
        if (reserva.getUsuario() != null && reserva.getUsuario().getId() != null) {
            usuarioCompleto = usuarioRepositorio.findById(reserva.getUsuario().getId()).orElse(null);
        }

        if (usuarioCompleto == null) {
            System.out.println("⚠️ Usuario no encontrado con id: " + (reserva.getUsuario() != null ? reserva.getUsuario().getId() : "null"));
            return null; // o lanzar excepción si prefieres
        }

        // Igual con la cancha
        Cancha canchaCompleta;
        if (reserva.getCancha() != null && reserva.getCancha().getId() != null) {
            canchaCompleta = canchaRepositorio.findById(reserva.getCancha().getId()).orElse(null);
        } else {
            canchaCompleta = null;
        }

        if (canchaCompleta == null) {
            System.out.println("⚠️ Cancha no encontrada con id: " + (reserva.getCancha() != null ? reserva.getCancha().getId() : "null"));
            return null; // o lanzar excepción
        }

        // Reemplazamos en reserva el usuario y cancha completos para tener toda la info
        reserva.setUsuario(usuarioCompleto);
        reserva.setCancha(canchaCompleta);

        // Validar que no exista reserva en el mismo horario para la misma cancha
        List<Reserva> reservas = repositorio.findAll();
        boolean yaExiste = reservas.stream().anyMatch(r ->
                r.getCancha().getId().equals(canchaCompleta.getId()) &&
                        r.getHorarioReserva().equals(reserva.getHorarioReserva())
        );

        if (yaExiste) {
            System.out.println("Ya existe una reserva para esa cancha en ese horario.");
            return null;
        }

        // Construir correo
        String asunto = "Reserva confirmada a nombre de " + reserva.getNombre();
        String mensajeHtml = "<p>Gracias por reservar con nosotros.</p>" +
                "<p>Estos son los métodos de pago disponibles:</p>" +
                "<img src='cid:imagenMediosPago' alt='Medios de Pago' />";

        String ubicacionImagen = "static/metodoPagos.png";

        // Enviar correo al usuario
        try {
            serviceEmail.enviarCorreo(usuarioCompleto.getCorreo(), asunto, ubicacionImagen, mensajeHtml);
        } catch (Exception e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }

        canchaCompleta.setReservadaCuenta(canchaCompleta.getReservadaCuenta() + 1);
        canchaRepositorio.save(canchaCompleta);

        return repositorio.save(reserva);
    }


    public Reserva editar(Reserva reserva) {
        String asunto = "Reserva editada a nombre de " + reserva.getNombre();
        String mensajeHtml = "<p>Gracias por reservar con nosotros.</p>" +
                "<p>Estos son tus datos actualizados:</p>" +
                "<p><strong>Nombre:</strong> " + reserva.getNombre() + "</p>" +
                "<p><strong>Correo:</strong> " +reserva.getUsuario().getCorreo() + "</p>" +
                "<p><strong>Fecha:</strong> " + reserva.getHorarioReserva() + "</p>" +
                "<p><strong>Personas:</strong> " + reserva.getCantidadPersonas() + "</p>";

        String ubicacionImagen = "static/graciasPorConfiar.jpg";

        try {
            serviceEmail.enviarCorreo(reserva.getUsuario().getCorreo(), asunto, ubicacionImagen, mensajeHtml);
        } catch (Exception e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }

        return null;
    }

    public Reserva cancela(Reserva reserva, String motivo) {
        String asunto = "Reserva  cancela a nombre de " + reserva.getNombre();
        String mensajeHtml = motivo+
        "<p>Gracias por su confianza con nosotros.</p>"
                ;
        String ubicacionImagen = "static/graciasPorConfiar.jpg";
            try {
                serviceEmail.enviarCorreo(reserva.getUsuario().getCorreo(), asunto, ubicacionImagen, mensajeHtml);
            } catch (Exception e) {
                System.out.println("Error al enviar el correo: " + e.getMessage());
            }

        return reserva;

    }


    public Reserva buscarPorId(Long id) {
        List<Reserva> reservas = repositorio.findAll();
        if (!reservas.isEmpty()) {
            for (Reserva reserva : reservas) {
                if (reserva.getId().equals(id)) {
                    return reserva;
                }
            }
        }

        return null;

    }

    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }


}
