package com.sgvcore.DTOs.rotaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class RotaCriarDTO {


    @NotNull
    private Long distancia;
    @NotNull
    private long preco;
    @NotNull
    private String nomerota;
}
