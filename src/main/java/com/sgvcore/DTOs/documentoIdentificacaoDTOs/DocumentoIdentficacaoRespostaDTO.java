package com.sgvcore.DTOs.documentoIdentificacaoDTOs;

import com.sgvcore.Model.DocumentoIdentifiacacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DocumentoIdentficacaoRespostaDTO {
    private String numeroDocumento;

    private Date dataValidade;
    private Long id;
    private boolean activo;
    public DocumentoIdentficacaoRespostaDTO(DocumentoIdentifiacacao documentoIdentificacao){
        this.numeroDocumento=documentoIdentificacao.getNumeroDocumento();
        this.dataValidade=documentoIdentificacao.getDataValidade();
        this.id=documentoIdentificacao.getId();
        this.activo=documentoIdentificacao.getActivo();
    }
}
