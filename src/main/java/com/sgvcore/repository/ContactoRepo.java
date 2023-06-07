package com.sgvcore.repository;

import com.sgvcore.Model.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepo extends JpaRepository<Contacto, Long> {
}
