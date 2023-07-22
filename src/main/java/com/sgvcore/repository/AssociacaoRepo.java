package com.sgvcore.repository;

import com.sgvcore.Model.Associacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociacaoRepo extends JpaRepository<Associacao, Long> {
    Associacao findByCodigo(String codigo);
}
