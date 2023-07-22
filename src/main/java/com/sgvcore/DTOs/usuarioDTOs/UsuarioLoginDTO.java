package com.sgvcore.DTOs.usuarioDTOs;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UsuarioLoginDTO {
    @Email(message = "O email inserido não é válido!")
    @NotNull(message = "O email não pode ser nulo!")
    private String username;
    @NotNull(message = "A senha não pode ser nula!")
    @Size(message = "A senha tem !", min = 5)
    private String password;

}
