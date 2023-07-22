package com.sgvcore.DTOs.modeloDTOs;

import com.sgvcore.Model.Cor;
import com.sgvcore.Model.Modelo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModeloRespostaDTO {

    private Long id;
    private String designacao;

    public ModeloRespostaDTO(Modelo modelo){
        this.designacao=modelo.getDesignacao();
        this.id=modelo.getId();
    }
}
