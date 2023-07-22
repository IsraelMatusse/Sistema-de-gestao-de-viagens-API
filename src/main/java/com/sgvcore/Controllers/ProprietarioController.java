package com.sgvcore.Controllers;

import com.sgvcore.DTOs.proprietarioDTOs.ProprietarioCriarDTO;
import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.exceptions.ContentAlreadyExists;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.exceptions.NotOwner;
import com.sgvcore.sevices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/proprietarios")
public class ProprietarioController {

    @Autowired
    private ProprietarioService proprietarioService;
    @Autowired
    private GeneroService generoService;
    @Autowired
    private DocumentoIdentificacaoService documentoIdentificacaoService;
    @Autowired
    private TipoProprietarioService tipoProprietarioService;
    @Autowired
    private TipoDocumentoService tipoDocumentoService;
    @Autowired
    private ContactoService contactoService;
    @Autowired
    private ProvinciaService provinciaService;
    @Autowired
    private DistritoService distritoService;

    @PostMapping("/adicionar")
    public ResponseEntity<ResponseAPI> criarProprietario(@RequestBody ProprietarioCriarDTO proprietarioCriarDTO) throws NoSuchAlgorithmException, NotOwner, ContentAlreadyExists, ModelNotFound {
        proprietarioService.criar(proprietarioCriarDTO);
        return ResponseEntity.status(201).body(new ResponseAPI(true, "201", "Proprietario cadastrado com sucesso", null));
    }

    @GetMapping
    public ResponseEntity<ResponseAPI> listarProprietarios() throws NotOwner {
        return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Proprietarios do sistema", proprietarioService.listar()));
    }

    @GetMapping("/{codido-proprietario}")
    public ResponseEntity<ResponseAPI> buscarProprietarioPorCodigo(@PathVariable(value = "codigo-proprietario") String codigoProprietario) throws NotOwner, ModelNotFound {
        return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Proprietarios do sistema", proprietarioService.buscarPorCodigo(codigoProprietario)));
    }
}
