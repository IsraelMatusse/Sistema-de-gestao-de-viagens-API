package com.sgvcore.Controllers;

import com.sgvcore.DTOs.rotaDTO.RotaCriarDTO;
import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.Model.Rota;
import com.sgvcore.sevices.RotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/rotas")
public class RotaController {

    @Autowired
    private RotaService rotaService;

    @PostMapping("/adicionar")
    public ResponseEntity<ResponseAPI> criarRotas(@RequestBody @Valid RotaCriarDTO dto) throws NoSuchAlgorithmException {
        try {
            Rota rota = rotaService.criar(new Rota(dto));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new ResponseAPI(false, "500", "Erro interno de servidor!", null));
        }
        return ResponseEntity.status(201).body(new ResponseAPI(true, "201", "Rota criada com sucesso!", null));
    }
    @GetMapping
    public ResponseEntity<ResponseAPI> listarRotas(){
        return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Rotas do sistema!", rotaService.listarRotas()));
    }

}
