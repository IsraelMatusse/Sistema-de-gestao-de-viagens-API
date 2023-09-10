package com.sgvcore.DTOs.terminalDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TerminalCriarDTO {

    @NotBlank
    private String designacao;
    @NotBlank
    private String nuit;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String codigoProvincia;
    @NotBlank
    private String codigoDistrito;
    @NotBlank
    private String msisdn;
}
