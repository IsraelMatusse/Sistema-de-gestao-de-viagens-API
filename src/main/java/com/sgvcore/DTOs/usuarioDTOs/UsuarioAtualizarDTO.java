package com.sgvcore.DTOs.usuarioDTOs;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UsuarioAtualizarDTO {
    @NotNull(message = "id cant be null")
    private Long id;
    @Email(message = "Invalid email!")
    @NotNull(message = "Email cant be null!")
    private String email;
    @NotNull(message = "Username cant be null!")
    @Size(min = 5, message = "Username must have at least 5 letters!")
    private String username;
}
