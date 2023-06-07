package com.sgvcore.repository;

import com.sgvcore.Model.Viactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViacturaRepo extends JpaRepository<Viactura, Long> {
}
