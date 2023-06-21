package com.sgvcore.DTOs.viagemDTO;

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
}
