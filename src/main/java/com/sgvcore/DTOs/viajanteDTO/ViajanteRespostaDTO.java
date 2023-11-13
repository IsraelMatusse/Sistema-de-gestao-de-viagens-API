package com.sgvcore.DTOs.viajanteDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgvcore.Model.Viajante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViajanteRespostaDTO {

    private Long id;
    private String nome;
    private String apelido;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dataNasicmento;
    private String email;

    public ViajanteRespostaDTO(Viajante viajante){
        this.dataNasicmento=viajante.getDataNascimento();
        this.apelido= viajante.getApelido();
        this.email= viajante.getEmail();
        this.nome=viajante.getNome();
        this.id=viajante.getId();
    }
}
