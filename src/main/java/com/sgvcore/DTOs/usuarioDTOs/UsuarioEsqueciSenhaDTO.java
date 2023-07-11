package com.sgvcore.DTOs.usuarioDTOs;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UsuarioEsqueciSenhaDTO {
    @NotNull(message = "A senha não pode ser nula!")
    @Size(message = "A senha tem !", min = 5)
    private String username;
}
