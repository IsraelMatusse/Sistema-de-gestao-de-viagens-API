package com.sgvcore.repository;

import com.sgvcore.Model.DocumentoIdentifiacacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoIdentificacaoRepo extends JpaRepository<DocumentoIdentifiacacao, Long> {
}
