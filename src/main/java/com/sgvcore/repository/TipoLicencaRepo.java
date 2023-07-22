package com.sgvcore.repository;

import com.sgvcore.Model.TipopLicenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoLicencaRepo extends JpaRepository<TipopLicenca, Long> {
    Optional<TipopLicenca> findById(Long id);
}
