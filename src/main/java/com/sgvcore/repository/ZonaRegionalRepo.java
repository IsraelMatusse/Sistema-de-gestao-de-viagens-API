package com.sgvcore.repository;

import com.sgvcore.Model.ZonaRegional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaRegionalRepo extends JpaRepository<ZonaRegional, Long> {

}
