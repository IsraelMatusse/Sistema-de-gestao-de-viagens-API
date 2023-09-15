package com.sgvcore.sevices;

import com.sgvcore.DTOs.documentoIdentificacaoDTOs.DocumentoIdentficacaoRespostaDTO;
import com.sgvcore.DTOs.documentoIdentificacaoDTOs.DocumentoIdentificacaoCriarDTO;
import com.sgvcore.Model.DocumentoIdentifiacacao;
import com.sgvcore.Model.TipoDocumentoIdentificacao;
import com.sgvcore.exceptions.ContentAlreadyExists;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.repository.DocumentoIdentificacaoRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentoIdentificacaoService {
    @Autowired
    private DocumentoIdentificacaoRepo documentoIdentificacaoRepo;
    @Autowired
    private TipoDocumentoService tipoDocumentoService;

    public DocumentoIdentifiacacao criar(DocumentoIdentifiacacao documentoIdentifiacacao) {
        return documentoIdentificacaoRepo.save(documentoIdentifiacacao);
    }

    public DocumentoIdentifiacacao criarDocumento(DocumentoIdentificacaoCriarDTO dto) throws ModelNotFound {
        TipoDocumentoIdentificacao tipoDocumentoIdentificacao = tipoDocumentoService.buscarTipoDocumentoporId(dto.getIdTipoDocumento());
        try {
            DocumentoIdentifiacacao documentoIdentificacao = new DocumentoIdentifiacacao(dto, tipoDocumentoIdentificacao);
            return documentoIdentificacaoRepo.save(documentoIdentificacao);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o documento");
        }
    }

    public DocumentoIdentificacaoCriarDTO converterDTO(DocumentoIdentifiacacao documentoIdentifiacacao) {
        DocumentoIdentificacaoCriarDTO dto = new DocumentoIdentificacaoCriarDTO();
        BeanUtils.copyProperties(documentoIdentifiacacao, dto);
        return dto;
    }

    public List<DocumentoIdentficacaoRespostaDTO> listarDocumentosIdentificao() {
        return documentoIdentificacaoRepo.findAll().stream().map(documentoIdentificacao -> new DocumentoIdentficacaoRespostaDTO(documentoIdentificacao)).collect(Collectors.toList());

    }

    public DocumentoIdentifiacacao buscarDocumentoporId(Long id) {
        return documentoIdentificacaoRepo.findById(id).orElse(null);
    }

    public boolean existePorId(Long id) {
        return documentoIdentificacaoRepo.existsById(id);
    }

    public DocumentoIdentifiacacao buscarPorNumeroDocumento(String numeroDocumento) throws ContentAlreadyExists {
        return documentoIdentificacaoRepo.findByNumeroDocumento(numeroDocumento).orElseThrow(() -> new ContentAlreadyExists("Documento Ja existe"));
    }

    public Boolean existePorNumeroDocumento(String numeroDocumento) {
        return documentoIdentificacaoRepo.existsByNumeroDocumento(numeroDocumento);
    }
}
