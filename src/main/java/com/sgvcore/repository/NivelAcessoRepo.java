package com.sgvcore.repository;

import com.sgvcore.Model.NivelAcesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NivelAcessoRepo extends JpaRepository<NivelAcesso, Long> {

}
