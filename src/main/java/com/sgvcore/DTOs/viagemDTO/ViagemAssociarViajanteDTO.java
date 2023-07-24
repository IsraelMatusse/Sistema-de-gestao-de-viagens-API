package com.sgvcore.DTOs.viagemDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViagemAssociarViajanteDTO {


    private String codigoViagem;
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
