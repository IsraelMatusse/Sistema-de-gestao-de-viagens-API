package com.sgvcore.repository;

import com.sgvcore.Model.TipoProprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoProprietariRepo extends JpaRepository<TipoProprietario, Long> {

    Optional<TipoProprietario> findByDesignacao(String designacao);

    Optional<TipoProprietario> findByCodigo(String codigoTipoProprietario);

}
