package com.gestion.login.service;

import com.gestion.login.entidad.Usuario;
import com.gestion.login.entidad.UsuarioDto;
import com.gestion.login.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepositorio repositorio;

    public UsuarioDto registrar(String nombre, String contrasena) {
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String contrasenaEncriptada = encoder.encode(contrasena);
            Usuario usuario = new Usuario();

            usuario.setNombre(nombre);
            usuario.setContrasena(contrasenaEncriptada);
            usuario.setTipoUsuario(1);
            repositorio.save(usuario);


            UsuarioDto usu = new UsuarioDto();
            usu.setNombre(usuario.getNombre());
            usu.setTipoUsuario(usuario.getTipoUsuario());
            return usu;
        } catch (Exception e) {
            UsuarioDto usu = null;
            return usu;
        }
    }

    public UsuarioDto login(String username, String password) {
        List<Usuario> usuarioLista = buscarPorNombre(username);

        if (!usuarioLista.isEmpty()) {
            Usuario usuario = usuarioLista.get(0);
            boolean passwordOk = new BCryptPasswordEncoder().matches(password, usuario.getContrasena());

            if (passwordOk) {
                UsuarioDto usuarioDto = new UsuarioDto();
                usuarioDto.setNombre(usuario.getNombre());
                usuarioDto.setTipoUsuario(usuario.getTipoUsuario());
                return usuarioDto;
            }
        }

        return null;
    }

    public List<Usuario> buscarPorNombre(String name) {
        List<Usuario> todosLosUsuario = repositorio.findAll();
        for (Usuario usuario : todosLosUsuario) {
            if (usuario.getNombre().equals(name)) {
                return List.of(usuario);
            }
        }
        List<Usuario> vacio = new ArrayList<>();
        return vacio;
    }

}

