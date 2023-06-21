package com.sgvcore.DTOs.viajanteDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "dd/mm/yyyy")
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
    private String numeroDocumento;
    @NotNull
    private Long idGenero;
    @NotNull
    private String msisdn;
    @NotNull
    private Long idTipoDocumento;
    @NotNull
    private Date dataValidade;
    @NotNull
    private String designacao;
    @NotNull
    private Long peso;

}
