package com.sgvcore.DTOs.associacaoDTOs;

import com.sgvcore.Model.Associacao;
import com.sgvcore.Model.Contacto;
import com.sgvcore.Model.Licenca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociacaoRespostaDTO {

    private Long id;
    private String designacao;
    private String emailassociacao;
    private String msidsn;
    private String designacaoLicenca;
    private String codigo;

    public AssociacaoRespostaDTO(Associacao associacao){
        this.codigo=associacao.getCodigo();
        this.designacaoLicenca=associacao.getIdLicenca().getIdLicenca().getDesignacao();
        this.id=associacao.getId();
        this.msidsn=associacao.getIdContacto().getMsidsn();
        this.emailassociacao=associacao.getEmailassociacao();
    }


}
