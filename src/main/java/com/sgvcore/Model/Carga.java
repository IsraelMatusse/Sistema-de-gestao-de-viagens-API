package com.sgvcore.Model;

import com.sgvcore.DTOs.cargaCriarDTO.CargaCriarDTO;
import com.sgvcore.DTOs.viagemDTO.ViagemAssociarViajanteDTO;
import com.sgvcore.DTOs.viajanteDTO.ViajanteCriarDTO;
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
public class Carga extends AccoesDoSistema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String designacao;
    @Column(nullable = false)
    private Long peso;
    @Column
    private String codigoCarga;

    public Carga(CargaCriarDTO dto) throws NoSuchAlgorithmException {
        this.designacao = dto.getDesignacao();
        this.peso = dto.getPeso();
        this.codigoCarga = GeneratePin.generateStringPin();
    }

    public Carga(ViajanteCriarDTO dto) throws NoSuchAlgorithmException {
        this.designacao = dto.getDesignacao();
        this.peso = dto.getPeso();
        this.codigoCarga = GeneratePin.generateStringPin();
    }

    public Carga(ViagemAssociarViajanteDTO dto) {
        this.designacao = dto.getDesignacao();
        this.peso = dto.getPeso();
    }
}
