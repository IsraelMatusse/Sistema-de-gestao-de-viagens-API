package com.sgvcore.repository;

import com.sgvcore.Model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarcaRepo extends JpaRepository<Marca, Long> {
    Optional<Marca> findByCodigo(String codigo);
}
