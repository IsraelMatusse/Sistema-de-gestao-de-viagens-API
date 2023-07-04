package com.sgvcore.DTOs.tipoLicencaDTOs;

import com.sgvcore.Model.TipopLicenca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.ws.soap.Addressing;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoLicencaRespostaDTO {

    private String designacao;
    private String abreviatura;
    private Long id;

    public TipoLicencaRespostaDTO(TipopLicenca tipopLicenca){
        this.abreviatura=tipopLicenca.getAbreviatura();
        this.designacao=tipopLicenca.getDesignacao();
        this.id=tipopLicenca.getId();
    }
}
