package com.sgvcore.repository;

import com.sgvcore.Model.TipoDocumentoIdentificacao;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDocumentoRepo extends JpaRepository<TipoDocumentoIdentificacao, Long> {

    TipoDocumentoIdentificacao findByDesignacao(String designacao);
    TipoDocumentoIdentificacao findByCodigo(String codigo);
}
