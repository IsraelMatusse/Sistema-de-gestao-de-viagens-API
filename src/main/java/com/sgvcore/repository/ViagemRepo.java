package com.sgvcore.repository;

import com.sgvcore.Model.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViagemRepo extends JpaRepository<Viagem, Long> {

}
