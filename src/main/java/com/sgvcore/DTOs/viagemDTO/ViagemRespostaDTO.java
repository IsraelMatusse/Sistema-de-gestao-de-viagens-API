package com.sgvcore.DTOs.viagemDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgvcore.Model.Viagem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViagemRespostaDTO {

    private String destinoViagem;
    private Date saida;
    @JsonFormat(pattern = "dd-MM-yy H")
    private Date prevChegada;
    private String codigoViagem;

    public ViagemRespostaDTO(Viagem viagem) {
        this.codigoViagem = viagem.getCodigo();
        this.destinoViagem = viagem.getDestinoViagem();
        this.prevChegada = viagem.getPrevChegada();
        this.saida = viagem.getSaida();
    }
}
