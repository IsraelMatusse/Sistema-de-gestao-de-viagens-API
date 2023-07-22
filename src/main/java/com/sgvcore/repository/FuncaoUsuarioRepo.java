package com.sgvcore.repository;

import com.sgvcore.Model.FuncaoDoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncaoUsuarioRepo extends JpaRepository <FuncaoDoUsuario, Long> {
    Optional<FuncaoDoUsuario> findByName(String name);
}
