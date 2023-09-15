package com.sgvcore.Controllers;

import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.sevices.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/usuarios")
@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/eu")
    public ResponseEntity<ResponseAPI> buscarUsaurioOnline(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Usuario Onlie", usuarioService.buscarUsuarioOnline()));
    }

}
