package com.sgvcore.Controllers;

import com.sgvcore.DTOs.viaturaDTOs.ViacturaAssociarMotoristaDTO;
import com.sgvcore.DTOs.viaturaDTOs.ViaturaCriarDTO;
import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.exceptions.ContentAlreadyExists;
import com.sgvcore.exceptions.ForbiddenException;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.exceptions.NotOwner;
import com.sgvcore.sevices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RequestMapping("/api/viaturas")
@RestController
public class ViaturaController {

    @Autowired
    private ViaturaService viaturaService;
    @Autowired
    private RotaService rotaService;
    @Autowired
    private ProprietarioService proprietarioService;
    @Autowired
    private AssociacaoService associacaoService;
    @Autowired
    private AssociacaoRotaService asociacaoRotaService;
    @Autowired
    private ProvinciaService provinciaService;
    @Autowired
    private GeneroService generoService;
    @Autowired
    private DocumentoIdentificacaoService documentoIdentificacaoService;
    @Autowired
    private TipoDocumentoService tipoDocumentoService;
    @Autowired
    private MotoristaViacturaService motoristaViacturaService;
    @Autowired
    private MotoristaService motoristaService;

    @PostMapping("/adicionar")
    public ResponseEntity<ResponseAPI> criarViacturas(@RequestBody @Valid ViaturaCriarDTO viaturaCriarDTO) throws NoSuchAlgorithmException, NotOwner, ModelNotFound, ContentAlreadyExists, ForbiddenException {
        viaturaService.criar(viaturaCriarDTO);
        return ResponseEntity.status(201).body(new ResponseAPI(true, "201", "Viatura criada com sucesso!", null));
    }

    @PostMapping("/associar-motorista")
    public ResponseEntity<ResponseAPI> associarViacturaMotorista(@RequestBody @Valid ViacturaAssociarMotoristaDTO dto) throws NotOwner, ContentAlreadyExists, ModelNotFound, ForbiddenException {
        motoristaViacturaService.criar(dto);
        return ResponseEntity.status(201).body(new ResponseAPI(true, "200", "Viactura e motorista Cadastrados com sucesso!", null));
    }

    @GetMapping
    public ResponseEntity<ResponseAPI> listar() {
        return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Viaturas do sistema!", viaturaService.listarViacturas()));
    }

}
