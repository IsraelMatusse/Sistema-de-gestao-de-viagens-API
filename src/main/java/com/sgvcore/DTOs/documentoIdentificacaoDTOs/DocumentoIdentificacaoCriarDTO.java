package com.sgvcore.DTOs.documentoIdentificacaoDTOs;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoIdentificacaoCriarDTO {

    @NotNull
    private String numeroDocumento;
    @NotNull
    private long idTipoDocumento;
    @NotNull
    private Date dataValidade;
}
