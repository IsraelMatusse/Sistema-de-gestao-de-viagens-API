package com.sgvcore.repository;

import com.sgvcore.Model.Associacao;
import com.sgvcore.Model.AssociacaoRota;
import com.sgvcore.Model.Rota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociacaoRotaRepo extends JpaRepository<AssociacaoRota, Long> {

    AssociacaoRota findByIdAssociacaoAndIdRotaCodigo(Associacao associacao, String codigo);
    List<AssociacaoRota> findByIdAssociacao(Associacao associacao);
}
