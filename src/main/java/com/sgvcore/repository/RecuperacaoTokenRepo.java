package com.sgvcore.repository;

import com.sgvcore.Model.RecuperacaoToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecuperacaoTokenRepo extends JpaRepository<RecuperacaoToken, Long> {
    RecuperacaoToken findByActivoTrueAndUsuarioUsername(String username);
}