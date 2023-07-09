package com.sgvcore.DTOs.modeloDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModeloCriarDTO {


    @NotBlank
    private String designacao;
}
