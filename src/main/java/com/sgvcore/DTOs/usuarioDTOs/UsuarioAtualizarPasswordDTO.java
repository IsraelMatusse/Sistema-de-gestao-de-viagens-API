package com.sgvcore.DTOs.usuarioDTOs;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UsuarioAtualizarPasswordDTO {
    @NotNull(message = "Password cant be null")
    @Size(message = "Password must have at least 5 letters!", min = 5)
    private String password;
}
