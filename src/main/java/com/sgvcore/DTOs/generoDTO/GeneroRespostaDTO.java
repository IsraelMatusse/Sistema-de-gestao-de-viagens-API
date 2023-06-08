package com.sgvcore.DTOs.generoDTO;

import com.sgvcore.Model.Genero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneroRespostaDTO {
    private Long id;
    private String designacao;
    private char gen;

    public GeneroRespostaDTO(Genero genero){
        this.designacao=genero.getDesignacao();
        this.gen=genero.getGen();
        this.id=genero.getId();
    }
}
