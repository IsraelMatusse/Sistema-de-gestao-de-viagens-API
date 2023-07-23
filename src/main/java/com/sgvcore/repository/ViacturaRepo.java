package com.sgvcore.repository;

import com.sgvcore.Model.Associacao;
import com.sgvcore.Model.Rota;
import com.sgvcore.Model.Viatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ViacturaRepo extends JpaRepository<Viatura, Long> {
    Optional<Viatura> findByCodigo(String codigoViactura);

    List<Viatura> findByIdAssociacao(Associacao associacao);

    Optional<Viatura> findByIdAssociacaoAndCodigo(Associacao associacao, String codigoViatura);

    List<Viatura> findByIdRota(Rota rota);

    Boolean existsByMatricula(String matricula);
}
