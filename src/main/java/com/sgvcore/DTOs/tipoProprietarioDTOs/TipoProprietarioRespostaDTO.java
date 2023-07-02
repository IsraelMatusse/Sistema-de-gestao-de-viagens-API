package com.sgvcore.DTOs.tipoProprietarioDTOs;

import com.sgvcore.Model.TipoProprietario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoProprietarioRespostaDTO {

    private Long id;
    private String designacao;
    private String codigo;

    public TipoProprietarioRespostaDTO(TipoProprietario tipoProprietario){
        this.designacao=tipoProprietario.getDesignacao();
        this.id=tipoProprietario.getId();
        this.codigo=tipoProprietario.getCodigo();
    }

}
