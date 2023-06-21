package com.sgvcore.DTOs.viagemDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String destinoViagem;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    //@NotNull
    private Date saida;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    //@NotNull
    private Date prevChegada;
    //@NotNull
    private Long idRota;
  //  @NotNull
    private String codigoAssociacao;
}
