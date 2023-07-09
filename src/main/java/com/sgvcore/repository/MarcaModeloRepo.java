package com.sgvcore.repository;

import com.sgvcore.Model.Marca;
import com.sgvcore.Model.MarcaModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaModeloRepo extends JpaRepository<MarcaModelo, Long> {
    MarcaModelo findByCodigo(String codigo);
    List<MarcaModelo> findByMarca(Marca marca);

}
