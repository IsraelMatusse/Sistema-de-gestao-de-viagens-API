package com.sgvcore.DTOs.corDTOs;

import com.sgvcore.Model.Cor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorRespostaDTO {
    private Long id;
    private String designacao;

    public CorRespostaDTO(Cor cor){
        this.designacao=cor.getDesignacao();
        this.id=cor.getId();
    }
}
