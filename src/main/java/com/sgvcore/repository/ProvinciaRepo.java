package com.sgvcore.repository;

import com.sgvcore.Model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepo extends JpaRepository<Provincia, Long> {

    boolean existsByDesignacao(String value);
    Provincia findByDesignacao(String designacao);
    Provincia findByCodigo(String codigo);
}
