package com.sgvcore.repository;

import com.sgvcore.Model.Viajante;
import javafx.embed.swing.JFXPanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajanteRepo extends JpaRepository<Viajante, Long> {

}
