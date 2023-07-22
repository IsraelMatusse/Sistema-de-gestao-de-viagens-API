package com.sgvcore.repository;

import com.sgvcore.Model.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactoRepo extends JpaRepository<Contacto, Long> {


    Optional<Contacto> findByMsisdn(String msisdn);

    Boolean existsByMsisdn(String msisdn);

}
