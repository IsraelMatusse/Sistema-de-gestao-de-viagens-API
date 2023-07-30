package com.sgvcore.DTOs.cargaCriarDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargaCriarDTO {

    @NotNull(message = "A designacao da carga nao pode ser nula")
    private String designacao;
    @NotNull(message = "O peso da carga nao pode ser nula")
    private Long peso;
}
