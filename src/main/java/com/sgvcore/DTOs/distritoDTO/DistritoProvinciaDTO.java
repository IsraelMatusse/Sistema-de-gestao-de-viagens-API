package com.sgvcore.DTOs.distritoDTO;

import com.sgvcore.projections.distritoProjections.DistritoProvinciaProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistritoProvinciaDTO {
    private Long idDistrito;
    private String nomeDistrito;
    private String nomeProvincia;
    private Boolean activo;
    public DistritoProvinciaDTO(DistritoProvinciaProjection distritoProvinciaProjection){
        this.idDistrito = distritoProvinciaProjection.getId_distrito();
        this.nomeDistrito = distritoProvinciaProjection.getNome_Distrito();
        this.nomeProvincia = distritoProvinciaProjection.getNome_Provincia();
    }
}
