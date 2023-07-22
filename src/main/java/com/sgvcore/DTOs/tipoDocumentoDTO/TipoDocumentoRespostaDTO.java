package com.sgvcore.DTOs.tipoDocumentoDTO;

import com.sgvcore.Model.TipoDocumentoIdentificacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoDocumentoRespostaDTO {
    private String designacao;
    private String abreviatura;
    private long id;
    public TipoDocumentoRespostaDTO(TipoDocumentoIdentificacao tipoDocumentoIdentificacao){
        this.designacao=tipoDocumentoIdentificacao.getDesignacao();
        this.abreviatura=tipoDocumentoIdentificacao.getAbreviatura();
        this.id=tipoDocumentoIdentificacao.getId();
    }
}
