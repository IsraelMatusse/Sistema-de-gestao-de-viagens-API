package com.sgvcore.repository;

import com.sgvcore.Model.Carga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargaRepo extends JpaRepository<Carga, Long> {
}
