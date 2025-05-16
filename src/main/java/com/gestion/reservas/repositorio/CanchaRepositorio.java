package com.gestion.reservas.repositorio;

import com.gestion.reservas.entidad.Cancha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CanchaRepositorio extends JpaRepository<Cancha,Long> {
}
