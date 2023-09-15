package com.sgvcore.DTOs.viagemDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViagemCriarDTO {

    @NotBlank(message = "O destino da viagem nao pode ser nula")
    private String destinoViagem;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "A previsao de chaga nao pode ser nula")
    private Date prevChegada;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date saida;
    @NotNull(message = "A rota nao pde ser nula")
    private Long idRota;
    @NotNull(message = "A viatura nao pode ser nula`")
    private String codigoViatura;
    @NotNull(message = "O motorista nao pode ser nulo")
    private String codigoMotorista;

}
