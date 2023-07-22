package com.sgvcore.repository;

import com.sgvcore.Model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepo extends JpaRepository<Marca, Long> {

    Marca findByCodigo(String codigo);
}
