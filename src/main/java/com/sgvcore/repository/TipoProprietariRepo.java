package com.sgvcore.repository;

import com.sgvcore.Model.TipoProprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoProprietariRepo extends JpaRepository<TipoProprietario, Long> {

    TipoProprietario findByDesignacao(String designacao);
    TipoProprietario findByCodigo(String codigoTipoProprietario);

}
