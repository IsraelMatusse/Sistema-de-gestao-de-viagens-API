package com.sgvcore.repository;

import com.sgvcore.Model.TipoDocumentoIdentificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoDocumentoRepo extends JpaRepository<TipoDocumentoIdentificacao, Long> {

    Optional<TipoDocumentoIdentificacao> findByDesignacao(String designacao);

    Optional<TipoDocumentoIdentificacao> findByCodigo(String codigo);

    boolean existsById(Long id);
}
