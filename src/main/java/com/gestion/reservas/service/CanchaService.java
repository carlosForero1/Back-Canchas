package com.gestion.reservas.service;

import com.gestion.reservas.entidad.Cancha;
import com.gestion.reservas.repositorio.CanchaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class CanchaService {

    @Autowired
    private CanchaRepositorio repositorio;


    public List<Cancha> todasLasCanchas() {
        List<Cancha> result = repositorio.findAll();
        return result;
    }


    public void crearCancha(Cancha cancha) {
        try {
            repositorio.save(cancha);
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void eliminarCancha(Long id) {
        try {
            repositorio.deleteById(id);
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public boolean actualizarCancha(Long id, Cancha cancha) {
        try {
            Optional<Cancha> canchaModificada = repositorio.findById(id).map(cancha1 -> {
                cancha1.setNombre(cancha.getNombre());
                cancha1.setCapacidad(cancha.getCapacidad());
                cancha1.setPrecio(cancha.getPrecio());
                cancha1.setImagen(cancha.getImagen());
                cancha1.setDescripcion(cancha.getDescripcion());
                return cancha1;
            });

            if (canchaModificada.isPresent()) {
                repositorio.save(canchaModificada.get());
                return true;
            }

            return false;

        } catch (Exception e) {
            return false;
        }
    }


}
