package com.sgvcore.DTOs.motoristDTOs;

import com.sgvcore.Model.Motorista;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotoristaRespostaDTO {
    private String nome;
    private String apelido;
    private Long anonascimento;
    private String email;

    public MotoristaRespostaDTO(Motorista motorista){
        this.anonascimento=motorista.getAnonascimento();
        this.apelido=motorista.getApelido();
        this.email=motorista.getEmail();
        this.nome=motorista.getNome();
    }
}
