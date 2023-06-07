package com.sgvcore.repository;


import com.sgvcore.Model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface GeneroRepo extends JpaRepository<Genero, Long> {
}
