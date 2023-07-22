package com.sgvcore.Model;

import com.sgvcore.DTOs.corDTOs.CorCriarDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cor extends AccoesDoSistema{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String codigo;
    @Column
    private String designacao;

    public Cor(CorCriarDTO dto){
        this.designacao=dto.getDesignacao();
    }

}
