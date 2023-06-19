package com.sgvcore.Model;

import com.sgvcore.DTOs.documentoIdentificacaoDTOs.DocumentoIdentificacaoCriarDTO;
import com.sgvcore.DTOs.viajanteDTO.ViajanteCriarDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoIdentifiacacao extends AccoesDoSistema {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String numeroDocumento;
    @Column
    private Date dataValidade;
    @ManyToOne
    private TipoDocumentoIdentificacao idIdentificacao;

    public  DocumentoIdentifiacacao (ViajanteCriarDTO viajanteCriarDTO, TipoDocumentoIdentificacao tipoDocumentoIdentificacao) {
        this.numeroDocumento=viajanteCriarDTO.getNumeroDocumento();
        this.dataValidade=viajanteCriarDTO.getDataValidade();
        this.idIdentificacao=tipoDocumentoIdentificacao;
    }
    }


