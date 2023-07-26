package com.sgvcore.repository;

import com.sgvcore.Model.Viagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ViagemRepo extends JpaRepository<Viagem, Long> {

    Optional<Viagem> findByCodigo(String codigo);

    List<Viagem> findBySaida(Date saida);

    Page<Viagem> findAllBySaida(Date saida, Pageable pageable);

}
