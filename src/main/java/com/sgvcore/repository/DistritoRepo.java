package com.sgvcore.repository;

import com.sgvcore.Model.Distrito;
import com.sgvcore.Model.Provincia;
import com.sgvcore.projections.distritoProjections.DistritoProvinciaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistritoRepo extends JpaRepository<Distrito, Long> {
    boolean existsByDesignacao(String value);

    Distrito findByCodigoAndIdProvincia(String codigo, Provincia idProvincia);

    List<Distrito> findByIdProvincia(Provincia id);

    Optional<Distrito> findByIdAndIdProvincia(Long id, Provincia idProvincia);

    @Query(nativeQuery = true, value = "SELECT D.id as id_distrito, D.designacao AS nome_distrito, P.designacao AS nome_provincia FROM distrito D INNER JOIN provincia P\n" +
            "ON D.id_provincia_fk_id = P.id\n" +
            "WHERE D.id = :distritoId")
    DistritoProvinciaProjection findByDistritoId(Long distritoId);
}
