package com.sgvcore.Model;

import com.sgvcore.DTOs.rotaDTO.RotaCriarDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rota extends  AccoesDoSistema{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String nomerota;
    @Column(nullable=false)
    private Long distancia;
    @Column(nullable=false)
    private Long preco;

    public Rota(RotaCriarDTO rotaCriarDTO){
        this.distancia=rotaCriarDTO.getDistancia();
        this.nomerota=rotaCriarDTO.getNomeRota();
        this.preco=rotaCriarDTO.getPreco();
    }
}
