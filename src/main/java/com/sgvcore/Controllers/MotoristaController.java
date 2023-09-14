package com.sgvcore.Controllers;

import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.sevices.MotoristaService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/motoristas")
@RequiredArgsConstructor
public class MotoristaController {

    private final MotoristaService motoristaService;

    @GetMapping
    public ResponseEntity<ResponseAPI> listarMotoristas(){
        return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Motoristas", motoristaService.listar()));
    }
    @GetMapping("/{codigo}")
    public ResponseEntity<ResponseAPI> buscarPorCodigo(@PathVariable(value = "codigo") String codigo){
        return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Motoristas", motoristaService.buscarPorCodigoRes(codigo)));
    }
}
