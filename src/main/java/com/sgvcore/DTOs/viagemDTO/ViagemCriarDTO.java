package com.sgvcore.DTOs.viagemDTO;

import com.sgvcore.Model.Associacao;
import com.sgvcore.Model.Rota;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViagemCriarDTO {

    @NotNull
    private String destino_viagem;
    @NotNull
    private Date saida;
    @NotNull
    private Date prev_chegada;
    @NotNull
    private Long idRota;
    @NotNull
    private Long idAssociacao;
}
