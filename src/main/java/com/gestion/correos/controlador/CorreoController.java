package com.gestion.correos.controlador;

import com.gestion.correos.entidad.EmailRequest;
import com.gestion.correos.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/correo")
public class CorreoController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/enviar")
    public String enviarCorreo(@RequestBody EmailRequest request) {
        try {
            emailService.enviarCorreoSinImagen(request.getPara(), request.getAsunto(), request.getMensaje());
            return "Correo enviado con éxito.";
        }catch (Exception e){
            System.out.println(e);
            return "Imprecision en el envio";
        }


    }
}


