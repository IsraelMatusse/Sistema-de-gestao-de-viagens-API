package com.sgvcore.repository;

import com.sgvcore.Model.Rota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RotaRepo extends JpaRepository<Rota, Long> {
    Rota findByCodigo (String codigoRota);
}
