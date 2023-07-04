package com.sgvcore.DTOs.viagemDTO;

import com.sgvcore.Model.Viagem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViagemRespostaDTO {

    private String destino_viagem;
    private Date saida;
    private Date prev_chegada;
    private String codigoViagem;

    public ViagemRespostaDTO(Viagem viagem){
        this.codigoViagem=viagem.getCodigo();
        this.destino_viagem=viagem.getDestino_viagem();
        this.prev_chegada=viagem.getPrevChegada();
        this.saida=viagem.getSaida();
    }
}
