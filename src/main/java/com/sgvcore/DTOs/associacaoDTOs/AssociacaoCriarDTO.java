package com.sgvcore.DTOs.associacaoDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociacaoCriarDTO {


    @NotBlank(message = "designacao da associacao nao pode ser nula")
    private String designacao;
    @NotBlank(message = "Email nao pode ser nulo")
    @Email
    private String emailAssociacao;
    @NotBlank(message = "Contacto nao pode ser nulo")
    @Size(min = 9, max = 9)
    private String msisdn;
    @NotBlank(message = "Numero de licenca nao pode ser nulo")
    private String numeroLicenca;
    @NotNull(message = "Data de validade do documento nao pode ser nula")
    private Date dataValidade;
    @NotNull(message = "tipo de licenca nao pode ser nula")
    private Long tipoLicenca;
    private List<String> rotas = new ArrayList<>();


}
