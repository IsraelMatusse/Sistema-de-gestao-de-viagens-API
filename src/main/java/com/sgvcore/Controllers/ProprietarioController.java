package com.sgvcore.Controllers;

import com.sgvcore.DTOs.proprietarioDTOs.ProprietarioCriarDTO;
import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.exceptions.*;
import com.sgvcore.sevices.ProprietarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/proprietarios")
@RequiredArgsConstructor
public class ProprietarioController {

    private final ProprietarioService proprietarioService;

    @PostMapping("/adicionar")
    public ResponseEntity<ResponseAPI> criarProprietario(@RequestBody ProprietarioCriarDTO proprietarioCriarDTO) throws NoSuchAlgorithmException, NotOwner, ContentAlreadyExists, ModelNotFound, ForbiddenException, UnprocessableEntity {
        proprietarioService.criar(proprietarioCriarDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseAPI(true, "201", "Proprietario cadastrado com sucesso", null));
    }

    @GetMapping
    public ResponseEntity<ResponseAPI> listarProprietarios() throws NotOwner, ForbiddenException {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Proprietarios do sistema", proprietarioService.listar()));
    }

    @GetMapping("/{codido-proprietario}")
    public ResponseEntity<ResponseAPI> buscarProprietarioPorCodigo(@PathVariable(value = "codigo-proprietario") String codigoProprietario) throws NotOwner, ModelNotFound {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Proprietarios do sistema", proprietarioService.buscarPorCodigo(codigoProprietario)));
    }
}
