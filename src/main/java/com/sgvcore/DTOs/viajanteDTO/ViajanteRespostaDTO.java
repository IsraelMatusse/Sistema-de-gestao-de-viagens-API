package com.sgvcore.DTOs.viajanteDTO;

import com.sgvcore.Model.Viajante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViajanteRespostaDTO {

    private Long id;
    private String nome;
    private String apelido;
    private int anonascimento;
    private String bairro;
    private String email;

    public ViajanteRespostaDTO(Viajante viajante){
        this.anonascimento=viajante.getAnonascimento();
        this.apelido= viajante.getApelido();
        this.bairro=viajante.getBairro();
        this.email= viajante.getEmail();
        this.nome=viajante.getNome();
        this.id=viajante.getId();
    }
}
