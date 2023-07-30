package com.sgvcore.DTOs.documentoIdentificacaoDTOs;

import com.sgvcore.DTOs.viajanteDTO.ViajanteCriarDTO;
import com.sgvcore.Model.TipoDocumentoIdentificacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoIdentificacaoCriarDTO {

    @NotNull(message = "O numero do documento nao pode ser nula")
    @Size(min = 9, max = 9)
    private String numeroDocumento;
    @NotNull(message = "O tipo de documento nao pode ser nulo")
    private long idTipoDocumento;
    @NotNull(message = "A data de validade nao pode ser nula")
    private Date dataValidade;

    public DocumentoIdentificacaoCriarDTO(ViajanteCriarDTO dto, TipoDocumentoIdentificacao tipoDocumentoIdentificacao) {
        this.dataValidade = dto.getDataValidade();
        this.idTipoDocumento = tipoDocumentoIdentificacao.getId();
        this.numeroDocumento = dto.getNumeroDocumento();
    }
}
