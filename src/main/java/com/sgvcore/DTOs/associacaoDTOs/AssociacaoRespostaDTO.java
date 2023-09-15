package com.sgvcore.DTOs.associacaoDTOs;

import com.sgvcore.Model.Associacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociacaoRespostaDTO {

    private Long id;
    private String designacao;
    private String msisdn;
    private String designacaoLicenca;
    private String codigo;
    private String email;

    public AssociacaoRespostaDTO(Associacao associacao) {
        this.codigo = associacao.getCodigo();
        this.designacaoLicenca = associacao.getIdLicenca().getIdLicenca().getDesignacao();
        this.id = associacao.getId();
        this.msisdn = associacao.getIdContacto().getMsisdn();
        this.designacao = associacao.getDesignacao();
        this.email = associacao.getEmailassociacao();
    }


}
