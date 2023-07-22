package com.sgvcore.DTOs.tipoLicencaDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoLicencaCriarDTO {

    private String designacao;
    private String abreviatura;
}
