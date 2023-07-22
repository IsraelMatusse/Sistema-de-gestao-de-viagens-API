package com.sgvcore.repository;

import com.sgvcore.Model.DocumentoIdentifiacacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentoIdentificacaoRepo extends JpaRepository<DocumentoIdentifiacacao, Long> {

    Optional<DocumentoIdentifiacacao> findByNumeroDocumento(String numeroDocumento);

    Boolean existsByNumeroDocumento(String numeroDocumento);
}
