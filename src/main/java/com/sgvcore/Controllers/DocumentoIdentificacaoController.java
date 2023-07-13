package com.sgvcore.Controllers;

import com.sgvcore.DTOs.documentoIdentificacaoDTOs.DocumentoIdentificacaoCriarDTO;
import com.sgvcore.Model.DocumentoIdentifiacacao;
import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.Model.TipoDocumentoIdentificacao;
import com.sgvcore.sevices.DocumentoIdentificacaoService;
import com.sgvcore.sevices.TipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public class DocumentoIdentificacaoController {
    @Autowired
    private DocumentoIdentificacaoService documentoIdentificacaoService;
    @Autowired
    private TipoDocumentoService tipoDocumentoIdentificacaoService;
    public ResponseEntity<ResponseAPI> criarDocumentoIdentificacao(@RequestBody @Valid DocumentoIdentificacaoCriarDTO documentoidentificacaoCriarDTO){
        TipoDocumentoIdentificacao tipoDocumentoIdentificacao= tipoDocumentoIdentificacaoService.buscarTipoDocumentoporId(documentoidentificacaoCriarDTO.getIdTipoDocumento());
        if (tipoDocumentoIdentificacao == null){
            return ResponseEntity.status(404).body(new ResponseAPI(true, "404", "Tipo de documento nao encontrado!", null));
        }
        try{
            DocumentoIdentifiacacao documentoIdentificacao = new DocumentoIdentifiacacao(documentoidentificacaoCriarDTO, tipoDocumentoIdentificacao);
            documentoIdentificacaoService.criar(documentoIdentificacao);
            return ResponseEntity.status(201).body(new ResponseAPI(true, "201", "Documento cadastrado com sucesso criado com sucesso!", null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ResponseAPI(false, "500", "Erro interno de servidor!", null));
        }
    }
}
