package com.sgvcore.sevices;

import com.sgvcore.DTOs.documentoIdentificacaoDTOs.DocumentoIdentficacaoRespostaDTO;
import com.sgvcore.Model.DocumentoIdentifiacacao;
import com.sgvcore.repository.DocumentoIdentificacaoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentoIdentificacaoService {
    @Autowired
    DocumentoIdentificacaoRepo documentoIdentificacaoRepo;

    public DocumentoIdentifiacacao criar(DocumentoIdentifiacacao documentoIdentificacao){
        return documentoIdentificacaoRepo.save(documentoIdentificacao);
    }
    public List<DocumentoIdentficacaoRespostaDTO> listarDocumentosIdentificao(){
        return documentoIdentificacaoRepo.findAll().stream().map(documentoIdentificacao -> new DocumentoIdentficacaoRespostaDTO(documentoIdentificacao)).collect(Collectors.toList());

    }
    public DocumentoIdentifiacacao buscarDocumentoporId(Long id){
        return documentoIdentificacaoRepo.findById(id).orElse(null);
    }

    public boolean existePorId(Long id){
        return documentoIdentificacaoRepo.existsById(id);
    }

    public DocumentoIdentificacao buscarPorNumeroDocumento(String numeroDocumento){
        return documentoIdentificacaoRepo.findBynumeroDocumento(numeroDocumento);
    }
}
