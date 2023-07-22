package com.sgvcore.DTOs.distritoDTO;

import com.sgvcore.Model.Distrito;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistritoRespostaDTO {
    private String designacao;
    private String codigo;
    private Long id;
    public DistritoRespostaDTO(Distrito distrito){
        this.codigo=distrito.getCodigo();
        this.designacao=distrito.getDesignacao();
        this.id=distrito.getId();
    }
}
