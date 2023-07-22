package com.sgvcore.DTOs.cargaCriarDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargaCriarDTO {

    @NotNull
    private String designacao;
    @NotNull
    private Long peso;
}
