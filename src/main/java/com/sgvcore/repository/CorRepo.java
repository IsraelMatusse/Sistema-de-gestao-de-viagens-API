package com.sgvcore.repository;

import com.sgvcore.Model.Cor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorRepo extends JpaRepository<Cor,Long> {

}
