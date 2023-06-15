package com.sgvcore.DTOs.viajanteDTO;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ViajanteCriarDTO {

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
    private String codigoIdentificacao;
    @NotNull
    private String codigoCarga;
    @NotNull
    private Long idGenero;
    @NotNull
    private String msisdn;
    @NotNull
    private Long idTipoDocumento;
    @NotNull
    private Date dataValidade;

}
