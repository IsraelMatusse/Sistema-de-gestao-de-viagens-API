package com.sgvcore.repository;

import com.sgvcore.Model.MotoristaViactura;
import com.sgvcore.Model.Viatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MotoristaViacturaRepo extends JpaRepository<MotoristaViactura, Long> {

    MotoristaViactura findByCodigo(String codigo);

    Optional<MotoristaViactura> findByIdViacturaAndIdMotoristaCodigo(Viatura viactura, String codigoMotorista);

}
