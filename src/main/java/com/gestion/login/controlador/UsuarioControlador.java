package com.gestion.login.controlador;

import com.gestion.login.entidad.Usuario;
import com.gestion.login.entidad.UsuarioDto;
import com.gestion.login.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("http://localhost:3000")
public class UsuarioControlador {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        UsuarioDto nuevo = usuarioService.registrar(usuario.getNombre(),usuario.getCorreo(), usuario.getContrasena());
        if (nuevo != null) {
            return ResponseEntity.ok(nuevo);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDto> login(@RequestBody Usuario usuario) {
        UsuarioDto usuarioDto = usuarioService.login(usuario.getNombre(), usuario.getContrasena());

        if (usuarioDto != null) {
            return ResponseEntity.ok(usuarioDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}

