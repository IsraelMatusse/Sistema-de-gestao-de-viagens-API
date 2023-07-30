package com.sgvcore.repository;

import com.sgvcore.Model.Rota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RotaRepo extends JpaRepository<Rota, Long> {
    Optional<Rota> findByCodigo(String codigoRota);

    Optional<Rota> findByNomeRota(String nomeRota);

    Boolean existsByNomeRota(String nomeRota);
}
