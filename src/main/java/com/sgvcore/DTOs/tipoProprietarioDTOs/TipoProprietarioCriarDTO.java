package com.sgvcore.DTOs.tipoProprietarioDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoProprietarioCriarDTO {

    private String designacao;
    private String codigo;
}
