package com.sgvcore.DTOs.tipoLicencaDTOs;

import com.sgvcore.Model.TipopLicenca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoLicencaRespostaDTO {

    private String designacao;
    private String abreviatura;
    private Long id;
    private String codigo;

    public TipoLicencaRespostaDTO(TipopLicenca tipopLicenca){
        this.abreviatura=tipopLicenca.getAbreviatura();
        this.designacao=tipopLicenca.getDesignacao();
        this.id=tipopLicenca.getId();
        this.codigo=tipopLicenca.getCodigo();
    }
}
