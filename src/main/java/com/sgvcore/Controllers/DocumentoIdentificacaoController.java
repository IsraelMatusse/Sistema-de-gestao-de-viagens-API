package com.sgvcore.Controllers;

import com.sgvcore.DTOs.documentoIdentificacaoDTOs.DocumentoIdentificacaoCriarDTO;
import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.exceptions.ModelNotFound;
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

    public ResponseEntity<ResponseAPI> criarDocumentoIdentificacao(@RequestBody @Valid DocumentoIdentificacaoCriarDTO documentoidentificacaoCriarDTO) throws ModelNotFound {
        documentoIdentificacaoService.criar(documentoidentificacaoCriarDTO);
        return ResponseEntity.status(201).body(new ResponseAPI(true, "201", "Documento cadastrado com sucesso criado com sucesso!", null));
    }
}
