package com.sgvcore.Controllers;

import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.sevices.MotoristaService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/motoristas")
@RequiredArgsConstructor
public class MotoristaController {

    private MotoristaService motoristaService;

    @GetMapping
    public ResponseEntity<ResponseAPI> listarMotoristas(){
        return ResponseEntity.ok(new ResponseAPI(true, "200", "Lista de motoristas", motoristaService.listar()));
    }
}
