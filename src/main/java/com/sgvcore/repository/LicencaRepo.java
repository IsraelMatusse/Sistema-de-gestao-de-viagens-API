package com.sgvcore.repository;

import com.sgvcore.Model.Licenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicencaRepo extends JpaRepository <Licenca, Long> {
    Licenca findByNumeroLicenca(String numeroLicenca);
}
