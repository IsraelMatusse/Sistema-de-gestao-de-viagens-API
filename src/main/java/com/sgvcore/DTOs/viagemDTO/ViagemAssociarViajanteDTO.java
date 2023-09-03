package com.sgvcore.DTOs.viagemDTO;

import com.sgvcore.validators.constraints.DateValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViagemAssociarViajanteDTO {


    @NotNull(message = "O nome do viajante nao pode ser nulo")
    private String nome;
    @NotNull(message = "O apelido do viajante nao pode ser nulo")
    private String apelido;
    @NotNull(message = "A data de nascimento nao pode ser nula")
    private Date dataNascimento;
    @NotNull(message = "O bairro nao pode ser nula")
    private String bairro;
    @NotNull(message = "O email nao pode ser nulo")
    @Email
    private String email;
    @NotBlank(message = "Viagem nao pode ser nula")
    private String codigoViagem;
    @NotNull(message = "A provincia nao pode ser nula")
    private String codigoProvincia;
    @NotNull(message = "O distrito nao pode ser nulo")
    private String codigoDistrito;
    @NotNull(message = "O numero do documento nao pode ser nulo")
    @Size(min = 9, max = 9)
    private String numeroDocumento;
    @NotNull(message = "O genero nao pode ser nulo")
    private Long idGenero;
    @NotNull(message = "O contacto nao pode ser nulo")
    @Size(min = 9, max = 9)
    private String msisdn;
    @NotNull(message = "O tipo de documento nao pode ser nulo")
    private Long idTipoDocumento;
    @NotNull(message = "A data de validade do documento nao pode ser nula")
    @DateValidation
    private Date dataValidade;
    @NotNull(message = "A carga nao pode ser nula")
    private String designacao;
    @NotNull(message = "O peso da craga nao pode ser nula")
    private Long peso;
}
