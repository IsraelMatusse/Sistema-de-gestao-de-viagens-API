package com.sgvcore.repository;

import com.sgvcore.Model.Associacao;
import com.sgvcore.Model.Rota;
import com.sgvcore.Model.Viactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViacturaRepo extends JpaRepository<Viactura, Long> {
    Viactura findByCodigo(String codigoViactura);
    List<Viactura> findByIdAssociacao(Associacao associacao);
    Viactura findByIdAssociacaoAndCodigo(Associacao associacao, String codigoViatura);
    List<Viactura> findByIdRota(Rota rota);
}
