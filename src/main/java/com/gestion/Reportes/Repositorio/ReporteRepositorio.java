package com.gestion.Reportes.Repositorio;

import com.gestion.Reportes.Entidad.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReporteRepositorio extends JpaRepository<Alerta, Long> {
    Optional<Alerta> findFirstByActivaTrue();
}
