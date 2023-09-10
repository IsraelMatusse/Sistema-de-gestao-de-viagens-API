package com.sgvcore.Controllers;

import com.sgvcore.DTOs.terminalDTOs.TerminalCriarDTO;
import com.sgvcore.Model.Associacao;
import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.Model.Terminal;
import com.sgvcore.Model.Usuario;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.sevices.FuncaoUsuarioService;
import com.sgvcore.sevices.TerminalService;
import com.sgvcore.sevices.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/terminais")
@RestController
@RequiredArgsConstructor
public class TerminalController {

    private final TerminalService terminalService;
    private final UsuarioService usuarioService;
    private final FuncaoUsuarioService funcaoUsuarioService;

    @PostMapping
    public ResponseEntity<ResponseAPI> criarTerminal(@Valid  @RequestBody  TerminalCriarDTO dto) throws Exception {
        terminalService.criarTerminal(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseAPI(true, "201", "Terminal Criado com sucesso", null));
    }

    @GetMapping
    public ResponseEntity<ResponseAPI>listarTerminais(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Lista de Terminais do sistema", terminalService.listarTerminais()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAPI>buscarTerminalPorId(@RequestParam(value = "id")Long id) throws ModelNotFound {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Terminal com o id " + id, terminalService.buscarPorId(id)));

    }

    @GetMapping("/perfil")
    public ResponseEntity<ResponseAPI>buscarTerminalPorUsuarioOnline() throws ModelNotFound {
        Usuario usuario = usuarioService.buscarUsuarioOnline();
        System.out.println( usuario);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Terminal online", terminalService.buscarTerminalPorUsuarioOnlineRes(usuario)));
    }
    }

