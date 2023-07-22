package com.sgvcore.repository;

import com.sgvcore.Model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvinciaRepo extends JpaRepository<Provincia, Long> {

    boolean existsByDesignacao(String value);

    Optional<Provincia> findByDesignacao(String designacao);

    Optional<Provincia> findByCodigo(String codigo);
}
