package com.sgvcore.DTOs.provinciaDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvinciaCriarDTO {

    private String designacao;
    private String codigo;
    private String sigla;
    private Long idZonaRegionalFK;
    private List<Long> distritos;
}
