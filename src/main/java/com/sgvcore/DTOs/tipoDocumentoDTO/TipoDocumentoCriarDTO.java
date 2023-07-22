package com.sgvcore.DTOs.tipoDocumentoDTO;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoDocumentoCriarDTO {

    private String designacao;
    private String abreviatura;
    private long id;
}
