package com.sgvcore.repository;

import com.sgvcore.Model.MotoristaViactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoristViacturaRepo extends JpaRepository<MotoristaViactura, Long> {

    MotoristaViactura findByCodigo(String codigo);

}
