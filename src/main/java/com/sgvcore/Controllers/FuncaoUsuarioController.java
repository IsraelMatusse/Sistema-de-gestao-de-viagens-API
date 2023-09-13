package com.sgvcore.Controllers;

import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.sevices.FuncaoUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/funcoes-usuario")
@RequiredArgsConstructor
public class FuncaoUsuarioController {

    private final FuncaoUsuarioService funcaoUsuarioService;

    @GetMapping
    public ResponseEntity<ResponseAPI> listarFuncoesDeUsuario(){
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Funcoes de usuario do sistema", funcaoUsuarioService.listarFuncoes() ));
    }

}
