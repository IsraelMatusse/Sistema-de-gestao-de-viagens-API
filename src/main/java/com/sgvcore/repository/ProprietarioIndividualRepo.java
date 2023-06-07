package com.sgvcore.repository;

import com.sgvcore.Model.ProprietarioIndividual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProprietarioIndividualRepo extends JpaRepository<ProprietarioIndividual, Long> {

}
