package com.sgvcore.repository;

import com.sgvcore.Model.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ViagemRepo extends JpaRepository<Viagem, Long> {

    Viagem findByCodigo(String codigo);
    List<Viagem> findBySaida(Date saida);

}
