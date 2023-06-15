package com.sgvcore.Model;

import com.sgvcore.DTOs.cargaCriarDTO.CargaCriarDTO;
import com.sgvcore.utils.GeneratePin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carga {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String designacao;
    @Column(nullable=false)
    private Long peso;
    @Column
    private String codigoCarga;

    public Carga(CargaCriarDTO dto) throws NoSuchAlgorithmException {
        this.designacao=dto.getDesignacao();
        this.peso=dto.getPeso();
        this.codigoCarga= GeneratePin.generateStringPin();
    }
}
