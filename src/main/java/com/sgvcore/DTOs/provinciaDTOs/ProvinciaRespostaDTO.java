package com.sgvcore.DTOs.provinciaDTOs;

import com.sgvcore.Model.Provincia;

public class ProvinciaRespostaDTO {

    private String designacao;
    private String codigo;
    private String sigla;
    public ProvinciaRespostaDTO(Provincia provincia){
        this.codigo=provincia.getCodigo();
        this.designacao=provincia.getDesignacao();
        this.sigla=provincia.getSigla();
    }
}
