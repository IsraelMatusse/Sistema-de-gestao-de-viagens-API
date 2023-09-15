package com.sgvcore.DTOs.motoristDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotoristaCriarDTO {
    @NotBlank
    private String nome;
    @NotBlank
    private String apelido;
    @NotNull
    private Long anonascimento;
    @Email
    @NotBlank
    private String email;


}
