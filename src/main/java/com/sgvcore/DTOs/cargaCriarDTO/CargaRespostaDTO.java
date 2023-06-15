package com.sgvcore.DTOs.cargaCriarDTO;

import com.sgvcore.Model.Carga;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CargaRespostaDTO {

    private Long id;
    private String designacao;
    private Long peso;
    private String codigoCarga;

    public CargaRespostaDTO(Carga carga){
        this.designacao=carga.getDesignacao();
        this.id=carga.getId();
        this.peso=carga.getPeso();
        this.codigoCarga=carga.getCodigoCarga();
    }
}
