package com.sgvcore.repository;

import com.sgvcore.Model.ProvinciaDistrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaDistritoRepo extends JpaRepository<ProvinciaDistrito, Long> {

}
