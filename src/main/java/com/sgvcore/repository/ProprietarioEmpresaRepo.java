package com.sgvcore.repository;

import com.sgvcore.Model.ProprietarioEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProprietarioEmpresaRepo extends JpaRepository<ProprietarioEmpresa, Long> {
}
