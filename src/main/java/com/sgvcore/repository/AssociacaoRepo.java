package com.sgvcore.repository;

import com.sgvcore.Model.Associacao;
import com.sgvcore.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociacaoRepo extends JpaRepository<Associacao, Long> {
    Optional<Associacao> findByCodigo(String codigo);

    Optional<Associacao> findByUsuario(Usuario usuario);
}
