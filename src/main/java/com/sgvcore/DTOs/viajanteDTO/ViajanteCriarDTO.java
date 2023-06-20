package com.sgvcore.DTOs.viajanteDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ViajanteCriarDTO {

    @NotNull
    private String nome;
    @NotNull
    private String apelido;
    @NotNull
    private Date dataNascimento;
    @NotNull
    private String bairro;
    @NotNull
    private String email;
    @NotNull
    private String codigoProvincia;
    @NotNull
    private String codigoDistrito;
    @NotNull
    private String NumeroDocumento;

    @NotNull
    private Long idGenero;
    @NotNull
    private String msisdn;
    @NotNull
    private Long idTipoDocumento;
    @NotNull
    private Date dataValidade;
    @NotNull
    private String numeroDocumento;
    @NotNull
    private String designacao;
    @NotNull
    private Long peso;

}
