package com.sgvcore.DTOs.rotaDTO;

import com.sgvcore.Model.Rota;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RotaRespostaDTO {

    private Long id;
    private String nomerota;
    private Long distancia;
    private Long preco;

    public RotaRespostaDTO(Rota rota) {
        this.distancia = rota.getDistancia();
        this.id = rota.getId();
        this.nomerota = rota.getNomeRota();
        this.preco = rota.getPreco();
    }


}
