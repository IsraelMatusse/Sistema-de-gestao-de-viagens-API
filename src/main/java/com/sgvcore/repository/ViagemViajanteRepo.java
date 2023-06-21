package com.sgvcore.repository;

import com.sgvcore.Model.ViagemViajante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViagemViajanteRepo extends JpaRepository<ViagemViajante, Long> {

}
