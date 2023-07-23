package com.sgvcore.Controllers;

import com.sgvcore.DTOs.viajanteDTO.ViajanteCriarDTO;
import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.exceptions.ContentAlreadyExists;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.sevices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/viajantes")
public class ViajanteController {

    @Autowired
    private ViajanteService viajanteService;
    @Autowired
    private GeneroService generoService;
    @Autowired
    private ProvinciaDistritoService provinciaDistritoService;
    @Autowired
    private ProvinciaService provinciaService;
    @Autowired
    private DistritoService distritoService;
    @Autowired
    private DocumentoIdentificacaoService documentoIdentificacaoService;
    @Autowired
    private TipoDocumentoService tipoDocumentoService;
    @Autowired
    private ContactoService contactoService;
    @Autowired
    private CargaService cargaService;

    @PostMapping("/adicionar")
    public ResponseEntity<ResponseAPI> criarViajante(@RequestBody ViajanteCriarDTO dto) throws ContentAlreadyExists, ModelNotFound {
        viajanteService.criar(dto);
        return ResponseEntity.status(201).body(new ResponseAPI(true, "201", "Viajante criado com sucesso!", null));
    }

    @GetMapping
    public ResponseEntity<ResponseAPI> ListarViajantesDoSistema(@RequestBody ViajanteCriarDTO dto) throws ContentAlreadyExists, ModelNotFound {
        viajanteService.criar(dto);
        return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Viajantes do sistema", viajanteService.listarViajantes()));
    }
}
