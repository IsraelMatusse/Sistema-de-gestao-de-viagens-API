package com.sgvcore.repository;

import com.sgvcore.Model.Terminal;
import com.sgvcore.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.config.web.servlet.oauth2.resourceserver.OpaqueTokenDsl;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TerminalRepo extends JpaRepository<Terminal, Long> {

    Optional<Terminal> findByCodigo(String codigo);
    boolean existsByDesignacao(String designacao);
    Optional<Terminal>findByUsuario(Usuario usuario);

}

