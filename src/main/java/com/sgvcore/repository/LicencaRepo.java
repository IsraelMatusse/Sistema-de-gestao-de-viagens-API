package com.sgvcore.repository;

import com.sgvcore.Model.Licenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LicencaRepo extends JpaRepository<Licenca, Long> {
    Optional<Licenca> findByNumeroLicenca(String numeroLicenca);

    Boolean existsByNumeroLicenca(String numeroLicenca);
}
