package com.sgvcore.repository;

import com.sgvcore.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByCreatedAt(Date date);

    List<Usuario> findByCreatedAtBetween(Date startCreatedAt, Date endCreatedAt);

    // Numbers use LessThan and GreaterThan
    List<Usuario> findByCreatedAtLessThanEqual(Date createdAt);

    List<Usuario> findByCreatedAtBefore(Date createdAt);

    List<Usuario> findByCreatedAtAfter(Date createdAt);

    void deleteByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
