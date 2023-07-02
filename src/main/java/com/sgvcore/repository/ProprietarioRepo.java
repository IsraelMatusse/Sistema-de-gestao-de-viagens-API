package com.sgvcore.repository;

import com.sgvcore.Model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProprietarioRepo extends JpaRepository<Proprietario, Long> {

    Proprietario findByCodigo(String codigoProprietario);
}
