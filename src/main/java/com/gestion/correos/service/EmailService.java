package com.gestion.correos.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCorreo(String destinatario, String asunto, String rutaImagen,String textoHtml) throws MessagingException {
        MimeMessage mensaje = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);
        helper.setTo(destinatario);
        helper.setSubject(asunto);
        helper.setText(textoHtml, true);

        ClassPathResource imagen = new ClassPathResource(rutaImagen);
        helper.addInline("imagenMediosPago", imagen);

        mailSender.send(mensaje);
    }

    public void enviarCorreoSinImagen(String destinatario, String asunto, String textoHtml) throws MessagingException {
        MimeMessage mensaje = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);
        helper.setTo(destinatario);
        helper.setSubject(asunto);
        helper.setText(textoHtml, true);


        mailSender.send(mensaje);
    }


}
