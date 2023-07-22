package com.sgvcore.repository;

import com.sgvcore.Model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeloRepo extends JpaRepository<Modelo, Long> {

    Modelo findByCodigo(String codigo);
}
