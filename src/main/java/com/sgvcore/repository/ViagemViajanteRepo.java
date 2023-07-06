package com.sgvcore.repository;

import com.sgvcore.Model.Viagem;
import com.sgvcore.Model.ViagemViajante;
import com.sgvcore.Model.Viajante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViagemViajanteRepo extends JpaRepository<ViagemViajante, Long> {

   List< ViagemViajante> findByIdViagem(Viagem viagem);
}
