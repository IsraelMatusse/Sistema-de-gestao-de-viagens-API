package com.sgvcore.repository;

import com.sgvcore.Model.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoristaRepo extends JpaRepository<Motorista, Long> {

}
