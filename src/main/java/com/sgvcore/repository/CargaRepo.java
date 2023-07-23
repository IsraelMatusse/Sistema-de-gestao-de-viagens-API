package com.sgvcore.repository;

import com.sgvcore.Model.Carga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CargaRepo extends JpaRepository<Carga, Long> {

    Optional<Carga> findByCodigoCarga(String codigoCarga);

    Optional<Carga> findByDesignacao(String designacao);
}
