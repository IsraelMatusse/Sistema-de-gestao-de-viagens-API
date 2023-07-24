package com.sgvcore.repository;

import com.sgvcore.Model.Associacao;
import com.sgvcore.Model.AssociacaoRota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssociacaoRotaRepo extends JpaRepository<AssociacaoRota, Long> {

    Optional<AssociacaoRota> findByIdAssociacaoAndIdRotaCodigo(Associacao associacao, String codigo);

    List<AssociacaoRota> findByIdAssociacao(Associacao associacao);
}
