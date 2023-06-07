package com.sgvcore.repository;

import com.sgvcore.Model.TipoViactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoViacturaRepo extends JpaRepository<TipoViactura, Long> {
}
