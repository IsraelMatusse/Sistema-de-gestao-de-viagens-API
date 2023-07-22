package com.sgvcore.DTOs.usuarioDTOs;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UsuarioCriarDTO {
    private String email;
    @NotNull(message = "O nome de usúario não pode ser nulo!")
    @Email(message = "O email inserido é inválido!")
    private String username;
    @NotNull(message = "A senha não pode ser nula!")
    @Size(message = "A password não pode ter menos de 4 digitos!", min = 4)
    private String password;
}
