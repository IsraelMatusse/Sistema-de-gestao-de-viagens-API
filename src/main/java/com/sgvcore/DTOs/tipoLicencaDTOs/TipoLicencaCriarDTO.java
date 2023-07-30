package com.sgvcore.DTOs.tipoLicencaDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoLicencaCriarDTO {

    @NotNull(message = "A designacao do tipo de licenca nao pode ser nula")
    private String designacao;
    private String abreviatura;
}
