package com.sgvcore.repository;

import com.sgvcore.Model.TipopLicenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoLicencaRepo extends JpaRepository<TipopLicenca, Long> {
}
