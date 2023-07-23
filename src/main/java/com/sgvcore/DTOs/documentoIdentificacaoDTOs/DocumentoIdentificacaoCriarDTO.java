package com.sgvcore.DTOs.documentoIdentificacaoDTOs;

import com.sgvcore.DTOs.viajanteDTO.ViajanteCriarDTO;
import com.sgvcore.Model.TipoDocumentoIdentificacao;
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

    public DocumentoIdentificacaoCriarDTO(ViajanteCriarDTO dto, TipoDocumentoIdentificacao tipoDocumentoIdentificacao) {
        this.dataValidade = dto.getDataValidade();
        this.idTipoDocumento = tipoDocumentoIdentificacao.getId();
        this.numeroDocumento = dto.getNumeroDocumento();
    }
}
