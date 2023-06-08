package com.sgvcore.Model;

import com.sgvcore.DTOs.generoDTO.GeneroCriarDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Genero extends AccoesDoSistema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String designacao;
    @Column
    private char gen;

    public Genero(GeneroCriarDTO dto){
        this.gen=dto.getGen();
        this.designacao=dto.getDesignacao();
    }
}
